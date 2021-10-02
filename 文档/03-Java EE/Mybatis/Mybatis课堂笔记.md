[TOC]

# Mybatis

环境：

- JDK 1.8
- Mysql 5.7
- maven 3.6.3
- IDEA 2020.2 

回顾：

- JDBC：https://blog.csdn.net/m0_46153949/article/details/107231974
- Mysql：https://blog.csdn.net/m0_46153949/article/details/107116168
- Java基础：https://blog.csdn.net/m0_46153949/article/details/106167213
- Maven：https://blog.csdn.net/m0_46153949/article/details/109265177
- Junit：https://blog.csdn.net/qq_40794973/article/details/89032870

> [SSM框架](https://baike.baidu.com/item/SSM/18801167)：配置文件的。  最好的方式：看官网文档；

## 1、简介

### 1.1、什么是Mybatis

![1569633932712](Mybatis课堂笔记.assets/1569633932712.png)

- MyBatis 是一款优秀的[**持久层框架**](https://baijiahao.baidu.com/s?id=1664144432266027831)
- 它支持定制化 SQL、存储过程以及高级映射。
- MyBatis 避免了几乎所有的 JDBC 代码和手动设置参数以及获取结果集。
- MyBatis 可以使用简单的 XML 或注解来配置和映射原生类型、接口和 Java 的 POJO（Plain Old Java Objects，普通老式 Java 对象）为数据库中的记录。
- MyBatis 本是[apache](https://baike.baidu.com/item/apache/6265)的一个开源项目[iBatis](https://baike.baidu.com/item/iBatis), 2010年这个项目由apache software foundation 迁移到了google code，并且改名为MyBatis 。
- 2013年11月迁移到[Github](https://github.com/mybatis/mybatis-3)。

如何获得Mybatis？

- maven仓库：

  ```xml
  <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
  <dependency>
      <groupId>org.mybatis</groupId>
      <artifactId>mybatis</artifactId>
      <version>3.5.7</version>
  </dependency>
  ```

- Github ： https://github.com/mybatis/mybatis-3/releases

![1608639555419](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1608639555419.png)

- 中文文档：https://mybatis.org/mybatis-3/zh/index.html

![1608639653138](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1608639653138.png)

### 1.2、持久化

> 数据持久化：

- 持久化就是将程序的数据在持久状态和瞬时状态转化的过程。
- 内存：**断电即失**。
- 数据库(Jdbc)，io文件持久化。
- 联系于生活：冷藏、罐头。

> **为什么需要需要持久化？**

- 有一些硬盘里的对象，不能让他丢掉！！！

- 内存太贵了。

### 1.3、持久层

> Dao层，Service层，Controller层….

- 完成持久化工作的代码块
- 层界限十分明显。

### 1.4 为什么需要Mybatis？

- 帮助程序猿将数据存入到数据库中。
- 方便
- 传统的JDBC代码太复杂了。简化。框架。自动化。
- 不用Mybatis也可以，更容易上手。 
- 优点：
  - 简单易学。
  - 灵活。
  - sql和代码的分离，提高了可维护性。
  - 提供映射标签，支持对象与数据库的orm字段关系映射。
  - 提供对象关系映射标签，支持对象关系组建维护。
  - 提供xml标签，支持编写动态sql。

> **最重要的一点：使用的人多！**
>
> Mybatis学完后，学Spring ---> SpringMVC  --->  SpringBoot

## 2、第一个Mybatis程序

- 思路：搭建环境-->导入Mybatis-->编写代码-->测试！

### 2.1、搭建环境

> 搭建数据库

```mysql
CREATE DATABASE mybatis;

USE mybatis;

CREATE TABLE USER ( id INT ( 20 ) NOT NULL PRIMARY KEY, NAME VARCHAR ( 30 ) DEFAULT NULL, pwd VARCHAR ( 30 ) DEFAULT NULL ) ENGINE = INNODB DEFAULT charset = utf8;

INSERT INTO USER ( id, NAME, pwd )
VALUES
	( 1, 'subei', '123456' ),
	( 2, '哇哈哈', '952741' ),
	( 3, '欣杰克', '854362' );
```

> 新建项目

1. 新建一个普通的maven项目

![1608641446898](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d01.png)

![1608641941583](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d02.png)

![1608643046589](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d03.png)

2. 删除src目录

![1608643141552](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d04.png)


3. 导入maven依赖

   ```xml
       <!--  导入依赖  -->
       <dependencies>
           <!--mysql驱动-->
           <dependency>
               <groupId>mysql</groupId>
               <artifactId>mysql-connector-java</artifactId>
               <version>5.1.47</version>
           </dependency>
           <!--mybatis-->
           <dependency>
               <groupId>org.mybatis</groupId>
               <artifactId>mybatis</artifactId>
               <version>3.5.2</version>
           </dependency>
           <!--junit-->
           <dependency>
               <groupId>junit</groupId>
               <artifactId>junit</artifactId>
               <version>4.12</version>
           </dependency>
       </dependencies>
   ```

### 2.2、创建一个模块

> Mybatis 3.5.4 中文官方文档：https://www.yuque.com/nizhegechouloudetuboshu/library/zsd0mk

![1608645198080](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d05.png)

- 编写mybatis的核心配置文件——mybatis-config.xml

  ![1608648810409](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d06.png)

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE configuration
          PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-config.dtd">
  <!--configuration核心配置文件-->
  <configuration>
      <environments default="development">
          <environment id="development">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="com.mysql.jdbc.Driver"/>
                  <property name="url" value="jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;serverTimezone=UTC&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                  <property name="username" value="root"/>
                  <property name="password" value="root"/>
              </dataSource>
          </environment>
      </environments>
  
      <!--  每一个Mapper.xml都需要在mybatis核心配置文件中注册！  -->
      <mappers>
          <mapper resource="com/github/subei/dao/UserMapper.xml"/>
      </mappers>
  
  </configuration>
  ```

- 编写mybatis工具类

  ![1608649483047](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d07.png)
  
  ```java
  package com.github.subei.utils;
  
  import org.apache.ibatis.io.Resources;
  import org.apache.ibatis.session.SqlSession;
  import org.apache.ibatis.session.SqlSessionFactory;
  import org.apache.ibatis.session.SqlSessionFactoryBuilder;
  
  import java.io.IOException;
  import java.io.InputStream;
  
  // sqlSessionFactory ---> SqlSession
  public class MybatisUtils {
      private static SqlSessionFactory sqlSessionFactory;
  
      static{
          try {
              // 使用Mybatis第一步：获取sqlSessionFactory对象
              String resource = "mybatis-config.xml";
              InputStream inputStream = Resources.getResourceAsStream(resource);
              sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
          } catch (IOException e) {
              e.printStackTrace();
          }
  
      }
  
      // 既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。
      // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
      public static SqlSession getSqlSession(){
          return sqlSessionFactory.openSession();
      }
  }
  ```

### 2.3、编写代码

![1608652770341](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d08.png)

- 实体类

  ```java
  package com.github.subei.pojo;
  // 实体类
  public class User {
      private int id;
      private String name;
      private String pwd;
  
      public int getId() {
          return id;
      }
  
      public void setId(int id) {
          this.id = id;
      }
  
      public String getName() {
          return name;
      }
  
      public void setName(String name) {
          this.name = name;
      }
  
      public String getPwd() {
          return pwd;
      }
  
      public void setPwd(String pwd) {
          this.pwd = pwd;
      }
  
      public User() {
      }
  
      public User(int id, String name, String pwd) {
          this.id = id;
          this.name = name;
          this.pwd = pwd;
      }
  
      @Override
      public String toString() {
          return "User{" +
                  "id=" + id +
                  ", name='" + name + '\'' +
                  ", pwd='" + pwd + '\'' +
                  '}';
      }
  }
  ```
  
- Dao接口

  ```java
  package com.github.subei.dao;
  
  import com.github.subei.pojo.User;
  
  import java.util.List;
  
  public interface UserDao {
      List<User> getUserList();
  }
  ```

- 接口实现类由原来的UserDaoImpl转变为一个 Mapper配置文件.

  ```xml
  <?xml version="1.0" encoding="UTF-8" ?>
  <!DOCTYPE mapper
          PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
          "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
  <!-- namespace=绑定一个对应的Dao/Mapper接口 -->
  <mapper namespace="com.github.subei.dao.UserDao">
  
      <!--select查询语句-->
      <select id="getUserList" resultType="com.github.subei.pojo.User">
         select * from mybatis.user;
     </select>
  
  </mapper>
  ```

### 2.4、测试

![1608708301792](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d15.png)

- junit测试

  ```java
  package com.github.subei.dao;
  
  import com.github.subei.pojo.User;
  import com.github.subei.utils.MybatisUtils;
  import org.apache.ibatis.session.SqlSession;
  import org.junit.Test;
  
  import java.util.List;
  
  public class UserDaoTest {
      @Test
      public void test(){
          //第一步：获得SqlSession对象
          SqlSession sqlSession = MybatisUtils.getSqlSession();
  
          //方式一：getMapper
          UserDao userDao = sqlSession.getMapper(UserDao.class);
          List<User> userList = userDao.getUserList();
          
            //方式二：不推荐使用！！！
  //        List<User> userList = sqlSession.selectList("com.github.dao.UserDao.getUserList");
  
  
          for (User user : userList) {
              System.out.println(user);
          }
          
          //关闭SqlSession
          sqlSession.close();
      }
  
  }
  ```

> 可以能会遇到的问题：

1. 配置文件没有注册
2. 绑定接口错误。
3. 方法名不对
4. 返回类型不对
5. Maven导出资源问题

## day01学习中遇到的报错

### 报错：error:java :程序包org.apache.ibatis.io不存在。:程序包org.apache.ibatis.session不存在

![1608706240734](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d09.png)

- IDEA 2020.1版本报错，解决方法： **出现这个问题根本办法是修改一下maven home directory为Bundled (Maven 3)，并修改为默认给的路径，不要用自己的maven本地仓库的路径，用idea 2020 内置的maven** ，至于具体原因，可能是IDEA2020的新功能吧，但能解决！！！
- 第一步，修改maven地址：

![1608707146251](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d11.png)

- 第二步，重装仓库文件：

![1608707274849](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d12.png)

- 第三步，运行程序：

![1608707032839](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d10.png)

> 如果还不行，参考这个解决：https://blog.csdn.net/bin_bujiangjiu/article/details/106727561

### 报错：org.apache.ibatis.binding.BindingException: Type interface com.github.subei.dao.UserDao is not known to the MapperRegistry.

![1608707449908](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d13.png)

- 解决方法：在mybatis-config.xm文件中添加如下代码：

```xml
    <!--  每一个Mapper.xml都需要在mybatis核心配置文件中注册！  -->
    <mappers>
        <mapper resource="com/github/subei/dao/UserMapper.xml"/>
    </mappers>
```

> **MapperRegistry是什么？**

- 核心配置文件中注册 mappers

### 报错：java.lang.ExceptionInInitializerError at com.kuang.dao.UserDaoTest.test(UserDaoTest.java:15)

![image-20210818114513289](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d14.png)

- 解决方法：在最外层的pom.xml文件中，添加如下代码：

```xml
<!--在build中配置resources，来防止我们资源导出失败的问题-->
    <build>
        <resources>
            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```

- 添加后，==如果还是报错，请删除所有.xml文件中的中文注释！==具体原因不明。

![image-20210818115432254](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/d16)

> 参考博文①：https://blog.csdn.net/bin_bujiangjiu/article/details/106784700
>
> 参考博客②：https://blog.csdn.net/bin_bujiangjiu/article/details/106727561
>
> 感谢大佬的博客帮我解决问题，节省时间！！！

## 3、CRUD

### 1、namespace

- namespace中的包名要和 Dao/mapper 接口的包名一致！

### 2、select

选择，查询语句;

- id : 就是对应的namespace中的方法名；
- resultType：Sql语句执行的返回值！
- parameterType ： 参数类型！

1. 编写接口

   ![1608713416646](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/01.png)
   
   ```java
       // 根据ID查询用户
       User getUserById(int id);
   ```
   
2. 编写对应的mapper中的sql语句

   ```java
       <select id="getUserById" parameterType="int" resultType="com.github.subei.pojo.User">
           select * from mybatis.user where id = #{id};
       </select>
   ```

3. 测试

   ```java
       @Test
       public void getUserById(){
           //第一步：获得SqlSession对象
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           User user = mapper.getUserById(1);
           System.out.println(user);
   
           //关闭SqlSession
           sqlSession.close();
       }
   ```

![1608713437615](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/02.png)

### 3、Insert

1. 编写接口

```java
    // insert一个用户
    int addUser(User user);
```

2. 编写对应的mapper中的sql语句

```xml
    <!--对象中的属性，可以直接取出来-->
    <insert id="addUser" parameterType="com.github.subei.pojo.User">
        insert into mybatis.user (id, name, pwd) values (#{id},#{name},#{pwd});
    </insert>
```

3. 测试

```java
	// 增删改需要提交事务
    @Test
    public void addUser(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res = mapper.addUser(new User(4,"好多鱼","421563"));
        if(res>0){
            System.out.println("插入成功！！");
        }

        //提交事务
        sqlSession.commit();

        //关闭SqlSession
        sqlSession.close();
    }
```

![1608714339417](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/03.png)


### 4、update

```java
    // 修改用户
    int updateUser(User user);


    <update id="updateUser" parameterType="com.github.subei.pojo.User">
        update mybatis.user set name=#{name},pwd=#{pwd}  where id = #{id};
    </update>

        
    @Test
    public void updateUser(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res = mapper.updateUser(new User(4,"好多鱼","233333"));
        if(res>0){
            System.out.println("上传成功！！");
        }

        //提交事务
        sqlSession.commit();

        //关闭SqlSession
        sqlSession.close();
    }
```

![1608714691825](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/04.png)

### 5、Delete

```java
    // 删除用户
    int deleteUser(int id);


    <delete id="deleteUser" parameterType="int">
        delete from mybatis.user where id = #{id};
    </delete>
        
        
    @Test
    public void deleteUser(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        int res = mapper.deleteUser(4);
        if(res>0){
            System.out.println("删除成功！！");
        }

        //提交事务
        sqlSession.commit();

        //关闭SqlSession
        sqlSession.close();
    }        
```

![1608714976370](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/05.png)

> 注意点：

- 增删改需要提交事务！

### 6、分析错误

- 标签不要匹配错
- resource 绑定mapper，需要使用路径！
- 程序配置文件必须符合规范！
- NullPointerException，没有注册到资源!
- 输出的xml文件中存在中文乱码问题！
- maven资源没有导出问题！

### 7、万能Map

> 假设，我们的实体类，或者数据库中的表，字段或者参数过多，我们应当考虑使用Map！

```java
    // 万能的Map
    int addUser2(Map<String,Object> map);
```

```xml
    <!-- 对象中的属性，可以直接取出来传递map的key -->
    <insert id="addUser2" parameterType="map">
        insert into mybatis.user (id, pwd) values (#{userid},#{passWord});
    </insert>
```

```java
    @Test
    public void addUser2(){
        //第一步：获得SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("userid",5);
        map.put("passWord","2222333");

        mapper.addUser2(map);
        
        //关闭SqlSession
        sqlSession.close();
    }
```

![1608716791797](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/06.png)

- Map传递参数，直接在sql中取出key即可！    【parameterType="map"】
- 对象传递参数，直接在sql中取对象的属性即可！【parameterType="Object"】
- 只有一个基本类型参数的情况下，可以直接在sql中取到！
- 多个参数用Map，**或者注解！**

### 8、思考题

> 模糊查询怎么写？这是两种方式：

1. Java代码执行的时候，传递通配符 % %

   ```java
       List<User> getUserLike(String value);
   
   
       <select id="getUserLike" resultType="com.github.subei.pojo.User">
           select * from mybatis.user where name like #{value };
       </select>
           
           
       @Test
       public void getUserLike(){
           //第一步：获得SqlSession对象
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
           UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
           List<User> userList = mapper.getUserLike("%欣%");
   
           for (User user:userList){
               System.out.println(user);
           }
   
           //关闭SqlSession
           sqlSession.close();
       }
   ```

![1608718153218](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/07.png)


2. 在sql拼接中使用通配符！

   ```java
   select * from mybatis.user where name like "%"#{value}"%"
   ```

## 4、配置解析

### 1、核心配置文件

- mybatis-config.xml

- MyBatis 的配置文件包含了会深深影响 MyBatis 行为的设置和属性信息。 

  ```javascript
  configuration（配置）
  properties（属性）
  settings（设置）
  typeAliases（类型别名）
  typeHandlers（类型处理器）
  objectFactory（对象工厂）
  plugins（插件）
  environments（环境配置）
  environment（环境变量）
  transactionManager（事务管理器）
  dataSource（数据源）
  databaseIdProvider（数据库厂商标识）
  mappers（映射器）
  ```

### 2、环境配置（environments）

- MyBatis 可以配置成适应多种环境；

- **不过要记住：尽管可以配置多个环境，但每个 SqlSessionFactory 实例只能选择一种环境。**

- 学会使用配置多套运行环境！

- Mybatis默认的事务管理器就是 JDBC  ， 连接池 ： POOLED。

### 3、属性（properties）

> 我们可以通过properties属性来实现引用配置文件

- 这些属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递。【db.properties】

![1569656528134](Mybatis课堂笔记.assets/1569656528134.png)

- 编写一个配置文件，db.properties

```properties
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/mybatis?useSSL=true&amp;serverTimezone=UTC&amp;useUnicode=true&characterEncoding=utf8
username=root
password=root
```

- 在核心配置文件中映入

![1608720476010](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/09.png)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--configuration核心配置文件-->
<configuration>

    <!--  引入外部文件  -->
    <properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="pwd" value="root"/>
    </properties>

    <environments default="development">
        
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${pwd}"/>
            </dataSource>
        </environment>
        
    </environments>

    <!--  每一个Mapper.xml都需要在mybatis核心配置文件中注册！  -->
    <mappers>
        <mapper resource="com/github/subei/dao/UserMapper.xml"/>
    </mappers>

</configuration>
```

- 可以直接引入外部文件；
- 可以在其中增加一些属性配置；
- 如果两个文件有同一个字段，优先使用外部配置文件的！

### 4、类型别名（typeAliases）

- 类型别名是为 Java 类型设置一个短的名字。
- 存在的意义仅在于用来减少类完全限定名的冗余。

![1608720944088](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/10.png)

```xml
    <!-- 可以给实体类起别名 -->
    <typeAliases>
        <typeAlias type="com.github.subei.pojo.User" alias="User"/>
    </typeAliases>
```

![1608720997247](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/11.png)

![1608721021406](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/12.png)

- 也可以指定一个包名，MyBatis 会在包名下面搜索需要的 Java Bean，比如：

- 扫描实体类的包，它的==默认别名就为这个类的 类名==，首字母小写！

```xml
    <!--  可以给实体类起别名  -->
    <typeAliases>
        <package name="com.github.subei.pojo"/>
    </typeAliases>
```

![1608721119951](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/13.png)

> 在实体类比较少的时候，使用第一种方式。

> 如果实体类十分多，建议使用第二种。

> 第一种可以DIY别名，第二种则`不行`，如果非要改，需要在实体上增加注解。

![1608721201737](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day02/14.png)

```java
@Alias("user")
public class User {}
```

### 5、设置

> 这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。 

![1569657659080](Mybatis课堂笔记.assets/1569657659080.png)

![1569657672791](Mybatis课堂笔记.assets/1569657672791.png)

### 6、其他配置

- [typeHandlers（类型处理器）](https://mybatis.org/mybatis-3/zh/configuration.html#typeHandlers)
- [objectFactory（对象工厂）](https://mybatis.org/mybatis-3/zh/configuration.html#objectFactory)
- plugins插件
  - mybatis-generator-core
  - mybatis-plus
  - 通用mapper

### 7、映射器（mappers）

> MapperRegistry：注册绑定我们的Mapper文件；

- 方式一： 【推荐使用】

```xml
    <!--  每一个Mapper.xml都需要在mybatis核心配置文件中注册！  -->
    <mappers>
        <mapper resource="com/github/subei/dao/UserMapper.xml"/>
    </mappers>
```

- 方式二：使用class文件绑定注册

```xml
    <!--  每一个Mapper.xml都需要在mybatis核心配置文件中注册！  -->
    <mappers>
        <mapper class="com.github.subei.dao.UserMapper"/>
    </mappers>
```

> 注意点：

- 接口和他的Mapper配置文件必须同名！
- 接口和他的Mapper配置文件必须在同一个包下！

---

- 方式三：使用扫描包进行注入绑定

```xml
<!--每一个Mapper.XML都需要在Mybatis核心配置文件中注册！-->
<mappers>
    <package name="com.github.subei.dao"/>
</mappers>
```

> 注意点：

- 接口和他的Mapper配置文件必须同名！
- 接口和他的Mapper配置文件必须在同一个包下！

> 练习时间：

- 将数据库配置文件外部引入；
- 实体类别名；
- 保证UserMapper 接口 和 UserMapper .xml 改为一致！并且放在同一个包下！

### 8、生命周期和作用域

![1569660357745](Mybatis课堂笔记.assets/1569660357745.png)

> 生命周期，和作用域，是至关重要的，因为错误的使用会导致非常严重的**并发问题**。

**SqlSessionFactoryBuilder：**

- 一旦创建了 SqlSessionFactory，就不再需要它了。
- 局部变量。

**SqlSessionFactory：**

- 说白了就是可以想象为 ：数据库连接池；
- SqlSessionFactory 一旦被创建就应该在应用的运行期间一直存在，**没有任何理由丢弃它或重新创建另一个实例。** 
- 因此 SqlSessionFactory 的最佳作用域是应用作用域。 
- 最简单的就是使用**单例模式**或者静态单例模式。

**SqlSession**

- 连接到连接池的一个请求！
- SqlSession 的实例不是线程安全的，因此是不能被共享的，所以它的最佳的作用域是请求或方法作用域。
- 用完之后需要赶紧关闭，否则资源被占用！

![1569660737088](Mybatis课堂笔记.assets/1569660737088.png)

> 这里面的每一个Mapper，就代表一个具体的业务！

---

## 5、解决属性名和字段名不一致的问题

### 1、 问题

- 数据库中的字段

![1569660831076](Mybatis课堂笔记.assets/1569660831076.png)

- 新建一个项目，拷贝之前的，测试实体类字段不一致的情况：

```java
package com.github.subei.pojo;

// 实体类
public class User {
    private int id;
    private String name;
    private String password;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
```

- 测试出现问题：

![1608792010702](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1569661145806.png)

```xml
//    select * from mybatis.user where id = #{id}
// 类型处理器
//    select id,name,pwd from mybatis.user where id = #{id}
```

> 解决方法：

- 起别名

  ```xml
  <select id="getUserById" resultType="com.kuang.pojo.User">
      select id,name,pwd as password from mybatis.user where id = #{id}
  </select>
  ```

![1608792427202](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/01.png)

### 2、resultMap

- 结果集映射：

```java
id   name   pwd
id   name   password
```

```xml
    <!-- 结果集映射 -->
    <resultMap id="userMap" type="User">
        <!-- column数据库中的字段，property实体类中的属性 -->
        <result column="id" property="id"/>
        <result column="name" property="name"/>
        <result column="pwd" property="password"/>
    </resultMap>
    
    <select id="getUserById" parameterType="int" resultMap="userMap">
        select id,name,pwd from mybatis.user where id = #{id};
    </select>
```

![1608792598939](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/02.png)

- `resultMap` 元素是 MyBatis 中最重要最强大的元素。
- ResultMap 的设计思想是，`对于简单的语句根本不需要配置显式的结果映射，而对于复杂一点的语句只需要描述它们的关系就行了`。
- `ResultMap` 最优秀的地方在于，虽然你已经对它相当了解了，但是根本就不需要显式地用到他们。

## 6、日志

### 6.1、日志工厂

> 如果一个数据库操作，出现了异常，我们需要排错。日志就是最好的助手！

- 曾经：sout 、debug

- 现在：日志工厂！

![1569892155104](Mybatis课堂笔记.assets/1569892155104.png)

- SLF4J 

- LOG4J  【掌握】
- LOG4J2
-  JDK_LOGGING
- COMMONS_LOGGING
- STDOUT_LOGGING   【掌握】
- NO_LOGGING

---

- 在Mybatis中具体使用那个一日志实现，在设置中设定！

- **STDOUT_LOGGING标准日志输出**！

- 在mybatis核心配置文件中，配置我们的日志！

![1608794441613](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/03.png)

```xml
<settings>
    <setting name="logImpl" value="STDOUT_LOGGING"/>
</settings>
```

![1608794709969](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1569892595060.png)

### 6.2、Log4j

> 什么是Log4j？

- Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件
- 我们也可以控制每一条日志的输出格式；
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
- 通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。

![1608795629756](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/04.png)

1. 先导入log4j的包

   ```xml
       <dependencies>
           <!-- https://mvnrepository.com/artifact/log4j/log4j -->
           <dependency>
               <groupId>log4j</groupId>
               <artifactId>log4j</artifactId>
               <version>1.2.17</version>
           </dependency>
       </dependencies>
   ```

2. log4j.properties

   ```properties
   #将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
   log4j.rootLogger=DEBUG,console,file
   
   #控制台输出的相关设置
   log4j.appender.console = org.apache.log4j.ConsoleAppender
   log4j.appender.console.Target = System.out
   log4j.appender.console.Threshold=DEBUG
   log4j.appender.console.layout = org.apache.log4j.PatternLayout
   log4j.appender.console.layout.ConversionPattern=[%c]-%m%n
   
   #文件输出的相关设置
   log4j.appender.file = org.apache.log4j.RollingFileAppender
   log4j.appender.file.File=./log/github.log
   log4j.appender.file.MaxFileSize=10mb
   log4j.appender.file.Threshold=DEBUG
   log4j.appender.file.layout=org.apache.log4j.PatternLayout
   log4j.appender.file.layout.ConversionPattern=[%p][%d{yy-MM-dd}][%c]%m%n
   
   #日志输出级别
   log4j.logger.org.mybatis=DEBUG
   log4j.logger.java.sql=DEBUG
   log4j.logger.java.sql.Statement=DEBUG
   log4j.logger.java.sql.ResultSet=DEBUG
   log4j.logger.java.sql.PreparedStatement=DEBUG
   ```

3. 配置log4j为日志的实现

   ```xml
   <settings>
       <setting name="logImpl" value=""/>
   </settings>
   
   
   <!-- 例如： -->
       <settings>
           <setting name="logImpl" value="LOG4J"/>
       </settings>
   ```

4. Log4j的使用！，直接测试运行刚才的查询

   ![1569893505842](Mybatis课堂笔记.assets/1569893505842.png)

**简单使用**

1. 在要使用Log4j 的类中，导入包  import org.apache.log4j.Logger；

2. 日志对象，参数为当前类的class；

   ```java
   static Logger logger = Logger.getLogger(UserDaoTest.class);
   ```

3. 日志级别；

   ```java
       @Test
       public void testLog4j(){
           logger.info("info:进入了testLog4j");
           logger.debug("debug:进入了testLog4j");
           logger.error("error:进入了testLog4j");
       }
   ```

![1608796832679](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/05.png)

![1608797210820](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/06.png)

## 7、分页

> **思考：为什么要分页？**

- 减少数据的处理量。

### 7.1、使用Limit分页

```sql
语法： SELECT * from user limit startIndex,pageSize;
	  SELECT * from user limit 3;  #[0,n]
```

> 使用Mybatis实现分页，核心SQL。

1. 接口；

   ```java
       // 分页
       List<User> getUserByLimit(Map<String,Integer> map);
   ```

2. Mapper.xml；

   ```xml
       <!-- 分页 -->
       <select id="getUserByLimit" parameterType="map" resultMap="userMap">
           select * from  mybatis.user limit #{startIndex},#{pageSize}
       </select>
   ```

3. 测试；

   ```java
   @Test
   public void getUserByLimit(){
   SqlSession sqlSession = MybatisUtils.getSqlSession();
   UserMapper mapper = sqlSession.getMapper(UserMapper.class);
   
   HashMap<String, Integer> map = new HashMap<String, Integer>();
       map.put("startIndex",1);
       map.put("pageSize",2);
   
       List<User> userList =  mapper.getUserByLimit(map);
       for (User user : userList) {
       System.out.println(user);
       }
   
       sqlSession.close();
       }
   
   ```

   ![1608799758404](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/07.png)

### 7.2、RowBounds分页

> 不再使用SQL实现分页。

1. 接口；

   ```java
       // 分页2
       List<User> getUserByRowBounds();
   ```

2. mapper.xml；

   ```xml
       <!-- 分页2 -->
       <select id="getUserByRowBounds" resultMap="userMap">
           select * from  mybatis.user
       </select>
   ```

3. 测试；

   ```java
       @Test
       public void getUserByRowBounds(){
           SqlSession sqlSession = MybatisUtils.getSqlSession();
   
   //      RowBounds实现
           RowBounds rowBounds = new RowBounds(1, 2);
   
   //      通过Java代码层面实现分页
           List<User> userList = sqlSession.selectList("com.github.subei.dao.UserMapper.getUserByRowBounds",null,rowBounds);
   
           for (User user : userList) {
               System.out.println(user);
           }
   
           sqlSession.close();
       }
   ```

   ![1608800248894](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day03/08.png)

### 7.3、分页插件

> 地址：https://mybatis.io/

![1608800691238](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1569896603103.png)

> 了解即可，万一以后公司的架构师，说要使用，你需要知道它是什么东西！

### 关于IDEA自动重置LanguageLevel和JavaCompiler版本的问题

- 使用IDEA时，导入的Maven项目默认的LanguageLevel和JavaCompiler都是1.5，1.5的情况下连最简单的@Override注解都不支持，所以项目可能出现一堆错。

![image-20210822215512267](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/image-20210822215512267.png)

![image-20210822215637749](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/image-20210822215637749.png)

- 如果遇到这类问题，手动次次修改会很麻烦；只需在`最外层的pom.xml文件`中加入如下：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>2.3.2</version>
            <configuration>
                <source>1.8</source>
                <target>1.8</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## 8、使用注解开发

### 8.1、面向接口编程

> 大家之前都学过面向对象编程，也学习过接口，但在真正的开发中，很多时候我们会选择面向接口编程。

- **根本原因 :  ==解耦== , 可拓展 , 提高复用 , 分层开发中 , 上层不用管具体的实现 , 大家都遵守共同的标准 , 使得开发变得容易 , 规范性更好**
- 在一个面向对象的系统中，系统的各种功能是由许许多多的不同对象协作完成的。在这种情况下，各个对象内部是如何实现自己的,对系统设计人员来讲就不那么重要了；
- 而各个对象之间的协作关系则成为系统设计的关键。小到不同类之间的通信，大到各模块之间的交互，在系统设计之初都是要着重考虑的，这也是系统设计的主要工作内容。面向接口编程就是指按照这种思想来编程。

---

> **关于接口的理解**

- 接口从更深层次的理解，应是定义（规范，约束）与实现（名实分离的原则）的分离。
- 接口的本身反映了系统设计人员对系统的抽象理解。
- 接口应有两类：
  - 第一类是对一个个体的抽象，它可对应为一个抽象体(abstract class)；
  - 第二类是对一个个体某一方面的抽象，即形成一个抽象面（interface）；
- 一个体有可能有多个抽象面。抽象体与抽象面是有区别的。

---

> **三个面向区别**：

- 面向对象是指，我们考虑问题时，以对象为单位，考虑它的属性及方法。
- 面向过程是指，我们考虑问题时，以一个具体的流程（事务过程）为单位，考虑它的实现。
- 接口设计与非接口设计是针对复用技术而言的，与面向对象（过程）不是一个问题.更多的体现就是对系统整体的架构。

### 8.2、使用注解开发

![1608803058552](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day04/01.png)

1. 注解在接口上实现；`UserMapper.java`

   ```java
   package com.github.subei.dao;
   
   import com.github.pojo.User;
   import org.apache.ibatis.annotations.Select;
   
   import java.util.List;
   
   public interface UserMapper {
       @Select("select * from user")
       List<User> getUsers();
   }
   ```

2. 需要在核心配置文件中绑定接口！`mybatis-config.xml`

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <configuration>
       <properties resource="db.properties">
           <property name="username" value="root"/>
           <property name="password" value="root"/>
       </properties>
   
       <settings>
           <setting name="logImpl" value="STDOUT_LOGGING"/>
       </settings>
   
       <typeAliases>
           <package name="com.github.subei.pojo"/>
       </typeAliases>
   
       <environments default="development">
           <environment id="development">
               <transactionManager type="JDBC"/>
               <dataSource type="POOLED">
                   <property name="driver" value="${driver}"/>
                   <property name="url" value="${url}"/>
                   <property name="username" value="${username}"/>
                   <property name="password" value="${password}"/>
               </dataSource>
           </environment>
       </environments>
   
       <!--绑定接口-->
       <mappers>
           <mapper class="com.github.subei.dao.UserMapper"/>
       </mappers>
   
   </configuration>
   ```

3. 测试

```java
package com.github.subei.test;

import com.github.subei.dao.UserMapper;
import com.github.subei.pojo.User;
import com.github.subei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        List<User> users = mapper.getUsers();
        for(User user : users){
            System.out.println(user);
        }

        sqlSession.close();
    }
}
```

![1608803093104](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day04/02.png)

- 本质：反射机制实现

- 底层：动态代理！

![1569898830704](Mybatis课堂笔记.assets/1569898830704.png)

> **Mybatis详细的执行流程！**

![1569898830704](Mybatis课堂笔记.assets/Temp.png)

### 8.3、CRUD

- 我们可以在工具类创建的时候实现自动提交事务！

![1608804700116](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day04/03.png)

```java
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession(true);
    }
```

- 编写接口，增加注解。

```java
package com.github.subei.dao;

import com.github.subei.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    @Select("select * from user")
    List<User> getUsers();

    // 方法存在多个参数，所有的参数前面必须加上 @Param("id")注解
    @Select("select * from user where id = #{id}")
    User getUserByID(@Param("id") int id);


    @Insert("insert into user(id,name,pwd) values (#{id},#{name},#{password})")
    int addUser(User user);


    @Update("update user set name=#{name},pwd=#{password} where id = #{id}")
    int updateUser(User user);


    @Delete("delete from user where id = #{uid}")
    int deleteUser(@Param("uid") int id);
}
```

- 测试类，【注意：必须要将接口注册绑定到核心配置文件中！】

```java
package com.github.subei.test;

import com.github.subei.dao.UserMapper;
import com.github.subei.pojo.User;
import com.github.subei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class UserMapperTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        // 底层主要应用反射
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        mapper.addUser(new User(5,"hello","123456"));

        sqlSession.close();
    }
}

/*
        List<User> users = mapper.getUsers();
        for(User user : users){
            System.out.println(user);
        }

        User userByID = mapper.getUserByID(1);
        System.out.println(userByID);

        mapper.addUser(new User(5,"hello","123456"));

        mapper.updateUser(new User(5,"to","213213"));
     
        mapper.deleteUser(5);
 */
```

![1608805274406](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day04/04.png)

> **关于@Param() 注解**

- 基本类型的参数或者String类型，需要加上。
- 引用类型不需要加。
- 如果只有一个基本类型的话，可以忽略，但是建议大家都加上！
- 我们在SQL中引用的就是我们这里的 @Param() 中设定的属性名！

> **#{}与${} 区别**

- 参考链接：https://blog.csdn.net/kaixuansui/article/details/88637311

- #{} 解析为一个 JDBC 预编译语句（prepared statement）的参数标记符，一个 #{ } 被解析为一个参数占位符；而${}仅仅为一个纯碎的 string 替换，在动态 SQL 解析阶段将会进行变量替换。

* #{} 解析之后会将String类型的数据自动加上引号，其他数据类型不会；而${} 解析之后是什么就是什么，他不会当做字符串处理。

* #{} 很大程度上可以防止SQL注入（SQL注入是发生在编译的过程中，因为恶意注入了某些特殊字符，最后被编译成了恶意的执行操作）；而${} 主要用于SQL拼接的时候，有很大的SQL注入隐患。
* 在某些特殊场合下只能用${}，不能用#{}。
  * 例如：在使用排序时ORDER BY $ {id}，如果使用#{id}，则会被解析成ORDER BY “id”,这显然是一种错误的写法。

## 9、Lombok

```java
Project Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java.
Never write another getter or equals method again, with one annotation your class has a fully featured builder, Automate your logging variables, and much more.
```

- java library
- plugs
- build tools
- with one annotation your class

---

> 使用步骤：

1. 在IDEA中安装Lombok插件！

![1608810432001](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day04/05.png)

2. 在项目中导入lombok的jar包

   ```xml
       <dependencies>
           <!-- https://mvnrepository.com/artifact/org.projectlombok/lombok -->
           <dependency>
               <groupId>org.projectlombok</groupId>
               <artifactId>lombok</artifactId>
               <version>1.18.12</version>
               <scope>provided</scope>
           </dependency>
       </dependencies>
   ```

3. 在实体类上加注解即可！

   ```java
   @Data
   @AllArgsConstructor
   @NoArgsConstructor
   ```

![1608810771457](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day04/06.png)

---

```java
@Getter and @Setter
@FieldNameConstants
@ToString
@EqualsAndHashCode
@AllArgsConstructor, @RequiredArgsConstructor and @NoArgsConstructor
@Log, @Log4j, @Log4j2, @Slf4j, @XSlf4j, @CommonsLog, @JBossLog, @Flogger
@Data
@Builder
@Singular
@Delegate
@Value
@Accessors
@Wither
@SneakyThrows
```

- 说明：

```java
@Data：无参构造，get、set、tostring、hashcode，equals
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
```

## 10、多对一处理

> 多对一：

![1569909163944](Mybatis课堂笔记.assets/1569909163944.png)

- 多个学生，对应一个老师
- 对于学生这边而言，  **关联** ..  多个学生，关联一个老师  【多对一】
- 对于老师而言， **集合** ， 一个老师，有很多学生 【一对多】

![1569909422471](Mybatis课堂笔记.assets/1569909422471.png)

> SQL：

```sql
CREATE TABLE `teacher` ( `id` INT ( 10 ) NOT NULL, `name` VARCHAR ( 30 ) DEFAULT NULL, PRIMARY KEY ( `id` ) ) ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO teacher ( `id`, `name` )
VALUES
	( 1, '秦老师' );
	
CREATE TABLE `student` (
	`id` INT ( 10 ) NOT NULL,
	`name` VARCHAR ( 30 ) DEFAULT NULL,
	`tid` INT ( 10 ) DEFAULT NULL,
	PRIMARY KEY ( `id` ),
	KEY `fktid` ( `tid` ),
	CONSTRAINT `fktid` FOREIGN KEY ( `tid` ) REFERENCES `teacher` ( `id` ) 
) ENGINE = INNODB DEFAULT CHARSET = utf8;

INSERT INTO `student` ( `id`, `name`, `tid` )
VALUES
	( '1', '小明', '1' );
	
INSERT INTO `student` ( `id`, `name`, `tid` )
VALUES
	( '2', '小红', '1' );
	
INSERT INTO `student` ( `id`, `name`, `tid` )
VALUES
	( '3', '小张', '1' );
	
INSERT INTO `student` ( `id`, `name`, `tid` )
VALUES
	( '4', '小李', '1' );
	
INSERT INTO `student` ( `id`, `name`, `tid` )
VALUES
	( '5', '小王', '1' );
```

### 10.1测试环境搭建

1. 导入lombok
2. 新建实体类 Teacher，Student

```java
package com.github.subei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private int id;
    private String name;

    // 学生需要关联一个老师
    private Teacher teacher;
}
```

```java
package com.github.subei.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private int id;
    private String name;
}
```

3. 建立Mapper接口

```java
package com.github.subei.dao;

