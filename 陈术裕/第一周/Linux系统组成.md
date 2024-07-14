## Linux系统组成

- 完整的操作系统：

Linux系统内核

系统级应用程序

VMware

CentOS Ubuntu两个发行版

FinalShell远程连接Linux

内核：硬件调度管理能力

虚拟机：

借助虚拟化技术，我们可以在系统中，通过软件：模拟计算机硬件，并给虚拟硬件安装真实的操作系统，得到一台模拟的电脑，即虚拟机

命令：ls

作用：验证在FianlShell中的操作跟在VMware是否相同

- 注意：如果把虚拟机关机或者重启，IP地址有可能发生变化

解决方法：重找IP地址即可

### 虚拟机快照

作用：将当前虚拟机的状态保存下来，通过快照回复虚拟机到保存的状态

关闭虚拟机

右键选择，快照快速拍照即可

### 目录结构

Linux的目录结构是一个树型结构

没用盘符概念，只有一个顶级根目录，用：/来表示

 示例：在根目录下有一个test文件夹，文件夹内有一个文件test.txt

/test/test.txt

## Linux命令入门

### 命令基础

- 命令行：即Linux终端，是一种命令提示符页面，纯字符去操作系统，发出指令
- 命令：即Linux程序，一个命令是一个程序，没有图形化界面，可以在命令行提供字符化的反馈
- 基础格式

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MGU4ZmY1YzgwZGM3YzcyM2I2NjcwMzlmY2MwZjg2MWRfUkpGUXpDUGVaNjdpUXRJWXlNTVJ1SEtkcWEwVHF0NXlfVG9rZW46R0FWY2JZVUNUbzFBaUZ4OTQySGNQZzMwbkhjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ODllN2U3NTAyMWU2YTIxMmRiNDdhM2RlMTNmYjcxOTBfWjMxTFdaU1dsRTdyc2p3STc3NE40UFhiSGN5M2tWQzlfVG9rZW46WlhlWGJvSGd4b1VIZkF4SU85YmM5bHVXbkpmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MWEyMTQ4MWNjZjEwMWVlZDIyMjIyZjc4NTg3OGI4MGZfaGR4NjZ2ZWE1V3JZckFLWlcwUGVLU2ZPMlpXOGNjQUVfVG9rZW46WGc1ZmJPZTRXb0g4MFZ4TGpsS2NOT0EwbnlkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 命令入门

#### ls命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NDFkYjYyZGRjODEwMGMzMTFlNTVkN2NjNTgxYjNhYzdfME9lUVVMREtaYTBLbDFpbUFVcHNoWGJzTGdRdUJDRE5fVG9rZW46UmJMaGJaNWNlb1VESFZ4Tk54MmNsUnRTbjFlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

HOME目录：每个操作用户在Linux系统的个人账户目录，路径在：/home/用户名

默认工作目录是HOME目录

##### 命令的参数

命令的参数是[Linux路径]，指定要查看的内容

##### ls的选项

-a选项：表示all的意思，列出全部文件（包含隐藏的文件）

-l：以列表的形式展示内容，并展示更多信息

-la：两个功能都有，可以组合使用

-h：以易于阅读的形式展示

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NmI5NWVkNTU2ODExNDY1ZWI2YmRjNTQyZGNlYThmYmNfcTBueURjV2F0N0MxckVQRU5KOW01NzRRMmNPbmg2d01fVG9rZW46VHlPSGJrN3pub1pVYnV4NEdxeGNBNHFubkJlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### cd命令

Change Directory

作用：切换工作目录

语法：cd [Linux路径]

cd命令无需选项，若写参数，那就转到参数，没有则默认到HOME目录

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MDFkNzAyNGFjNDY2YjU4ZGY1ZDMwMjAyMzRkMDBlNDZfUDRBZU1EcFNEdG5JeEVMZzZqalZiY0loSWI0WUlOY2xfVG9rZW46Rmk4ZmJjazkzb3dlQXp4NThtVWNjTHYzbmVnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### pwd命令

Print work directory

作用：查看当前所在的工作目录

直接使用命令：pwd

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmM4ZTk1NmIxZTJlYWViZjliMTQ1YzBkZjczMTEwZTFfTVlDVVhONVdwWUtET2RuYWlhMThDT1BMWEpmaElwSHRfVG9rZW46Q1BJM2JpNDVPbzBxMEF4SmdlemNZZm1Bbm1jXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 相对路径，绝对路径

- 绝对路径：以根目录为起点，描述路径的写法，以/开头
- 相对路径：以当前目录为起点，描述路径的一种写法，路径无需以/开头

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTkxM2FkNWJhMDEyNmRkMDQyZTY1OWYxOTBlNTJkODBfVkJJMG10RDBNaDNBcGR1Y2F3N3R2YTJTR1QxZ1NPZzdfVG9rZW46R2h0emJUdUdIb2VLSUh4dFQ0UWNFQWFUbmNmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 特殊路径符

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NjYwMzc5YThlOWI4ZjJjMWI4ZjhiYWQyMTg5MzViNzJfVjRyM3RYVm04WFJuYlpUVHltR2hGUWlKQlpFZVdYdGRfVG9rZW46UUVBVGJvandBbzNUVnp4cEZBV2NTUmpmbkZlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NGQ3MjQ1NWEzYTIzMjExZTlhMGFlNjMxYmI1Mjc3ZjFfZTVHeTFZS1BER1AzbXNSSUYwMW1nVjVrcEU3OFZodmdfVG9rZW46VGZFZWJwNTNkb0x6bjd4akhVWmNmN1p5blNiXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### mkdir命令

Make directory

