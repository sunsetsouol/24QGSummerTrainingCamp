# Redis简介

## Nosql

![image-20240811162148261](D:\Typora-image\image-20240811162148261.png)

### SQL

**结构化**

![image-20240811162324070](D:\Typora-image\image-20240811162324070.png)

**关联**

![image-20240811162602013](D:\Typora-image\image-20240811162602013.png)

**SQL查询**

```sql
select * from user where id - 1
```

**ACID**(事务)

![image-20240811163004385](D:\Typora-image\image-20240811163004385.png)

### NoSQL

**非结构化**

![image-20240811162432810](D:\Typora-image\image-20240811162432810.png)

1. 键值类型(key|value)
2. 文档类型(json)
3. 列类型
4. Graph类型

**非关联**

通过json进行数据存储

数据易重复

```
{
  id: 1,
  name: "张三",
  orders: [
    {
       id: 1,
       item: {
	 id: 10, title: "荣耀6", price: 4999
       }
    },
    {
       id: 2,
       item: {
	 id: 20, title: "小米11", price: 3999
       }
    }
  ]
}
```

**非sql**

命令

```
get user:1
```

函数

```
db.user.find({_id:1})
```

请求

```
GET http://localhost:9200/users/1
```

**BASE**

![image-20240811163029153](D:\Typora-image\image-20240811163029153.png)

#### 总结

![image-20240811163248900](D:\Typora-image\image-20240811163248900.png)



---

## Redis介绍

Redis诞生于2009年全称是**Remote Dictionary Server**，远程词典服务器，是一个基于内存的键值型NoSQL数据库。

**特征**:

- 键值(key-value)型，value支持多种不同数据结构，功能丰富
- 单线程，每个命令具备原子性
- 低延迟，速度块（基于内存，IO多路复用，良好的编码）
- 支持数据持久化
- 支持主从集群，分片集群
- 支持多语言客户端

Redis的官方网站地址：https://redis.io/

