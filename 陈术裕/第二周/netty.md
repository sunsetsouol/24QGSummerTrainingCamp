# Netty

是一个异步的、基于事件驱动的网络应用框架，用于快速开发可维护、高性能的网络服务器和客户端

快速入门

服务器代码

```Java
public class HelloServer {
    public static void main(String[] args) {
        //启动器、负责组装netty组件、启动服务器
        new ServerBootstrap()
                //group组
                .group(new NioEventLoopGroup())
                //BIO、OIO、我们选择的是NIO，选择ServerSocketChannel的实现
                .channel(NioServerSocketChannel.class)
                //boss负责处理连接、worker（child）负责处理读写，决定了worker能执行哪些操作
                .childHandler(
                        //代表和客户端进行数据读写的通道，Initializer初始化，负责添加别的handler（操作）
                        new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch){
                        //添加具体 handler
                        ch.pipeline().addLast(new StringDecoder()); //将ByteBuf转换为字符串
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { //自定义handler
                            @Override //读事件
                            public void channelRead(ChannelHandlerContext ctx, Object msg){
                                //打印上一步转换好的字符串
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(8080); //决定了绑定的监听端口
    }
}
```

客户端代码

```Java
public class HelloClient {
    public static void main(String[] args) throws InterruptedException {
        //启动类
        new Bootstrap()
                //添加EventLoop
                .group(new NioEventLoopGroup())
                //选择客户端channel实现
                .channel(NioSocketChannel.class)
                //添加处理器
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override //在连接建立后被调用
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder()); //把字符串转换成ByteBuf
                    }
                })
                //连接到服务器
                .connect(new InetSocketAddress("localhost", 8080))
                .sync() //阻塞方法，直到连接建立
                .channel() //代表连接对象
                //向服务器发送数据
                .writeAndFlush("hello world");

    }
}
```

#### 💡 提示

> 一开始需要树立正确的观念
>
> - 把 channel 理解为数据的通道
> - 把 msg 理解为流动的数据，最开始输入是 ByteBuf，但经过 pipeline 的加工，会变成其它类型对象，最后输出又变成 ByteBuf
> - 把 handler 理解为数据的处理工序
>   - 工序有多道，合在一起就是 pipeline，pipeline 负责发布事件（读、读取完成...）传播给每个 handler， handler 对自己感兴趣的事件进行处理（重写了相应事件处理方法）
>   - handler 分 Inbound 和 Outbound 两类
> - 把 eventLoop 理解为处理数据的工人
>   - 工人可以管理多个 channel 的 io 操作，并且一旦工人负责了某个 channel，就要负责到底（绑定）
>   - 工人既可以执行 io 操作，也可以进行任务处理，每位工人有任务队列，队列里可以堆放多个 channel 的待处理任务，任务分为普通任务、定时任务
>   - 工人按照 pipeline 顺序，依次按照 handler 的规划（代码）处理数据，可以为每道工序指定不同的工人

## EventLoop

单线程执行器（同时维护了一个selector），里面有run方法处理channel上源源不断的io事件

#### 💡 handler 执行中如何换人？

关键代码 `io.netty.channel.AbstractChannelHandlerContext#invokeChannelRead()`

```Java
static void invokeChannelRead(final AbstractChannelHandlerContext next, Object msg) {
    final Object m = next.pipeline.touch(ObjectUtil.checkNotNull(msg, "msg"), next);
    // 下一个 handler 的事件循环是否与当前的事件循环是同一个线程
    EventExecutor executor = next.executor();
    // 是，直接调用
    if (executor.inEventLoop()) {
        next.invokeChannelRead(m);
    }
    // 不是，将要执行的代码作为任务提交给下一个事件循环处理（换人）
    else {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                next.invokeChannelRead(m);
            }
        });
    }
}
```

- 如果两个 handler 绑定的是同一个线程，那么就直接调用
- 否则，把要调用的代码封装为一个任务对象，由下一个 handler 的线程来调用

## Channel

close()关闭channel

pipeline（）加入处理器

write（）将数据写入，不会立马输出数据，放到缓冲区中

writeandflush就会立马发出去

- closeFuture() 用来处理 channel 的关闭
  - sync 方法作用是同步等待 channel 关闭
  - 而 addListener 方法是异步等待 channel 关闭