创建新的目录

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MWFhOThkNDVkNWMxNWRiY2ZlYjVjMjM2MzRjNzM4ZTNfY2s4MG5vSzgzeWlpaWFxRmJscjZvQ3V3YnhGc0ZLWE5fVG9rZW46Q2JERWJ4RENKb2F0WXh4OWRGOWNtMENrbjZnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YWJhODAxYjdlYTUwMzk0YzcxMTZkYjFjN2E5NjEyZjNfVjJFOUF2M0E0UFNGVDJkOEhIVFZMWFduUTVKM1dOTVVfVG9rZW46TWFmaWJLdkEyb3E4S3l4U3M5c2NINWI5bkdoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MDI3N2QyOTk0YTc4OWI2YmE5MGQ3MWZkMDIyOWEzMzdfTmVxNE81UkNhT1c4NlFNVGdNUXAxcUFsbFVWQ0l6UlNfVG9rZW46RlRZd2JhYWxjb3pKckd4NFZ3Z2MxUTNwblFlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

- **注意**

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=Yzg2MWFkNTllM2NmZDNiNThmNzNiOWFiYjEwYTk5NDVfWEVia2d6S1FWdWxsdUdRODB5SFlmaVRhV3VCZGNXdjJfVG9rZW46TFZSTGJhb0pObzEzMVF4Z1hoVmM5a3lubnViXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MTZlOWM3MWNkN2JhMDg2NTg4ODE5Zjk4MDExYTRlODhfMjhxMWtiUWVISHBsbk8xVXVUYTFiTWxsV2NQcDZkRE9fVG9rZW46Rmk2UmJGWUdPb054bUp4bGhBaWN0MUxubjZjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### touch 命令

- 作用：创建文件
- 语法：touch [Linux路径]

命令无选项，参数必须填，各种路径都可以

### cat 命令

- 作用：查看文件内容
- 语法：cat [Linux路径]

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YWVjNmY2MGZjMzM5ZGY1MDQwNzUxZjI3MDY5OTRhY2VfRk1OQjJUeGRqRWVKeEdzNUhMcGJDOXV1bjFRYWNmcVFfVG9rZW46Sldub2JXYUprb0tUbnp4WjJUVmNXYTd2bnZkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### more 命令

- 作用：查看文件内容，与cat不同的是，支持翻页，若文件内容过多，可以一页页展示
- 语法：more [Linux路径]

命令无选项，参数必须填，各种路径都可以

q可以退出，空格进行翻页

### cp 命令

copy

- 作用：复制文件文件夹

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OTU0NDNlN2U2NDBlNGQyNDNlNTA2MmMwY2E0M2U0NjZfTktETXF0MHkyN2o1TXVyUVpNWTVMd2lOdTAyb0QxTERfVG9rZW46WEtSYWJwRFd4b3pLTmV4cGlJRWNRVUo1bnNjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YTQxNWQwN2Y4MTVjYjExODNmNWJmZTAzYjZjYWRiMDZfYVlMUlJ1cUpCWnRRMWNXSVYwSm9UczQxMnFqS3dVWmFfVG9rZW46SUdORmJpT29Mb3NNTG54UzFCV2NlUmhYbm9nXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NTgxMThmMWEzNDU0YWE0YWY5ZWYzNmFlNDZiNTMzYjJfOGZZSjBGd0E2aTkyR01NZzYyWEVwVFRkSHowMEF5UW9fVG9rZW46SXU5b2JuR2VybzJRckV4ZmwwU2NMOUtDbnFoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### mv 命令

move

作用：应用于移动文件/文件夹

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmZhYTNkM2Y2MzVhMGJiOGY2YTM2OGNmNTYyM2IzYmFfYTI2dnRoVlFNa08wQlBOUHJFWUdENmk0Z0lDY0Y0THhfVG9rZW46WFZaeGJnM1ZZb0ZhcVV4WFlmRGNBbXZWbnlnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTM1NjEwZjQ0MDU2YWUwMjk2M2E1ODI2NGM2MjMwNWNfM3dZelZlNzZxZ0ttMVhWT004MWxjV3ZPTm1BNmJTcWRfVG9rZW46RWozbWJPbVdsb3dDWWx4NE9qUGNBb0FZbnliXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YzFlZGQ1NWM4NmU4YTY1NDJlZmE1NzhmNWI3YjlmODhfaHQ4QzlLVjJVUThJdnhYeHN6NFpTSmx5R0ZSNzRGQTFfVG9rZW46TEdHYWJiemlKb1EyU0d4RkNIVmN1TTBvbnVjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### rm 命令

remove

作用：删除文件、文件夹

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTI0N2E3MWVhNzc4MzY5YWIxMGQwY2U5ZTFkMzJkMzFfdWxhM0hJSHBBN0VibGRBTjVxUEtkZnowQzIydU9sZ3ZfVG9rZW46UWp6UWI2c3R5b3dOTzJ4bmY3RWN2cUloblNkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

- rm命令支持通配符*，可以做模糊匹配

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=N2MwYWYzMzAyNzI2YjFjNmM1NWYzYTVjYTI4MTZiZDZfdlhUZ2tCQ0VHeU93VjlQbWdCalpZM2c0dFpGb0ZjMEFfVG9rZW46TFl4OWI1aW5Hb0FTVlR4TEdRSGNMTllRbnRiXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGRlNzZlMWVlMDU3NmJkMzgwMWZkMjlhNDA5YmEyZGFfYUt0M0FvYmNWaDQ2OXNORVBKS1hPek1HSnk4YVZvVDlfVG9rZW46UzNqbWI3STlqb1h0UUt4NWZGNmMxWHhMbmJnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### Which

作用：查找命令

语法：which 要查找的命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NTlmMzgzODQ5ZTkxYWNlZjQ2NjdjYWVkNTUwMjE2MThfSGdKQkk0ODBRcGlUbnA1aDRXdDlaZERYOW5keDBzYWVfVG9rZW46SDROaGIwRFN5b1R3QVh4YkFab2NQMDBkbkxnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### find

- 作用：查找指定文件

