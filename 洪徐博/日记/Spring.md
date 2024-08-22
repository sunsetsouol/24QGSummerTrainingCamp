# Spring

## Ioc控制反转

- 使用对象时，由**外部**提供对象，将对象创建控制权转移到外部，称为**控制反转**。
- 使用IOC容器创建对象。被创建或被管理的对象在ioc容器统称**bean**

## DI依赖注入

- 在容器中建立bean与bean之间的**依赖关系**的整个过程。

- 提供依赖对象的setter方法

- ```java
  public void setUserDao(UserDao userDao){
      this.userDao=userDao;
  }
  ```

  ![](E:\md java笔记\暑期培训\屏幕截图 2024-07-12 210648.png)

## Bean作用范围配置

- 利用scope属性，定义bean的作用范围，可选范围：

  singleton:单例(默认)

  prototype:非单例

- 适合造单例的bean：

  - 表现层
  - 业务层
  - 数据层
  - 工具对象

- 不适合的bean：

  - 封装实体的域对象

## Bean实例化

1. 使用构造方法(必须无参)实例化bean：

   ```java
   public BookDaoImpl(){
       ...
   }
   ```

2. 使用静态工厂实例化bean：

   ![](E:\md java笔记\暑期培训\屏幕截图 2024-07-12 212642.png)

3. **FactoryBean**实例化bean(后期必要)

   ```java
   public class UserDaoFactoryBean implements FactoryBean<UserDao>{
       public UserDao getObject(){
           return new UserDaoImpl();//创建并返回对象
       }
       public Class<?> getObjectType(){
           return UserDao.class;//返回对象的字节码
       }
       public boolean isSingleton(){
           return false;//设置是否为非单例，一般不写
       }
   }
   ```

   ![](E:\md java笔记\暑期培训\屏幕截图 2024-07-12 213734.png)

   此处获得的是UserDaoFactoryBean中返回的对象，不是FactoryBean本身。

## Bean生命周期控制

- ![](E:\md java笔记\暑期培训\屏幕截图 2024-07-12 214754.png)

## 依赖注入方式

1. DI依赖注入

2. setter注入(主要使用)：

   在配置中使用property标签**value属性**注入**简单类型数据**

3. 构造器注入

## 依赖自动装配

- Ioc容器根据bean依赖的资源**在容器中自动查找**并注入bean
- 推荐使用**byType**按类型注入

## AOP

- 作用：在不改变原始设计的基础上为其进行功能增强
- Spring理念：无入侵式

### AOP切入点表达式：

- 切入点：要进行增强的方法

- 切入点表达式：要进行增强的方法的描述方式

- 切入点表达式标准格式：动作关键字(访问修饰符  返回值  包名.类/接口名.方法名(参数) 异常名)

- 例：

  ```java
  excution(void com.hongge.dao.userdao.update());
  ```

  ![](E:\md java笔记\暑期培训\屏幕截图 2024-07-13 112318.png)

- ![](E:\md java笔记\暑期培训\屏幕截图 2024-07-13 113015.png)

- 