flush（）就可以把缓冲区的数据发送出去

```Java
//启动类
ChannelFuture channelFuture = new Bootstrap()
        //添加EventLoop
        .group(new NioEventLoopGroup())
        //选择客户端channel实现
        .channel(NioSocketChannel.class)
        //添加处理器
        .handler(new ChannelInitializer<NioSocketChannel>() {
            @Override //在连接建立后被调用
            protected void initChannel(NioSocketChannel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder()); //把字符串转换成ByteBuf
            }
        })
        //连接到服务器
        .connect(new InetSocketAddress("localhost", 8080));

channelFuture.sync(); //阻塞方法，直到连接建立
Channel channel = channelFuture.channel();//代表连接对象
channel.writeAndFlush("hello world"); //发送数据
```

- 1 处返回的是 ChannelFuture 对象，它的作用是利用 channel() 方法来获取 Channel 对象

**注意** connect 方法是异步的，意味着不等连接建立，方法执行就返回了。因此 channelFuture 对象中不能【立刻】获得到正确的 Channel 对象

连接建立成功的标志：各种端口号都显示出来了

有两种方法同步处理结果

1. sync（）方法

```Java
//1.使用sync 方法同步处理结果
channelFuture.sync(); //阻塞方法，阻塞当前线程，直到nio线程连接建立完毕
//无阻塞向下执行获取channel
Channel channel = channelFuture.channel();//代表连接对象
channel.writeAndFlush("hello world"); //发送数据
```

1. addListener方法

```Java
//2.使用addListener(回调对象) 方法异步处理结果
channelFuture.addListener(new ChannelFutureListener() {
    @Override
    // 在nio线程连接建立好后，调用operationComplete
    public void operationComplete(ChannelFuture future) throws Exception {
        Channel channel = future.channel();
        channel.writeAndFlush("success");
    }
});
```

## 优雅关闭客户端

1. 同步处理操作

```Java
ChannelFuture closeFuture = channel.closeFuture();
System.out.println("waiting close----");
closeFuture.sync();
System.out.println("处理关闭后的操作");
```

1. 异步处理操作 addListener

```Java
//异步 addListener
ChannelFuture closeFuture = channel.closeFuture();
closeFuture.addListener(new ChannelFutureListener() {
    @Override
    public void operationComplete(ChannelFuture future) throws Exception {
        System.out.println("处理关闭之后的操作");
    }
});
```

最重要的一个方法

```Java
group.shutdownGracefully();
```

Future & Promise

- jdk Future 只能同步等待任务结束（或成功、或失败）才能得到结果
- netty Future 可以同步等待任务结束得到结果，也可以异步方式得到结果，但都是要等任务结束
- netty Promise 不仅有 netty Future 的功能，而且脱离了任务独立存在，只作为两个线程间传递结果的容器

## Handler & Pipeline

ChannelHandler 用来处理 Channel 上的各种事件，分为入站、出站两种。所有 ChannelHandler 被连成一串，就是 Pipeline

- 入站处理器通常是 ChannelInboundHandlerAdapter 的子类，主要用来读取客户端数据，写回结果
- 出站处理器通常是 ChannelOutboundHandlerAdapter 的子类，主要对写回结果进行加工

打个比喻，每个 Channel 是一个产品的加工车间，Pipeline 是车间中的流水线，ChannelHandler 就是流水线上的各道工序，而后面要讲的 ByteBuf 是原材料，经过很多工序的加工：先经过一道道入站工序，再经过一道道出站工序最终变成产品

入站是顺序的，出站是反过来的顺序的，有write才有出站的操作

ctx的channelRead 的查找顺序是直接从当前位置倒过去的

##### EmbeddedChannel可以用来测试Handler

## ByteBuf

可以动态修改自己的内容字节，看具体传入的数据大小

#### 池化 vs 非池化

池化的最大意义在于可以重用 ByteBuf，优点有

- 没有池化，则每次都得创建新的 ByteBuf 实例，这个操作对直接内存代价昂贵，就算是堆内存，也会增加 GC 压力
- 有了池化，则可以重用池中 ByteBuf 实例，并且采用了与 jemalloc 类似的内存分配算法提升分配效率
- 高并发时，池化功能更节约内存，减少内存溢出的可能