按照文件名

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjI3M2JhMjIxYmYyMGVhZGE0YmIxYjQ0Y2VhM2U3YjZfOFdlUGFTeU5GdFUwakM0bkZmdjlIeFNocVdDeDBQdmxfVG9rZW46RHkyQmJ2YnM3b1g3ajF4Z2V6SmNUZ0d1bnloXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDNmMTU5MGNmOGVlZTdkZjJlMzQ5ODhlN2ZiN2FlZWRfOVFFOW02RUJtWGlOUGw1bUc4UHljd2RDUXFqMHB2YnRfVG9rZW46WnJWMmJDN0ttb0hJczZ4UXVrZ2M5YnZjbjBjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjIyYWM4OTY4MTMzMTNjZmU5YmY3MDhkYmQzNTg0ODVfWTQwN1hHTHQ2VmRQV0gwYWI1ejZhMkRvRGVmaEl5Rk5fVG9rZW46UGQwOWJLbXVmb0xsTG54UHIzVGNZcEJsbnViXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

按文件大小搜索

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YjVkN2MwNzVmMTNmNWVhNGE0MDNjZDFmNGQyNDVhZTBfTmpzNm54am10Szk2a0ZRY0VWUGpOSldUMDM5TmVrNnJfVG9rZW46QlY3RmJFMnVNbzhlcWd4b3lkRWNzQkNybmJBXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### grep 命令 

- 作用：从文件中通过关键字过滤文件行

可作为管道符的输入

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YmNmMWNiNjhmOThmOTk3NWZmMTExYWM4YWE3M2JmNzNfM2FyNjNrbFJWZFlUUkhvbFlJYTEwb2lTeHRqN3h1ZjdfVG9rZW46TjA3T2J3aDZBb3VVdld4d1Y2TWNrYk5RblFjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MDgzMjgwMDE5YjEzNDFlZDc0ZmFiOGQwNzJlNzI2NTFfZzFEbjR5c09admE3ZEFiRm9ObXZSUHhjWmNEUXRCM3VfVG9rZW46VlRWc2J3cjFLb3RISzl4SWVuNmN6aUNLbnpkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### wc 命令

- 作用：统计文件的行数，单词数量等

可作为管道符的输入

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OGQxNTYxZWM1ZmRmYTJkMDkxMmEzMzAzMjE1N2I3NDJfZjZNVm1HeWgyQ3dyMmdHaWxCRDZ5dVNPY3l4Ym5hT2VfVG9rZW46VXBaZWJyVHJjb05LUmt4Wk1kbGNsb3hxbmNkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YTNhYzA3OTE0ZTYxYjk5ZDA5NzBlNDQ0NjhhMDg0ZDZfYzk5UnIyak1BcjVBSHJzVE9ib1hRMndWUkxvS2ZxbklfVG9rZW46UVZoVWI4S0xtbzdmR1R4S3o4VGNPYUl3blJkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 管道符 

**|**

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YjFhMTM1MzVlZjAxYjRjNzBlOWY4OWIzNGZiN2I0YzlfNlpJQmdNeUgxVUx3YUt2aUhJTXhmYWxTWFI5bDc4elRfVG9rZW46V1J1cmJwczhjb2hxSkN4QVdHbGNFcXJxbjhnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OTg3Y2U5ZDgzOWZhOGI0NGQ5ZDcyYTA4OTY3YWQ1YTZfNzlBckl3ejlKUm8weUlKYml4U0JTQ2lqejZlMEtEbDVfVG9rZW46RTNXT2JZVkdab0lCdGt4NVBVa2NrVVlBbk9jXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 多层管道符

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MjJjNjA1NzZhM2E0NTdmMTgwZWRmNmZhNDZkNjMxNjVfT0FGSktaYjBxWGJ5QjlhRklUNWVrSXIzQjhtbG9HN1dfVG9rZW46VEptUWJkQlNmb3ZUOWV4Y01KUGNTc0FubnplXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### echo命令

- 作用：输出指定内容

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OWE0YzI1YmRmM2E2MzdkMGYzODk0MzM4NDM3YWQ3YjFfYVZKbTV5WjlXVTVKVGhmcWZQaHh1bTlDV2xmWDY5MHZfVG9rZW46UXY3cWJwcnlZbzhUek94MEZaU2NjVmxPbmdjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 反引号

- 作用：被包围的内容，会被作为命令执行，而不是普通的字符输出

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2U0YjE5ODVmZjdkMmNhNzc3MWY3OTVkMTJmZTU1OWZfdU9DSUtwbjB4c3RRVW53NGdJbXBsMWx1amRaa05ubEpfVG9rZW46SFVoZGJHbzd5b2ZIc014RllFZWNVNGVhbnhyXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 重定向符

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ODgxZGNlNTlkMzliNTY4MmQzMDdkNTc4NmYzYjE0ZjBfd3padWozSWZHbTJtdzU0TGhwVGVQNkhSUVkyYW5aRHhfVG9rZW46UFpzSGJuNGZub0x3dFB4anJ4MmN2Y25WbjhkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

**只要可以产生结果的都可以往右边写**

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZGE5YzkzMTRjN2U3NzFjMmYxNzU2OWU5YWQ1ZTFlMmNfMGZmTHU3cEdTT2g4bGN2ZklvY3ZEWGFnT2Z0UWk3cWlfVG9rZW46SUZ2R2JjRENEb3BxREt4Qkt3emNIZDJNbkhoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=Mjg2NTZiMTVjYzU1NWYxNWVmZTIyZjc0OTc1YTdlYmZfME9PMXk0YWM1QTUzT0h3ZjhvTzJyVmt6SFNIUkh3ZzZfVG9rZW46Q0ROOWJMNmppb3hQSjZ4NVFsQmNYbThpbkVnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### tail 命令