import com.github.pojo.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface TeacherMapper {

    @Select("select * from teacher where id=#{tid}")
    Teacher getTeacher(@Param("tid") int id);
}
```

4. 建立Mapper.XML文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.github.subei.dao.TeacherMapper">

</mapper>
```

5. 在核心配置文件中绑定注册我们的Mapper接口或者文件！【方式很多，随心选】`mybatis-config.xml`

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </properties>

    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>

    <typeAliases>
        <package name="com.github.subei.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper class="com.github.subei.dao.StudentMapper"/>
        <mapper class="com.github.subei.dao.TeacherMapper"/>
    </mappers>

</configuration>
```

6. 测试查询是否能够成功！

```java
import com.github.subei.dao.TeacherMapper;
import com.github.subei.pojo.Teacher;
import com.github.subei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;

public class MyTest {
    public static void main(String[] args) {
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        System.out.println(teacher);
        sqlSession.close();
    }

}
```



![1608904630315](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/01.png)

### 10.2按照查询嵌套处理

- StudentMapper接口

```java
package com.github.subei.dao;

import com.github.subei.pojo.Student;

import java.util.List;

public interface StudentMapper {
    // 查询所有的学生的信息，以及对应老师的信息
    public List<Student> getStudent();

}
```

- StudentMapper.xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--configuration核心配置文件-->
<mapper namespace="com.github.subei.dao.StudentMapper">

    <select id="getStudent" resultType="Student">
        select * from student
    </select>

</mapper>
```

