# Nignx

## 安装

去官网下载tar包然后传给虚拟机，虚拟机解压即可

## 打开虚拟机

在nginx下的bin文件夹输入`./nginx`即可打开nginx

关闭niginx同样`./nginx -s stop`

## 实现负载均衡

在`nginx/conf/nginx.conf `文件中更改如下内容即可:



    http {
    
    基本HTTP服务器配置
    
      server {
            listen 80;                 # 监听端口
            server_name example.com;   # 绑定域名
    	# 匹配所有请求，将它们转发到后端服务器
        location / {
            proxy_pass http://backend-server:8080;  # 后端服务器地址与端口
            proxy_set_header Host $host;             # 保留原始Host头
            proxy_set_header X-Real-IP $remote_addr; # 传递真实客户端IP
            proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
            proxy_set_header X-Forwarded-Proto $scheme;  # 传递请求协议（http/https）
     
            # 其他可选配置，如缓存、超时、重试等
        }
    }

只需要将location全部删掉加入第一条然后更改为服务器地址即可

代码注释：

- listen: 指定Nginx监听的IP地址和端口，这里为标准HTTP端口80。

- server_name: 设置服务器块所关联的域名，即客户端请求的域名。

- location /: 匹配所有以根路径（"/"）开头的请求。

- proxy_pass: 设置后端服务器的URL。此处将所有匹配到的请求转发到http://backend-server:8080。

- proxy_set_header: 设置请求转发时要修改或添加的HTTP头部。这些指令用于保持与后端服务器的正确交互，如：

  - Host: 保持原请求的Host头，确保后端服务器能够正确识别请求的目的主机。

  - X-Real-IP: 传递真实的客户端IP地址，防止后端服务器因反向代理而获取到Nginx服务器的IP。

  - X-Forwarded-For: 传递经过代理链的客户端IP列表，多个代理时会追加。

  - X-Forwarded-Proto: 传递原始请求的协议（http或https），以便后端服务器识别请求是否经过SSL加密。

更多详细内容查看博客：https://blog.csdn.net/z_344791576/article/details/137855427

## 负载均衡

### 3.1 Upstream模块

Upstream模块定义了一组后端服务器,实现客户端请求到后端服务器的负载均衡。

#### 3.1.1 后端服务器配置

```
upstream backend {
  server 192.168.1.100;
  server 192.168.1.101; 
}
```



#### 3.1.2 负载均衡策略

Nginx支持round-robin、weight、least_conn、ip_hash等策略。

```
upstream backend {
  least_conn;
  server server1 weight=5;
  server server2; 
}
```

### 3.2 Location模块反向代理

Location模块配置反向代理到定义的Upstream。

```
location / {
  proxy_pass http://backend; 
}
```

### 3.3 动静分离部署

将动态请求和静态请求分发到不同的后端服务器。

```
upstream dynamic {
  server 192.168.1.102;
}

upstream static {
  server 192.168.1.103; 
}

location /static/ {
  proxy_pass http://static;
}

location / {
  proxy_pass http://dynamic;
}
```

### 3.4 会话保持方法

#### 3.4.1 cookie

```
upstream backend {
server server1;
server server2;

sticky cookie srv_id expires=1h domain=.example.com path=/;
}
```

#### 3.4.2 ip_hash

```
upstream backend {
ip_hash;
server server1;
server server2;
```