- 作用：查看文件尾部内容，跟踪文件的最新更改

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NWM5ZWRkYWJmNTY5YzkwMTJmMTAyZDJhYzU4Nzc5MzlfVDFIR1hMazFuWG9pcXp2WkNndUtrcXRsWklmbzdOWUhfVG9rZW46T0QyamJlMVlzb2d3cUp4UTVEV2NIVjZ2bnJkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OTVhNDllYmFmODg0MGY2Yzc3ZTljMmM5MDYyMTJkNjdfbGVEWk5mOG0zV25zWml5VWlZUkNRQ045dWpDZTNiaklfVG9rZW46WmRPMWJHcFpyb1k2UEl4SlI2emM3WnptbnRiXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

**ctrl+c强制停止跟踪**

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NjQ2ODdkOTg4NTk5ZDFmMGNlZjM1YTllNWExOGRlNWZfOG1EZ0dncWJQanBqd3c1blVoY2pSMVR3UlhmUXZ3dXZfVG9rZW46S081b2J2bjgxb0R0aWV4NnJjWGMzV1o3bndlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

vi/vim编辑器

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NDcxNGUwNTFlYTlhYjkxNjY2NTZhNjBmYmQzODE0NmRfZTQ1SG1vWERiaUZqeENpNGU3WkRLelV1MHJhc3d6UmVfVG9rZW46UkRoWWIxQWk4b1VRbFh4bURkMGNBQkQ4bmtkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

三种工作模式

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YWU3NWRmYTJkNTk0MGQ1YWRiZDAzNGM0MjU5MjM2YTFfZTlyVnZtUDFvUkhqVEJSTkxZcFg1MUxTcFVJV21ndkdfVG9rZW46S3hSTmJrcTN2b1UwQzh4RjZ4MGNWbGtZbmRRXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 命令模式

Vi 文件路径

vim 文件路径

有文件就对该文件操作，若没有则新建一个文件再操作

按i进入输入状态，esc则是退出

两下y+一下p复制，两下d即可删除

按shift+冒号进入底线命令模式，输入wq表示保存并退出

输入模式与底线命令模式需要通过命令模式转换

#### 快捷键

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OTllOGQwODViZDY4NWRmNmFiNmI3MjgzMThkMjc5OGZfVzBsUEFKVmpBem5JN3BSN2haWjZhcDNSUHRHcXdnemVfVG9rZW46U2dCRGJ4N3d5bzZtT0h4dWpXQWNieEtBblNiXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NGJkZDNjMDE2YTRhM2U5MmZkYmViMzJjY2Q4YzQwY2JfVEIyOWlHRk9QMVREeUYwMWs3T2FEb1o1NmhQYlBYVzVfVG9rZW46Rmg3VGJhUnQ2b0lBOVp4Y095NWMyY2tIbkxkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MGMwZGEwNDEzNTE4YTI5NGZlY2VmYjM1ZWZlZGVjMmJfem96cDNCSkU0ZFFzbHZzb3FEdzlFM1VmdW9qV0xyTHFfVG9rZW46UjNmaWJ2WFc0b1JvME94RnNXWGNzbFRFbmFnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

## Linux用户与权限

root（超级管理员）

作用：拥有最大的操作权限

普通用户，出来home之后很多都用不了

### su和exit命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YzBiN2QyOTJkOTFmYjE0MjFmMTQzZjc5ZDM5MDViYTdfRUN5cVk1dERzQ0g5bnl3UFltQm9TR2hhTWhOSmtMeUFfVG9rZW46QmVzeGJZOVE4b3hocTF4QTJ2b2NBaTdwbmdiXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

root切换到其他用户不需要密码

### sudo命令

为普通命令授权，临时以root身份进行

语法： sudo 其他命令

#### 为普通用户配置sudo

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTU3MTI1MmJmMzJkOTBhNTVlMjA1OGY3YjdhZTI3MzZfV3IzOVZoYTJVbHlwUlE5MnZXS3luTGdRRnFCZHVmUlBfVG9rZW46RTlUa2JiRnU1b1VFRGR4bHF4SmNWeFBwbm1iXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YzJmOWI5N2RiNTFiZThjNzQyZWI0MjE2YTYxZjFkMDhfbGJGbWRCVVJWcUV0UWwxRVY0THlHNTIzUlpPT0ZiZTlfVG9rZW46RGN3bWJnRkl0b0pCa1Z4VGFLeGM4dEczbm1mXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 用户、用户组管理

（需要root执行命令）

配置多个用户、多个用户组、用户加入多个用户组

#### 用户组管理

创建用户组

groupadd 组名

删除用户组

groupdel 组名

#### 用户管理

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2FjMmM3ZDE0OWM2NGE4ZWI5YzRhN2M3Yzg1OGMyMTdfQmd1UlNsZExuRXhsdWhZZUQ2d0pwZWJid2JFbEZidDlfVG9rZW46Wlk0dWI1akhDb2pLVWl4N0ZSemMzcWNLbmtoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NDgzMjdlNzc2MDcwNDllNGUyODRjM2IxNDYwMjQ5NTlfc3lLb21GS1daaVROUHhwYlZ4Y1NWRnczVjBBbExvanNfVG9rZW46SldBTGJOM2RtbzhtbFR4NVRKZ2NveXR2bjlnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YzQ2MGJlZWQ1NTJjY2JkMjQwZGExYWJlMTVlM2IwYTBfRGtLaHhkZDFNa1ltOG9uejlPTmt0bndqMGpXeUI2NUFfVG9rZW46SUg4R2JxTlZGb2lzQTh4UEwxSmNrSDdWbjBmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzdlYjkyMGNlZjM2OThjMGY5MTQzM2I3MzM5MGU0YTJfVE94WmhmSGhrWDV2YU9UNVVHYmVXQm5RN3F6MUN1VHRfVG9rZW46WXR2bmJlcTFkb1ZxcUh4dUNWVGNERjhhblpmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### getent命令

getent passwd