- 测试类

```java
import com.github.subei.dao.StudentMapper;
import com.github.subei.dao.TeacherMapper;
import com.github.subei.pojo.Student;
import com.github.subei.pojo.Teacher;
import com.github.subei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void testStudent(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> studentList = mapper.getStudent();
        for(Student student : studentList){
            System.out.println(student);
        }
        sqlSession.close();
    }
}
```

![1608964584730](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/02.png)

- 修改xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--configuration核心配置文件-->
<mapper namespace="com.github.subei.dao.StudentMapper">

    <!--
    思路:
        1. 查询所有的学生信息
        2. 根据查询出来的学生的tid，寻找对应的老师！  子查询
    -->

    <select id="getStudent" resultMap="StudentTeacher">
        select * from student;
    </select>

    <resultMap id="StudentTeacher" type="Student">
        <result property="id" column="id"/>
        <result property="name" column="name"/>
        <!-- 复杂的属性，需要单独处理 
        对象： association 集合： collection 
        -->
        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>

    <select id="getTeacher" resultType="Teacher">
        select * from teacher where id = #{id};
    </select>

</mapper>
```

![1608964632300](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/03.png)

### 10.3按照结果嵌套处理

```xml
<!--按照结果嵌套处理-->
<select id="getStudent2" resultMap="StudentTeacher2">
    select s.id sid,s.name sname,t.name tname
    from student s,teacher t
    where s.tid = t.id;