池化功能是否开启，可以通过下面的系统环境变量来设置

-Dio.netty.allocator.type={unpooled|pooled}

- 4.1 以后，非 Android 平台默认启用池化实现，Android 平台启用非池化实现
- 4.1 之前，池化功能还不成熟，默认是非池化实现

#### 组成

ByteBuf 由四部分组成

最开始读写指针都在 0 位置

![img](C:\Users\28937\Desktop\17217368952771.png)

### 扩容规则

- 如何写入后数据大小未超过 512，则选择下一个 16 的整数倍，例如写入后大小为 12 ，则扩容后 capacity 是 16
- 如果写入后数据大小超过 512，则选择下一个 2^n，例如写入后大小为 513，则扩容后 capacity 是 2^10=1024（2^9=512 已经不够了）
- 扩容不能超过 max capacity 会报错

#### retain & release

由于 Netty 中有堆外内存的 ByteBuf 实现，堆外内存最好是手动来释放，而不是等 GC 垃圾回收。

- UnpooledHeapByteBuf 使用的是 JVM 内存，只需等 GC 回收内存即可
- UnpooledDirectByteBuf 使用的就是直接内存了，需要特殊的方法来回收内存
- PooledByteBuf 和它的子类使用了池化机制，需要更复杂的规则来回收内存

Netty 这里采用了引用计数法来控制回收内存，每个 ByteBuf 都实现了 ReferenceCounted 接口

- 每个 ByteBuf 对象的初始计数为 1
- 调用 release 方法计数减 1，如果计数为 0，ByteBuf 内存被回收
- 调用 retain 方法计数加 1，表示调用者没用完之前，其它 handler 即使调用了 release 也不会造成回收
- 当计数为 0 时，底层内存会被回收，这时即使 ByteBuf 对象还在，其各个方法均无法正常使用

谁最后使用了ByteBuf,谁就来释放ByteBuf

切片Slice

【零拷贝】的体现之一，对原始 ByteBuf 进行切片成多个 ByteBuf，切片后的 ByteBuf 并没有发生内存复制，还是使用原始 ByteBuf 的内存，切片后的 ByteBuf 维护独立的 read，write 指针

这时调用 slice 进行切片，无参 slice 是从原始 ByteBuf 的 read index 到 write index 之间的内容进行切片，切片后的 max capacity 被固定为这个区间的大小，因此不能追加 write

#### 💡 ByteBuf 优势

- 池化 - 可以重用池中 ByteBuf 实例，更节约内存，减少内存溢出的可能
- 读写指针分离，不需要像 ByteBuffer 一样切换读写模式
- 可以自动扩容
- 支持链式调用，使用更流畅
- 很多地方体现零拷贝，例如 slice、duplicate、CompositeByteBuf

### 💡 读和写的误解

我最初在认识上有这样的误区，认为只有在 netty，nio 这样的多路复用 IO 模型时，读写才不会相互阻塞，才可以实现高效的双向通信，但实际上，Java Socket 是全双工的：在任意时刻，线路上存在`A 到 B` 和 `B 到 A` 的双向信号传输。即使是阻塞 IO，读和写是可以同时进行的，只要分别采用读线程和写线程即可，读不会阻塞写、写也不会阻塞读

双向通信代码例子

```Java
public class EchoServer {
    public static void main(String[] args) {
        new ServerBootstrap()
                .group(new NioEventLoopGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                ByteBuf buffer = (ByteBuf) msg;
                                System.out.println(buffer.toString(Charset.defaultCharset()));

                                //使用crx.alloc（）创建ByteBuf
                                ByteBuf buffer1 = ctx.alloc().buffer();
                                buffer1.writeBytes(buffer);
                                ctx.writeAndFlush(buffer1);
                                //buffer不用释放
                                //buffer1要释放
                                buffer1.release();

                            }
                        });
                    }
                }).bind(8080);
    }
}
public class EchoClient {

    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();

        Channel channel = new Bootstrap()
                .group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringEncoder());
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                            @Override
                            public void channelRead(ChannelHandlerContext ctx , Object msg){
                                ByteBuf buf = (ByteBuf) msg;
                                System.out.println(buf.toString(Charset.defaultCharset()));
                            }
                        });
                    }
                }).connect("localhost", 8080)
                .sync().channel();

        //异步关闭
        channel.closeFuture().addListener(future -> {
            group.shutdownGracefully();
        });

        new Thread(()->{
            Scanner scanner = new Scanner(System.in);
            while (true){
                String next = scanner.next();
                if(next.equals("q")){
                    channel.close();
                    break;
                }
                channel.writeAndFlush(next);
            }
        }).start();

    }
}
```