作用：查看当前系统有哪些用户

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=Y2JiMTZiYWMyZjA3OTc2NTQzNzA2YjFhMzc4NGI2MGNfeEtxTExJaDBOa1RKc0xERXZyVTJaSUtGRGhsbE84R1pfVG9rZW46TG5Rc2JsSlpFb2NiVWd4cVFHQmNRSUkxbkFjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

语法：getent passwd

getent group

作用：看系统中有哪些组

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OGE1ZDE3NWNhMWNkNzYyMGJjODg1ZTJiOWZjNjVlOWRfUUJzUlFnN0w4eFZOaVVWc0lsSW5uTDRIbFR0cWRpNkpfVG9rZW46V0k2d2I2STNmb0dDcHN4TE4xbmN4ck51bkFmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 认知权限信息

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NTQ1NDA0OWEyMDVkZTc2Y2RmOTViZTAzMjI5OTc1NmNfQzBxd3hmVHBHbkdvRUNqV1Z3RTV0eVNicTB4azFYUWFfVG9rZW46WmRrWGJRTWRlb05pcTB4SXY4OGNVSlpWblJnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDYyOWY0MjFlYzgwMTY2ZDA3ZWU4Y2UxN2E5YjFjZmNfak5zb1h1RHVYMExSaWptMUdQRTNVMjFhdjRYVWhTZHJfVG9rZW46TFJVMGJSbFRQb2phZXF4YmJXdWMxeWdVbnNkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### r代表读权限

针对文件可以查看内容，针对文件夹查看内容，如ls

#### w代表写权限

针对文件可以修改，针对文件夹可以创建、删除、改名等

#### x代表执行权限

 可以将文件视为程序执行，针对文件夹可以改工作目录到此工作夹，即cd进入

### chmod修改权限信息

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZDE0MTE4OTFiNjE0ZDE1OWIxNzdiZjg5MTlkODMzYWJfWmJoRHJ0YUJ4S0RZeTJmNE85U2NOa2xZdHRLZ01KaExfVG9rZW46SHQxTWJZVlN4b09vNjF4ZElwV2M2akF5bnhiXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MWRlNzI4MjFlMGFkMzUzNTE0MjEyOTc0NTlmNzkxZThfMG5VZjhBUTV3OUdBSkVkVXRnMWYwR1IyUFRXM2NybFdfVG9rZW46VFZGQWJrb2dXb1UzeUt4ZDNFY2NjSGpHbkhlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 权限的数字序号（记熟）

r=4，w=2，x=1

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZmZkMTEyOTUxYzg5YWYyNjBlNDdhMjQ2YWFiZjZhYWVfYUM2cnhIamNheXVRSHprV1M1M0piMUI3djBGWjRNS2dfVG9rZW46QWRoM2IzVWlGb056b0F4SU5UYmNhU0EybkljXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NWY2OWE5NzBmOGRhZDVmNzhmNGU5ZTBlNGMxMmIzYjZfMzdvaUp1dXdPVUQ1dXNQTmg1d0FJbmpVcFFYTE9TbGRfVG9rZW46WVQydmI5QXQxb3ZhZmp4b3lKOGM3Wm1sbkRkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### chown修改文件、文件夹所属的用户和用户组

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NTBkYjE2NDM1MGYzZTkxYzJiMDRmYmI3Mjc3NmQ4NmNfaDlldmV0UWdVamhPY2lxczBLMWR5NmJ0TFVFOUxJWFZfVG9rZW46RGJYamJKNEtob3BLcWF4d3ZnRWM0amtQbkdoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NTEzZTkzMTYxZTE1OGFiMTI2ZmJkOGNlNzAyN2E2NjRfek5ld1JKYXB4azFzY1k5WDNwOG8yNkpPb1Fob3hXT0JfVG9rZW46Tzc5WGJzeWxwb0ZsVjZ4Z3g1UGNRM2oxblhnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

## Linux的实用操作

### 各类小技巧

- 强制停止：ctrl+c
- 退出或登出：ctrl+d （不可以退出vi）
- 搜索历史命令

1. history
2. ！命令前缀，自动执行上一次匹配前缀的命令，时间间隔太久不宜使用

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTcwNDI2OThiOTUzN2M2YjMyMmU3ZTk2OGJmNGY5MzNfcUF0aGFJdUtzcE5OdFNZdUZKd1dscklua1ZjQWU5OEtfVG9rZW46TGs5WWJaUkpnbzVud2h4d0s5S2NHMnE1blFlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

1. ctrl+r，输入内容去匹配历史命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NjQwZjRmMjBhZjc3MDZmNWVjN2ZjZjAzNmZhM2M3ZDRfeEd2WUJmVnY0cnhMcFVyVGZraW5mR2xVNDdJWHVLMGdfVG9rZW46SXY0VGJTcTE5b1h1TUZ4NEVRN2NwVVJYbmpmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 光标移动快捷键

ctrl+a，跳到命令开头

ctrl+e，跳到命令结尾

ctrl+左键，向左跳一个单词

ctrl+右键，向右跳一个单词

#### 清屏

ctrl+l

clear命令

### 安装软件

yum命令

要求：root权限，联网

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NjIyYzU0YWU0MGJjNWFmMzY4MWU1NzcxZjQ1NTA4ZjRfQ0FkcnlJUm43MHpKNFd0OWFnbmllZVVZbDN3dFBqWnBfVG9rZW46VHdPRGJGbjc2b0VsOWN4ZHBkRWNuaDlpbnRkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### systemctl命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YTQ3ZTllMWJiOWZkZWNhNTVjNTFhNWRiZjJjOWRlMmFfVDl3ZkdTakp5MTcxV0dxaEhYNDlBMnBUbWVreWYzNHVfVG9rZW46UjNxY2J2R3lwb2RLMmp4SWdIMGN1WW1Wbkg0XzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=N2FiNDM0OThlMGY4NzhmNjZhOTE5ZWQ0YWUyMmFjMWZfTUVsTnhQNGo4TzZ5dWNDYUhpeXdWZGh4Y0xDdmhZVkRfVG9rZW46WDVTQmJNcG9ab3kwdHl4ajNxTWNoVWczbjRjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### ln命令