</select>

<resultMap id="StudentTeacher2" type="Student">
    <result property="id" column="sid"/>
    <result property="name" column="sname"/>
    <association property="teacher" javaType="Teacher">
        <result property="name" column="tname"/>
    </association>
</resultMap>
```

![1608970555453](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/04.png)

> 回顾Mysql 多对一查询方式：

- 子查询
- 联表查询

## 11、一对多处理

- 比如：一个老师拥有多个学生！

- 对于老师而言，就是一对多的关系!

### 11.1环境搭建

1. 环境搭建，和刚才一样。

![1608970969057](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/05.png)

**实体类**

```java
package com.github.subei.pojo;

import lombok.Data;

@Data
public class Student {
    private int id;
    private String name;
    private int tid;
}
```

```java
package com.github.subei.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Teacher {
    private int id;
    private String name;

    // 一个老师拥有多个学生
    private List<Student> students;
}
```

- 测试一下，编写TeacherMapper接口

```java
package com.github.subei.dao;

import com.github.subei.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {

    // 获取老师
    List<Teacher> getTeacher();
}
```

- 编写xml文档

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--configuration核心配置文件-->
<mapper namespace="com.github.subei.dao.TeacherMapper">

    <select id="getTeacher" resultType="Teacher">
        select * from mybatis.teacher;
    </select>
</mapper>
```

