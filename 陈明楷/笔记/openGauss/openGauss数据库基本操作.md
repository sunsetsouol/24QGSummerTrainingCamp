# openGauss数据库基本操作

## 1. 以操作系统用户omm登录数据库主节点

```
su - omm
```

### 1.1 启动服务

```
分布式openGauss：
gs_om -t start    启动服务
gs_om -t restart  重启服务
集中式openGauss：
gs_om -t stop   关闭服务
gs_om -t start  启动服务
```

### 1.2查询openGauss各实例状态情况

```
gs_om -t status --detail
```

- 如下部署了集中式openGauss集群，数据库主节点实例的服务器IP地址为172.20.73.178
- 数据库主节点数据路径为/opt/gaussdb/master1/
- 集中式没有CN，通过主DN访问

```
[omm@openGauss01 ~]$ gs_om -t status --detail
[  CMServer State   ]

node           node_ip         instance                             state
---------------------------------------------------------------------------
2  openGauss02 172.20.73.179   1    /opt/gaussdb/cmserver/cm_server Standby
3  openGauss03 172.20.74.210   2    /opt/gaussdb/cmserver/cm_server Primary

[    ETCD State     ]

node           node_ip         instance                       state
---------------------------------------------------------------------------
1  openGauss01 172.20.73.178   7001 /opt/huawei/xuanyuan/etcd StateFollower
2  openGauss02 172.20.73.179   7002 /opt/huawei/xuanyuan/etcd StateLeader
3  openGauss03 172.20.74.210   7003 /opt/huawei/xuanyuan/etcd StateFollower

[   Cluster State   ]

cluster_state   : Normal
redistributing  : No
balanced        : Yes
current_az      : AZ_ALL

[  Datanode State   ]

node           node_ip         instance                   state            | node           node_ip         instance                   state            | node           node_ip         instance                   state
---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
1  openGauss01 172.20.73.178   6001 /opt/gaussdb/master1  P Primary Normal | 2  openGauss02 172.20.73.179   6002 /opt/gaussdb/slave1_1 S Standby Normal | 3  openGauss03 172.20.74.210   6003 /opt/gaussdb/slave1_2 S Standby Normal
[omm@openGauss01 ~]$

```

### 1.3检查数据库性能

```
gs_checkperf

1. 以简要格式在屏幕上显示性能统计结果。
gs_checkperf -i pmk -U omm 
2. 以详细格式在屏幕上显示性能统计结果。
gs_checkperf -i pmk -U omm --detai

```

### 1.4 确认数据库主节点的端口号