### ServerBootstrap和Bootstrap

ServerBootstrap 是 Netty 中的服务器端启动助手，通过它可以完成服务器端的各种配置。

Bootstrap 是 Netty 中的客户端启动助手，通过它可以完成客户端的各种配置。

## 黏包和半包

channelActive方法，会在连接 channel 建立成功后， 会触发 active 事件

### 解决方案

1. 短链接，发一个包建立一次连接，这样连接建立到连接断开之间就是消息的边界，缺点效率太低

2. 每一条消息采用固定长度，缺点浪费空间

3. 每一条消息采用分隔符，例如 \n，缺点需要转义

4. 每一条消息分为 head 和 body，head 中包含 body 的长度

5. Netty

   是一个异步的、基于事件驱动的网络应用框架，用于快速开发可维护、高性能的网络服务器和客户端

   快速入门

   服务器代码

   ```Java
   public class HelloServer {
       public static void main(String[] args) {
           //启动器、负责组装netty组件、启动服务器
           new ServerBootstrap()
                   //group组
                   .group(new NioEventLoopGroup())
                   //BIO、OIO、我们选择的是NIO，选择ServerSocketChannel的实现
                   .channel(NioServerSocketChannel.class)
                   //boss负责处理连接、worker（child）负责处理读写，决定了worker能执行哪些操作
                   .childHandler(
                           //代表和客户端进行数据读写的通道，Initializer初始化，负责添加别的handler（操作）
                           new ChannelInitializer<NioSocketChannel>() {
                       @Override
                       protected void initChannel(NioSocketChannel ch){
                           //添加具体 handler
                           ch.pipeline().addLast(new StringDecoder()); //将ByteBuf转换为字符串
                           ch.pipeline().addLast(new ChannelInboundHandlerAdapter() { //自定义handler
                               @Override //读事件
                               public void channelRead(ChannelHandlerContext ctx, Object msg){
                                   //打印上一步转换好的字符串
                                   System.out.println(msg);
                               }
                           });
                       }
                   })
                   .bind(8080); //决定了绑定的监听端口
       }
   }
   ```

   客户端代码

   ```Java
   public class HelloClient {
       public static void main(String[] args) throws InterruptedException {
           //启动类
           new Bootstrap()
                   //添加EventLoop
                   .group(new NioEventLoopGroup())
                   //选择客户端channel实现
                   .channel(NioSocketChannel.class)
                   //添加处理器
                   .handler(new ChannelInitializer<NioSocketChannel>() {
                       @Override //在连接建立后被调用
                       protected void initChannel(NioSocketChannel ch) throws Exception {
                           ch.pipeline().addLast(new StringEncoder()); //把字符串转换成ByteBuf
                       }
                   })
                   //连接到服务器
                   .connect(new InetSocketAddress("localhost", 8080))
                   .sync() //阻塞方法，直到连接建立
                   .channel() //代表连接对象
                   //向服务器发送数据
                   .writeAndFlush("hello world");
   
       }
   }
   ```

   #### 💡 提示

   > 一开始需要树立正确的观念
   >
   > - 把 channel 理解为数据的通道
   > - 把 msg 理解为流动的数据，最开始输入是 ByteBuf，但经过 pipeline 的加工，会变成其它类型对象，最后输出又变成 ByteBuf
   > - 把 handler 理解为数据的处理工序
   >   - 工序有多道，合在一起就是 pipeline，pipeline 负责发布事件（读、读取完成...）传播给每个 handler， handler 对自己感兴趣的事件进行处理（重写了相应事件处理方法）
   >   - handler 分 Inbound 和 Outbound 两类
   > - 把 eventLoop 理解为处理数据的工人
   >   - 工人可以管理多个 channel 的 io 操作，并且一旦工人负责了某个 channel，就要负责到底（绑定）
   >   - 工人既可以执行 io 操作，也可以进行任务处理，每位工人有任务队列，队列里可以堆放多个 channel 的待处理任务，任务分为普通任务、定时任务
   >   - 工人按照 pipeline 顺序，依次按照 handler 的规划（代码）处理数据，可以为每道工序指定不同的工人

   ## EventLoop

   单线程执行器（同时维护了一个selector），里面有run方法处理channel上源源不断的io事件

   #### 💡 handler 执行中如何换人？

   关键代码 `io.netty.channel.AbstractChannelHandlerContext#invokeChannelRead()`

   ```Java
   static void invokeChannelRead(final AbstractChannelHandlerContext next, Object msg) {
       final Object m = next.pipeline.touch(ObjectUtil.checkNotNull(msg, "msg"), next);
       // 下一个 handler 的事件循环是否与当前的事件循环是同一个线程
       EventExecutor executor = next.executor();
       // 是，直接调用
       if (executor.inEventLoop()) {
           next.invokeChannelRead(m);
       }
       // 不是，将要执行的代码作为任务提交给下一个事件循环处理（换人）
       else {
           executor.execute(new Runnable() {
               @Override
               public void run() {
                   next.invokeChannelRead(m);
               }
           });
       }
   }
   ```

   - 如果两个 handler 绑定的是同一个线程，那么就直接调用
   - 否则，把要调用的代码封装为一个任务对象，由下一个 handler 的线程来调用

   ## Channel

   close()关闭channel

   pipeline（）加入处理器

   write（）将数据写入，不会立马输出数据，放到缓冲区中

   writeandflush就会立马发出去

   - closeFuture() 用来处理 channel 的关闭
     - sync 方法作用是同步等待 channel 关闭
     - 而 addListener 方法是异步等待 channel 关闭

   flush（）就可以把缓冲区的数据发送出去

   ```Java
   //启动类
   ChannelFuture channelFuture = new Bootstrap()
           //添加EventLoop
           .group(new NioEventLoopGroup())
           //选择客户端channel实现
           .channel(NioSocketChannel.class)
           //添加处理器
           .handler(new ChannelInitializer<NioSocketChannel>() {
               @Override //在连接建立后被调用
               protected void initChannel(NioSocketChannel ch) throws Exception {
                   ch.pipeline().addLast(new StringEncoder()); //把字符串转换成ByteBuf
               }
           })
           //连接到服务器
           .connect(new InetSocketAddress("localhost", 8080));
   
   channelFuture.sync(); //阻塞方法，直到连接建立
   Channel channel = channelFuture.channel();//代表连接对象
   channel.writeAndFlush("hello world"); //发送数据
   ```

   - 1 处返回的是 ChannelFuture 对象，它的作用是利用 channel() 方法来获取 Channel 对象

   **注意** connect 方法是异步的，意味着不等连接建立，方法执行就返回了。因此 channelFuture 对象中不能【立刻】获得到正确的 Channel 对象

   连接建立成功的标志：各种端口号都显示出来了

   有两种方法同步处理结果

   1. sync（）方法

   ```Java
   //1.使用sync 方法同步处理结果
   channelFuture.sync(); //阻塞方法，阻塞当前线程，直到nio线程连接建立完毕
   //无阻塞向下执行获取channel
   Channel channel = channelFuture.channel();//代表连接对象
   channel.writeAndFlush("hello world"); //发送数据
   ```

   1. addListener方法

   ```Java
   //2.使用addListener(回调对象) 方法异步处理结果
   channelFuture.addListener(new ChannelFutureListener() {
       @Override
       // 在nio线程连接建立好后，调用operationComplete
       public void operationComplete(ChannelFuture future) throws Exception {
           Channel channel = future.channel();
           channel.writeAndFlush("success");
       }
   });
   ```

   ## 优雅关闭客户端

   1. 同步处理操作

   ```Java
   ChannelFuture closeFuture = channel.closeFuture();
   System.out.println("waiting close----");
   closeFuture.sync();
   System.out.println("处理关闭后的操作");
   ```

   1. 异步处理操作 addListener

   ```Java
   //异步 addListener
   ChannelFuture closeFuture = channel.closeFuture();
   closeFuture.addListener(new ChannelFutureListener() {
       @Override
       public void operationComplete(ChannelFuture future) throws Exception {
           System.out.println("处理关闭之后的操作");
       }
   });
   ```

   最重要的一个方法

   ```Java
   group.shutdownGracefully();
   ```

   Future & Promise

   - jdk Future 只能同步等待任务结束（或成功、或失败）才能得到结果
   - netty Future 可以同步等待任务结束得到结果，也可以异步方式得到结果，但都是要等任务结束
   - netty Promise 不仅有 netty Future 的功能，而且脱离了任务独立存在，只作为两个线程间传递结果的容器

   ## Handler & Pipeline

   ChannelHandler 用来处理 Channel 上的各种事件，分为入站、出站两种。所有 ChannelHandler 被连成一串，就是 Pipeline

   - 入站处理器通常是 ChannelInboundHandlerAdapter 的子类，主要用来读取客户端数据，写回结果
   - 出站处理器通常是 ChannelOutboundHandlerAdapter 的子类，主要对写回结果进行加工

   打个比喻，每个 Channel 是一个产品的加工车间，Pipeline 是车间中的流水线，ChannelHandler 就是流水线上的各道工序，而后面要讲的 ByteBuf 是原材料，经过很多工序的加工：先经过一道道入站工序，再经过一道道出站工序最终变成产品

   入站是顺序的，出站是反过来的顺序的，有write才有出站的操作

   ctx的channelRead 的查找顺序是直接从当前位置倒过去的

   ##### EmbeddedChannel可以用来测试Handler

   ## ByteBuf

   可以动态修改自己的内容字节，看具体传入的数据大小

   #### 池化 vs 非池化

   池化的最大意义在于可以重用 ByteBuf，优点有

   - 没有池化，则每次都得创建新的 ByteBuf 实例，这个操作对直接内存代价昂贵，就算是堆内存，也会增加 GC 压力
   - 有了池化，则可以重用池中 ByteBuf 实例，并且采用了与 jemalloc 类似的内存分配算法提升分配效率
   - 高并发时，池化功能更节约内存，减少内存溢出的可能

   池化功能是否开启，可以通过下面的系统环境变量来设置

   -Dio.netty.allocator.type={unpooled|pooled}

   - 4.1 以后，非 Android 平台默认启用池化实现，Android 平台启用非池化实现
   - 4.1 之前，池化功能还不成熟，默认是非池化实现

   #### 组成

   ByteBuf 由四部分组成

   最开始读写指针都在 0 位置

   ![img](C:\Users\28937\Desktop\17217368952771.png)

   ### 扩容规则

   - 如何写入后数据大小未超过 512，则选择下一个 16 的整数倍，例如写入后大小为 12 ，则扩容后 capacity 是 16
   - 如果写入后数据大小超过 512，则选择下一个 2^n，例如写入后大小为 513，则扩容后 capacity 是 2^10=1024（2^9=512 已经不够了）
   - 扩容不能超过 max capacity 会报错

   #### retain & release

   由于 Netty 中有堆外内存的 ByteBuf 实现，堆外内存最好是手动来释放，而不是等 GC 垃圾回收。

   - UnpooledHeapByteBuf 使用的是 JVM 内存，只需等 GC 回收内存即可
   - UnpooledDirectByteBuf 使用的就是直接内存了，需要特殊的方法来回收内存
   - PooledByteBuf 和它的子类使用了池化机制，需要更复杂的规则来回收内存

   Netty 这里采用了引用计数法来控制回收内存，每个 ByteBuf 都实现了 ReferenceCounted 接口

   - 每个 ByteBuf 对象的初始计数为 1
   - 调用 release 方法计数减 1，如果计数为 0，ByteBuf 内存被回收
   - 调用 retain 方法计数加 1，表示调用者没用完之前，其它 handler 即使调用了 release 也不会造成回收
   - 当计数为 0 时，底层内存会被回收，这时即使 ByteBuf 对象还在，其各个方法均无法正常使用

   谁最后使用了ByteBuf,谁就来释放ByteBuf

   切片Slice

   【零拷贝】的体现之一，对原始 ByteBuf 进行切片成多个 ByteBuf，切片后的 ByteBuf 并没有发生内存复制，还是使用原始 ByteBuf 的内存，切片后的 ByteBuf 维护独立的 read，write 指针

   这时调用 slice 进行切片，无参 slice 是从原始 ByteBuf 的 read index 到 write index 之间的内容进行切片，切片后的 max capacity 被固定为这个区间的大小，因此不能追加 write

   #### 💡 ByteBuf 优势

   - 池化 - 可以重用池中 ByteBuf 实例，更节约内存，减少内存溢出的可能
   - 读写指针分离，不需要像 ByteBuffer 一样切换读写模式
   - 可以自动扩容
   - 支持链式调用，使用更流畅
   - 很多地方体现零拷贝，例如 slice、duplicate、CompositeByteBuf

   ### 💡 读和写的误解

   我最初在认识上有这样的误区，认为只有在 netty，nio 这样的多路复用 IO 模型时，读写才不会相互阻塞，才可以实现高效的双向通信，但实际上，Java Socket 是全双工的：在任意时刻，线路上存在`A 到 B` 和 `B 到 A` 的双向信号传输。即使是阻塞 IO，读和写是可以同时进行的，只要分别采用读线程和写线程即可，读不会阻塞写、写也不会阻塞读

   双向通信代码例子

   ```Java
   public class EchoServer {
       public static void main(String[] args) {
           new ServerBootstrap()
                   .group(new NioEventLoopGroup())
                   .channel(NioServerSocketChannel.class)
                   .childHandler(new ChannelInitializer<NioSocketChannel>() {
                       @Override
                       protected void initChannel(NioSocketChannel ch) throws Exception {
                           ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                               @Override
                               public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                   ByteBuf buffer = (ByteBuf) msg;
                                   System.out.println(buffer.toString(Charset.defaultCharset()));
   
                                   //使用crx.alloc（）创建ByteBuf
                                   ByteBuf buffer1 = ctx.alloc().buffer();
                                   buffer1.writeBytes(buffer);
                                   ctx.writeAndFlush(buffer1);
                                   //buffer不用释放
                                   //buffer1要释放
                                   buffer1.release();
   
                               }
                           });
                       }
                   }).bind(8080);
       }
   }
   public class EchoClient {
   
       public static void main(String[] args) throws InterruptedException {
           NioEventLoopGroup group = new NioEventLoopGroup();
   
           Channel channel = new Bootstrap()
                   .group(group)
                   .channel(NioSocketChannel.class)
                   .handler(new ChannelInitializer<NioSocketChannel>() {
                       @Override
                       protected void initChannel(NioSocketChannel ch) throws Exception {
                           ch.pipeline().addLast(new StringEncoder());
                           ch.pipeline().addLast(new ChannelInboundHandlerAdapter(){
                               @Override
                               public void channelRead(ChannelHandlerContext ctx , Object msg){
                                   ByteBuf buf = (ByteBuf) msg;
                                   System.out.println(buf.toString(Charset.defaultCharset()));
                               }
                           });
                       }
                   }).connect("localhost", 8080)
                   .sync().channel();
   
           //异步关闭
           channel.closeFuture().addListener(future -> {
               group.shutdownGracefully();
           });
   
           new Thread(()->{
               Scanner scanner = new Scanner(System.in);
               while (true){
                   String next = scanner.next();
                   if(next.equals("q")){
                       channel.close();
                       break;
                   }
                   channel.writeAndFlush(next);
               }
           }).start();
   
       }
   }
   ```

   ### ServerBootstrap和Bootstrap

   ServerBootstrap 是 Netty 中的服务器端启动助手，通过它可以完成服务器端的各种配置。

   Bootstrap 是 Netty 中的客户端启动助手，通过它可以完成客户端的各种配置。

   ## 黏包和半包

   channelActive方法，会在连接 channel 建立成功后， 会触发 active 事件

   ### 解决方案

   1. 短链接，发一个包建立一次连接，这样连接建立到连接断开之间就是消息的边界，缺点效率太低
   2. 每一条消息采用固定长度，缺点浪费空间
   3. 每一条消息采用分隔符，例如 \n，缺点需要转义
   4. 每一条消息分为 head 和 body，head 中包含 body 的长度