- 编写测试文档

```java
import com.github.subei.dao.TeacherMapper;
import com.github.subei.pojo.Teacher;
import com.github.subei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void FTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        List<Teacher> teacherList = mapper.getTeacher();
        for (Teacher teacher : teacherList){
            System.out.println(teacher);
        }
        sqlSession.close();
    }
}
```

![1608975919254](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/07.png)

### 11.2按照结果嵌套处理

- 修改接口文档

```java
package com.github.subei.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.subei.pojo.Teacher;

import java.util.List;

public interface TeacherMapper {

    // 获取老师
   // List<Teacher> getTeacher();

    // 获取指定老师下的所有的学生及老师信息
    Teacher getTeacher(@Param("tid") int id);
}
```

- 修改xml文档

```xml
    <!--按结果嵌套查询-->
    <select id="getTeacher" resultMap="TeacherStudent">
        select s.id sid, s.name sname, t.name tname,t.id tid
        from student s,teacher t
        where s.tid = t.id and t.id = #{tid};
    </select>

    <resultMap id="TeacherStudent" type="Teacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>
        <!--复杂的属性，需要单独处理
        对象： association 集合： collection
        javaType="" 指定属性的类型！
        集合中的泛型信息，我们使用ofType获取
        -->
        <collection property="students" ofType="Student">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="tid"/>
        </collection>
    </resultMap>
```

- 测试文档

```java
import com.github.subei.dao.TeacherMapper;
import com.github.subei.pojo.Teacher;
import com.github.subei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class MyTest {
    @Test
    public void FTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        System.out.println(teacher);
        sqlSession.close();
    }
}
```

![1608976508619](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/08.png)

### 11.3按照查询嵌套处理

```java
    Teacher getTeacher2(@Param("tid") int id);
```

```xml
<select id="getTeacher2" resultMap="TeacherStudent2">
    select * from mybatis.teacher where id = #{tid}
</select>

<resultMap id="TeacherStudent2" type="Teacher">
    <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTeacherId" column="id"/>
</resultMap>

<select id="getStudentByTeacherId" resultType="Student">
    select * from mybatis.student where tid = #{tid}
</select>
```

```java
    @Test
    public void TTest(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        TeacherMapper mapper = sqlSession.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher2(1);
        System.out.println(teacher);
        sqlSession.close();
    }
```

![1608993005927](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day05/09.png)

### 小结

1. 关联 - association   【多对一】
2. 集合 - collection   【一对多】
3. javaType    &   ofType
   1. JavaType  用来指定实体类中属性的类型
   2. ofType  用来指定映射到List或者集合中的 pojo类型，泛型中的约束类型！

---

> 注意点：

- 保证SQL的可读性，尽量保证通俗易懂！
- 注意一对多和多对一中，属性名和字段的问题！
- 如果问题不好排查错误，可以使用日志 ， 建议使用 Log4j！

> **慢SQL       1s        1000s**      

> 面试高频：

- Mysql引擎
- InnoDB底层原理
- 索引
- 索引优化！

## 12、动态 SQL

> ==**什么是动态SQL：动态SQL就是指根据不同的条件生成不同的SQL语句**==

- 利用动态 SQL 这一特性可以彻底摆脱这种痛苦。

```java
动态 SQL 元素和 JSTL 或基于类似 XML 的文本处理器相似。
在 MyBatis 之前的版本中，有很多元素需要花时间了解。
MyBatis 3 大大精简了元素种类，现在只需学习原来一半的元素便可。
   MyBatis 采用功能强大的基于 OGNL 的表达式来淘汰其它大部分元素。

	if
	choose (when, otherwise)
	trim (where, set)
	foreach
```

### 搭建环境

```sql
CREATE TABLE `blog` (
	`id` VARCHAR ( 50 ) NOT NULL COMMENT '博客id',
	`title` VARCHAR ( 100 ) NOT NULL COMMENT '博客标题',
	`author` VARCHAR ( 30 ) NOT NULL COMMENT '博客作者',
	`create_time` datetime NOT NULL COMMENT '创建时间',
`views` INT ( 30 ) NOT NULL COMMENT '浏览量' 
) ENGINE = INNODB DEFAULT CHARSET = utf8;
```

> 创建一个基础工程：

- 导包。
- IDutil工具类

```java
package com.github.subei.utils;

import java.util.UUID;

public class IUtils {
    public static String genId(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
```

- 编写实体类

```java
package com.github.subei.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
    private String id;
    private String title;
    private String author;
    private Date createTime;
    private int views;
}
```

- 编写Mapper接口及xml文件

![1608996349180](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/01.png)

```java
package com.github.dao;

import com.github.subei.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    // 新增一个博客
    int addBlog(Blog blog);
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.github.dao.BlogMapper">

    <insert id="addBlog" parameterType="blog">
        insert into blog (id, title, author, create_time, views)
        values (#{id},#{title},#{author},#{createTime},#{views});
    </insert>

</mapper>
```

- mybatis核心配置文件

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="db.properties">
        <property name="username" value="root"/>
        <property name="password" value="root"/>
    </properties>

    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
    </settings>

    <typeAliases>
        <package name="com.github.subei.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper class="com.github.subei.dao.BlogMapper"/>
    </mappers>

