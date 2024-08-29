#  Linux

## Linux目录结构

![image-20221027214128453](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027214128.png)

- `/`，根目录是最顶级的目录了
- Linux只有一个顶级目录：`/`
- 路径描述的层次关系同样适用`/`来表示
- /home/itheima/a.txt，表示根目录下的home文件夹内有itheima文件夹，内有a.txt





## Linux基本命令

### ls命令

列出文件夹信息

语法：`ls [-l -h -a] [参数]`

- 参数：被查看的文件夹，不提供参数，表示查看当前工作目录
- -l，以列表形式查看
- -h，配合-l，以更加人性化的方式显示文件大小
- -a，显示隐藏文件（Linux中以`.`开头的都是隐藏文件）

参数可以组合使用

例如：ls -l -a  或者 ls-la  或者 ls - al

三种写法都表示同时应用-l 和 -a 功能

---

### 目录、路径的命令与概念

#### pwd命令

展示当前工作目录

语法：`pwd`

#### cd命令

切换工作目录

语法：`cd [目标目录]`

目标目录不填则代表切换到`当前用户HOME目录`

#### HOME目录

每一个用户在Linux系统中都有自己的专属工作目录，称之为HOME目录。

- 普通用户的HOME目录，默认在：`/home/用户名`

- root用户的HOME目录，在：`/root`

默认工作目录就是用户的HOME目录

#### 相对路径、绝对路径

- 相对路径，==不==以`/`开头的路径

  相对路径表示以`当前目录`作为起点，去描述路径

- 绝对路径，==以==`/`开头的路径

  绝对路径从根开始描述路径

#### 特殊路径符

- `.`,表示当前目录
- `..`,表示上级目录
- `~`，表示用户的HOME目录，如`cd ~`可切换到用户HOME目录

----

### 对于文件的操作

#### mkdir命令（创建文件夹）

语法：`mkdir [-p] 参数`

- 参数：被创建文件夹的路径
- 选项：-p，可选，表示创建前置路径

#### touch命令（创建文件）

语法：`touch 参数`

- 参数：被创建的文件路径以及名称 

#### cat命令（查看文件内容）

语法：`cat 参数`

- 参数：被查看的文件路径

#### more命令(高级cat，可翻页)

语法：`more 参数`

- 参数：被查看的文件路径
- 在查看过程中：
  - `空格`键翻页
  - `q`退出查看

#### cp命令(复制文件、文件夹)

语法：`cp [-r] 参数1 参数2`

- 参数1，被复制的
- 参数2，要复制去的地方
- 选项：-r，可选，复制文件夹使用

示例：

- cp a.txt b.txt，复制当前目录下a.txt为b.txt
- cp a.txt test/，复制当前目录a.txt到test文件夹内
- cp -r test test2，复制文件夹test到当前文件夹内为test2存在

#### mv命令（移动、改名文件）

语法：`mv 参数1 参数2`

- 参数1：被移动的
- 参数2：要移动去的地方，参数2如果不存在，则会进行改名

​	eg:mv test.txt Desktop/

​	特殊情况 :mv test2.txt test3.txt 

​	<!--test3.txt本身不存在-->相当于重命名

#### rm命令（删除文件、文件夹）

语法：`rm [-r -f] 参数...参数`

- 参数：支持多个，每一个表示被删除的，空格进行分隔
- 选项：-r，删除文件夹使用
- 选项：-f，强制删除，不会给出确认提示，一般root用户会用到

rm命令支持通配符 *、用来做模糊匹配

- 符号*表示通配符，即匹配任意内容（包含空），示例：
- test*，表示匹配任何以test开头的内容
- *test，表示匹配任何以test结尾的内容
- `*`test`*`，表示匹配任何包含test的内容

> rm命令很危险，一定要注意，特别是切换到root用户的时候。

切换到管理员可以通过su - root 并输入密码

可以再使用exit命令，返回普通用户