作用：创建软连接，可以将文件、文件夹链接到其它位置

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YWE0NWUzMzdhODM1ZGZjMTRhMGI3OTVlZjU1MDg1ZDRfb1ZyV0lSMnJvdEtuUHRpeVBrdmlKYUJsOVVNSTdxSHpfVG9rZW46V3JCYmJ0Mjh2b1AyNzB4S3NwOWNPNmp3bkRoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NmZmZGFhYjhhMThmZWY3NTdlZTQyM2RhNTlmODA2NzVfUWU5MldPU0NoT2RGQ2FHUnRNaUExc243MUppMFQ2U1lfVG9rZW46QVJRZGJPbGt6b3pvYnN4bG11VGM1OHJnbnloXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

**注意****：~前面要空格，后面不用空格，也可以不需要这个波浪号，两个参数之间空格即可，可以不需要波浪号**

### 日期与时区

date命令

查看系统时间

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MzBjNmQzNDFkM2I1ZTAxYjc2MjE1ZTgxZTk4ZTkyYTZfZHZZaTdXMmMzem9yUmF0dXJHSTZ6WmJIenJ2NEt4ZUZfVG9rZW46VnZyRWI1NmlUb015TE54T1h6amN5VzVFbkZnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

**注意****：如果要求的格式中间有空格，需要用引号框起来**

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MWY0NmY0NGUxY2RhOTQ3N2MyMDM1ZGMxYzM2MzM0MTFfTUVKY09LNUVmelN4ZUJ6ZlRscG1UQUFISDRKd2UzazVfVG9rZW46TnBBNmJ0SWZvb0FVNU94aTdyM2N3SDdzblBkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjMxZTJjODNlYmQ1MGFiMWFmNTA1ZDRkMWYzZjkxNDZfOENnQTdHTEEyd0I3azd1NWxmT3BYdkVJU2ZWODlJckFfVG9rZW46RFhnN2JJa3Z1b3I3QzZ4ckZYYWNrR0ZEbkpjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 修改Linux时区

需要root权限

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MTQ1OGI4YTFjMTExNjcyOTEyNjllMmI5OWY0NTdhNzdfYUVkQk9JWVJ5N1hSeVB2N2RQaDBpV1YxemtPbVlwRnBfVG9rZW46WGlhOWJPU1lJb3hOWjd4VmFPV2N4UlowbmlnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### IP地址 主机名

#### IP地址

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzgxODg2YTQyOTE2NjBhNTZjZDRjZjhiODZhZmVhMjBfRmFjR0JxNklvZVoxSndMVU5rSzJVdDZDWFphS1NFVHNfVG9rZW46STROcmIwNzhQb3BMT3R4bE1rUGM2bm9NbmpiXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

ifconfig查看本机的ip地址

##### 127.0.0.1 

这个ip地址指代本机

##### 0.0.0.0 特殊地址

指代本机

在端口绑定中确定绑定关系

表示所有ip

#### 主机名

Hostname

查看主机名

修改主机名

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MGU5M2U0MTgwM2EyNDljMzY0N2RiZTgzM2ZhYWRkNmVfeGhVVHVMc1ZobVI4ZzRVUWRkMk9jN2xBbHhyamttY1JfVG9rZW46V20zQ2Iyek9Nb0FjNzV4ZExFQmNmRkY0bmllXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OWQ0NTY3MTJjYjY5YWZlMWYzYTRjMmY2MjU3NTdhOWRfRDNHR3JvZEZ4QUhORllZVmFZb2NodUtNSGxkSWlpRHhfVG9rZW46VlRsSGJUMUFMb0pFMDB4VkREZGNxSkpUbmZjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 域名解析

字符串来代表IP地址

#### 固定IP地址

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MDI0NWZlZDQ0MjkxNTU0ZDZiYTE1NDJkMGIzMTY5YzhfNXE0NUNsQkw5VThPR3R2MXpaa2VLNVV6WXZkMEtLSDBfVG9rZW46VUtwNWJKRUtzbzRHaDl4bDR1TmNTU3E3bm1jXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

原来是223.0，改为88.0

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjQ1MTViNzM0NGI1NTQ4NzVhZWM3OGIxNzIwOTc2YTJfSDJjdlMzUUlDNVdEeVFqSmlxYkljVk5jZktMU3hrdkNfVG9rZW46RjdEemJXTzB6bzVwa2d4VHAwMGMydnZ4bnNoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 网络请求和下载

##### ping命令

作用：检查指定的网络服务器是否是可联通状态

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OGYzZWQ5ZWY5NDNkNmEzZDM5YzJlMTQ2NDUxMjc2YTlfZWt0SDVjZHJ4cVk2alNRdVFXZ2RUUlFCSmh1V2E0WnBfVG9rZW46UWZhUGI0OTNzb2RrcnF4T1hROGNycVdIbnllXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YmYzZGY5OWRlODIwNmI5NWViYjUzYTg3MzgyN2RkMjdfM1BsS3BXUXBhYWJOa3ZYektOZjA1M2xVdGlDVUNtVXVfVG9rZW46RWpaTWJoand2b2FESE14alNhV2NUMTU3bkRmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

##### wget命令

作用：非交互式的文件下载器，可以在命令行内下载网络文件

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MTE4NzJiOTBhNjMyYTgyODM4ZjUzODFiNTc4MWRiM2VfVVdseHlSd2Y5MnlFNTZzaEs4aWVBOGNqVnA3SDhyb0hfVG9rZW46QldudmJsWjcyb1NEaWt4bzFKNGMzRmFrbkViXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

可以通过tail命令去监控后台下载进度，-f可以持续跟踪

##### curl命令