</configuration>
```

- 测试类

```java
import com.github.subei.dao.BlogMapper;
import com.github.subei.pojo.Blog;
import com.github.subei.utils.IUtils;
import com.github.subei.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class MyTest {
    @Test
    public void addInitBlog(){
        SqlSession session = MybatisUtils.getSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = new Blog();

        blog.setId(IUtils.genId());
        blog.setTitle("Mybatis如此简单");
        blog.setAuthor("功夫河粉");
        blog.setCreateTime(new Date());
        blog.setViews(998);
        mapper.addBlog(blog);

        blog.setId(IUtils.genId());
        blog.setTitle("Java如此困难");
        blog.setCreateTime(new Date());
        blog.setViews(998);
        mapper.addBlog(blog);

        blog.setId(IUtils.genId());
        blog.setTitle("Spring如此困难");
        blog.setCreateTime(new Date());
        blog.setViews(998);
        mapper.addBlog(blog);

        blog.setId(IUtils.genId());
        blog.setTitle("微服务如此困难");
        blog.setCreateTime(new Date());
        blog.setViews(998);
        mapper.addBlog(blog);

        session.close();
    }
}
```

![image-20210825182217080](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/image-20210825182217080.png)

- 接口文件

```java
    // 查询博客
    List<Blog> queryBlogIF(Map map);
```

- xml文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--configuration核心配置文件-->
<mapper namespace="com.github.subei.dao.BlogMapper">

    <select id="queryBlogIF" parameterType="map" resultType="blog">
        select * from mybatis.blog where 1=1
        <if test="title != null">
            and title = #{title}
        </if>
        <if test="author != null">
            and author = #{author}
        </if>
    </select>
    
</mapper>
```

- 测试类

```java
    @Test
    public void IFTest(){
        SqlSession session = MybatisUtils.getSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        HashMap map = new HashMap();
        map.put("title","java如此困难");

        List<Blog> blogs = mapper.queryBlogIF(map);
        for(Blog blog : blogs){
            System.out.println(blog);
        }

        session.close();
    }
```

![1609081717886](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/02.png)

### choose (when, otherwise)

> 传入了 “title” 就按 “title” 查找，传入了 “author” 就按 “author” 查找的情形。若两者都没有传入，就返回标记为 featured 的 BLOG（这可能是管理员认 为，与其返回大量的无意义随机 Blog，还不如返回一些由管理员精选的 Blog）。

```java
    List<Blog> queryBlogChoose(Map map);
```

```xml
    <select id="queryBlogChoose" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <choose>
                <when test="title != null">
                    title = #{title}
                </when>
                <when test="author != null">
                    and author = #{author}
                </when>
                <otherwise>
                    and views = #{views}
                </otherwise>
            </choose>
        </where>
    </select>
```

```java
    @Test
    public void chooseTest(){
        SqlSession session = MybatisUtils.getSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        HashMap map = new HashMap();
        // map.put("title","java如此困难");
        map.put("views",998);

        List<Blog> blogs = mapper.queryBlogIF(map);
        for(Blog blog : blogs){
            System.out.println(blog);
        }

        session.close();
    }
```

![1609083145906](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/03.png)

### trim (where,set)

```java
    // 选择查询
    List<Blog> queryBlogChoose(Map map);

    // 修改博客
    int updateBlog(Map map);
```

```xml
    <select id="queryBlogChoose" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <if test="title != null">
                title = #{title}
            </if>
            <if test="author != null">
                and author = #{author}
            </if>
        </where>
    </select>
```

```xml
<update id="updateBlog" parameterType="map">
    update mybatis.blog
    <set>
        <if test="title != null">
            title = #{title},
        </if>
        <if test="author != null">
            author = #{author}
        </if>
    </set>
    where id = #{id}
</update>
```

```java
    @Test
    public void chooseTest(){
        SqlSession session = MybatisUtils.getSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        HashMap map = new HashMap();
        // map.put("title","java如此困难");
        map.put("views",998);

        List<Blog> blogs = mapper.queryBlogIF(map);
        for(Blog blog : blogs){
            System.out.println(blog);
        }

        session.close();
    }

    @Test
    public void updateBlog(){
        SqlSession session = MybatisUtils.getSqlSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);

        HashMap map = new HashMap();
        map.put("title","MybatisPlus如此困难");
        map.put("id","3690");

        mapper.updateBlog(map);

        session.close();
    }
```

![1609084387796](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/04.png)

- ==**所谓的动态SQL，本质还是SQL语句 ， 只是我们可以在SQL层面，去执行一个逻辑代码**==。

### SQL片段

- 有的时候，我们可能会将一些功能的部分抽取出来，方便复用！

1. 使用SQL标签抽取公共的部分；

   ```xml
   <sql id="if-title-author">
       <if test="title != null">
           title = #{title}
       </if>
       <if test="author != null">
           and author = #{author}
       </if>
   </sql>
   ```

2. 在需要使用的地方使用Include标签引用即可；

   ```xml
   <select id="queryBlogIF" parameterType="map" resultType="blog">
       select * from mybatis.blog
       <where>
           <include refid="if-title-author"></include>
       </where>
   </select>
   ```

   ![1609085102769](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/05.png)

> 注意事项：

- 最好基于单表来定义SQL片段！
- 不要存在where标签；

### Foreach

```sql
select * from user where 1=1 and 

  <foreach item="id" collection="ids"
      open="(" separator="or" close=")">
        #{id}
  </foreach>

(id=1 or id=2 or id=3)
```

![1569979229205](Mybatis课堂笔记.assets/1569979229205.png)

![1609085923594](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/06.png)

```xml
    <!--
        select * from mybatis.blog where 1=1 and (id=1 or id = 2 or id=3)
        现在传递一个万能的map ， 这map中可以存在一个集合！
    -->
    <select id="queryBlogForeach" parameterType="map" resultType="blog">
        select * from mybatis.blog
        <where>
            <foreach collection="ids" item="id" open="and (" close=")" separator="or">
                id = #{id}
            </foreach>
        </where>
    </select>
```

![1609086393217](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/day06/07.png)

- ==动态SQL就是在拼接SQL语句，我们只要保证SQL的正确性，按照SQL的格式，去排列组合就可以了==

> 建议：

- 现在Mysql中写出完整的SQL,再对应的去修改成为我们的动态SQL实现通用即可！

## 13、缓存 （了解）

### 13.1、简介

```java
查询  ：  连接数据库 ，耗资源！
	一次查询的结果，给他暂存在一个可以直接取到的地方！--> 内存 ： 缓存
	
我们再次查询相同数据的时候，直接走缓存，就不用走数据库了
```

1. 什么是缓存 [ Cache ]？
   - 存在内存中的临时数据。
   - 将用户经常查询的数据放在缓存（内存）中，用户去查询数据就不用从磁盘上(关系型数据库数据文件)查询，从缓存中查询，从而提高查询效率，解决了高并发系统的性能问题。
2. 为什么使用缓存？
   
   - 减少和数据库的交互次数，减少系统开销，提高系统效率。
3. 什么样的数据能使用缓存？
   
   - 经常查询并且不经常改变的数据。【可以使用缓存】
   

### 13.2、Mybatis缓存

- MyBatis包含一个非常强大的查询缓存特性，它可以非常方便地定制和配置缓存。缓存可以极大的提升查询效率。
- MyBatis系统中默认定义了两级缓存：**一级缓存**和**二级缓存**。
  - 默认情况下，只有一级缓存开启。（SqlSession级别的缓存，也称为本地缓存）。
  
  - 二级缓存需要手动开启和配置，他是基于namespace级别的缓存。
  
  - 为了提高扩展性，MyBatis定义了缓存接口Cache。我们可以通过实现Cache接口来自定义二级缓存。
  

### 13.3、一级缓存

- 一级缓存也叫本地缓存：  SqlSession
  - 与数据库同一次会话期间查询到的数据会放在本地缓存中。
  - 以后如果需要获取相同的数据，直接从缓存中拿，没必须再去查询数据库；

> 测试步骤：

1. 开启日志！
2. 测试在一个Sesion中查询两次相同记录
3. 查看日志输出。

![1609139187717](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1609139187717.png)

> 缓存失效的情况：

1. 查询不同的东西。

2. 增删改操作，可能会改变原来的数据，所以必定会刷新缓存！

   ![1609139561765](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1609139561765.png)

3. 查询不同的Mapper.xml

4. 手动清理缓存！

   ![1609139634993](Mybatis%E8%AF%BE%E5%A0%82%E7%AC%94%E8%AE%B0.assets/1609139634993.png)

- 小结：一级缓存默认是开启的，只在一次SqlSession中有效，也就是拿到连接到关闭连接这个区间段！

- 一级缓存就是一个Map。


### 13.4、二级缓存

- 二级缓存也叫全局缓存，一级缓存作用域太低了，所以诞生了二级缓存
- 基于namespace级别的缓存，一个名称空间，对应一个二级缓存；
- 工作机制
  - 一个会话查询一条数据，这个数据就会被放在当前会话的一级缓存中；
  - 如果当前会话关闭了，这个会话对应的一级缓存就没了；但是我们想要的是，会话关闭了，一级缓存中的数据被保存到二级缓存中；
  - 新的会话查询信息，就可以从二级缓存中获取内容；
  - 不同的mapper查出的数据会放在自己对应的缓存（map）中；

> 步骤：

1. 开启全局缓存；

   ```xml
   <!--显示的开启全局缓存-->
   <setting name="cacheEnabled" value="true"/>
   ```

2. 在要使用二级缓存的Mapper中开启；

   ```xml
   <!--在当前Mapper.xml中使用二级缓存-->
   <cache/>
   ```

   - 也可以自定义参数；

   ```xml
   <!--在当前Mapper.xml中使用二级缓存-->
   <cache  eviction="FIFO"
          flushInterval="60000"
          size="512"
          readOnly="true"/>
   ```

3. 测试。

   1. 问题:我们需要将实体类序列化！否则就会报错！

      ```java
      Caused by: java.io.NotSerializableException: com.github.spojo.User
      ```

> 小结：