==rm是一个危险命令，特别是再处理root用户时谨慎使用==、

**如下指令切记不可在root用户下执行：**

**rm -rf/** 

**rm -rf/***

==效果等同于再Windows上执行C盘格式化==

#### find命令（搜索文件）

##### 语法1

按文件名搜索：`find 起始路径 -name 参数`

路径，搜索的起始路径

参数，搜索的关键字，支持通配符*， 比如：`*`test表示搜索任意以test结尾的文件

- 符号*表示通配符，即匹配任意内容（包含空），示例：
- test*，表示匹配任何以test开头的内容
- *test，表示匹配任何以test结尾的内容
- `*`test`*`，表示匹配任何包含test的内容

##### 语法2

按文件大小查找文件：`find 起始路径 -size +|-n[kMG]`

- +、-表示大于和小于
- n等于大小数字
- kMG表示大小单位，k(小写字母)表示kb，M表示MB，G表示GB

eg：

- 查找小于10kb的文件： find / -size -10k
- 查找大于100MB的文件:  find / -size +100M
- 查找大于1GB的文件 : find / -size +1G

可以通过 `ls -lh 文件路径 `查找文件的大小

#### tail命令(查看文件尾部内容)

语法：`tail [-f -num] 参数`

- 参数：被查看的文件
- 选项：-f，持续跟踪文件修改(Ctrl + C 停止)
- 选项:  -num ,表示查看尾部多少行，不填默认10行（不是填-num 而是把num换成一个具体的数字）

eg:

`[oramk@localhost ~]$ tail test.txt
opt
proc
root
run
sbin
srv
sys
tmp
usr
var`



`[oramk@localhost ~]$ tail -5 test.txt
srv
sys
tmp
usr
var`

#### head命令

功能：查看文件头部内容

语法：`head [-n] 参数`

- 参数：被查看的文件
- 选项：-n，查看的行数

#### 重定向符

功能：将符号左边的结果，输出到右边指定的文件中去

- `>`，表示覆盖输出(将左侧命令的结果，==覆盖==写入到符号右侧指定的文件中)
- `>>`，表示追加输出(将左侧命令的结果，==追加==写入到符号右侧指定的文件中)

eg：

原test.txt:

itheima is a brand of itcast.
itcast stock code is 003032

`echo "hello linux" > test.txt`

变成 

hello linux

`echo "IM linux" >> test.txt`

变成

hello linux
IM linux

---

### 统计与过滤以及嵌套

#### grep命令（过滤关键字）



语法：`grep [-n] 关键字 文件路径`

- 选项-n，可选，表示在结果中显示匹配的行的行号。
- 参数，关键字，必填，表示过滤的关键字，带有空格或其它特殊符号，建议使用””将关键字包围起来
- 参数，文件路径，必填，表示要过滤内容的文件路径，可作为内容输入端口

​	eg :

- [oramk@localhost ~]$ grep "itheima" test.txt
  ==itheima== is a brand of itcast.
- [oramk@localhost ~]$ grep "itcast" test.txt
  itheima is a brand of ==itcast==.
- [oramk@localhost ~]$ grep "itcast" test.txt
  itheima is a brand of ==itcast==.
  ==itcast== stock code is 003032
- [oramk@localhost ~]$ grep -n "code" test.txt
  **2**:itcast stock ==code== is 003032

​		

> 参数文件路径，可以作为管道符的输入

#### wc命令（统计）

语法：`wc [-c -m -l -w] 文件路径`

- 选项，-c，统计bytes数量
- 选项，-m，统计字符数量
- 选项，-l，统计行数
- 选项，-w，统计单词数量
- 参数，文件路径，被统计的文件，可作为内容输入端口

eg:

- [oramk@localhost ~]$ wc test.txt
  2 11 58 test.txt

  <!--2表示有几行，11表示有几个单词，58表示有多少个字节数量，test.txt文件名-->

- [oramk@localhost ~]`$` wc  -c  test.txt
  58 test.txt
  [oramk@localhost ~]`$` wc -m test.txt
  58 test.txt
  [oramk@localhost ~]`$ `wc -l test.txt
  2 test.txt
  [oramk@localhost ~]`$ `wc -w test.txt
  11 test.txt

> 参数文件路径，可作为管道符的输入

#### 管道符| (左结右入)

功能：将符号左边的结果，作为符号右边的输入

语法：`|`

eg：

- `cat a.txt | grep itheima`，将cat a.txt的结果，作为grep命令的输入，用来过滤`itheima`关键字
- `[oramk@localhost ~]$ cat test.txt | grep itheima
  itheima is a brand of itcast.``
- ``[oramk@localhost ~]$ cat test.txt | wc -l
  2`
- [oramk@localhost ~]$ ls | grep test
  test.txt` 
- `[oramk@localhost ~]$ ls -l /usr/bin | grep gtf
  -rwxr-xr-x. 1 root root      15640 11月  2 2018 gtf`
- `[oramk@localhost ~]$ ls -l /usr/bin | wc -l
  1720`

可以支持嵌套：

`cat a.txt | grep itheima | grep itcast`

---

### 输出与执行（echo，`）

#### echo命令(输出内容)

语法：`echo 参数`

- 参数：被输出的内容

eg:

- [oramk@localhost ~]`$` echo Hello Linux
  Hello Linux
- [oramk@localhost ~]`$` echo "Hello Linux"
  Hello Linux

#### `反引号

功能：被两个反引号包围的内容，会==作为命令执行==

示例：

- echo \`pwd\`，会输出当前工作目录

---

### vim编辑模式

#### vi工作模式

![image-20240709144701149](D:\Typora-image\image-20240709144701149.png)

#### 三种工作模式

==命令==模式

命令模式下，所敲的按键编辑器都理解为命令，以命令驱动执行不同的功能。此模型下，不能自由进行文本编辑

==输入==模式

也就是所谓的编辑模式、插入模式。在此模式下，可以对文本内容进行自由编辑

==底线命令==模式

以：开始，通常用于文件的保存、退出

#### 命令模式

如果需要通过vi/vim编辑器编辑文件，会打开一个新的窗口，此时这个窗口就是：命令模式窗口

命令模式是vi编辑器的入口和出口：

- 进入vi编辑器会进入命令模式
- 通过命令模式输入键盘指令，可以进入输入模式
- 输入模式需要退回到命令模式，然后再通过命令可以进入底线命令模式

#### 命令模式快捷键

![image-20221027215841573](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027215841.png)

![image-20221027215846581](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027215846.png)

![image-20221027215849668](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027215849.png)

#### 底线命令快捷键

![image-20221027215858967](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027215858.png)

---





## Linux常用操作

### 快捷操作

#### Ctrl+c 强制停止

![image-20240709160617767](D:\Typora-image\image-20240709160617767.png)

#### Ctrl + d 退出或者登出

![image-20240709160722802](D:\Typora-image\image-20240709160722802.png)

#### 历史命令搜索

##### **history**

![image-20240709160908538](D:\Typora-image\image-20240709160908538.png)

##### **!命令前缀**

![image-20240709160938870](D:\Typora-image\image-20240709160938870.png)

##### **Ctrl + r**

![   ](D:\Typora-image\image-20240709161357037.png)

#### 光标移动

![image-20240709161510974](D:\Typora-image\image-20240709161510974.png)

#### 清屏

![image-20240709161543597](D:\Typora-image\image-20240709161543597.png)

---

### 用户和用户组相关

#### root用户(超级管理员)

![image-20240709150925309](D:\Typora-image\image-20240709150925309.png)

#### 用户管理

![image-20221027222407618](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027222407.png)

eg:

![image-20240709152754909](D:\Typora-image\image-20240709152754909.png)

#### 用户组管理

![image-20221027222354498](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027222354.png)

#### getenv命令

- `getenv group`，查看系统全部的用户组

  ![image-20221027222446514](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027222446.png)

  ![image-20240709153147722](D:\Typora-image\image-20240709153147722.png)

  包含3份信息，组名称：组认证(显示为x)：组ID

- `getenv passwd`，查看系统全部的用户

  ![image-20221027222512274](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027222512.png)

​		![image-20240709152921747](D:\Typora-image\image-20240709152921747.png)

共有7份信息，分别是：

用户名：密码(x):用户ID：组ID：描述信息（无用）:HOME目录:执行终端(默认bash)

#### su命令（切换用户）

语法：`su [-] [用户]`

![image-20240709151009690](D:\Typora-image\image-20240709151009690.png)

---

### 权限

#### 认证权限信息

![image-20240709153440004](D:\Typora-image\image-20240709153440004.png)

![image-20240709153603563](D:\Typora-image\image-20240709153603563.png)

##### rwx

![image-20240709153810142](D:\Typora-image\image-20240709153810142.png)



#### sudo命令（使命令拥有root权限）

![image-20221027222035337](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027222035.png)

**为普通用户配置sudo认证**

**![image-20240709151356739](D:\Typora-image\image-20240709151356739.png)**

比如：

```shell
itheima ALL=(ALL)       NOPASSWD: ALL
```

在visudo内最后一行配置如上内容，可以让itheima用户，无需密码直接使用`sudo`

**用户如何使用sudo**

![image-20240709151706992](D:\Typora-image\image-20240709151706992.png)

#### chmod命令（修改文件权限）

我们可以使用chmod命令，修改文件、文件夹的权限信息。

==注意，只有文件、文件夹的所属用户或root用户可以修改==

语法：`chmod [-R] 权限 文件或文件夹`

![image-20240709154806752](D:\Typora-image\image-20240709154806752.png)

- 权限，要设置的权限，比如755，表示：`rwxr-xr-x`

  ![image-20221027222157276](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027222157.png)

  所以751表示：rwx(7)r-x(5)--x(1)

- 参数，被修改的文件、文件夹

- 选项-R，设置文件夹和其内部全部内容应用同样的操作

#### chown命令(修改文件所属用户、组)

==普通用户无法修改所属为其他用户或组，所以此命令只适用于root用户执行==

语法：`chown [-R] [用户][:][用户组] 文件或文件夹`

![image-20221027222326192](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027222326.png)

---

### 软件安装

- CentOS系统使用：

  ​     yum：RPM包软件管理器，用于自动化安装配置Linux软件，并可以自动解决依赖问题

  - yum [-y] [install | remove  | search] 软件名称

    - install 安装
    - remove 卸载
    - search 搜索
    - -y，自动确认，无需手动确认安装或卸载过程

    ==yum命令需要root权限，可以使用sudo提权==

    ==yum命令需要联网==

​			   **yum命令**

			1. yum [-y] install ,通过yum命令安装wget程序
			1. yum [-y] remove wget,通过yum命令卸载wget程序
			1. yum search wget,通过yum命令，搜索是否有wget安装包

- Ubuntu系统使用
  - apt [install remove search] [-y] 软件名称
    - install 安装
    - remove 卸载
    - search 搜索
    - -y，自动确认

> yum 和 apt 均需要root权限

---

### systemctl控制系统服务开关

语法：`systemctl start | stop | restart | disable | enable | status 服务名`

- start，启动
- stop，停止
- status，查看状态
- disable，关闭开机自启
- enable，开启开机自启
- restart，重启

系统内置的服务比较多，比如：

- NetworkManaager，主网络服务
- network，副网络服务
- firewalld，防火墙服务
- sshd，ssh服务（FinalShell远程登录Linux使用的就是这个服务）

---

### 软链接(快捷方式)

语法：`ln -s 参数1 参数2`

- -s选项，软件软连接

- 参数1：被链接的
- 参数2：要链接去的地方（快捷方式的名称和存放位置）

![image-20240709173150824](D:\Typora-image\image-20240709173150824.png)

### 日期和时区

#### 日期 （date命令）

通过date命令可以在命令行中查看系统的时间

语法：`date [-d] [+格式化字符串]`

- -d 按照给定的字符串显示日期，一般用于日期计算

- 格式化字符串：通过特定的字符串标记，来控制显示的日期格式
  - %Y   年%y   年份后两位数字 (00..99)
  - %m   月份 (01..12)
  - %d   日 (01..31)
  - %H   小时 (00..23)
  - %M   分钟 (00..59)
  - %S   秒 (00..60)
  - %s   自 1970-01-01 00:00:00 UTC 到现在的秒数



示例：

- 按照2022-01-01的格式显示日期

  ![image-20221027220514640](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027220514.png)

- 按照2022-01-01 10:00:00的格式显示日期

  ![image-20221027220525625](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027220525.png)

- -d选项日期计算

  ![image-20221027220429831](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027220429.png)

  - 支持的时间标记为：

    ![image-20221027220449312](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027220449.png)



#### 时区

修改时区为中国时区

![image-20221027220554654](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027220554.png)

#### ntp(同步时间)

功能：同步时间

安装：`yum install -y ntp`

启动管理：`systemctl start | stop | restart | status | disable | enable ntpd`

定期==联网==校准

手动校准时间：`ntpdate -u ntp.aliyun.com`

---

### IP地址和主机名

#### ip地址

P地址有两个版本 V4和V6版本 V6比较少用

V4版本

格式：a.b.c.d

- abcd为0~255的数字

一个标准的IP地址:192.168.88.101

**特殊IP：**

- 127.0.0.1，表示本机
- 0.0.0.0
  - 可以表示本机
  - 可以在端口绑定中用来确定绑定关系
  - 在一些IP地址限制中，表示所有IP的意思，如放行规则设置为0.0.0.0(表示允许任意IP访问)



查看ip：`ifconfig`

如果无法使用`ifconfig`命令，可以安装：yum -y install net-tools

#### 主机名

功能：Linux系统的名称

查看：`hostname`

设置：`hostnamectl set-hostname 主机名`

#### 域名解析

![image-20240709175843575](D:\Typora-image\image-20240709175843575.png)

#### 配置VMware固定IP

1. 修改VMware网络，参阅PPT，图太多

2. 设置Linux内部固定IP

   修改文件：`/etc/sysconfig/network-scripts/ifcfg-ens33`

   示例文件内容：

   ```shell
   TYPE="Ethernet"
   PROXY_METHOD="none"
   BROWSER_ONLY="no"
   BOOTPROTO="static"			# 改为static，固定IP
   DEFROUTE="yes"
   IPV4_FAILURE_FATAL="no"
   IPV6INIT="yes"
   IPV6_AUTOCONF="yes"
   IPV6_DEFROUTE="yes"
   IPV6_FAILURE_FATAL="no"
   IPV6_ADDR_GEN_MODE="stable-privacy"
   NAME="ens33"
   UUID="1b0011cb-0d2e-4eaa-8a11-af7d50ebc876"
   DEVICE="ens33"
   ONBOOT="yes"
   IPADDR="192.168.88.131"		# IP地址，自己设置，要匹配网络范围
   NETMASK="255.255.255.0"		# 子网掩码，固定写法255.255.255.0
   GATEWAY="192.168.88.2"		# 网关，要和VMware中配置的一致
   DNS1="192.168.88.2"			# DNS1服务器，和网关一致即可
   ```

###  网络请求和下载

#### ping命令（网络联通）

语法：`ping [-c num] 参数`

![image-20221027221129782](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221129.png)

#### wget命令（下载文件）

![image-20221027221148964](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221149.png)

#### curl命令

![image-20221027221201079](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221201.png)

![](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221210.png)

---

### 端口

#### 解释端口

![image-20240709194227559](D:\Typora-image\image-20240709194227559.png)

![image-20240709194240771](D:\Typora-image\image-20240709194240771.png)

#### nmap命令(查看暴露端口)

先安装nmap : `yum -y install nmap`

`nmap IP地址`

查看指定IP的对外暴露端口

![image-20221027221241123](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221241.png)

#### netstat命令（查看端口占用）

先安装 netstat：`yum -y install net-tools`

用法：`netstat -anp | grep xxx`

![image-20240709201002289](D:\Typora-image\image-20240709201002289.png)

---

### 进程管理

#### ps命令（查看进程）

功能：查看进程信息

语法：`ps [-e -f]`

可以搭配grep做过滤：`ps -ef | grep xxx`

- 选项:-e ,显示出全部的进程

- 选项:-f ，以完全格式化的形式展示信息(展示全部信息)

  一般来说固定用法就说:ps -ef列出全部进程的全部信息

   ![image-20240709201726754](D:\Typora-image\image-20240709201726754.png)

#### kill命令(终止进程)

![image-20221027221303037](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221303.png)

---

### 主机状态监控

#### top命令

功能：查看主机运行状态

语法：`top`，查看基础信息(类似于任务管理器)



可用选项：

![image-20221027221340729](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221340.png)



交互式模式中，可用快捷键：

![image-20221027221354137](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221354.png)

##### top命令内容详解

![image-20240709202642148](D:\Typora-image\image-20240709202642148.png)

 ![image-20240709203000384](D:\Typora-image\image-20240709203000384.png)

#### df命令

查看磁盘占用

![image-20221027221413787](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221413.png)



#### iostat命令

查看CPU、磁盘的相关信息

![image-20221027221439990](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221440.png)

![image-20221027221514237](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221514.png)



#### sar命令

查看网络统计

![image-20221027221545822](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221545.png)

---

### 环境变量

#### env命令

查看系统全部的环境变量

语法：`env`

#### export命令

- 临时设置：export 变量名=变量值
- 永久设置：
  - 针对用户，设置用户HOME目录内：`.bashrc`文件
  - 针对全局，设置`/etc/profile`

#### PATH变量

记录了执行程序的搜索路径

可以将自定义路径加入PATH内，实现自定义命令在任意地方均可执行的效果



#### $符号

可以取出指定的环境变量的值

语法：`$变量名`

示例：

`echo $PATH`，输出PATH环境变量的值

`echo ${PATH}ABC`，输出PATH环境变量的值以及ABC

如果变量名和其它内容混淆在一起，可以使用${}

### 上传下载

![image-20240709211743622](D:\Typora-image\image-20240709211743622.png)

#### rz、sz命令

<img src="D:\Typora-image\image-20240709211530492.png" alt="image-20240709211530492" style="zoom:50%;" />

---

### 压缩和解压

![image-20240709214618821](D:\Typora-image\image-20240709214618821.png)

#### 压缩

![image-20240709214710863](D:\Typora-image\image-20240709214710863.png)

#### zip命令压缩文件

可以用zip命令压缩为.zip文件

`zip [-r] 参数1 参数2 参数N`

![image-20221027221906247](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221906.png)



#### 解压

![image-20240709215228592](D:\Typora-image\image-20240709215228592.png)

`tar -zxvf 被解压的文件 -C 要解压去的地方`

- -z表示使用gzip，可以省略
- -C，可以省略，指定要解压去的地方，不写解压到当前目录



#### unzip命令解压文件

unzip命令方便解压zip压缩包

`unzip [-d] 参数`

![image-20221027221939899](https://image-set.oss-cn-zhangjiakou.aliyuncs.com/img-out/2022/10/27/20221027221939.png)