在1.2查到的数据库主节点数据路径下的postgresql.conf文件中[查看端口号](https://so.csdn.net/so/search?q=查看端口号&spm=1001.2101.3001.7020)信息。示例如下：
cat /opt/[gaussdb](https://so.csdn.net/so/search?q=gaussdb&spm=1001.2101.3001.7020)/master1/postgresql.conf |grep port

```
[omm@openGauss01 ~]$ cat /opt/gaussdb/master1/postgresql.conf |grep port
port = '36000'                          # (change requires restart)
#ssl_renegotiation_limit = 0            # amount of data between renegotiations, no longer supported
                                        # supported by the operating system:

```

- 36000为数据库主节点的端口号
- 端口号在安装数据库时，会在xml文件中配置，查看安装时的xml配置文件也可以找到端口。

### 1.5列出所有可用的数据库

```
gsql -d postgres -p 36000 -l
[omm@openGauss01 ~]$ gsql -d postgres -p 36000 -l
                          List of databases
   Name    | Owner | Encoding  | Collate | Ctype | Access privileges
-----------+-------+-----------+---------+-------+-------------------
 db1       | song  | SQL_ASCII | C       | C     |
 db2       | song  | SQL_ASCII | C       | C     |
 kwdb      | kw    | SQL_ASCII | C       | C     |
 mydb      | song  | GBK       | C       | C     |
 postgres  | omm   | SQL_ASCII | C       | C     |
 song_suse | song  | SQL_ASCII | C       | C     |
 template0 | omm   | SQL_ASCII | C       | C     | =c/omm           +
           |       |           |         |       | omm=CTc/omm
 template1 | omm   | SQL_ASCII | C       | C     | =c/omm           +
           |       |           |         |       | omm=CTc/omm
(8 rows)


```

其中，[postgres](https://so.csdn.net/so/search?q=postgres&spm=1001.2101.3001.7020)为openGauss安装完成后默认生成的数据库。初始可以连接到此数据库进行新数据库的创建。

## 2. 查看数据库对象

```
1. 登陆默认数据库postgres：
gsql -d postgres -p 36000
[omm@openGauss01 ~]$ gsql -d postgres -p 36000
gsql ((GaussDB Kernel V500R002C00 build fab4f5ea) compiled at 2021-10-24 11:58:09 commit 3086 last mr 6592 release)
Non-SSL connection (SSL connection is recommended when requiring high-security)
Type "help" for help.

openGauss=#
2. 登陆自建数据库song_suse:
gsql -d 数据库名 -p 36000 -U 用户名 -W 密码  -r
[omm@openGauss01 ~]$ gsql -d song_suse -p 36000 -U song -W ******  -r
gsql ((GaussDB Kernel V500R002C00 build fab4f5ea) compiled at 2021-10-24 11:58:09 commit 3086 last mr 6592 release)
Non-SSL connection (SSL connection is recommended when requiring high-security)
Type "help" for help.

song_suse=>
```

### 1）查看帮助信息:

```
postgres=# \?
```

### 2）切换数据库：

```
postgres=# \c dbname
```

### 3）列举数据库：

```
使用\l元命令查看数据库系统的数据库列表。
postgres=# \l
使用如下命令通过系统表pg_database查询数据库列表。
postgres=# select dataname from pg_database;
```

### 4)列举表：

```
postgres=# \dt
postgres=# \d
```

### 5)列举所有表、视图和索引：

```
postgres=# \d+
```

### 6）使用gsql的\d+命令查询表的属性：

```
postgres=# \d+ tablename
```

### 7）查看表结构：

```
postgres=# \d tablename
```

### 8）列举schema：

```
postgres=# \dn
```

### 9）查看索引：

```
postgres=# \di
```

### 10）查询表空间

```
使用gsql程序的元命令查询表空间。postgres=# \db
检查pg_tablespace系统表。如下命令可查到系统和用户定义的全部表空间。
postgres=# select spcname from pg_tablespace;
```

### 11）查看数据库用户列表：

```
postgres=# select * from pg_user;
```

### 12）要查看用户属性：

```
postgres=# select * from pg_authid;
```

### 13）查看所有角色：

```
postgres=# select * from PG_ROLES;
```

### 14）切换用户：

```
postgres=# \c – username
```

### 15）退出数据库：

```
postgres=# \q
```

## 常用SQL语言

### 1）创建数据库用户

```
create user 用户名 with password "密码";
create user jack password "******";
在每次创建新用户时，系统会在当前登录的数据库中为新用户创建一个同名Schema。对于其他数据库，若需要同名Schema，则需要用户手动创建。
```



```
openGauss=# create user jack password "******";
CREATE ROLE
openGauss=# \du
                                                              List of roles
 Role name |                                                    Attributes                                                    | Member of
-----------+------------------------------------------------------------------------------------------------------------------+-----------
 jack      |                                                                                                                  | {}
 kw        |                                                                                                                  | {}
 omm       | Sysadmin, Create role, Create DB, Replication, Administer audit, Monitoradmin, Operatoradmin, Policyadmin, UseFT | {}
 song      | Create role, Sysadmin                                                                                            | {}
```

### 2）删除数据库用户

```
drop user 用户名 cascade;
drop user jack cascade;
```

### 3）创建数据库，并指定所有者owner

```
create database 数据库名 owner 用户名;
create database jack_test owner jack;
```

### 4）为用户授权

```
给用户授权对某数据库的所有权限
grant all privileges on database jack_test to jack;
为用户追加有创建角色的CREATEROLE权限
alter user jack createrole;
将sysadmin权限授权给用户joe
grant all privileges to jack;

```



```
openGauss=# create database jack_test owner jack;
CREATE DATABASE
openGauss=# \l
                          List of databases
   Name    | Owner | Encoding  | Collate | Ctype | Access privileges
-----------+-------+-----------+---------+-------+-------------------
 db1       | song  | SQL_ASCII | C       | C     |
 db2       | song  | SQL_ASCII | C       | C     |
 jack_test | jack  | SQL_ASCII | C       | C     |
 kwdb      | kw    | SQL_ASCII | C       | C     |
 mydb      | song  | GBK       | C       | C     |
 postgres  | omm   | SQL_ASCII | C       | C     |
 song_suse | song  | SQL_ASCII | C       | C     | =Tc/song         +
           |       |           |         |       | song=CTc/song    +
           |       |           |         |       | song=APm/song
 template0 | omm   | SQL_ASCII | C       | C     | =c/omm           +
           |       |           |         |       | omm=CTc/omm
 template1 | omm   | SQL_ASCII | C       | C     | =c/omm           +
           |       |           |         |       | omm=CTc/omm
(9 rows)

openGauss=# grant all privileges on database jack_test to jack;
GRANT
openGauss=# alter user jack createrole;
ALTER ROLE
openGauss=# grant all privileges to jack;
ALTER ROLE
openGauss=# \du
                                                              List of roles
 Role name |                                                    Attributes                                                    | Member of
-----------+------------------------------------------------------------------------------------------------------------------+-----------
 jack      | Create role, Sysadmin                                                                                            | {}
 kw        |                                                                                                                  | {}
 omm       | Sysadmin, Create role, Create DB, Replication, Administer audit, Monitoradmin, Operatoradmin, Policyadmin, UseFT | {}
 song      | Create role, Sysadmin                                                                                            | {}

openGauss=#


```

### 5）删除数据库

```
drop database 数据库名；
drop database jack_test;
```

### 6）创建schema(模式，默认为public)

```
1. 切换到jack_test数据库：
openGauss=# \c jack_test
Non-SSL connection (SSL connection is recommended when requiring high-security)
You are now connected to database "jack_test" as user "omm".
2. 切换到jack用户
jack_test=# \c - jack
Password for user jack:
Non-SSL connection (SSL connection is recommended when requiring high-security)
You are now connected to database "jack_test" as user "jack".
3. 查看schema
jack_test=> \dn
       List of schemas
         Name         | Owner
----------------------+-------
 blockchain           | omm
 cstore               | omm
 db4ai                | omm
 dbe_application_info | omm
 dbe_file             | omm
 dbe_lob              | omm
 dbe_match            | omm
 dbe_output           | omm
 dbe_perf             | omm
 dbe_pldebugger       | omm
 dbe_pldeveloper      | omm
 dbe_random           | omm
 dbe_raw              | omm
 dbe_scheduler        | omm
 dbe_session          | omm
 dbe_sql              | omm
 dbe_task             | omm
 dbe_utility          | omm
 pkg_service          | omm
 pkg_util             | omm
 public               | omm
 snapshot             | omm
 sqladvisor           | omm
 sys                  | omm
(24 rows)

jack_test=>
4. 查看当前用户：
select * from current_user;
select user;
jack_test=> select * from current_user;
 current_user
--------------
 jack
(1 row)

jack_test=> select user;
 current_user
--------------
 jack
(1 row)

jack_test=>


5. 为用户jack创建一个模式jack1
create schema 模式名 authorization 用户名;
create schema jack1 authorization jack;
jack_test=> create schema jack1 authorization jack;
CREATE SCHEMA
jack_test=> \dn
       List of schemas
         Name         | Owner
----------------------+-------
 blockchain           | omm
 cstore               | omm
 db4ai                | omm
 dbe_application_info | omm
 dbe_file             | omm
 dbe_lob              | omm
 dbe_match            | omm
 dbe_output           | omm
 dbe_perf             | omm
 dbe_pldebugger       | omm
 dbe_pldeveloper      | omm
 dbe_random           | omm
 dbe_raw              | omm
 dbe_scheduler        | omm
 dbe_session          | omm
 dbe_sql              | omm
 dbe_task             | omm
 dbe_utility          | omm
 jack1                | jack
 pkg_service          | omm
 pkg_util             | omm
 public               | omm
 snapshot             | omm
 sqladvisor           | omm
 sys                  | omm
(25 rows)

jack_test=>
6. 修改schema名称
alter schema jack1 rename to jack2;
7. 要更改当前会话的默认Schema，请使用SET命令。
执行如下命令将搜索路径设置为myschema、public，首先搜索myschema。
set search_path to jack2;
查看当前schema
show search_path;
jack_test=> set search_path to jack2;
SET
jack_test=>  show search_path;
 search_path
-------------
 jack2
(1 row)

jack_test=>


```

### 7）授权

```
grant usage on schema jack2 to jack;
回收(revoke)

将Schema中的表或者视图对象授权给其他用户或角色时，需要将表或视图所属Schema的USAGE权限同时授予该用户或角色。否则用户或角色将只能看到这些对象的名称，并不能实际进行对象访问。

```

### 8）数据库重新命名

```
alter database jack_test rename to jack_db;
```

### 9)命令查看当前数据库存储编码

```
show server_encoding;
```

### 10）查询表分布列（分布式openGauss才有，集中式没有）：

```
select getdistributekey ('schemaName.tableName');
select getdistributekey ('tableName');
```

### 11）修改omm管理员密码

openGauss数据库登陆如果提示omm密码过期，需要修改omm管理员密码，例如：

```
alter role omm identified by '新密码' replace '旧密码';
```