- 只要开启了二级缓存，在同一个Mapper下就有效；
- 所有的数据都会先放在一级缓存中；
- 只有当会话提交，或者关闭的时候，才会提交到二级缓冲中！

### 13.5、缓存原理

![1569985541106](Mybatis课堂笔记.assets/1569985541106.png)

### 13.6、自定义缓存-ehcache

- Ehcache是一种广泛使用的开源Java分布式缓存。主要面向通用缓存。

- 要在程序中使用ehcache，先要导包！

```xml
<!-- https://mvnrepository.com/artifact/org.mybatis.caches/mybatis-ehcache -->
<dependency>
    <groupId>org.mybatis.caches</groupId>
    <artifactId>mybatis-ehcache</artifactId>
    <version>1.1.0</version>
</dependency>
```

- 在mapper中指定使用我们的ehcache缓存实现！

```xml
<!--在当前Mapper.xml中使用二级缓存-->
<cache type="org.mybatis.caches.ehcache.EhcacheCache"/>
```

- ehcache.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<ehcache xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:noNamespaceSchemaLocation="http://ehcache.org/ehcache.xsd"
         updateCheck="false">
    <!--
       diskStore：为缓存路径，ehcache分为内存和磁盘两级，此属性定义磁盘的缓存位置。参数解释如下：
       user.home – 用户主目录
       user.dir  – 用户当前工作目录
       java.io.tmpdir – 默认临时文件路径
     -->
    <diskStore path="./tmpdir/Tmp_EhCache"/>
    
    <defaultCache
            eternal="false"
            maxElementsInMemory="10000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="1800"
            timeToLiveSeconds="259200"
            memoryStoreEvictionPolicy="LRU"/>
 
    <cache
            name="cloud_user"
            eternal="false"
            maxElementsInMemory="5000"
            overflowToDisk="false"
            diskPersistent="false"
            timeToIdleSeconds="1800"
            timeToLiveSeconds="1800"
            memoryStoreEvictionPolicy="LRU"/>
    <!--
       defaultCache：默认缓存策略，当ehcache找不到定义的缓存时，则使用这个缓存策略。只能定义一个。
     -->
    <!--
      name:缓存名称。
      maxElementsInMemory:缓存最大数目
      maxElementsOnDisk：硬盘最大缓存个数。
      eternal:对象是否永久有效，一但设置了，timeout将不起作用。
      overflowToDisk:是否保存到磁盘，当系统当机时
      timeToIdleSeconds:设置对象在失效前的允许闲置时间（单位：秒）。仅当eternal=false对象不是永久有效时使用，可选属性，默认值是0，也就是可闲置时间无穷大。
      timeToLiveSeconds:设置对象在失效前允许存活时间（单位：秒）。最大时间介于创建时间和失效时间之间。仅当eternal=false对象不是永久有效时使用，默认是0.，也就是对象存活时间无穷大。
      diskPersistent：是否缓存虚拟机重启期数据 Whether the disk store persists between restarts of the Virtual Machine. The default value is false.
      diskSpoolBufferSizeMB：这个参数设置DiskStore（磁盘缓存）的缓存区大小。默认是30MB。每个Cache都应该有自己的一个缓冲区。
      diskExpiryThreadIntervalSeconds：磁盘失效线程运行时间间隔，默认是120秒。
      memoryStoreEvictionPolicy：当达到maxElementsInMemory限制时，Ehcache将会根据指定的策略去清理内存。默认策略是LRU（最近最少使用）。你可以设置为FIFO（先进先出）或是LFU（较少使用）。
      clearOnFlush：内存数量最大时是否清除。
      memoryStoreEvictionPolicy:可选策略有：LRU（最近最少使用，默认策略）、FIFO（先进先出）、LFU（最少访问次数）。
      FIFO，first in first out，这个是大家最熟的，先进先出。
      LFU， Less Frequently Used，就是上面例子中使用的策略，直白一点就是讲一直以来最少被使用的。如上面所讲，缓存的元素有一个hit属性，hit值最小的将会被清出缓存。
      LRU，Least Recently Used，最近最少使用的，缓存的元素有一个时间戳，当缓存容量满了，而又需要腾出地方来缓存新的元素的时候，那么现有缓存元素中时间戳离当前时间最远的元素将被清出缓存。
   -->

</ehcache>

```

- Redis数据库来做缓存！


## 练习：29道练习题实战！

> 错误: 代理抛出异常错误: java.rmi.server.ExportException: Port already in use: 1099; nested exception is: 

- 说的是1099端口被其它进程占用了。
- 使用下命令查询被占用端口：

````cmd
netstat -aon|findstr 1099
````

- 关闭占用该端口的进程：

```cmd
taskkill -f -pid 11184
```

-----

## 使用mybatis优化smbms项目

> 实体类优化

- 导入Lombok

```java
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.20</version>
        </dependency>
```

- Bill.java

```java
@Data
public class Bill {
    private Integer id; // 主键ID
    private String billCode;    // 账单编码
    private String productName; // 商品名称
    private String productDesc; // 商品描述
    private String productUnit; // 商品单位
    private BigDecimal productCount;   // 商品数量
    private BigDecimal totalPrice; // 商品总额
    private int isPayment;  // 是否支付
    private Integer createdBy;  // 创建者
    private Date creationDate;  //创建时间
    private Integer modifyBy;   // 更新者
    private Date modifyDate;    // 更新时间
    private String providerName; // 供应商名称
    private Integer providerId; // 供应商ID

}
```

- Provider.java

```java
@Data
public class Provider {
    private Integer id;  // 主键ID
    private String proCode; // 供应商编码
    private String proName; // 供应商名称
    private String proDesc; // 供应商详细描述
    private String proContact;  // 供应商联系人
    private String proPhone;    // 联系电话
    private String proAddress;  // 地址
    private String proFax;  // 传真
    private Integer createdBy;   // 创建者
    private Date creationDate;    // 创建时间
    private Date modifyDate;  // 更新时间
    private Integer modifyBy;    //更新者

}
```

- Role.java

```java
@Data
public class Role {
    private Integer id; // 主键ID
    private String roleCode;    // 角色编码
    private String roleName;    // 角色名称
    private Integer createdBy;  // 创建者
    private Date creationDate;  // 创建时间
    private Integer modifyBy;   // 修改者
    private Date modifyDate;    // 修改时间

}
```

- User.java

```java
@Data
public class User {
    private Integer id; // 主键ID
    private String userCode;    // 用户编码
    private String userName;    // 用户名称
    private String userPassword;    // 用户密码
    private Integer gender; // 性别
    private Date birthday;  // 出生日期
    private String phone;   // 手机
    private String address; // 地址
    private Integer userRole;   // 用户角色
    private Integer createdBy;  // 创建者
    private Date creationDate;  // 创建时间
    private Integer modifyBy;   // 更新者
    private Date modifyDate;    // 更新时间

    private Integer age;    // 年龄
    private String userRoleName;    // 用户角色名称

    public Integer getAge() {
        Date date = new Date();
        Integer age = date.getYear()-birthday.getYear();
        return age;
    }
}
```

> 添加配置文件

- db.properties

```java
driver=com.mysql.jdbc.Driver
# 在和mysql传递数据的过程中，使用unicode编码格式，并且字符集设置为utf-8
url=jdbc:mysql://localhost:3306/smbms?useSSL=true&useUnicode=true&characterEncoding=utf-8
username=root
password=root
```

- mybatis-config.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="db.properties">    </properties>

    <settings>
        <setting name="logImpl" value="STDOUT_LOGGING"/>
        <setting name="mapUnderscoreToCamelCase" value="true"/>
    </settings>
    
    <typeAliases>
        <package name="com.github.pojo"/>
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>

        <environment id="test">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://localhost:3306/smbms?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </dataSource>
        </environment>
    </environments>
    <mappers>
        <package name="com.github.dao"/>
    </mappers>

</configuration>
```

> DAO层修改

- MybatisUtils.java

```java
package com.github.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory;

    static{
        try {
            // 使用Mybatis第一步：获取sqlSessionFactory对象
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 既然有了 SqlSessionFactory，顾名思义，我们就可以从中获得 SqlSession 的实例了。
    // SqlSession 完全包含了面向数据库执行 SQL 命令所需的所有方法。
    public static SqlSession getSqlSession(){
        return sqlSessionFactory.openSession();
    }
}
```

- UserDao.java

```java
public interface UserDao {
    //通过userCode获取User
    public User getLoginUser(@Param("userCode") String userCode);

    //增加用户信息
    public int add(User user);

    //通过条件查询userList
    public List<User> getUserList(@Param("userName") String userName,
                                  @Param("userRole") Integer userRole,
                                  @Param("from") Integer from,
                                  @Param("pageSize") Integer pageSize);

    //通过条件查询-用户记录数
    public int getUserCount(@Param("userName") String userName,
                            @Param("userRole") Integer userRole);

    //通过userId删除user
    public int deleteUserById(@Param("id") Integer id);

    //通过useId获取user
    public User getUserById(@Param("id") Integer id);

    //修改用户信息
    public int modify(User user);

    //修改当前用户密码
    public int updatePwd(@Param("id") Integer id,@Param("pwd") String pwd);
}
```

- UserMapper.xml