作用：可以发送http网络请求，可用于下载文件、获取信息等

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=Zjg0ZmE1ZDVmOGRhMmYwMTEzYmU2YTE0NWQ1MTNkZjZfN2FBaUxTc3FxMnZ1RzN2cmxOUVNyUDg2WjVkSkNnc3VfVG9rZW46RXZpcmJ6aWhVb0hnVkF4Z3M5V2MzbE5wbnRoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 端口

设备与外界通讯的出入口，物理端口和虚拟端口

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OTg0OWZmNWJlMjJkODQ0MjQ5MjBhMDk2NGY0MTU2NjlfeXJQZmlNZ0U1OG5kcTFtS3g5SE0zTFFMcVZ0OXFaMnhfVG9rZW46WFdjN2Iwd1Jmb0JuREp4S2s4bmNkNlVrbno1XzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

示例：计算机找计算机，ip地址只能锁定计算机，端口可以找到对应程序

#### 查看端口占用情况

使用nmap 被查看的IP地址

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NWNmN2ViOTY5ZTU2MjUzZjdmN2M3NDUxZDgwNTgzNmZfN1h3R3FWdXdSb2JOeXN5aWVQNGdva3gzREljOWQ0TldfVG9rZW46UzV3VWJ3czUxbzA3Tk14d2NBQmNwaEtubkRlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

##### netstat命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MjYxNDVmOTcxZDBmNzYzMjQ5N2YyNjg1MTQwNDA2ODBfZGNFcXR5VTdZVGZjRk9XZ2l5dUJ6Q3NkSlU1bGd6M05fVG9rZW46R2lYZ2JuTVdTb3d3Z1F4V05zOGN0WExZbmVlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NThkMzJhZDNmM2Q4ZWQ3YWIwMWQ5NThiYmQ0NTYyNmJfN1dBNHVRTmFUVnFSbEkwcHRzZ3p4UHk0WUZQYlN1QTVfVG9rZW46R29vUGJxZmtpb0htd2d4Nm04c2NGNURjbkpoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 进程管理

#### 查看进程

ps命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzkxODQ3OWRkZWI0NDMzZmZlMTU3NzgwNWQ1ZDlmMWNfOW5uSVBSb2hOaFg4WTFtM1c0YldtN3Zmd2xHUlM3aGpfVG9rZW46UjdsUGIzRHNNb29uSmx4dzQydGNMYmZwbmhjXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MTI5ZDI0ZmY3MzJiMmUzMjc3OWU2NzFiOGQ5OGI2NzBfaHFTUTVGaHVDaDlPS2VxTVNBTzFCR0t0YTRLdzBpcjBfVG9rZW46TU52aGJycWp3b3lSUjZ4N1o3d2NjbXM3bjlnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 关闭进程

kill

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YzE5NmJlYmE1NTY0YzBmZTYyNzcyYjc5MDlmODNkYjlfQURaaUY2WEdwbFJwWDBoS09lYjZpaXdUQlZmVjFDMGZfVG9rZW46S0xRZWIwUk1LbzZtd1F4d2hLVGNCenB2bnFoXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTExMzk0OWMyZDJhMWNmMmUyNTFhMGE1OTA3NmE1YzVfVFpmZ05xNXpiSzB6QmN6bkJDR2hpSk5LTlhQaG1BeHZfVG9rZW46WTZsWWIyMVhvb0hhR0l4UWt6bWNubHZ2bkRlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 查看系统资源占用

top

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MjAxMjI0NjY4NDEzYjRmNzc3NWJjMGFjNWY5Njk1YjBfZ0czd0VEMlB4Z0NkRkZ1ckthTXBXSEhGY0s1eGdVckpfVG9rZW46WURPOGJUZWNOb2hYeGd4WjM1QmM2bHZSbkxmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NDViMmI3N2MzNGRiYmE5MTczN2FkZDkzYjcwOGFiNTlfOU9pY0tsRW5XQ0c5ZjFRa3pqSnNwcHBuR0k1QnQzTzdfVG9rZW46VlhkVGJyWERRb2h6UnF4V3VGU2NucHBFbnViXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

并且该命令支持选项

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MTBlMDczYmMxNDE4ODcwMDQ0NTdlNWUzNzY0NjM3MDFfdGZHZEJIYlVZSE1laDFnbnZEYzdlOXRqdG1GNmtYbVdfVG9rZW46VTV1MmJ5QWdNb1lpcGV4RGNWTWNXQUQzbkxkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

Top 交互式选项

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NWEyZGJmOGVkYWU2MDY2ZmFmMmRkNzVmMWYxMDUyNjRfWVBOY201b01kd1VOcVRsYVZlUW51a2dWMVp4b3E3M1lfVG9rZW46UHBLM2JjZjlzb2JIT2Z4MGtmOWNnSnhNbk1iXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 磁盘信息监控

使用df命令，查看硬盘使用情况

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MjVkMTEwZTM2NzRmZDA2NWVjOWIxODU0ZmI5ZTg1YmZfRDEzTDBLSHJSNGFVVm5kQzBiclRIYzE5R2RNc0M2ZktfVG9rZW46TTMwN2JFUXZPb3hENTJ4a0NYQmMwVEtObnN2XzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 查看cpu，磁盘的相关信息

命令：iostat

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NDEyMzgyYjAxNWQ2NGUyNGMzMTMzMzEwODU0ZDdiNzRfR0NXbEtSb3FjcWx1c3JpenRzck5jSGlRQU9NSzNMT01fVG9rZW46WHB6UGJxQXpRb1VsYWJ4R0E3NGNlNWFablN1XzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 网络状态监控