```java
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.github.dao.user.UserMapper">
    <select id="getLoginUser" parameterType="String" resultType="user">
        select * from smbms_user
        <trim prefix="where" prefixOverrides="and | or">
            <if test="userCode!=null">useCode=#{userCode}</if>
        </trim>
    </select>

    <insert id="add" parameterType="user">
        insert into smbms_user(userCode,userName,userPassword,gender,birthday,phone,address,userRole,createdBy,creationDate,idPicPath,workPicPath)
        values(#{userCode},#{userName},#{userPassword},#{gender},#{birthday},#{phone},#{address},#{userRole},#{createdBy},#{creationDate},#{idPicPath},#{workPicPath})
    </insert>

    <select id="getUserList" resultMap="userList">
        select u.*,r.roleName from smbms_user u,smbms_role r where u.userRole = r.id
        <if test="userRole!=null">and u.userRole=#{userRole}</if>
        <if test="userName!=null and userName != ''">and u.userName like CONCAT('%','userName','%')</if>
        order by creationDate DESC limit #{from},#{pageSize}
    </select>
    <resultMap id="userList" type="user">
        <result property="id" column="id"/>
        <result property="userCode" column="userCode"/>
        <result property="userName" column="userName"/>
        <result property="phone" column="phone"/>
        <result property="birthday" column="birthday"/>
        <result property="gender" column="gender"/>
        <result property="userRole" column="userRole"/>
        <result property="userRoleName" column="roleName"/>
    </resultMap>

    <select id="getUserCount" resultType="int">
        select count(1) as count from smbms_user u,smbms_role r where u.userRole=r.id
        <if test="userRole!=null">and userRole=#{userRole}</if>
        <if test="userName!=null and userName!=''">and userName like CONCAT('%',#{userName},'%')</if>
    </select>

    <delete id="deleteUserById" parameterType="Integer">
        delete from smbms_user where id=#{id}
    </delete>

    <select id="getUserById" parameterType="Integer" resultType="user">
        select u.*,r.roleName as userRoleName from smbms_user u,smbms_role r where u.id=#{id} and u.userRole = r.id
    </select>

    <update id="modify">
        update smbms_user
        <trim prefix="set" suffixOverrides="," suffix="where id=#{id}">
            <if test="userCode!=null">userCode=#{userCode},</if>
            <if test="userName!=null">useName=#{userName},</if>
            <if test="userPassword!=null">userPassword=#{userPassword},</if>
            <if test="gender!=null">gender=#{gender},</if>
            <if test="birthday!=null">birthday=#{birhtday},</if>
            <if test="phone!=null">phone=#{phone},</if>
            <if test="address!=null">address=#{address},</if>
            <if test="userRole!=null">userRole=#{userRole},</if>
            <if test="modifyBy!=null">modifyBy=#{modifyBy},</if>
            <if test="modifyDate!=null">modifyDate=#{modifyDate}</if>
        </trim>
    </update>

    <update id="updatePwd" >
        update smbms_user set userPassword=#{pwd} where id=#{id}
    </update>
</mapper>
```

- RoleDao.java

```java
public interface RoleDao {
    //获取角色列表
    public List<Role> getRoleList();

    //增加角色信息
    public int add(Role role);

    //通过Id删除Role
    public int deleteRoleById(@Param("id") Integer delId);

    //修改角色信息
    public int modify(Role role);

    //通过Id获取role
    public Role getRoleById(@Param("id") Integer id);

    //根据roleCode，进行角色编码的唯一性验证
    public int roleCodeIsExist(@Param("roleCode") String roleCode);

}
```

- RoleMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.github.dao.role.RoleMapper">
    <select id="getRoleList" resultType="role">
        select * from smbms_role
    </select>

    <insert id="add" parameterType="role">
        insert into smbms_role(roleCode,roleName,createdBy,creationDate)
        values(#{roleCode},#{roleName},#{createdBy},#{creationDate})
    </insert>

    <delete id="deleteRoleById" parameterType="Integer">
        delete from smbms_role where id=#{id}
    </delete>

    <update id="modify" parameterType="role">
        update smbms_role
        <trim prefix="set" suffixOverrides="," suffix="where id=#{id}">
            <if test="roleCode!=null">roleCode=#{roleCode},</if>
            <if test="roleName!=null">roleName=#{roleName},</if>
            <if test="createdBy!=null">createdBy=#{createdBy},</if>
            <if test="creationDate!=null">creationDate=#{creationDate}</if>
        </trim>
    </update>

    <select id="getRoleById" parameterType="Integer" resultType="role">
        select * from smbms_role where id=#{id}
    </select>

    <select id="roleCodeIsExist" parameterType="String">
        select count(*) as count from smbms_role where roleCode=#{roleCode}
    </select>

</mapper>
```

- ProviderDao.java

```java
public interface ProviderDao {

    //增加用户信息
    public int add(@Param("provider") Provider provider);

    //通过条件查询providerList
    public List<Provider> getProviderList(@Param("proName") String proName,
                                          @Param("proCode") String proCode,
                                          @Param("from") Integer currentPageNo,
                                          @Param("pageSize") Integer pageSize);

    //获取供应商列表
    public List<Provider> getProList();

    //通过条件查询供应商记录数
    public int getProviderCount(@Param("proName") String proName,
                                @Param("proCode") String proCode);

    //通过供应商Id删除供应商信息
    public int deleteProviderById(@Param("id") Integer delId);

    //根据供应商Id获取供应商信息
    public Provider getProviderById(@Param("id") Integer id);

    //修改供应商
    public int modify(Provider provider);
}
```

- ProviderMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.github.dao.provider.ProviderMapper">
    <insert id="add" parameterType="provider">
        insert into smbms_provider(proCode,proName,proDesc,proContact,proPhone,proAddress,proFax,createdBy,creationDate)
		values(#{proCode},#{proName},#{proDesc},#{proContact},#{proPhone},#{proAddress},#{proFax},#{createdBy},#{creationDate})
    </insert>

    <select id="getProviderList" resultType="provider">
        select * from smbms_provider
        <trim prefix="where" prefixOverrides="and | or">
            <if test="proName!=null and proName!=''">
                and proName like CONCAT('%',#{proName},'%')
            </if>
            <if test="proCode!=null and proCode!=''">
                and proCode like CONCAT('%',#{proCode},'%')
            </if>
        </trim>
        order by creationDate DESC
        limit #{from},#{pageSize}
    </select>

    <select id="getProList" resultType="provider">
        select * from smbms_provider
    </select>

    <select id="getProviderCount">
        select count(1) as count from smbms_provider
        <where>
            <if test="proCode!=null and proCOde!=''">
                and proCode like CONCAT('%',#{proCode},'%')
            </if>
            <if test="proName!=null and proName!=''">
                and proName like CONCAT('%',#{proName},'%')
            </if>
        </where>
    </select>

    <delete id="deleteProviderById" parameterType="Integer">
        delete from smbms_provider where id=#{id}
    </delete>

    <select id="getProviderById" resultType="provider">
        select * from smbms_provider where id=#{id}
    </select>

    <update id="modify" parameterType="provider">
        update smbms_provider
        <trim prefix="set" suffixOverrides=",">
            <if test="proName!=null">proName=#{proName},</if>
            <if test="proDesc!=null">proDesc=#{proDesc},</if>
            <if test="proContact!=null">proContact=#{proContact},</if>
            <if test="proPhone!=null">proPhone=#{proPhone},</if>
            <if test="proAddress!=null">proAddress=#{proAddress},</if>
            <if test="proFax!=null">proFax=#{proFax},</if>
            <if test="modifyBy!=null">modifyBy=#{modifyBy},</if>
            <if test="modifyDate!=null">modifyDate=#{modifyDate},</if>
        </trim>
        where id=#{id}
    </update>

</mapper>
```

- BillDao.java

```java
public interface BillDao {
    //根据供应商Id查询订单数量
    public int getBillCountByProviderId(@Param("providerId") Integer providerId);

    //增加订单
    public int add(Bill bill);

    //通过查询条件获取供应商列表-getBillList
    public List<Bill> getBillList(@Param("productName") String productName,
                                  @Param("providerId") String providerId,
                                  @Param("isPayment") String isPayment,
                                  @Param("from") Integer from,
                                  @Param("pageSize") Integer pageSize);

    //通过条件查询，查询供货商数量
    public int getBillCount(@Param("productName") String productName,
                            @Param("providerId") String providerId,
                            @Param("isPayment") String isPayment);

    //通过delId删除Bill
    public int deleteBillById(@Param("id") Integer id);

    //通过billId获取Bill
    public Bill getBillById(@Param("id") Integer id);

    //修改订单信息
    public int modify(Bill bill);

    //根据供应商Id删除订单信息
    public int deleteBillByProviderId(@Param("providerId") Integer providerId);
    
}
```

- BillMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.github.dao.bill.BillMapper">
    <select id="getBillCountByProviderId" parameterType="Integer">
        select count(1) from smbms_bill where providerId=#{providerId}
    </select>

    <insert id="add" parameterType="bill">
        insert into smbms_bill()values()
    </insert>

    <select id="getBillList" resultType="bill">
        select b.*,p.proName as providerName from smbms_provider p,smbms_bill b
        <trim prefix="where b.providerId=p.id" prefixOverrides="and | or">
            <if test="productName!=null and productName!=''">
                and productName like CONCAT('%','#{productName}','%')
            </if>
            <if test="providerId!=null and providerId!=''">
                and providerId=#{providerId}
            </if>
            <if test="isPayment!=null and isPayment!=''">
                and isPayment=#{isPayment}
            </if>
        </trim>
        order by creationDate DESC
        limit #{from},#{pageSize}
    </select>

    <select id="getBillCount" >
        select count(1) as count from smbms_bill b,smbms_provider p
        <trim prefix="where b.providerId=p.id" prefixOverrides="and | or">
            <if test="productName!=null and productName!=''">
                and productName like CONCAT('%','#{productName}','%')
            </if>
            <if test="providerId!=null and providerId!=''">
                and providerId=#{providerId}
            </if>
            <if test="isPayment">
                and isPayment=#{isPayment}
            </if>
        </trim>
    </select>

    <delete id="deleteBillById" parameterType="Integer">
        delete from smbms_bill where id=#{id}
    </delete>

    <select id="getBillById" parameterType="Integer" resultType="bill">
        select * from smbms_bill where id=#{id}
    </select>

    <update id="modify" parameterType="bill">
        update smbms_bill
        <trim prefix="set" suffixOverrides="," suffix="where id=#{id}">
            <if test="productName!=null">productName=#{productName},</if>
        </trim>
    </update>

    <delete id="deleteBillByproviderId" parameterType="Integer">
        delete from smbms_bill b,smbms_provider p where b.providerId=p.id and b.providerId=#{providerId}
    </delete>
</mapper>
```