sar

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ODcyZmJmOTU0Yzk3ZTMwNTk3NTliYzJkZGU4NWJmMGRfMWM2R0FPNGNMZ2dPbDAwb3dEd1dHTVlUcTJZWDhFSmdfVG9rZW46QXM4ZWJ3WVUzb3ZEaUp4VUVqRWM0ZDQ1bm9mXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MzMxMTdhMDk5MTk3MjU0Y2UxOGU4MDFkNjcwZTM2Y2VfWnIySjVrWWtZa1QycDFMQzYxQ3JSOG1tVnJoSEVxOXRfVG9rZW46Q3p4bWJMajgxb24xc2d4cjZvN2NmMVBRblliXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=N2NhMmFlN2FmNjdlM2ZhZTJiOTkxN2VmMzA3ZDQ4ZTVfTzZ5TERmVTdMalExSXNYemsxMEpkTFJreDlsNk1DV2hfVG9rZW46T3BTTGI5cFVxb2ZuTDh4TUVJcGN3aGJZbjdmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 环境变量

#### env查看环境变量

PATH记录了系统执行所有命令的路径

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=NzlkYjQzYjZjMzVhZjBkMDJlMjYxZjVlYzQ5YzllYjlfcG8zRWF3cU9kbVJHOGtVN09odWQxS2FmaGJESXJJUUxfVG9rZW46U1N6MmJHN24wb282REl4QmJZbWNGQlNVbkRnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

只去value值

$符号

$环境变量

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YTU3OTQ4Yzk2YjdmMzIwMTg0MTU4ODYxMWYxYTI3OGRfY2E0aDVVMDZOTVRrM1hKbThWNHVUWEQxN2hOdUlBREtfVG9rZW46RWpNcmJkWE9Fb1Zob214UXprZmNjMVdSbmFlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### 自行设置环境变量

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=M2JhMDAyYTQxY2EyNWJiMzBkZjU2YTAxOGVkZGQ2ZWRfQUlkUDlKdUJzVEl3Z3R3YWVjZGk3ZjNDbk9Ra3JFWUhfVG9rZW46UlVKVGJ4VTVqbzV1TGZ4bGZkVmMwV1dobldlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZjFkOGE0MjU3ODk5ZDU1NmU5NTQ5YzIzOWQwZTAwYmNfRGtna3g3VEt0UjEwR3QyWjRQUWtjcTNVQkdoWnFFZlFfVG9rZW46TDNlcmJPZUh5b1lOaU14aWFzQWNVQjFtbjllXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 上传下载

##### 拖拽可以

##### rz，sz命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YmI0MzMzNTg1ZWNkMWM2MDY5NTZkODQ0NjYxYTNiYmVfUDFmdDVNSzRyb2hsaVJNUDNoSGsxN1lQcUZBcEszdnJfVG9rZW46R2hMbWJkTGIwb1M5ZVR4UUFMb2NXRlNlbm1vXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

### 压缩解压

压缩格式常用：tar、gzip、zip

#### tar命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=OWEwZTAwMjc0ZTg2ZTJkOGRjODc4MzM5M2ZlODU2YzZfY0REVzRKVXdhQ1JHTGdvS2M1TnNpYm5aVFkwR1h6dXRfVG9rZW46TXFaN2JiSlFrb1pNZWx4a2hHbWM0dkxPbmpmXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MThiZGY3ZTRmZTI4ZTNhNjQ5NDIyNzg2MjNjOWM2N2VfZTQwWkp0Z1JhbEUyeldpOTc3MEhpaFhEbURNaWtvYTJfVG9rZW46S3B3QmJXb0tNb3djMnp4UTM2SGNFZUFLblRlXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=M2ZiMTEyMjAyMDA4OTFkYjEzMTVlZTdjNDVkNzk0OGJfVThYNmtuTkhEUGFXUkJncnh3dW13czJQb045eWJzRlhfVG9rZW46SGIwQWJjR1lYb2hJWFZ4ZE5DcmN1bmZJbkloXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

解压

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=YjBjN2Q3YTUzMTBkNGJkMjY2OWE1OTU3ZWQ3ODcwYzFfYjFSeDV3a0NMZkxWd2FaWXMzREZpOW9KZFlQcmpTQkNfVG9rZW46T0NiWWJoMzBxb1ZXWG94dTJLcWNRdXljbldkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MzI3OGVmN2EwMzEwZTkyYWIwMzhiYjA2YjdhMDMzNTNfQnNKYnpOeU00YWg1dUZMVWc1MmhRMFZ1elBJcDR6WnlfVG9rZW46VnVCS2JDajY5b1VpUDR4SG90VGNTVXhKbmdnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### zip命令

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MjAxNjQ4NGM5N2MwZTk5YjdmYjY5ZDFkMGY0ZWQ3NDNfV1pCNTRjMGJNSjdRalgxU3loc1ZJQU80dGljc3RpWGNfVG9rZW46SXN4VGIyc0dSb2M5Tml4ajduT2NQbVE3bnFkXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=ZTBmOTNhNDAxMjMwNTE0ODhlM2EzNmU3ZGYxYzhiYzdfYVlONjhXVVJxMVcyNzFCOWd5VFMxOHFIYVB0ZXRlSTdfVG9rZW46U1VCc2JKYlVMb29wMXh4VmxlTGNGYnZvbkhnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

#### unzip解压

![img](https://g16urso0agu.feishu.cn/space/api/box/stream/download/asynccode/?code=MDNmOWFiMDE5MTRhZmY3MDVhOTcwNmM0OWQyYmYwNjdfb1ZITkRlS0puNnB6d3JtRjh0V3dEd0tVQmV2bXJHdnNfVG9rZW46WDhtemIzc3hkb3RNYUN4VWJvb2MzYlJ3bktnXzE3MjA5NjQxMzU6MTcyMDk2NzczNV9WNA)

## Linux上部署各类软件

#### Mysql

密码：CSYcsy666^

登录

Mysql -uroot -p

检查端口

Netstat -anp | grep 3306

#### tomcat安装

先安装jdk

安装了jdk11

tomcat版本10.1.25

tomcat建议非root用户安装，创建一个新用户：tomcat

检查端口 8080

直接stop，disable关闭防火墙

#### Nginx安装

高性能HTTP和反向代理web服务器，可以托管用户编写的web应用程序