JavaWeb快速入门

## 1.基本概念

### 1.前言

> web开发：

- web，网页的意思  ， www.baidu.com
- 静态web
  - html，css
  - 提供给所有人看的数据始终不会发生变化！
- 动态web
  - 淘宝，几乎是所有的网站；
  - 提供给所有人看的数据始终会发生变化，每个人在不同的时间，不同的地点看到的信息各不相同！
  - 技术栈：Servlet/JSP，ASP，PHP。

- 在Java中，==动态web资源开发的技术统称为JavaWeb==。

### 2.web应用程序

> web应用程序：可以提供浏览器访问的程序。

- a.html.b.html......多个web资源，这些web资源可以被外界访问，对外界提供服务。
- 我们能访问到的任何一个页面或者资源，都存在于这个世界的某一个角落的计算机上。
- URL 
- 这些统一的web资源会被放在同一个文件夹下，web应用程序-->Tomcat：服务器。
- 一个web应用由多部分组成 （静态web，动态web）。
  - html，css，js
  - jsp，servlet
  - Java程序
  - jar包
  - 配置文件 （Properties）

- web应用程序编写完毕后，若想提供给外界访问：需要一个服务器来统一管理；

### 3.静态web

- *.htm, *.html，这些都是网页的后缀，如果服务器上一直存在这些东西，我们就可以直接进行读取。通络；

![image-20210718103304233](img/JavaWeb/1567822802516.png)

- 静态web存在的缺点：
  - Web页面无法动态更新，所有用户看到都是同一个页面；
    - 轮播图，点击特效：伪动态；
    - JavaScript [实际开发中，它用的最多]；
    - VBScript；
  - 它无法和数据库交互（数据无法持久化，用户无法交互）。

### 4.动态web

> 页面会动态展示： “Web的页面展示的效果因人而异”；

![image-20210718105040752](img/JavaWeb/1567823191289.png)

缺点：

- 加入服务器的动态web资源出现了错误，需要重新编写**后台程序**，重新发布；
  - 停机维护。

优点：

- Web页面可以动态更新，所有用户看到都不是同一个页面；
- 它可以与数据库交互 （数据持久化：注册，商品信息，用户信息........）；

![image-20210718110225605](img/JavaWeb/1567823350584.png)

## 2.web服务器

### 1.技术讲解

> **ASP:**

- 微软：国内最早流行的就是ASP；

- 在HTML中嵌入了VB的脚本，  ASP + COM；

- 在ASP开发中，基本一个页面都有几千行的业务代码，页面极其换乱，维护成本高！

- C# 

- IIS

  ```html
  <h1>
      <h1></h1>
          <h1>
              <h1>
                  <h1>
                  <h1>
              </h1>
              <%
              System.out.println("hello")
              %>
              <h1>
              </h1>
                  <h1></h1>
         	<h1>
         </h1>
  ```

> **php：**

- PHP开发速度很快，功能很强大，跨平台，代码很简单 （70% , WP）
- 无法承载大访问量的情况（局限性）

> **JSP/Servlet : ** 

B/S：浏览和服务器

C/S:  客户端和服务器

- sun公司主推的B/S架构
- 基于Java语言的 (所有的大公司，或者一些开源的组件，都是用Java写的)
- 可以承载三高问题带来的影响；
- 语法像ASP ， ASP-->JSP , 加强市场强度；

.....

### 2.web服务器

- 服务器是一种被动的操作，用来处理用户的一些请求和给用户一些响应信息；

- **IIS**
  - 微软的； ASP...,Windows中自带的

- **Tomcat**

![1567824446428](img/JavaWeb/1567824446428.png)

- ==面向百度编程==；

- Tomcat是Apache 软件基金会（Apache Software Foundation）的Jakarta 项目中的一个核心项目，最新的Servlet 和JSP 规范总是能在Tomcat 中得到体现，因为Tomcat 技术先进.性能稳定，而且**免费**，因而深受Java 爱好者的喜爱并得到了部分软件开发商的认可，成为目前比较流行的Web 应用服务器。

- Tomcat 服务器是一个免费的开放源代码的Web 应用服务器，属于轻量级应用[服务器](https://baike.baidu.com/item/服务器)，在中小型系统和并发访问用户不是很多的场合下被普遍使用，是开发和调试JSP 程序的首选。对于一个Java初学web的人来说，它是最佳的选择。

- Tomcat 实际上运行JSP 页面和Servlet。Tomcat最新版本为**9.0。**

....

> **工作3-5年之后，可以尝试手写Tomcat服务器；**

- 下载tomcat：
  1. 安装 or  解压；
  2. 了解配置文件及目录结构；
  3. 这个东西的作用。

## 3.Tomcat

### 1. 安装tomcat

- Tomcat官网：[http://tomcat.apache.org/](http://tomcat.apache.org/)

![1567825600842](img/JavaWeb/1567825600842.png)

![image-20210718113905232](img/JavaWeb/1567825627138.png)



### 2.Tomcat启动和配置

- 文件夹作用：

![image-20210718120802978](img/JavaWeb/1567825763180.png)

- **启动。关闭Tomcat**

![image-20210718161618528](img/JavaWeb/1567825840657.png)

- 访问测试：http://localhost:8080/

- 可能遇到的问题：
  1. Java环境变量没有配置；
  2. 闪退问题：需要配置兼容性；
  3. 乱码问题：配置文件中设置。

### 3.配置

![image-20210718121033543](img/JavaWeb/1567825967256.png)

- 可以配置启动的端口号
  - Tomcat的默认端口号为：8080
  - mysql：3306
  - http：80
  - https：443

```xml
<Connector port="8081" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443" />
```
- 可以配置主机的名称
  - 默认的主机名为：localhost->127.0.0.1
  - 默认网站应用存放的位置为：webapps

```xml
<Host name="www.subeily.com"  appBase="webapps"
            unpackWARs="true" autoDeploy="true">
```
#### 高难度面试题

> 请你谈谈网站是如何进行访问的！

1. 输入一个域名；回车

2. 检查本机的 C:\Windows\System32\drivers\etc\hosts配置文件下有没有这个域名映射；

   1. 有：直接返回对应的ip地址，这个地址中，有我们需要访问的web程序，可以直接访问

      ```java
      127.0.0.1       www.subeily.com
      ```

      

   2. 没有：去DNS服务器找，找到的话就返回，找不到就返回找不到；

   ![1567827057913](img/JavaWeb/1567827057913.png)

4. 可以配置一下环境变量（可选性）。

### 4.发布一个web网站

- 不会就先模仿：
  - 将自己写的网站，放到服务器(Tomcat)中指定的web应用的文件夹（webapps）下，就可以访问了。

- 网站应该有的结构

```java
--webapps ：Tomcat服务器的web目录
	-ROOT
	-books ：网站的目录名
		- WEB-INF
			-classes : java程序
			-lib：web应用所依赖的jar包
			-web.xml ：网站配置文件
		- index.html 默认的首页
		- static 
            -css
            	-style.css
            -js
            -img
         -.....
```

- HTTP协议 ： 面试

- Maven：构建工具
  - Maven安装包

- Servlet 入门
  - HelloWorld！
  - Servlet配置
  - 原理

## 4.Http

### 1.什么是HTTP

> HTTP（超文本传输协议）是一个简单的请求-响应协议，它通常运行在TCP之上。

- 文本：html，字符串 ….
- 超文本：图片，音乐，视频，定位，地图…….
- 80

> Https：安全的

- 443

### 2.两个时代

- http1.0

  - HTTP/1.0：客户端可以与web服务器连接后，只能获得一个web资源，断开连接

- http2.0

  - HTTP/1.1：客户端可以与web服务器连接后，可以获得多个web资源。‘


### 3.Http请求

- 客户端---发请求（Request）---服务器

百度：

```java
Request URL:https://www.baidu.com/   请求地址
Request Method:GET    get方法/post方法
Status Code:200 OK    状态码：200
Remote（远程） Address:14.215.177.39:443
```

```java
Accept:text/html  
Accept-Encoding:gzip, deflate, br
Accept-Language:zh-CN,zh;q=0.9    语言
Cache-Control:max-age=0
Connection:keep-alive
```

> 请求行

- 请求行中的请求方式：GET
- 请求方式：**Get，Post**，HEAD,DELETE,PUT,TRACT…
  - get：请求能够携带的参数比较少，大小有限制，会在浏览器的URL地址栏显示数据内容，不安全，但高效
  - post：请求能够携带的参数没有限制，大小没有限制，不会在浏览器的URL地址栏显示数据内容，安全，但不高效。

> 消息头

```java
Accept：告诉浏览器，它所支持的数据类型
Accept-Encoding：支持哪种编码格式  GBK   UTF-8   GB2312  ISO8859-1
Accept-Language：告诉浏览器，它的语言环境
Cache-Control：缓存控制
Connection：告诉浏览器，请求完成是断开还是保持连接
HOST：主机..../.
```

### 4.Http响应

- 服务器---响应-----客户端

百度：

```java
Cache-Control:private    缓存控制
Connection:Keep-Alive    连接
Content-Encoding:gzip    编码
Content-Type:text/html   类型
```

> 响应体

```java
Accept：告诉浏览器，它所支持的数据类型
Accept-Encoding：支持哪种编码格式  GBK   UTF-8   GB2312  ISO8859-1
Accept-Language：告诉浏览器，它的语言环境
Cache-Control：缓存控制
Connection：告诉浏览器，请求完成是断开还是保持连接
HOST：主机..../.
Refresh：告诉客户端，多久刷新一次；
Location：让网页重新定位；
```

> 响应状态码

200：请求响应成功  200

3xx：请求重定向 

- 重定向：你重新到我给你新位置去；

4xx：找不到资源   404

- 资源不存在；

5xx：服务器代码错误   500       502:网关错误



**常见面试题：**

当你的浏览器中地址栏输入地址并回车的一瞬间到页面能够展示回来，经历了什么？

## 5.Maven

> **为什么要学习这个技术？**

1. 在Java Web开发中，需要使用大量的jar包，**我们手动去导入，这种操作很麻烦，PASS！！！**

2. 如何能够让一个东西自动帮我导入和配置这个jar包。

   由此，Maven诞生了！

### 1. Maven项目架构管理工具

> 在Javaweb的学习中，就是用来就是方便导入jar包的！

> Maven的核心思想：**约定大于配置**。

- 有约束，不要去违反。

> Maven会规定好你该如何去编写自己的Java代码，必须要按照具体规范来。

### 2.下载安装Maven

> 官网：https://maven.apache.org/

![image-20210718175023269](img/JavaWeb/image-20210718175023269.png)

![image-20210718175129229](img/JavaWeb/image-20210718175129229.png)

> - 下载完成后，解压即可；
>
> - **建议将电脑上的所有环境都放在一个文件夹下，方便后期管理**。

### 3.配置环境变量

> **环境不进行配置也可以使用，但建议配置环境！！！**
>
> 在电脑的系统环境变量中，进行如下配置：

- 变量名：M2_HOME；变量值：你的maven目录下的bin目录。

![image-20210718175354438](img/JavaWeb/image-20210718175354438.png)

- 变量名：MAVEN_HOME；变量值：你的maven的目录。

![image-20210718175453119](img/JavaWeb/image-20210718175453119.png)

- 在系统的path中配置：

  ```java
  %MAVEN_HOME%\bin
  ```

![image-20210718175552106](img/JavaWeb/image-20210718175552106.png)

> 测试Maven是否安装成功，在cmd命令行输入：**mvn -version**，如下图则表示成功。

![image-20210718174532836](img/JavaWeb/1567842882993.png)

### 4.阿里云镜像

- 镜像：[mirrors](https://developer.aliyun.com/article/512821)
  - 作用：加速我们的下载
- 国内建议使用阿里云的镜像。
- 在setttins.xml文件中找到<mirrors></mirrors>标签对,进行修改： 

![image-20210719082523668](img/JavaWeb/image-20210719082523668.png)

![image-20210718180008372](img/JavaWeb/image-20210718180008372.png)

```xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>*</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror> 
```

### 5.本地仓库

> 本地仓库：在本地的仓库。

> **建立一个本地仓库：**localRepository

![image-20210718191306773](img/JavaWeb/image-20210718191306773.png)

![image-20210718191220341](img/JavaWeb/image-20210718191220341.png)

```xml
<localRepository>F:\java\Maven\apache-maven-3.8.1\maven-repo</localRepository>
```

### 6.在IDEA中使用Maven

1. 启动IDEA；

2. 创建一个Maven项目；

![image-20210718191034999](img/JavaWeb/image-20210718191034999.png)

![image-20210719083030123](img/JavaWeb/image-20210719083030123.png)

![image-20210719083316161](img/JavaWeb/image-20210719083316161.png)

![image-20210719083421650](img/JavaWeb/image-20210719083421650.png)

3. 等待项目初始化完毕。最后运行结果如下图：

![image-20210719084006259](img/JavaWeb/image-20210719084006259.png)

4. 观察maven仓库中多了什么东西？

![image-20210719084341700](img/JavaWeb/image-20210719084341700.png)

5. IDEA中的Maven设置

> 注意：IDEA项目创建成功后，看一眼Maven的配置。

![image-20210719084449323](img/JavaWeb/image-20210719084449323.png)

> 注意：在IDEA中配置，Maven经常在IDEA中会出现一个问题就是项目自动创建完成后，它这个==Maven Home会使用IDEA默认==，如果发先了这个问题，手动改为本地的。

6. 到这里，Maven在IDEA中的配置和使用就OK了!

### 7.创建一个普通的Maven项目

![image-20210719085734944](img/JavaWeb/image-20210719085734944.png)

![image-20210719085800044](img/JavaWeb/image-20210719085800044.png)

![image-20210719085914035](img/JavaWeb/image-20210719085914035.png)

![image-20210719090107397](img/JavaWeb/image-20210719090107397.png)

> 这个只有在Web应用下才会有！

![image-20210719090158506](img/JavaWeb/image-20210719090158506.png)

---

#### 创建中报错：Cannot resolve plugin org.apache.maven.plugins:maven-jar-plugin

![1603535152253](img/JavaWeb/24.png)

> 主要原因是本地maven的配置文件和仓库地址不一致。 

![1603535227793](img/JavaWeb/25.png)

![1603535273054](img/JavaWeb/26.png)

![1603536246393](img/JavaWeb/25-2.png)

> 修改后如果仍是不行，可修改一下项目的pom.xml文件，加入如下代码:

```xml
<repositories>
    <repository>
        <id>alimaven</id>
        <name>Maven Aliyun Mirror</name>
        <url>https://repo.maven.apache.org/maven2/</url>
        <releases>
            <enabled>true</enabled>
        </releases>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
    </repository>
</repositories> 
```

> 再次进行刷新。如果你有更好的方法，欢迎留言交流！！！

### 8.标记文件夹功能

![image-20210719090853443](img/JavaWeb/image-20210719090853443.png)

![image-20210719091055119](img/JavaWeb/image-20210719091055119.png)

### 9.在 IDEA中配置Tomcat

![image-20210719091501767](img/JavaWeb/image-20210719091501767.png)

![image-20210719091534280](img/JavaWeb/image-20210719091534280.png)

![image-20210719091924057](img/JavaWeb/image-20210719091924057.png)

> 解决警告问题。必须要的配置：**为什么会有这个问题：我们访问一个网站，需要指定一个文件夹名字；**

![image-20210719092915825](img/JavaWeb/image-20210719092915825.png)

![image-20210719093122023](img/JavaWeb/image-20210719093122023.png)

![image-20210719093213227](img/JavaWeb/image-20210719093213227.png)

> 中间的地址，可以自定义。它是在浏览器中的访问地址。

![image-20210719093413735](img/JavaWeb/image-20210719093413735.png)

### 10. pom文件

> pom.xml是Maven的核心配置文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!--Maven版本和头文件-->
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <!--这里就是我们刚才配置的GAV-->
  <groupId>com.github</groupId>
  <artifactId>Javaweb-01-maven</artifactId>
  <version>1.0-SNAPSHOT</version>
  <!--Package：项目的打包方式
  jar：java应用
  war：JavaWeb应用
  -->
  <packaging>war</packaging>

  <!--配置-->
  <properties>
    <!--项目的默认构建编码-->
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <!--编码版本-->
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
  </properties>

  <!--项目依赖-->
  <dependencies>
    <!--    具体依赖的jar包配置文件-->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>

  <!--项目构建用的东西-->
  <build>
    <finalName>Javaweb-01-maven</finalName>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-war-plugin</artifactId>
          <version>3.2.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.github</groupId>
    <artifactId>JavaWeb-01-Maven02</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!--    具体依赖的jar包配置文件-->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
            <scope>test</scope>
        </dependency>
        <!--    maven的高级之处在于可以自动导入这个jar包所依赖的其他jar包-->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.1.9.RELEASE</version>
        </dependency>
    </dependencies>
</project>
```

![image-20210719095403096](img/JavaWeb/image-20210719094303469.png)

> maven由于他的约定大于配置，我们之后可以能遇到我们写的配置文件，无法被导出或者生效的问题，解决方案：

```xml
<!--在build中配置resources，来防止资源导出失败的问题-->
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

> IDEA操作
>
> - 参考：[IDEA 中如何查看maven项目中所有jar包的依赖关系图](https://blog.csdn.net/qq_27093465/article/details/69226949)

![image-20210719100615266](img/JavaWeb/image-20210719100615266.png)

![image-20210719101003983](img/JavaWeb/image-20210719100645219.png)

### 11. 解决遇到的问题

1. [Tomcat闪退](https://blog.csdn.net/qq904069486/article/details/80909780)

2. IDEA中每次都要重复配置Maven；在IDEA中的全局默认配置中去配置。

![image-20210719101143628](img/JavaWeb/image-20210719101143628.png)

![image-20210719101326926](img/JavaWeb/image-20210719101326926.png)

3. maven默认web项目中的web.xml版本问题。

   ![image-20210719101427052](img/JavaWeb/image-20210719101427052.png)

4. 替换为webapp4.0版本和tomcat一致。

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                         http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0"
            metadata-complete="true">
   
   </web-app>
   ```

5. Maven仓库的使用。

   地址：https://mvnrepository.com/

   ![image-20210719101642835](img/JavaWeb/image-20210719101642835.png)

![image-20210719105149813](img/JavaWeb/image-20210719105149813.png)

![image-20210719105231496](img/JavaWeb/image-20210719105231496.png)

![image-20210719105259107](img/JavaWeb/image-20210719105259107.png)

## 6.Servlet

### 1.Servlet简介

- Servlet就是sun公司开发动态web的一门技术。
- Sun在这些API中提供一个接口叫做：Servlet，如果你想开发一个Servlet程序，只需要完成两个小步骤：
  - 编写一个类，实现Servlet接口。
  - 把开发好的Java类部署到web服务器中。

- **把实现了Servlet接口的Java程序叫做，Servlet**。

### 2.HelloServlet

> Serlvet接口Sun公司有两个默认的实现类：HttpServlet，GenericServlet。

1. 构建一个普通的Maven项目，删掉里面的src目录，后面的笔记就在这个项目里面建立Moudel；这个空的工程就是Maven主工程；之后在其中建立一个maven子项目。

   ![image-20210719165845354](img/JavaWeb/image-20210719165845354.png)

   ![image-20210719185856100](img/JavaWeb/image-20210719185856100.png)

2. 关于Maven父子工程的理解：

   - 父项目中会有：

   ```xml
   	<modules>
           <module>Servlet-01</module>
       </modules>
   ```

   - 子项目会有：

   ```xml
   	<parent>
           <artifactId>JavaWeb-02-Servlet</artifactId>
           <groupId>com.github</groupId>
           <version>1.0-SNAPSHOT</version>
       </parent>
   ```

   - 父项目中的java子项目可以直接使用。

   ```java
   son extends father
   ```

3. Maven环境优化。

   1. 修改web.xml为最新的。

      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                            http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
               version="4.0"
               metadata-complete="true">
      
      </web-app>
      ```

   2. 将maven的结构搭建完整。

      ![image-20210721095738447](img/JavaWeb/image-20210721095738447.png)

4. 编写一个Servlet程序。

   ![1567911804700](img/JavaWeb/1567911804700.png)

   1. 编写一个普通类。

   2. 实现Servlet接口，这里我们直接继承HttpServlet

      ```java
      package com.github.servlet;
      
      import javax.servlet.ServletException;
      import javax.servlet.http.HttpServlet;
      import javax.servlet.http.HttpServletRequest;
      import javax.servlet.http.HttpServletResponse;
      import java.io.IOException;
      import java.io.PrintWriter;
      
      public class HelloServlet extends HttpServlet {
          // 由于get或post只是请求实现的不同的方式，可以相互调用，业务逻辑都是一样；
          @Override
          protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      //        ServletOutputStream outputStream = resp.getOutputStream();
              PrintWriter writer = resp.getWriter();  // 响应流
              writer.print("Hello,Serlvet");
          }
      
          @Override
          protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
              super.doPost(req, resp);
          }
      }
      ```

6. 编写Servlet的映射

   - 为什么需要映射：我们写的是JAVA程序，但是要通过浏览器访问，而浏览器需要连接web服务器，所以我们需要再web服务中注册我们写的Servlet，还需给他一个浏览器能够访问的路径；

   ```xml
       <!--注册Servlet-->
       <servlet>
           <servlet-name>hello</servlet-name>
           <servlet-class>com.github.servlet.HelloServlet</servlet-class>
       </servlet>
       <!--Servlet的请求路径-->
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello</url-pattern>
       </servlet-mapping>
   ```
   
7. 配置Tomcat

   - 注意：配置项目发布的路径就可以了。

7. 启动测试，OK！

![image-20210721101413738](img/JavaWeb/image-20210721101413738.png)

### 3.Servlet原理

- Servlet是由Web服务器调用，web服务器在收到浏览器请求之后，会：

![1567913793252](img/JavaWeb/1567913793252.png)

### 4.Mapping问题

1. 一个Servlet可以指定一个映射路径。

   ```xml
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello</url-pattern>
       </servlet-mapping>
   ```

2. 一个Servlet可以指定多个映射路径。

   ```xml
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello2</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello3</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello4</url-pattern>
       </servlet-mapping>
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello5</url-pattern>
       </servlet-mapping>
   ```
   
3. 一个Servlet可以指定通用映射路径。

   ```xml
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/hello/*</url-pattern>
       </servlet-mapping>
   ```

4. 默认请求路径。

   ```xml
       <!--默认请求路径-->
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>/*</url-pattern>
       </servlet-mapping>
   ```

5. 指定一些后缀或者前缀等等….

   ```xml
       <!-- 可以自定义后缀实现请求映射
            注意点，*前面不能加项目映射的路径
            hello/subei.github
           -->
       <servlet-mapping>
           <servlet-name>hello</servlet-name>
           <url-pattern>*.github</url-pattern>
       </servlet-mapping>
   ```
   
6. 优先级问题。
   
   - 指定了固有的映射路径优先级最高，如果找不到就会走默认的处理请求；
   
   ```java
   package com.github.servlet;
   
   import javax.servlet.ServletException;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;
   import java.io.IOException;
   import java.io.PrintWriter;
   
   public class ErrorServelt extends HelloServlet{
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           resp.setContentType("text/html");
           resp.setCharacterEncoding("utf-8");
   
           PrintWriter writer = resp.getWriter();
           writer.println("<h1>404</h1>");
       }
   
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           super.doPost(req, resp);
       }
   }
   ```
   
   ```xml
   <!--  404  -->
       <servlet>
           <servlet-name>error</servlet-name>
           <servlet-class>com.github.servlet.ErrorServelt</servlet-class>
       </servlet>
       <servlet-mapping>
           <servlet-name>error</servlet-name>
           <url-pattern>/*</url-pattern>
       </servlet-mapping>
   ```
   

### 5.ServletContext

- web容器在启动的时候，它会为每个web程序都创建一个对应的ServletContext对象，它代表了当前的web应用；

#### 1.共享数据

- 在这个Servlet中保存的数据，可以在另外一个servlet中拿到；

```java
package com.github.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        this.getInitParameter(); 初始化参数
//        this.getServletConfig(); Servlet配置
//        this.getServletContext(); Servlet上下文
        ServletContext context = this.getServletContext();

        String name = "学习超好";   // 数据
        // 将一个数据保存在了ServletContext中，名字为：name ,值 name
        context.setAttribute("name",name);

        System.out.println("Hello");

    }
}
```

```java
package com.github.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String name = (String)context.getAttribute("name");

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().print("名字:"+name);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```XML
<servlet>
        <servlet-name>hello</servlet-name>
        <servlet-class>com.github.servlet.HelloServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>hello</servlet-name>
        <url-pattern>/hello</url-pattern>
    </servlet-mapping>
    
    <servlet>
        <servlet-name>getc</servlet-name>
        <servlet-class>com.github.servlet.GetServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>getc</servlet-name>
        <url-pattern>/getc</url-pattern>
    </servlet-mapping>
```

- 测试访问结果：

![image-20210725103712199](img/JavaWeb/image-20210725103712199.png)

> [Java-IDEA2020-IDEA或者启动Tomcat控制台中文乱码解决](https://blog.csdn.net/gaogzhen/article/details/107307459)
>
> - 解决后如下：

![image-20210725101105430](img/JavaWeb/image-20210725101105430.png)

#### 2.获取初始化参数

```xml
<!-- 配置一些Web应用初始化参数 -->
    <context-param>
        <param-name>url</param-name>
        <param-value>jdbc:mysql://localhost:3306/mybatis</param-value>
    </context-param>

    <servlet>
        <servlet-name>gp</servlet-name>
        <servlet-class>com.github.servlet.ServletDemo03</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>gp</servlet-name>
        <url-pattern>/gp</url-pattern>
    </servlet-mapping>
```

```java
package com.github.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        String url = context.getInitParameter("url");
        resp.getWriter().print(url);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

![image-20210725104935589](img/JavaWeb/image-20210725104935589.png)

#### 3.请求转发

```java
package com.github.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDemo04 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext context = this.getServletContext();
        System.out.println("进入ServletDemo04文件！");
//        转发的请求路径
//        RequestDispatcher requestDispatcher = context.getRequestDispatcher("/gp");
//        调用forward实现请求转发;
//        requestDispatcher.forward(req,resp);
        context.getRequestDispatcher("/gp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>gp02</servlet-name>
        <servlet-class>com.github.servlet.ServletDemo04</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>gp02</servlet-name>
        <url-pattern>/gp02</url-pattern>
    </servlet-mapping>
```

![image-20210725105741200](img/JavaWeb/image-20210725105741200.png)

![image-20210726214634873](img/JavaWeb/image-20210726214634873.png)

#### 4.读取资源文件

- Properties
  - 在java目录下新建properties
  - 在resources目录下新建properties

![image-20210725111132117](img/JavaWeb/image-20210725111132117.png)

- 发现：都被打包到了同一个路径下：classes，我们俗称这个路径为classpath:

![image-20210725111450382](img/JavaWeb/image-20210725111450382.png)

- 思路：需要一个文件流；

```properties
username=root365
password=123456
```

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ServletDemo05 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        InputStream is = this.getServletContext().getResourceAsStream("/WEB-INF/classes/com/github/servlet/aa.properties");

        Properties prop = new Properties();
        prop.load(is);
        String user = prop.getProperty("username");
        String pwd = prop.getProperty("password");

        resp.getWriter().print(user+":"+pwd);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
	<servlet>
        <servlet-name>db</servlet-name>
        <servlet-class>com.github.servlet.ServletDemo05</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>db</servlet-name>
        <url-pattern>/db</url-pattern>
    </servlet-mapping>
```

- 访问测试！

![image-20210725114243510](img/JavaWeb/image-20210725112556068.png)

### 6.HttpServletResponse

- web服务器接收到客户端的http请求，针对这个请求，分别创建一个代表请求的HttpServletRequest对象，代表响应的一个HttpServletResponse；
  - 如果要获取客户端请求过来的参数：找HttpServletRequest；
  - 如果要给客户端响应一些信息：找HttpServletResponse。

#### 1.简单分类

- 负责向浏览器发送数据的方法。

```java
ServletOutputStream getOutputStream() throws IOException;
PrintWriter getWriter() throws IOException;
```

- 负责向浏览器发送响应头的方法。

```java
    void setCharacterEncoding(String var1);

    void setContentLength(int var1);

    void setContentLengthLong(long var1);

    void setContentType(String var1);

    void setDateHeader(String var1, long var2);

    void addDateHeader(String var1, long var2);

    void setHeader(String var1, String var2);

    void addHeader(String var1, String var2);

    void setIntHeader(String var1, int var2);

    void addIntHeader(String var1, int var2);
```

- 响应的状态码。

```java
    int SC_CONTINUE = 100;
    int SC_SWITCHING_PROTOCOLS = 101;
    int SC_OK = 200;
    int SC_CREATED = 201;
    int SC_ACCEPTED = 202;
    int SC_NON_AUTHORITATIVE_INFORMATION = 203;
    int SC_NO_CONTENT = 204;
    int SC_RESET_CONTENT = 205;
    int SC_PARTIAL_CONTENT = 206;
    int SC_MULTIPLE_CHOICES = 300;
    int SC_MOVED_PERMANENTLY = 301;
    int SC_MOVED_TEMPORARILY = 302;
    int SC_FOUND = 302;
    int SC_SEE_OTHER = 303;
    int SC_NOT_MODIFIED = 304;
    int SC_USE_PROXY = 305;
    int SC_TEMPORARY_REDIRECT = 307;
    int SC_BAD_REQUEST = 400;
    int SC_UNAUTHORIZED = 401;
    int SC_PAYMENT_REQUIRED = 402;
    int SC_FORBIDDEN = 403;
    int SC_NOT_FOUND = 404;
    int SC_METHOD_NOT_ALLOWED = 405;
    int SC_NOT_ACCEPTABLE = 406;
    int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
    int SC_REQUEST_TIMEOUT = 408;
    int SC_CONFLICT = 409;
    int SC_GONE = 410;
    int SC_LENGTH_REQUIRED = 411;
    int SC_PRECONDITION_FAILED = 412;
    int SC_REQUEST_ENTITY_TOO_LARGE = 413;
    int SC_REQUEST_URI_TOO_LONG = 414;
    int SC_UNSUPPORTED_MEDIA_TYPE = 415;
    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    int SC_EXPECTATION_FAILED = 417;
    int SC_INTERNAL_SERVER_ERROR = 500;
    int SC_NOT_IMPLEMENTED = 501;
    int SC_BAD_GATEWAY = 502;
    int SC_SERVICE_UNAVAILABLE = 503;
    int SC_GATEWAY_TIMEOUT = 504;
    int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
```

#### 2.下载文件

1. 向浏览器输出消息；
2. 下载文件：
   1. 要获取下载文件的路径；
   2. 下载的文件名是啥？
   3. 设置想办法让浏览器能够支持下载我们需要的东西；
   4. 获取下载文件的输入流；
   5. 创建缓冲区；
   6. 获取OutputStream对象；
   7. 将FileOutputStream流写入到buffer缓冲区；
   8. 使用OutputStream将缓冲区中的数据输出到客户端！
   
   ![image-20210726211647300](img/JavaWeb/image-20210726211647300.png)

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        1. 要获取下载文件的路径；
        // F:\java\IDEA2020.2\JavaWeb-02-Servlet\response\target\classes\02.png
        String realPath = "F:\\java\\IDEA2020.2\\JavaWeb-02-Servlet\\response\\target\\classes\\02.png";
        System.out.println("下载文件的路径:" + realPath);
//        2. 下载的文件名是啥？
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
//        3. 设置想办法让浏览器能够支持下载我们需要的东西；
        resp.setHeader("Content-Disposition","attachment;filename="+ URLEncoder.encode(fileName,"UTF-8"));
//        4. 获取下载文件的输入流；
        FileInputStream in = new FileInputStream(realPath);
//        5. 创建缓冲区；
        int len = 0;
        byte[] buffer = new byte[1024];
//        6. 获取OutputStream对象；
        ServletOutputStream out = resp.getOutputStream();
//        7. 将FileOutputStream流写入到buffer缓冲区；
        while ((len=in.read(buffer)) > 0){
            out.write(buffer,0,len);
        }

        in.close();
        out.close();
//        8. 使用OutputStream将缓冲区中的数据输出到客户端！
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>/response</servlet-name>
        <servlet-class>com.github.servlet.FileServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/response</servlet-name>
        <url-pattern>/down</url-pattern>
    </servlet-mapping>

</web-app>
```

![image-20210726211721514](img/JavaWeb/image-20210726211721514.png)

#### 3.验证码功能

- 验证怎么来的？
  - 前端实现；
  - 后端实现，需要用到 Java 的图片类，生产一个图片。

```java
package com.github.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class ImageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        如何让浏览器3秒自动刷新一次;
        resp.setHeader("refresh","3");

//        在内存中创建一个图片
        BufferedImage image = new BufferedImage(90,40,BufferedImage.TYPE_INT_RGB);
//        得到图片,笔
        Graphics2D g = (Graphics2D) image.getGraphics();
//        设置图片的背景颜色
        g.setColor(Color.white);
        g.fillRect(0,0,90,40);
//        给图片写数据
        g.setColor(Color.RED);
        g.setFont(new Font(null,Font.BOLD,20));
        g.drawString(makeNum(),8,30);

//        告诉浏览器，这个请求用图片的方式打开
        resp.setContentType("image/jpeg");
//        网站存在缓存，不让浏览器缓存
        resp.setDateHeader("expires",-1);
        resp.setHeader("Cache-Control","no-cache");
        resp.setHeader("Pragma","no-cache");

//        把图片写给浏览器
        ImageIO.write(image,"jpg", resp.getOutputStream());

    }

//    生成随机数
    private String makeNum(){
        Random random = new Random();
        String num = random.nextInt(9999999) + "";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < 7-num.length() ; i++) {
            sb.append("0");
        }
        num = sb.toString() + num;
        return num;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>/imgs</servlet-name>
        <servlet-class>com.github.servlet.ImageServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/imgs</servlet-name>
        <url-pattern>/img</url-pattern>
    </servlet-mapping>
```

![image-20210726214000995](img/JavaWeb/image-20210726214000995.png)

#### 4.实现重定向

![image-20210726214521074](img/JavaWeb/image-20210726214521074.png)

- B一个web资源收到客户端A请求后，B他会通知A客户端去访问另外一个web资源C，这个过程叫`重定向`。

常见场景：

- 用户登录

```java
void sendRedirect(String var1) throws IOException;
```

- 测试：

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*
        resp.setHeader("Location","/response/img");
        resp.setStatus(302);
         */
        resp.sendRedirect("/response/img");    // 重定向
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>/redirectServlet</servlet-name>
        <servlet-class>com.github.servlet.RedirectServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/redirectServlet</servlet-name>
        <url-pattern>/red</url-pattern>
    </servlet-mapping>
```

> 面试题：请你聊聊重定向和转发的区别？

- 相同点：页面都会实现跳转；
- 不同点：
  - 请求转发的时候，url不会产生变化；
  - 重定向时候，url地址栏会发生变化；

![image-20210726214558482](img/JavaWeb/image-20210726214558482.png)

#### 5.简单实现登录重定向

![image-20210727191438650](img/JavaWeb/image-20210727191438650.png)

```jsp
<%--
    这里提交的路径需要寻找到项目的路径
    ${pageContext.request.contextPath} : 代表当前项目
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>登录</title>
  </head>
  <body>
  <form action="${pageContext.request.contextPath}/login" method="get">
    用户名:<input type="text" name="username"><br>
    密  码:<input type="password" name="password"><br>
    <input type="submit">
  </form>
  </body>
</html>
```

```JAVA
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        System.out.println(username+":"+password);

        // 重定向时需注意路径问题，否则为404;
        // /response/success.jsp
        resp.sendRedirect("/response/success.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>/request</servlet-name>
        <servlet-class>com.github.servlet.RequestTest</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/request</servlet-name>
        <url-pattern>/login</url-pattern>
    </servlet-mapping>
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>

<h1>登录成功！！！</h1>

</body>
</html>
```

![image-20210727191340337](img/JavaWeb/image-20210727191340337.png)

### 7.HttpServletRequest

- HttpServletRequest代表客户端的请求，用户通过Http协议访问服务器，HTTP请求中的所有信息会被封装到HttpServletRequest，通过这个HttpServletRequest的方法，获得客户端的所有信息；

![1567933996830](img/JavaWeb/1567933996830.png)

![1567934023106](img/JavaWeb/1567934023106.png)

> 获取参数，请求转发：

![1567934110794](img/JavaWeb/1567934110794.png)

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class HttpServletRequestTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String[] hobbys = req.getParameterValues("hobbies");
        System.out.println("=============================");
        // 后台接收中文乱码问题
        System.out.println(username);
        System.out.println(password);
        System.out.println(Arrays.toString(hobbys));
        System.out.println("=============================");

        System.out.println(req.getContextPath());
        // 通过请求转发
        // 这里的 / 代表当前的web应用
        req.getRequestDispatcher("/success.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>/hsrt</servlet-name>
        <servlet-class>com.github.servlet.HttpServletRequestTest</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/hsrt</servlet-name>
        <url-pattern>/hsrt</url-pattern>
    </servlet-mapping>
```

![image-20210728083444520](img/JavaWeb/image-20210728083444520.png)

## 7.Cookie.Session

### 1.会话

- **会话**：用户打开一个浏览器，点击了很多超链接，访问多个web资源，关闭浏览器，这个过程可以称之为会话；

- **有状态会话**：一个同学来过教室，下次再来教室，我们会知道这个同学，曾经来过，称之为有状态会话；

> **你能怎么证明你是西开的学生？**

```java
你              西开

1. 发票                西开给你发票
2. 学校登记        西开标记你来过了
```

> **一个网站，怎么证明你来过？**

```java
客户端              服务端

1. 服务端给客户端一个 信件，客户端下次访问服务端带上信件就可以了； cookie
2. 服务器登记你来过了，下次你来的时候我来匹配你； seesion
```

### 2.保存会话的两种技术

**cookie**

- 客户端技术   （响应，请求）

**session**

- 服务器技术，利用这个技术，可以保存用户的会话信息？ 我们可以把信息或者数据放在Session中！

> 常见常见：网站登录之后，你下次不用再登录了，第二次访问直接就上去了！

### 3.Cookie

![1568344447291](img/JavaWeb/1568344447291.png)

1. 从请求中拿到cookie信息；
2. 服务器响应给客户端cookie；

```java
Cookie[] cookies = req.getCookies(); // 获得Cookie
cookie.getName(); // 获得cookie中的key
cookie.getValue(); // 获得cookie中的vlaue
new Cookie("lastLoginTime", System.currentTimeMillis()+""); // 新建一个cookie
cookie.setMaxAge(24*60*60); // 设置cookie的有效期
resp.addCookie(cookie); // 响应给客户端一个cookie
```

- **cookie：一般会保存在本地的 用户目录下 appdata；**
- 案例：

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 保存用户上一次访问的时间
 */
public class CookieDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 服务器，告诉你，你来的时间，将这个时间封装为一个信件
        // 解决中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();

        // Cookie,服务端从客户端获取
        Cookie[] cookies = req.getCookies();
        // 这里返回数组，说明Cookie可能存在多个

        // 判断Cookie是否存在
        if(cookies!=null){
            // 如果存在，遍历数组
            writer.write("你上一次访问的时间是:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 获取Cookie的名字
                if(cookie.getName().equals("lastLoginTime")) {
                    // 获得cookie中的value
                    long parseLong = Long.parseLong(cookie.getValue());
                    Date date = new Date(parseLong);
                    writer.write(date.toLocaleString());
                }
            }
        }else{
            writer.write("第一次访问本站！！！");
        }

        // 服务端给客户端响应一个Cookie;
        Cookie cookie = new Cookie("lastLoginTime", System.currentTimeMillis()+"");

        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
    <servlet>
        <servlet-name>/cookieDemo01</servlet-name>
        <servlet-class>com.github.servlet.CookieDemo01</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/cookieDemo01</servlet-name>
        <url-pattern>/cd01</url-pattern>
    </servlet-mapping>
```

![image-20210731093237579](img/JavaWeb/image-20210731093237579.png)

> 一个网站cookie是否存在上限！**聊聊细节问题**。

- 一个Cookie只能保存一个信息；
- 一个web站点可以给浏览器发送多个cookie，最多存放20个cookie；
- Cookie大小有限制4kb；
- 300个cookie浏览器上限。

> **删除Cookie；**

- 不设置有效期，关闭浏览器，自动失效；
- 设置有效期时间为 0 ；

> **编码解码：**

```java
URLEncoder.encode("哇哈哈","utf-8")
URLDecoder.decode(cookie.getValue(),"UTF-8")
```

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

/**
 * 中文数据传递
 */
public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决中文乱码
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        PrintWriter writer = resp.getWriter();
        Cookie[] cookies = req.getCookies();

        // 判断Cookie是否存在
        if(cookies!=null){
            // 如果存在，遍历数组
            writer.write("你上一次访问的用户是:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                // 获取Cookie的名字
                if(cookie.getName().equals("name")) {
                    // 解码
                    writer.write(URLDecoder.decode(cookie.getValue(),"UTF-8"));
                }
            }
        }else{
            writer.write("第一次访问本站！！！");
        }

        // 编码
        Cookie cookie = new Cookie("name", URLEncoder.encode("哇哈哈","UTF-8"));
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
```

```xml
   <servlet>
        <servlet-name>/cookieDemo03</servlet-name>
        <servlet-class>com.github.servlet.CookieDemo03</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/cookieDemo03</servlet-name>
        <url-pattern>/cd03</url-pattern>
    </servlet-mapping>
```

![image-20210731100120981](img/JavaWeb/image-20210731100120981.png)

### 4.Session(重点)

![1568344560794](img/JavaWeb/1568344560794.png)

> 什么是Session：

- 服务器会给每一个用户（浏览器）创建一个Seesion对象；
- 一个Seesion独占一个浏览器，只要浏览器没有关闭，这个Session就存在；
- 用户登录之后，整个网站它都可以访问！--> 保存用户的信息；保存购物车的信息…..

![1568342773861](img/JavaWeb/1568342773861.png)

> Session和cookie的区别：

- Cookie是把用户的数据写给用户的浏览器，浏览器保存 （可以保存多个）
- Session把用户的数据写到用户独占Session中，服务器端保存  （保存重要的信息，减少服务器资源的浪费）
- Session对象由服务创建；

> 使用场景：

- 保存一个登录用户的信息；
- 购物车信息；
- 在整个网站中经常会使用的数据，我们将它保存在Session中；

> 使用Session：

```java
package com.github.pojo;

public class Person {
    private int age;
    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person() {
    }

    public Person(String name, int age) {
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", name='" + name + '\'' +
                '}';
    }
}
```

```java
package com.github.servlet;

import com.github.pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

public class SessionDemo01 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决乱码问题
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // 得到Session
        HttpSession session = req.getSession();
        // 在session中存东西
        session.setAttribute("name",new Person("懒羊羊",1));
        // 获取Session中的ID
        String id = session.getId();

        // 判断Session是否为新创建的
        if(session.isNew()){
            resp.getWriter().write("session创建成功！ID:" + id);
        }else{
            resp.getWriter().write("session已经在服务器中存在了！ID:" + id);
        }

        // Session创建的时候做了什么事情:
//        Cookie cookie = new Cookie("JSESSIONID", id);
//        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}

//// 得到Session
//HttpSession session = req.getSession();
//
//Person person = (Person) session.getAttribute("name");
//
//System.out.println(person.toString());
//
//HttpSession session = req.getSession();
//session.removeAttribute("name");
//// 手动注销Session
//session.invalidate();
```

```xml
    <servlet>
        <servlet-name>/sessionDemo</servlet-name>
        <servlet-class>com.github.servlet.SessionDemo01</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>/sessionDemo</servlet-name>
        <url-pattern>/sd01</url-pattern>
    </servlet-mapping>
```

![image-20210731093513716](img/JavaWeb/image-20210731093513716.png)

> **会话自动过期：web.xml配置**！

```xml
<!--设置Session默认的失效时间-->
<session-config>
    <!--15分钟后Session自动失效，以分钟为单位-->
    <session-timeout>15</session-timeout>
</session-config>
```

![1568344679763](img/JavaWeb/1568344679763.png)

## 8.JSP

### 1.什么是JSP

> Java Server Pages ： Java服务器端页面，也和Servlet一样，用于动态Web技术！

- 最大的特点：
  - 写JSP就像在写HTML；
  - 区别：
    - HTML只给用户提供静态的数据；
    - JSP页面中可以嵌入JAVA代码，为用户提供动态数据；

### 2.JSP原理

思路：JSP到底怎么执行的！

- 代码层面没有任何问题；

- 服务器内部工作：

  - tomcat中有一个work目录；
- IDEA中使用Tomcat的会在IDEA的tomcat中生产一个work目录；
  

![image-20210731111233413](img/JavaWeb/image-20210731111233413.png)

- 我电脑的地址：
  
```java
C:\Users\subei\AppData\Local\JetBrains\IntelliJIdea2020.2\tomcat\Unnamed_JavaWeb-02-Servlet_4\work\Catalina\localhost\Session\org\apache\jsp
```

- 发现页面转变成了Java程序！
  

![image-20210731111517039](img/JavaWeb/image-20210731111517039.png)



- **浏览器向服务器发送请求，不管访问什么资源，其实都是在访问Servlet！**

- JSP最终也会被转换成为一个Java类！

- **JSP 本质上就是一个Servlet**！

```java
// 初始化
  public void _jspInit() {
  }
// 销毁
  public void _jspDestroy() {
  }
// JSPService
  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {   
```

1. 判断请求；

2. 内置一些对象；

   ```java
   final javax.servlet.jsp.PageContext pageContext;  // 页面上下文
   javax.servlet.http.HttpSession session = null;    // session
   final javax.servlet.ServletContext application;   // applicationContext
   final javax.servlet.ServletConfig config;         // config
   javax.servlet.jsp.JspWriter out = null;           // out
   final java.lang.Object page = this;               // page：当前
   HttpServletRequest request                        // 请求
   HttpServletResponse response                      // 响应
   ```

3. 输出页面前增加的代码；

   ```java
   response.setContentType("text/html");       // 设置响应的页面类型
   pageContext = _jspxFactory.getPageContext(this, request, response,
                                             null, true, 8192, true);
   _jspx_page_context = pageContext;
   application = pageContext.getServletContext();
   config = pageContext.getServletConfig();
   session = pageContext.getSession();
   out = pageContext.getOut();
   _jspx_out = out;
   ```

4. 以上的这些个对象我们可以在JSP页面中直接使用！

![1568347078207](img/JavaWeb/1568347078207.png)

- 在JSP页面中：
  - 只要是 JAVA代码就会原封不动的输出；
  - 如果是HTML代码，就会被转换为：

```java
      out.write("<html>\n");
      out.write("  <head>\n");
      out.write("    <title>$Title$</title>\n");
      out.write("  </head>\n");
      out.write("  <body>\n");
      out.write("  $END$\n");
      out.write("  </body>\n");
      out.write("</html>\n");
```

- 这样的格式，输出到前端！

### 3.JSP基础语法

- 任何语言都有自己的语法，JAVA中有，JSP 作为java技术的一种应用，它拥有一些自己扩充的语法（了解，知道即可！），Java所有语法都支持！
- 配置必需的maven环境：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>JavaWeb-02-Servlet</artifactId>
        <groupId>com.github</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>Jsp</artifactId>

    <dependencies>
<!--   Servlet 依赖   -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
<!--        JSP 依赖   -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.3</version>
        </dependency>
<!--        JSTL表达式的依赖-->
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
        </dependency>
<!--        standard标签库-->
        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>
        
    </dependencies>

</project>
```

![image-20210731175103524](img/JavaWeb/image-20210731175103524.png)

#### **JSP表达式**

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <%--JSP表达式
  作用：用来将程序的输出，输出到客户端
  <%= 变量或者表达式%>
  --%>
  <%= new java.util.Date()%>

  </body>
</html>
```

#### **jsp脚本片段**

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <%--jsp脚本片段--%>
  <%
    int sum = 0;
    for (int i = 1; i <=100 ; i++) {
      sum+=i;
    }
    out.println("<h1>Sum="+sum+"</h1>");
  %>

  </body>
</html>
```

- **脚本片段的再实现**

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <%
    int x = 10;
    out.println(x);
  %>
  <p>这是一个JSP文档</p>
  <%
    int num = 2;
    out.println(num);
  %>

  <hr>

  <%--在代码嵌入HTML元素--%>
  <%
    for (int i = 0; i < 5; i++) {
  %>
  <h1>Hello,World  <%=i%> </h1>
  <%
    }
  %>

  </body>
</html>
```

#### JSP声明

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>

  <%!
    static {
      System.out.println("Loading Servlet!");
    }

    private int globalVar = 0;

    public void guo(){
      System.out.println("进入了方法guo！");
    }
  %>

  </body>
</html>
```

- JSP声明：会被编译到JSP生成Java的类中！其他的，就会被生成到_jspService方法中！

![image-20210731181245696](img/JavaWeb/image-20210731181245696.png)

- 在JSP，嵌入Java代码即可！

```jsp
<%%>
<%=%>
<%!%>

<%--注释--%>
```

- JSP的注释，不会在客户端显示，HTML就会！

### 4.JSP指令

> 404与500页面实现

![image-20210731223105414](img/JavaWeb/image-20210731223105414.png)

![image-20210731222414122](img/JavaWeb/image-20210731222414122.png)

- jsp2.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%--定制错误页面--%>
<%--<%@ page errorPage="error/500.jsp" %>--%>
<html>

<head>
    <title>Title</title>
</head>
<body>

<%
    int x = 1/0;
%>

</body>
</html>
```

- 404.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <img src="${pageContext.request.contextPath}/img/2-404.png" alt="404">
</body>
</html>
```

- 500.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>自定义500错误的界面</h2>
<img src="${pageContext.request.contextPath}/img/1-500.png" alt="500">
</body>
</html>
```

- web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <error-page>
        <error-code>404</error-code>
        <location>/error/404.jsp</location>
    </error-page>
    <error-page>
        <error-code>500</error-code>
        <location>/error/500.jsp</location>
    </error-page>

</web-app>
```

![image-20210731223017698](img/JavaWeb/image-20210731223017698.png)

> 头部和尾部页面拼接

![image-20210731222218975](img/JavaWeb/image-20210731222218975.png)

- footer.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>footer</title>
</head>
<body>

<h1>我是footer</h1>

</body>
</html>
```

- header.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>head</title>
</head>
<body>

<h1>我是header</h1>

</body>
</html>
```

- jsp3.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<title>jsp3</title>
</head>
<body>
    <%-- @include会将两个页面合二为一 --%>

    <%@include file="common/header.jsp"%>
    <h1>网页主体</h1>
    <%@include file="common/footer.jsp"%>

    <hr>

    <%--
        jsp标签
        jsp:include：拼接页面，本质还是三个
    --%>
    <jsp:include page="/common/header.jsp"/>
    <h1>网页主体</h1>
    <jsp:include page="/common/footer.jsp"/>

</body>
</html>
```

![image-20210731223309033](img/JavaWeb/image-20210731223309033.png)

### 5.9大内置对象

- PageContext    存东西
- Request     存东西
- Response
- Session      存东西
- Application   【SerlvetContext】   存东西
- config    【SerlvetConfig】
- out
- page ，不用了解
- exception

```java
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--内置对象--%>
<%
    pageContext.setAttribute("name1","天启1号"); // 保存的数据只在一个页面中有效
    request.setAttribute("name2","天启2号"); // 保存的数据只在一次请求中有效，请求转发会携带这个数据
    session.setAttribute("name3","天启3号"); // 保存的数据只在一次会话中有效，从打开浏览器到关闭浏览器
    application.setAttribute("name4","天启4号");  // 保存的数据只在服务器中有效，从打开服务器到关闭服务器
%>

<%--
    脚本片段中的代码，会被原封不动生成到.jsp.java
    要求：这里面的代码，必须保证Java语法的正确性
--%>

<%
    // 从pageContent取出，我们通过寻找的方式来
    // 从底层到高层（作用域）:
    String name1 = (String) pageContext.findAttribute("name1");
    String name2 = (String) pageContext.findAttribute("name2");
    String name3 = (String) pageContext.findAttribute("name3");
    String name4 = (String) pageContext.findAttribute("name4");
    String name5 = (String) pageContext.findAttribute("name5"); // 作用域

%>

<%--使用EL表达式输出 ${} --%>
<h1>取出的值:</h1>
<h3>${name1}</h3>
<h3>${name2}</h3>
<h3>${name3}</h3>
<h3>${name4}</h3>
<h3> <%=name5%> </h3>

</body>
</html>
```

![image-20210801175726215](img/JavaWeb/image-20210801175726215.png)

> 如果EL表达式不生效，请在JSP页面最上面加上：<%@page isELIgnored="false" %>

![image-20210801175312931](img/JavaWeb/image-20210801175312931.png)

- request：客户端向服务器发送请求，产生的数据，用户看完就没用了，比如：新闻，用户看完没用的！
- session：客户端向服务器发送请求，产生的数据，用户用完一会还有用，比如：购物车；
- application：客户端向服务器发送请求，产生的数据，一个用户用完了，其他用户还可能使用，比如：聊天数据；

### 6.JSP标签.JSTL标签.EL表达式

- EL表达式：  ${ }
  - **获取数据**
  - **执行运算**
  - **获取web开发的常用对象**


```jsp
<!-- JSTL表达式的依赖 -->
<dependency>
    <groupId>javax.servlet.jsp.jstl</groupId>
    <artifactId>jstl-api</artifactId>
    <version>1.2</version>
</dependency>
<!-- standard标签库 -->
<dependency>
    <groupId>taglibs</groupId>
    <artifactId>standard</artifactId>
    <version>1.1.2</version>
</dependency>
```

- **JSP标签**

![image-20210802105718417](img/JavaWeb/image-20210802105718417.png)

- jspTag.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>jspTag</title>
</head>
<body>

<h1>Tag1</h1>

<%--jsp:include--%>

<%--
http://localhost:8080/Jsp/jspTag.jsp?name=subeily&age=18
--%>

<jsp:forward page="/jspTag2.jsp">
    <jsp:param name="name" value="subeiLY"/>
    <jsp:param name="age" value="18"/>
</jsp:forward>


</body>
</html>
```

- jspTag2.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>jspTag2</title>
</head>
<body>

<h1>Tag2</h1>

<%--取出参数--%>
    
名字:<%=request.getParameter("name")%>
年龄:<%=request.getParameter("age")%>

</body>
</html>
```

![image-20210802105915771](img/JavaWeb/image-20210802105915771.png)

- [**JSTL表达式**](https://www.runoob.com/jsp/jsp-jstl.html)
  - JSTL标签库的使用就是为了弥补HTML标签的不足；它自定义许多标签，可以供我们使用，标签的功能和Java代码一样！
- **格式化标签**
- **SQL标签**
- **XML 标签**
- **核心标签** （掌握部分）

![1568362473764](img/JavaWeb/1568362473764.png)

- **JSTL标签库使用步骤**：
  - 引入对应的 taglib；
  - 使用其中的方法；
  - **在Tomcat 的lib目录下也需要引入 jstl-api-1.2.jar、standard-1.1.2.jar的包，否则会报错：JSTL解析错误**；

- c：if

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%--引入jstl核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>core-if</title>
</head>
<body>

<h4>if测试</h4>

<hr>

<form action="core-if.jsp" method="get">
    <%--
        EL表达式获取表单中的数据
        ${param.参数名}
    --%>
    <input type="text" name="username" value="${param.username}">
    <input type="submit" value="登录">
</form>

<%--判断如果提交的用户名是管理员，则登录成功--%>
<c:if test="${param.username=='admin'}" var="isAdmin">
    <c:out value="管理员欢迎您！"/>
</c:if>

<%--自闭合标签--%>
<c:out value="${isAdmin}"/>

</body>
</html>
```

![image-20210802113054041](img/JavaWeb/image-20210802113054041.png)

- c:choose   c:when

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%--引入jstl核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>core-for</title>
</head>
<body>

<%--定义一个变量score，值为85--%>
<c:set var="score" value="65"/>

<c:choose>
    <c:when test="${score>=90}">
        你的成绩为优秀
    </c:when>
    <c:when test="${score>=80}">
        你的成绩为一般
    </c:when>
    <c:when test="${score>=70}">
        你的成绩为良好
    </c:when>
    <c:when test="${score<=60}">
        你的成绩为不及格
    </c:when>
</c:choose>

</body>
</html>
```

- c:forEach

```jsp
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%--引入jstl核心标签库--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>core-foreach</title>
</head>
<body>

<%

    ArrayList<String> people = new ArrayList<>();
    people.add(0,"张三");
    people.add(1,"李四");
    people.add(2,"王五");
    people.add(3,"赵六");
    people.add(4,"田六");
    request.setAttribute("list",people);
%>

<%--
var , 每一次遍历出来的变量
items, 要遍历的对象
begin,   哪里开始
end,     到哪里
step,   步长
--%>
<c:forEach var="people" items="${list}">
    <c:out value="${people}"/> <br>
</c:forEach>

<hr>

<c:forEach var="people" items="${list}" begin="1" end="3" step="1" >
    <c:out value="${people}"/> <br>
</c:forEach>

</body>
</html>
```

## 9.JavaBean

实体类，JavaBean有特定的写法：

- 必须要有一个无参构造
- 属性必须私有化
- 必须有对应的get/set方法；

一般用来和数据库的字段做映射  ORM；

ORM ：对象关系映射

- 表--->类
- 字段-->属性
- 行记录---->对象

**people表**

|  id  |  name   | age  | address |
| :--: | :-----: | :--: | :-----: |
|  1   | 饺子1号 |  3   |  成都   |
|  2   | 饺子2号 |  18  |  成都   |
|  3   | 饺子3号 |  85  |  成都   |

![image-20210802161642932](img/JavaWeb/image-20210802161642932.png)


```java
class People{
    private int id;
    private String name;
    private int age;
    private String address;
}
```

![image-20210802162035461](img/JavaWeb/image-20210802162035461.png)

```java
class A{
    new People(1,"饺子1号",3，"成都");
    new People(2,"饺子1号",18，"成都");
    new People(3,"饺子1号",85，"成都");
}
```

- javaBean.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
//    People people = new People();
//    people.setAddress();
//    people.setId();
//    people.getAge()
//    people.setName();

%>

<jsp:useBean id="people" class="com.github.pojo.People" />

<jsp:setProperty name="people" property="address" value="成都"/>
<jsp:setProperty name="people" property="id" value="1"/>
<jsp:setProperty name="people" property="age" value="2"/>
<jsp:setProperty name="people" property="name" value="哇哈哈AD钙"/>

姓名:<jsp:getProperty name="people" property="name"/>
 ID:<jsp:getProperty name="people" property="id"/>
年龄:<jsp:getProperty name="people" property="age"/>
地址:<jsp:getProperty name="people" property="address"/>

</body>
</html>
```

![image-20210802163852023](img/JavaWeb/image-20210802163852023.png)

- 过滤器
- 文件上传
- 邮件发送
- JDBC 复习 ： 如何使用JDBC ,  JDBC crud， jdbc 事务

## 10.MVC三层架构

- 什么是MVC：  Model     view     Controller  模型.视图.控制器

### 1.早些年

![1568423664332](img/JavaWeb/1568423664332.png)

- 用户直接访问控制层，控制层就可以直接操作数据库；

```java
servlet--CRUD-->数据库
弊端：程序十分臃肿，不利于维护  
servlet的代码中：处理请求.响应.视图跳转.处理JDBC.处理业务代码.处理逻辑代码

架构：没有什么是加一层解决不了的！
程序猿调用
|
JDBC
|
Mysql Oracle SqlServer ....
```

### 2.MVC三层架构

![1568424227281](img/JavaWeb/1568424227281.png)



Model

- 业务处理 ：业务逻辑（Service）
- 数据持久层：CRUD   （Dao）

View

- 展示数据
- 提供链接发起Servlet请求 （a，form，img…）

Controller  （Servlet）

- 接收用户的请求 ：（req：请求参数.Session信息….）

- 交给业务层处理对应的代码 

- 控制视图的跳转  

  ```java
  登录--->接收用户的登录请求--->处理用户的请求（获取用户登录的参数，username，password）---->交给业务层处理登录业务（判断用户名密码是否正确：事务）--->Dao层查询用户名和密码是否正确-->数据库
  ```

## 11.Filter （重点）

Filter：过滤器 ，用来过滤网站的数据；

- 处理中文乱码
- 登录验证….

![1568424858708](img/JavaWeb/1568424858708.png)

Filter开发步骤：

1. 导包

2. 编写过滤器

   1. 导包不要错；

      ```xml
      <?xml version="1.0" encoding="UTF-8"?>
      <project xmlns="http://maven.apache.org/POM/4.0.0"
               xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
               xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
          <parent>
              <artifactId>JavaWeb-02-Servlet</artifactId>
              <groupId>com.github</groupId>
              <version>1.0-SNAPSHOT</version>
          </parent>
          <modelVersion>4.0.0</modelVersion>
      
          <artifactId>Filer</artifactId>
      
          <dependencies>
              <!--   Servlet 依赖   -->
              <dependency>
                  <groupId>javax.servlet</groupId>
                  <artifactId>servlet-api</artifactId>
                  <version>2.5</version>
              </dependency>
              <!--        JSP 依赖   -->
              <dependency>
                  <groupId>javax.servlet.jsp</groupId>
                  <artifactId>javax.servlet.jsp-api</artifactId>
                  <version>2.3.3</version>
              </dependency>
              <!--        JSTL表达式的依赖-->
              <dependency>
                  <groupId>javax.servlet.jsp.jstl</groupId>
                  <artifactId>jstl-api</artifactId>
                  <version>1.2</version>
              </dependency>
              <!--        standard标签库-->
              <dependency>
                  <groupId>taglibs</groupId>
                  <artifactId>standard</artifactId>
                  <version>1.1.2</version>
              </dependency>
              <!--    连接数据库-->
              <dependency>
                  <groupId>mysql</groupId>
                  <artifactId>mysql-connector-java</artifactId>
                  <version>5.1.47</version>
              </dependency>
          </dependencies>
      
      </project>
      ```
   
      ![image-20210802171522721](img/JavaWeb/image-20210802171522721.png)
   
   2. 实现Filter接口，重写对应的方法即可；
   
      ```java
      package com.github.filter;
      
      import javax.servlet.*;
      import java.io.IOException;
      
      public class CharacterEncodingFilter implements Filter {
          
          /**
           * 初始化：web服务器启动，就以及初始化了，随时等待过滤对象出现！
           */
          public void init(FilterConfig filterConfig) {
              System.out.println("CharacterEncodingFilter初始化");
          }
      
          /**
           * Chain : 链
           * 
           * 1. 过滤中的所有代码，在过滤特定请求的时候都会执行
           * 2. 必须要让过滤器继续同行
           *    chain.doFilter(request,response);
           */
          public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
              request.setCharacterEncoding("utf-8");
              response.setCharacterEncoding("utf-8");
              response.setContentType("text/html;charset=UTF-8");
      
              System.out.println("CharacterEncodingFilter执行前....");
              // 让我们的请求继续走，如果不写，程序到这里就被拦截停止！
              chain.doFilter(request,response);
              System.out.println("CharacterEncodingFilter执行后....");
          }
      
          /**
           * 销毁：web服务器关闭的时候，过滤会销毁
           */
          public void destroy() {
              System.out.println("CharacterEncodingFilter销毁");
          }
      }
      ```
   
3. 在web.xml中配置 Filter；

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
            version="4.0">
   
       <filter>
           <filter-name>CharacterEncodingFilter</filter-name>
           <filter-class>com.github.filter.CharacterEncodingFilter</filter-class>
       </filter>
       <filter-mapping>
           <filter-name>CharacterEncodingFilter</filter-name>
           <!--只要是 /servlet的任何请求，会经过这个过滤器-->
           <url-pattern>/servlet/*</url-pattern>
           <!--<url-pattern>/*</url-pattern>-->
       </filter-mapping>
   
   </web-app>
   ```

## 12.监听器

实现一个监听器的接口；（有N种）

1. 编写一个监听器；

   实现监听器的接口…

   ```java
   package com.github.listener;
   
   import javax.servlet.ServletContext;
   import javax.servlet.http.HttpSessionEvent;
   import javax.servlet.http.HttpSessionListener;
   
   /**
    * @author: subei
    * @Description: 统计网站在线人数 ： 统计session
    */
   public class OnlineCountListener implements HttpSessionListener {
       /**
        * 创建session监听： 看你的一举一动
        * 一旦创建Session就会触发一次这个事件！
        * @param se
        */
       public void sessionCreated(HttpSessionEvent se) {
           ServletContext ctx = se.getSession().getServletContext();
   
           System.out.println(se.getSession().getId());
   
           Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
   
           if (onlineCount==null){
               onlineCount = new Integer(1);
           }else {
               int count = onlineCount.intValue();
               onlineCount = new Integer(count+1);
           }
   
           ctx.setAttribute("OnlineCount",onlineCount);
       }
   
       /**
        * 销毁session监听
        * 一旦销毁Session就会触发一次这个事件！
        * @param se
        */
       public void sessionDestroyed(HttpSessionEvent se) {
           ServletContext ctx = se.getSession().getServletContext();
   
           Integer onlineCount = (Integer) ctx.getAttribute("OnlineCount");
   
           if (onlineCount==null){
               onlineCount = new Integer(0);
           }else {
               int count = onlineCount.intValue();
               onlineCount = new Integer(count-1);
           }
   
           ctx.setAttribute("OnlineCount",onlineCount);
       }
       /**
        * Session销毁：
        * 1. 手动销毁  getSession().invalidate();
        * 2. 自动销毁
        */
   }
   ```
   
2. web.xml中注册监听器；

   ```xml
   <!--注册监听器-->
   <listener>
       <listener-class>com.github.listener.OnlineCountListener</listener-class>
   </listener>
   ```

3. 看情况是否使用！

## 13.过滤器.监听器常见应用

- **监听器：GUI编程中经常使用；**

```java
package com.github.listener;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TestPanel {
    public static void main(String[] args) {
        // 新建一个窗体
        Frame frame = new Frame("建军节快乐");  
        // 面板
        Panel panel = new Panel(null);
        // 设置窗体的布局
        frame.setLayout(null); 

        frame.setBounds(300,300,500,500);
        // 设置背景颜色1
        frame.setBackground(new Color(68, 227, 177)); 

        panel.setBounds(50,50,300,300);
        // 设置背景颜色2
        panel.setBackground(new Color(255, 242,0)); 

        frame.add(panel);

        frame.setVisible(true);

        // 监听事件，监听关闭事件
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
            }
        });

    }
}
```

> 案例：用户登录之后才能进入主页！用户注销后就不能进入主页了！

1. 用户登录之后，向Sesison中放入用户的数据
2. 进入主页的时候要判断用户是否已经登录；要求：在过滤器中实现！

- 因为：个人tomcat配置的为 /Filer 如下图：

![image-20210803212625696](img/JavaWeb/image-20210803212625696.png)

- LoginServlet.java

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        super.doGet(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取前端请求的参数
        String username = req.getParameter("username");

        // 登陆成功
        if("admin".equals(username)){
            req.getSession().setAttribute("USER_SESSION",req.getSession().getId());
            resp.sendRedirect("/Filer/sys/success.jsp");

        } else {    // 登陆失败
            resp.sendRedirect("/Filer/error.jsp");
        }
    }
}
```

- LogoutServlet.java

```java
package com.github.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object user_session = req.getSession().getAttribute("USER_SESSION");
        if(user_session!=null){
            req.getSession().removeAttribute("USER_SESSION");
            resp.sendRedirect("/Filer/Login.jsp");
        } else {
            resp.sendRedirect("/Filer/Login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req,resp);
    }
}
```

- Login.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>登陆界面</h1>
<form action="/Filer/servlet/login" method="post">
    <input type="text" name="username">
    <input type="submit">
</form>

</body>
</html>
```

- success.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>成功</title>
</head>
<body>

<%--<%--%>
<%--    Object userSession = request.getSession().getAttribute("USER_SESSION");--%>
<%--    if(userSession==null){--%>
<%--        response.sendRedirect("Filer/Login.jsp");--%>
<%--    }--%>
<%--%>--%>

<h1>主页</h1>

<p><a href="/Filer/servlet/logout">注销</a> </p>

</body>
</html>
```

- error.jsp

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>error</title>
</head>
<body>

<h1>错误</h1>
<h3>没有权限，用户名错误</h3>

<p> <a href="/Filer/Login.jsp">返回登录主页</a></p>

</body>
</html>
```

- 进行登录注销无法登录判断；
- Constant.java

```java
package com.github.Util;

public class Constant {
    public static String USER_SESSION="USER_SESSION";
}
```

- SysFilter.java

```java
package com.github.listener;

import com.github.Util.Constant;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        if (request.getSession().getAttribute(Constant.USER_SESSION)==null){
            response.sendRedirect("/Filer/error.jsp");
        }

        chain.doFilter(request,response);
    }

    public void destroy() {

    }
}
```

- web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">

    <servlet>
        <servlet-name>LoginServlet</servlet-name>
        <servlet-class>com.github.servlet.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LoginServlet</servlet-name>
        <url-pattern>/servlet/login</url-pattern>
    </servlet-mapping>
    <servlet>
        <servlet-name>LogoutServlet</servlet-name>
        <servlet-class>com.github.servlet.LogoutServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LogoutServlet</servlet-name>
        <url-pattern>/servlet/logout</url-pattern>
    </servlet-mapping>

    <filter>
        <filter-name>SysFilter</filter-name>
        <filter-class>com.github.listener.SysFilter</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>SysFilter</filter-name>
        <url-pattern>/sys/*</url-pattern>
    </filter-mapping>
    
</web-app>
```

- 运行结果如下：

![login](img/JavaWeb/login.gif)

## 14.JDBC

- 什么是JDBC ： Java连接数据库！

![1568439601825](img/JavaWeb/1568439601825.png)

需要jar包的支持：

- java.sql
- javax.sql
- mysql-conneter-java…  连接驱动（必须要导入）

**实验环境搭建**

```sql
USE jdbc;

CREATE TABLE users(
    id INT PRIMARY KEY,
    `name` VARCHAR(40),
    `password` VARCHAR(40),
    email VARCHAR(60),
    birthday DATE
);

INSERT INTO users(id,`name`,`password`,email,birthday)
VALUES(1,'张三','123456','zs@qq.com','2000-01-01');
INSERT INTO users(id,`name`,`password`,email,birthday)
VALUES(2,'李四','123456','ls@qq.com','2000-01-01');
INSERT INTO users(id,`name`,`password`,email,birthday)
VALUES(3,'王五','123456','ww@qq.com','2000-01-01');

SELECT	* FROM users;
```

- 导入数据库依赖

```xml
<!--mysql的驱动-->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
```

- IDEA中连接数据库：

![](img/JavaWeb/1568440926845.png)

- **JDBC 固定步骤：**

1. 加载驱动
2. 连接数据库,代表数据库
3. 向数据库发送SQL的对象Statement : CRUD
4. 编写SQL （根据业务，不同的SQL）
5. 执行SQL
6. 关闭连接

```java
package com.github.test;

import java.sql.*;

public class TestJdbc {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // 配置信息
        // useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url="jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";

        // 1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3.向数据库发送SQL的对象Statement,PreparedStatement : CRUD
        Statement statement = connection.createStatement();

        // 4.编写SQL
        String sql = "select * from users";

        // 5.执行查询SQL，返回一个 ResultSet  ： 结果集
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()){
            System.out.println("id="+rs.getObject("id"));
            System.out.println("name="+rs.getObject("name"));
            System.out.println("password="+rs.getObject("password"));
            System.out.println("email="+rs.getObject("email"));
            System.out.println("birthday="+rs.getObject("birthday"));
        }

        // 6.关闭连接，释放资源（一定要做） 先开后关
        rs.close();
        statement.close();
        connection.close();
    }
}
```

- **预编译SQL**

```java
package com.github.test;

import java.sql.*;

public class TestJDBC2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 配置信息
        // useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url="jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";

        // 1.加载驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 2.连接数据库,代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3.编写SQL
        String sql = "insert into  users(id, name, password, email, birthday) values (?,?,?,?,?);";

        // 4.预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        // 给第一个占位符？ 的值赋值为1；
        preparedStatement.setInt(1,2);
        // 给第二个占位符？ 的值赋值为狂神说Java；
        preparedStatement.setString(2,"哇哈哈AD钙");
        // 给第三个占位符？ 的值赋值为123456；
        preparedStatement.setString(3,"123456");
        // 给第四个占位符？ 的值赋值为1；
        preparedStatement.setString(4,"24736743@qq.com");
        // 给第五个占位符？ 的值赋值为new Date(new java.util.Date().getTime())；
        preparedStatement.setDate(5,new Date(new java.util.Date().getTime()));

        // 5.执行SQL
        int i = preparedStatement.executeUpdate();

        if (i>0){
            System.out.println("插入成功@");
        }

        // 6.关闭连接，释放资源（一定要做） 先开后关
        preparedStatement.close();
        connection.close();
    }
}
```

**事务**

- 要么都成功，要么都失败！

- ACID原则：保证数据的安全。

```java
开启事务
事务提交  commit()
事务回滚  rollback()
关闭事务

转账：
A:1000
B:1000
    
A(900)   --100-->   B(1100) 
```

**Junit单元测试**

- 依赖

```xml
<!--单元测试-->
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
</dependency>
```

简单使用

- @Test注解只有在方法上有效，只要加了这个注解的方法，就可以直接运行！

```java
@Test
public void test(){
    System.out.println("Hello");
}
```

![image-20210804103811478](img/JavaWeb/image-20210804103811478.png)

- 失败的时候是红色：

![1568442289597](img/JavaWeb/1568442289597.png)

- **搭建一个环境**

```sql
USE jdbc;

CREATE TABLE account(
   id INT PRIMARY KEY AUTO_INCREMENT,
   `name` VARCHAR(40),
   money FLOAT
);

INSERT INTO account(`name`,money) VALUES('A',1000);
INSERT INTO account(`name`,money) VALUES('B',1000);
INSERT INTO account(`name`,money) VALUES('C',1000);
```

```java
package com.github.test;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJdbc3 {
    @Test
    public void test() {
        // 配置信息
        // useUnicode=true&characterEncoding=utf-8 解决中文乱码
        String url="jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=utf-8";
        String username = "root";
        String password = "root";

        Connection connection = null;

        // 1.加载驱动
        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 2.连接数据库,代表数据库
            connection = DriverManager.getConnection(url, username, password);

            // 3.通知数据库开启事务,false 开启
            connection.setAutoCommit(false);

            String sql = "update account set money = money-100 where name = 'A'";
            connection.prepareStatement(sql).executeUpdate();

            // 制造错误
            // int i = 1/0;

            String sql2 = "update account set money = money+100 where name = 'B'";
            connection.prepareStatement(sql2).executeUpdate();

            // 以上两条SQL都执行成功了，就提交事务！
            connection.commit();
            System.out.println("success");
        } catch (Exception e) {
            try {
                // 如果出现异常，就通知数据库回滚事务
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

![image-20210804104005793](img/JavaWeb/image-20210804104005793.png)

## 15.smbms项目

> 参考smbms项目

## 16.文件传输原理及实现

> 此部分内容参考：[博客园-Jpbito](https://www.cnblogs.com/thhh/)

- 新建一个空项目；

![image-20210815183700545](img/File/image-20210815183700545.png)

![image-20210815183750739](img/File/image-20210815183750739.png)

![image-20210815183843948](img/File/image-20210815183843948.png)

![image-20210815183923446](img/File/image-20210815183923446.png)

![image-20210815184006668](img/File/image-20210815184006668.png)

![image-20210815184050733](img/File/image-20210815184050733.png)

![image-20210815184247915](img/File/image-20210815184247915.png)

![image-20210815184329776](img/File/image-20210815184329776.png)

![image-20210815212305474](img/File/image-20210815212305474.png)

- 修改web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0"
         metadata-complete="true">
</web-app>
```

- 配置Tomcat，运行空项目。可运行再执行下面操作。

![image-20210815222528129](img/File/image-20210815222528129.png)

![image-20210815222727411](img/File/image-20210815222727411.png)

- 整体框架；

![image-20210815161733712](img/File/image-20210815161733712.png)

> 具体实现

- 文件在网络上都是使用IO的方式，即流的方式进行的传输，要实现的文件上传功能可以直接使用apache的组件commons-fileupload(针对文件上传的工具类包)，这个jar包又依赖commons-io包(封装了大量的IO操作的工具类)，所以在实现文件上传功能的时候需要导入如下两个依赖：

- maven导入URL：
  - https://mvnrepository.com/artifact/commons-io/commons-io
  - https://mvnrepository.com/artifact/commons-fileupload/commons-fileupload

- 在Maven网站上我们有两种导入jar包的方式。

![image-20210816092837790](img/File/image-20210816092837790.png)

![image-20210816092937180](img/File/image-20210816092937180.png)

> 如何查看所下载的jar包中的源码是什么？

- 双击打开下载的jar包；

![image-20210816093513730](img/File/image-20210816093513730.png)

- 这些都是别人写好的Java代码编译之后的\*.class文件，如果想要查看源码，可以使用反编译工具来获得，==最简单的反编译工具就是我们使用的IDEA==，直接将 *.class拷贝到IDEA中的文件夹，就可以查看它的源码了。
- 为什么压缩的是.class文件？
  - 因为这个文件已经通过了编译器的编译，引入 \*.class文件可以减少编译这些引用的代码的步骤与时间，直接拿过来就可以使用；更是一种原作者保护自己源码的一种手段！
- 如何查看这些*.class中的源码？
  - 反编译。

![image-20210816094133135](img/File/image-20210816094133135.png)

![image-20210816094204632](img/File/image-20210816094204632.png)

> 手动导入需要的jar包。

- 在IDEA中，新建一个lib包，复制jar包到lib文件夹。

![image-20210816094335569](img/File/image-20210816094335569.png)

![image-20210816094418268](img/File/image-20210816094418268.png)

![image-20210816094435699](img/File/image-20210816094435699.png)

![image-20210816094541987](img/File/image-20210816094541987.png)

> 上传文件都是通过使用表单实现的。


<p>     
    <input type="file" name="file"> 
</p>

```jsp
<p>     
    <input type="file" name="file"> 
</p>
```

- 注意：上传文件的表单是上面这么写的，但是如果一个表单中包含文件数据，那么该表单如果想要提交文件数据，那么它必须包含enctype属性，且属性值必须为：`enctype="multipart/form-data"`

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello World!</h2>

<%--
    通过表单上传文件
    get:上传文件有大小限制
    post:上传文件无大小限制
--%>

<form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
    上传用户: <input type="text" name="username"><br>
    上传文件1: <input type="file" name="file1"><br>
    上传文件2: <input type="file" name="file2"><br>
    <input type="submit" value="提交文件">
</form>

</body>
</html>
```

![image-20210816095904145](img/File/image-20210816095904145.png)

- 【面试：文件上传调优】
  1. 上传的文件存放在一个不能使用外界URL访问的目录下面；
  2. 上传到同一个文件夹中的文件名称应该唯一：使用时间戳/UUID/MD5等手段实现；
  3. 限制上传文件的最大值：因为服务器上硬盘资源很贵，不能让用户随意的使用；
  4. 限制文件上传类型：比如这个文件夹只用来存储图片，那你就不能上传一个.mp4的文件；

> 编写servlet

- 前端jsp页面——index.jsp；

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Hello World!</h2>

<%--
    通过表单上传文件
    get:上传文件有大小限制,只能提交4-5kb的数据
    post:上传文件无大小限制
    注意：文件一般比较大，所以上传文件都是使用post方式提交
    ${pageContext.request.contextPath}:获取到webapp路径
--%>

<form action="${pageContext.request.contextPath}/upload.do" enctype="multipart/form-data" method="post">
    上传用户: <input type="text" name="username"><br>
    上传文件1: <input type="file" name="file1"><br>
    上传文件2: <input type="file" name="file2"><br>
    <p> <input type="submit" value="提交文件">  | <input type="reset" value="重置"> </p>
</form>

</body>
</html>
```

- 上传成功后的页面——success.jsp；

```jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成功</title>
</head>
<body>

<%=request.getAttribute("msg")%>
<h2>🎉上传成功🎉</h2>

</body>
</html>
```

- 新建servlet包

![image-20210816103329895](img/File/image-20210816103329895.png)

![image-20210816103407914](img/File/image-20210816103407914.png)

- 修改web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
                      http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0"
         metadata-complete="true">

    <servlet>
        <servlet-name>upload</servlet-name>
        <servlet-class>com.github.servlet.FileSerlvet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>upload</servlet-name>
        <url-pattern>/upload.do</url-pattern>
    </servlet-mapping>
</web-app>
```

![image-20210816104532024](img/File/image-20210816104532024.png)

![image-20210816104552316](img/File/image-20210816104552316.png)

- servlet层

```java
package com.github.servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 * @author subei
 */
public class FileSerlvet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        // response.getWriter().append("Served at: ").append(request.getContextPath());

        // 判断上传的文件普通表单还是带文件的表单
        if (!ServletFileUpload.isMultipartContent(request)) {
            return;//终止方法运行,说明这是一个普通的表单,直接返回
        }
        //创建上传文件的保存路径,建议在WEB-INF路径下,安全,用户无法直接访间上传的文件;
        String uploadPath =this.getServletContext().getRealPath("/WEB-INF/upload");
        File uploadFile = new File(uploadPath);
        if (!uploadFile.exists()){
            uploadFile.mkdir(); //创建这个月录
        }

        // 创建上传文件的保存路径，建议在WEB-INF路径下，安全，用户无法直接访问上传的文件
        String tmpPath = this.getServletContext().getRealPath("/WEB-INF/tmp");
        File file = new File(tmpPath);
        if (!file.exists()) {
            file.mkdir();//创建临时目录
        }

        // 处理上传的文件,一般都需要通过流来获取,我们可以使用 request, getInputstream(),原生态的文件上传流获取,十分麻烦
        // 但是我们都建议使用 Apache的文件上传组件来实现, common-fileupload,它需要旅 commons-io组件;
        try {
            // 创建DiskFileItemFactory对象，处理文件路径或者大小限制
            DiskFileItemFactory factory = getDiskFileItemFactory(file);
            /*
             * //通过这个工厂设置一个缓冲区,当上传的文件大于这个缓冲区的时候,将他放到临时文件 factory.setSizeThreshold(1024 *
             * 1024); //缓存区大小为1M factory.setRepository (file);//临时目录的保存目录,需要一个File
             */

            // 2、获取ServletFileUpload
            ServletFileUpload upload = getServletFileUpload(factory);

            // 3、处理上传文件
            // 把前端请求解析，封装成FileItem对象，需要从ServletFileUpload对象中获取
            String msg = uploadParseRequest(upload, request, uploadPath);

            // Servlet请求转发消息
            System.out.println(msg);
            if(msg == "文件上传成功!") {
                // Servlet请求转发消息
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }else {
                msg ="请上传文件";
                request.setAttribute("msg",msg);
                request.getRequestDispatcher("success.jsp").forward(request, response);
            }

        } catch (FileUploadException e) {
            // TODO 自动生成的 catch 块
            e.printStackTrace();
        }
    }

    public static DiskFileItemFactory getDiskFileItemFactory(File file) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 通过这个工厂设置一个缓冲区,当上传的文件大于这个缓冲区的时候,将他放到临时文件中;
        factory.setSizeThreshold(1024 * 1024);// 缓冲区大小为1M
        factory.setRepository(file);// 临时目录的保存目录,需要一个file
        return factory;
    }

    public static ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 监听长传进度
        upload.setProgressListener(new ProgressListener() {

            // pBYtesRead:已读取到的文件大小
            // pContextLength:文件大小
            @Override
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("总大小：" + pContentLength + "已上传：" + pBytesRead);
            }
        });

        // 处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        // 设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10);
        // 设置总共能够上传文件的大小
        // 1024 = 1kb * 1024 = 1M * 10 = 10м

        return upload;
    }

    public static String uploadParseRequest(ServletFileUpload upload, HttpServletRequest request, String uploadPath)
            throws FileUploadException, IOException {

        String msg = "";

        // 把前端请求解析，封装成FileItem对象
        List<FileItem> fileItems = upload.parseRequest(request);
        for (FileItem fileItem : fileItems) {
            if (fileItem.isFormField()) {// 判断上传的文件是普通的表单还是带文件的表单
                // getFieldName指的是前端表单控件的name;
                String name = fileItem.getFieldName();
                String value = fileItem.getString("UTF-8"); // 处理乱码
                System.out.println(name + ": " + value);
            } else {// 判断它是上传的文件

                // ============处理文件==============

                // 拿到文件名
                String uploadFileName = fileItem.getName();
                System.out.println("上传的文件名: " + uploadFileName);
                if (uploadFileName.trim().equals("") || uploadFileName == null) {
                    continue;
                }

                // 获得上传的文件名/images/girl/paojie.png
                String fileName = uploadFileName.substring(uploadFileName.lastIndexOf("/") + 1);
                // 获得文件的后缀名
                String fileExtName = uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1);

                /*
                 * 如果文件后缀名fileExtName不是我们所需要的 就直按return.不处理,告诉用户文件类型不对。
                 */

                System.out.println("文件信息[件名: " + fileName + " ---文件类型" + fileExtName + "]");
                // 可以使用UID（唯一识别的通用码),保证文件名唯
                // 0UID. randomUUID(),随机生一个唯一识别的通用码;
                String uuidPath = UUID.randomUUID().toString();

                // ================处理文件完毕==============

                // 存到哪? uploadPath
                // 文件真实存在的路径realPath
                String realPath = uploadPath + "/" + uuidPath;
                // 给每个文件创建一个对应的文件夹
                File realPathFile = new File(realPath);
                if (!realPathFile.exists()) {
                    realPathFile.mkdir();
                }
                // ==============存放地址完毕==============


                // 获得文件上传的流
                InputStream inputStream = fileItem.getInputStream();
                // 创建一个文件输出流
                // realPath =真实的文件夹;
                // 差了一个文件;加上翰出文件的名产"/"+uuidFileName
                FileOutputStream fos = new FileOutputStream(realPath + "/" + fileName);

                // 创建一个缓冲区
                byte[] buffer = new byte[1024 * 1024];
                // 判断是否读取完毕
                int len = 0;
                // 如果大于0说明还存在数据;
                while ((len = inputStream.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                // 关闭流
                fos.close();
                inputStream.close();

                msg = "文件上传成功!";
                fileItem.delete(); // 上传成功,清除临时文件
                //=============文件传输完成=============
            }
        }
        return msg;

    }
}
```

![image-20210816113752256](img/File/image-20210816110825147.png)

- 运行后，如下情况：

![image-20210816113836748](img/File/image-20210816113836748.png)

- 在pom.xml中添加jar包的maven;

```xml
    <dependency>
      <groupId>commons-fileupload</groupId>
      <artifactId>commons-fileupload</artifactId>
      <version>1.4</version>
    </dependency>
    <dependency>
      <groupId>commons-io</groupId>
      <artifactId>commons-io</artifactId>
      <version>2.6</version>
    </dependency>
```

- 再次运行；

![image-20210816114054823](img/File/image-20210816114054823.png)

- 显示成功，但文件在哪里？？？

![image-20210816114509156](img/File/image-20210816114509156.png)

## 17.邮件发送原理及实现

### 1.概述

> 传输协议

- SMTP协议
  发送邮件：
  我们通常把处理用户smtp请求(邮件发送请求)的服务器称之为SMTP服务器(邮件发送服务器)。
- POP3协议
  接收邮件：
  我们通常把处理用户pop3请求(邮件接收请求)的服务器称之为POP3服务器(邮件接收服务器)。

> 邮件收发原理
>
> - 请参考：[连接](https://www.jb51.net/article/125852.htm)

> 使用Java实现邮件发送需要使用到的类

- 我们将用代码完成邮件的发送。这在实际项目中应用的非常广泛，比如注册需要发送邮件进行账号激活，再比如A项目中利用邮件进行任务提醒等等。
- 使用Java发送Emai分简单，但是首先你应该准备 JavaMail API和 Java Activation framework。
- 得到两个jar包：
  - [mail.jar](https://mvnrepository.com/artifact/javax.mail/mail/1.4.7)
  - [activation.jar](https://mvnrepository.com/artifact/javax.activation/activation/1.1.1)

> 导入jar包时，报错：`Cannot resolve mail.jar:mail.jar:1.4`
>
> - 解决：maven重新导入如下。

```xml
    <dependency>
      <groupId>javax.activation</groupId>
      <artifactId>activation</artifactId>
      <version>1.1</version>
    </dependency>
    <dependency>
        <groupId>javax.mail</groupId>
        <artifactId>mail</artifactId>
        <version>1.4.7</version>
    </dependency>
```

- JavaMail是sωn公司（现以被甲骨文收购）为方便Java开发人员在应用程序中实现邮件发送和接收功能而提供的一套标准开发包，它支持一些常用的邮件协议，如前面所讲的SMTP,POP3，IMAP，还有MIME等。我们在使用 JavaMail！API编写邮件时，无须考虑邮件的底层实现细节，只要调用 Javamail开发包中相应的API类就可以了。

- 我们可以先尝试发送一封简单的邮件，确保电脑可以连接网络。  
  - 创建包含邮件服务器的网络连接信息的Session对象。
  - 创建代表邮件内容的Message对象； 
  - 创建Transport对象，连接服务器，发送Message，关闭连接；
- 主要有四个核心类，我们在编写程序时，记住这四个核心类，就很容易编写出Java邮件处理程序。

![image-20210816161821576](img/File/image-20210816161821576.png)

### 2.简单邮件

> 邮件分类

-  简单邮件：没有除了文字以外的其他所有文件(包括附件和图片、视频等)，即纯文本邮件；
-  复杂邮件：除了传统的文字信息外，还包括了一些非文字数据的邮件；

![image-20210816164151437](img/File/image-20210816164151437.png)

> 需要发送邮件首先就要我们的邮箱账号支持POP3和SMTP协议,所以我们需要开启邮箱的POP3+SMTP服务，然后我们需要复制下图中的授权码，这个授权码就相当于你的QQ密码，你可以使用你的邮箱账号+授权码来发送邮件,而SMTP服务器也就是使用这个来识别你的身份的。

![image-20210816162226437](img/File/image-20210816162226437.png)

```java
package com.github.test;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @Author: subei
 * @Description: 一封简单的邮件
 */
public class MailDemo01 {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");
        // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");
        // 需要验证用户名密码

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 使用JavaMail发送邮件的5个步骤

        // 1.创建定义整个应用程序所需的环境信息的 Session 对象
        // 使用QQ邮箱的时候才需要，其他邮箱不需要这一段代码
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            // 获取和SMTP服务器的连接对象
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、授权码
                return new PasswordAuthentication("XXXX@qq.com", "授权码");
            }
        });

        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);

        // 2.通过session得到transport对象
        Transport ts = session.getTransport();
        // 通过这一次和SMTP服务器的连接对象获取发送邮件的传输对象

        // 3.使用邮箱的用户名和授权码连上SMTP邮件服务器，即登陆
        ts.connect("smtp.qq.com", "XXXX@qq.com", "授权码");

        // 4.创建邮件对象MimeMessage——点击网页上的写信
        // 创建一个邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人——在网页上填写发件人
        message.setFrom(new InternetAddress("XXXX@qq.com"));
        // 设置发件人
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发——在网页上填写收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("XXXX@qq.com"));
        // 设置收件人
        // 邮件的标题——在网页上填写邮件标题
        message.setSubject("简单邮件发送实现");
        // 设置邮件主题
        // 邮件的文本内容——在网页上填写邮件内容
        message.setContent("<h2 style='color:red'>你好啊！</h2>", "text/html;charset=UTF-8");
        // 设置邮件的具体内容

        // 5.发送邮件——在网页上点击发送按钮
        ts.sendMessage(message, message.getAllRecipients());

        // 6.关闭连接对象，即关闭服务器上的连接资源
        ts.close();
    }
}
```

- 运行测试

![image-20210816164449987](img/File/image-20210816164449987.png)

### 3.复杂邮件

- 复杂邮件就是非纯文本的邮件，可能还包含了图片和附件等资源。
- **MIME（多用途互联网邮件扩展类型）**
- 先认识两个类一个名词：

- **MimeBodyPart类**

  -  javax.mail.internet.MimeBodyPart类表示的是一个MIME消息，它和MimeMessage类一样都是从Part接口继承过来。
  - 即一个MIME消息对应一个MimeBodyPart对象，而MimeBodyPart对象就是我们写的邮件内容中的元素。

- **MimeMultipart类**
  - javax.mail.internet.MimeMultipart是抽象类 Multipart的实现子类，它用来组合多个MIME消息。一个MimeMultipart对象可以包含多个代表MIME消息的MimeBodyPart对象。
  - 即一个MimeMultipart对象表示多个MimeBodyPart的集合，而一个MimeMultipart表示的就是我们一封邮件的内容。

- MimeMultipart对象的使用的时候需要设置setSubType()的属性值，一共就下面3种取值：

  - alternative：表明这个MimeMultipart对象中的MimeMessage对象的数据是纯文本文件；
  - related：表明这个MimeMultipart对象中的MimeMessage对象的数据包含非纯文本文件；
  - mixed：表明这个MimeMultipart对象中的MimeMessage对象的数据包含附件；

  > 我们在使得的时候如果不知道使用哪一个，直接使用mixed即可，使用这个属性值一定不会报错。

  ![image-20210816170022329](img/File/image-20210816170022329.png)

- 相较于简单邮件，复杂邮件变化的地方只是在于邮件内容本身会发送变化，而其他的步骤都是一样的：
  1. 准备一些参数；
  2. 获取session对象；
  3. 获取传输对象；
  4. 登陆授权；
  5. 写邮件 (和简单邮件相区别)；
  6. 发送邮件；
  7. 关闭服务器资源。

> 发送包含图片的复杂邮件

```java
package com.github.test;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

/**
 * @Author: subei
 * @Description: 图片的邮件
 */
public class MailDemo01 {
    public static void main(String[] args) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("mail.host", "smtp.qq.com");
        // 设置QQ邮件服务器
        prop.setProperty("mail.transport.protocol", "smtp");
        // 邮件发送协议
        prop.setProperty("mail.smtp.auth", "true");
        // 需要验证用户名密码

        // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        prop.put("mail.smtp.ssl.enable", "true");
        prop.put("mail.smtp.ssl.socketFactory", sf);

        // 使用JavaMail发送邮件的5个步骤

        // 1.创建定义整个应用程序所需的环境信息的 Session 对象
        // 使用QQ邮箱的时候才需要，其他邮箱不需要这一段代码
        Session session = Session.getDefaultInstance(prop, new Authenticator() {
            // 获取和SMTP服务器的连接对象
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                // 发件人邮件用户名、授权码
                return new PasswordAuthentication("XXXX@qq.com", "授权码");
            }
        });

        // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
        session.setDebug(true);

        // 2.通过session得到transport对象
        Transport ts = session.getTransport();
        // 通过这一次和SMTP服务器的连接对象获取发送邮件的传输对象

        // 3.使用邮箱的用户名和授权码连上SMTP邮件服务器，即登陆
        ts.connect("smtp.qq.com", "XXXX@qq.com", "授权码");

        // 4.创建邮件对象MimeMessage——点击网页上的写信
        // 创建一个邮件对象
        MimeMessage message = new MimeMessage(session);
        // 指明邮件的发件人——在网页上填写发件人
        message.setFrom(new InternetAddress("XXXX@qq.com"));
        // 设置发件人
        // 指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发——在网页上填写收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("XXXX@qq.com"));
        // 设置收件人
        // 邮件的标题——在网页上填写邮件标题
        message.setSubject("简单邮件发送实现");
        // 设置邮件主题

        System.out.println("=============================复杂邮件的邮件内容设置==================================");

        // 准备邮件数据

        // 准备图片数据
        // 获取一个图片的MimeBodyPart对象
        MimeBodyPart image = new MimeBodyPart();
        // 由于图片需要字符化才能传输，所以需要获取一个DataHandler对象
        DataHandler dh = new DataHandler(new FileDataSource("图片的绝对地址"));
        // 将图片序列化
        image.setDataHandler(dh);
        // 为图片的MimeBodyPart对象设置一个ID，我们在文字中就可以使用它了
        image.setContentID("p6.jpg");

        // 准备正文数据
        MimeBodyPart text = new MimeBodyPart();
        // 获取一个文本的MimeBodyPart对象
        text.setContent("这是一封邮件正文带图片<img src='cid:p6.jpg'>的邮件", "text/html;charset=UTF-8");
        // 设置文本内容，注意在里面嵌入了<img src='cid:p6.jpg'>

        // 描述数据关系
        // 获取MimeMultipart
        MimeMultipart mm = new MimeMultipart();
        // 将文本MimeBodyPart对象加入MimeMultipart中
        mm.addBodyPart(text);
        // 将图片MimeBodyPart对象加入MimeMultipart中
        mm.addBodyPart(image);
        // 设置MimeMultipart对象的相对熟悉为related，即发送的数据为文本+非附件资源
        mm.setSubType("related");

        // 设置到消息中，保存修改
        message.setContent(mm);
        // 将MimeMultipart放入消息对象中
        message.saveChanges();
        // 保存上面的修改

        System.out.println("===============================================================");

        // 5.发送邮件——在网页上点击发送按钮
        ts.sendMessage(message, message.getAllRecipients());

        // 6.关闭连接对象，即关闭服务器上的连接资源
        ts.close();
    }
}
```

- 只需修改第5步：

```java
        // 准备图片数据
        // 获取一个图片的MimeBodyPart对象
        MimeBodyPart image = new MimeBodyPart();
        // 由于图片需要字符化才能传输，所以需要获取一个DataHandler对象
        DataHandler dh = new DataHandler(new FileDataSource("图片的绝对地址"));
        // 将图片序列化
        image.setDataHandler(dh);
        // 为图片的MimeBodyPart对象设置一个ID，我们在文字中就可以使用它了
        image.setContentID("p6.jpg");

        // 准备正文数据
        MimeBodyPart text = new MimeBodyPart();
        // 获取一个文本的MimeBodyPart对象
        text.setContent("这是一封邮件正文带图片<img src='cid:p6.jpg'>的邮件", "text/html;charset=UTF-8");
        // 设置文本内容，注意在里面嵌入了<img src='cid:p6.jpg'>

        // 描述数据关系
        // 获取MimeMultipart
        MimeMultipart mm = new MimeMultipart();
        // 将文本MimeBodyPart对象加入MimeMultipart中
        mm.addBodyPart(text);
        // 将图片MimeBodyPart对象加入MimeMultipart中
        mm.addBodyPart(image);
        // 设置MimeMultipart对象的相对熟悉为related，即发送的数据为文本+非附件资源
        mm.setSubType("related");

        // 设置到消息中，保存修改
        message.setContent(mm);
        // 将MimeMultipart放入消息对象中
        message.saveChanges();
```

- 测试运行：

![image-20210816171744359](img/File/image-20210816171744359.png)

> 发送包含附件的复杂邮件

- 需修改的代码：

```java
        System.out.println("=============================复杂邮件的邮件内容设置==================================");

        // 图片
        MimeBodyPart body1 = new MimeBodyPart();
        body1.setDataHandler(new DataHandler(new FileDataSource("图片的绝对地址")));
        // 图片设置ID
        body1.setContentID("some.png");

        // 文本
        MimeBodyPart body2 = new MimeBodyPart();
        body2.setContent("请注意，这是文本附件<img src='cid:test.png'>","text/html;charset=utf-8");

        // 附件
        MimeBodyPart body3 = new MimeBodyPart();
        body3.setDataHandler(new DataHandler(new FileDataSource("附件1的绝对地址")));
        // 附件设置名字
        body3.setFileName("demo.c");

        MimeBodyPart body4 = new MimeBodyPart();
        body4.setDataHandler(new DataHandler(new FileDataSource("附件2的绝对地址")));
        // 附件设置名字
        body4.setFileName("demo.txt");

        // 拼装邮件正文内容
        MimeMultipart multipart1 = new MimeMultipart();
        multipart1.addBodyPart(body1);
        multipart1.addBodyPart(body2);
        // 1.文本和图片内嵌成功！
        multipart1.setSubType("related");

        // new MimeBodyPart().setContent(multipart1);
        // 将拼装好的正文内容设置为主体
        MimeBodyPart contentText =  new MimeBodyPart();
        contentText.setContent(multipart1);

        // 拼接附件
        MimeMultipart allFile =new MimeMultipart();
        // 附件
        allFile.addBodyPart(body3);
        // 附件
        allFile.addBodyPart(body4);
        // 正文
        allFile.addBodyPart(contentText);
        // 正文和附件都存在邮件中，所有类型设置为mixed；
        allFile.setSubType("mixed");

        // 设置到消息中，保存修改
        message.setContent(allFile);
        // 将MimeMultipart放入消息对象中
        message.saveChanges();
        // 保存上面的修改

        System.out.println("===============================================================");
```

- 测试运行：

![image-20210816172826825](img/File/image-20210816172826825.png)

### 4.网站注册发送邮件功能实现

- 创建一个javaWeb项目；

![image-20210816192521945](img/File/image-20210816192521945.png)

![image-20210816202351298](img/File/image-20210816202351298.png)

![image-20210816202432179](img/File/image-20210816202432179.png)

- 环境搭建完成，测试通过。
- 导入maven依赖——pom.xml：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>org.example</groupId>
    <artifactId>mail</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <!--   Servlet 依赖   -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>servlet-api</artifactId>
            <version>2.5</version>
        </dependency>
        <!--        JSP 依赖   -->
        <dependency>
            <groupId>javax.servlet.jsp</groupId>
            <artifactId>javax.servlet.jsp-api</artifactId>
            <version>2.3.3</version>
        </dependency>
        <!--        JSTL表达式的依赖-->
        <dependency>
            <groupId>javax.servlet.jsp.jstl</groupId>
            <artifactId>jstl-api</artifactId>
            <version>1.2</version>
        </dependency>
        <!--        standard标签库-->
        <dependency>
            <groupId>taglibs</groupId>
            <artifactId>standard</artifactId>
            <version>1.1.2</version>
        </dependency>

        <dependency>
            <groupId>javax.activation</groupId>
            <artifactId>activation</artifactId>
            <version>1.1</version>
        </dependency>
        <dependency>
            <groupId>javax.mail</groupId>
            <artifactId>mail</artifactId>
            <version>1.4.7</version>
        </dependency>

    </dependencies>

</project>
```

- 拷贝前端素材；
  - index.jsp——注册页面；
  - info.jsp——提示成功页面；

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<%--注册填写邮箱的前端页面--%>
<html>
<head>
  <title>注册</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/RegisterServlet.do" method="post">
  用户名：<input type="text" name="username"><br/>
  密码：<input type="password" name="password"><br/>
  邮箱：<input type="text" name="email"><br/>
  <input type="submit" value="注册">
</form>

</body>
</html>
```

```jsp
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>注册成功</title>
</head>
<body>

<h2>🎉注册成功🎉</h2>
${message}

</body>
</html>
```

- 编写User.java

```java
package com.github.pojo;

public class User {
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
```

- 编写工具类Sendmail.java

```java
package com.github.util;

/**
 * 多线程实现邮件发送
 * 使用多线程的原因就是提高用户的体验，一旦一个页面3s及以上的时间白屏就可能被用户关掉
 * 所以我们在用户提交表单之后，将费时的邮件发送工作使用一个子线程来完成，而主线程还是去完成它自己的事情
 * 这么做就可以提高用户体验，不然用户等待邮件发送的时间
 */

import com.github.pojo.User;
import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author subei
 * 多线程这种处理就可以称为异步处理
 */
public class Sendmail extends Thread{

    // 用于向客户发送邮件的邮箱
    private String from = "XXXX@qq.com";
    // 用于登陆SMTP服务器的用户名
    private String username = "XXXX@qq.com";
    // 授权码
    private String password = "授权码";
    // 发送邮件的地址
    private String host = "smtp.qq.com";

    private User user;
    public Sendmail(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        try {
            Properties prop = new Properties();
            // 设置QQ邮件服务器
            prop.setProperty("mail.host", host);
            // 邮件发送协议
            prop.setProperty("mail.transport.protocol", "smtp");
            // 需要验证用户名密码
            prop.setProperty("mail.smtp.auth", "true");

            // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);

            // 1.创建定义整个应用程序所需的环境信息的 Session 对象
            // 使用QQ邮箱的时候才需要，其他邮箱不需要这一段代码
            // 获取和SMTP服务器的连接对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    // 发件人邮件用户名、授权码
                    return new PasswordAuthentication("XXXX@qq.com", "授权码");
                }
            });

            // 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);

            // 2.通过session得到transport对象
            Transport ts = session.getTransport();

            // 3.使用邮箱的用户名和授权码连上SMTP邮件服务器，即登陆
            ts.connect(host, username, password);

            //4、创建邮件对象MimeMessage——点击网页上的写信
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
            message.setSubject("用户注册邮件！");
            message.setContent("<p><h2>恭喜注册成功！</h2></p>您的用户名为: <h4>"+user.getUsername()+
                    "</h4><p>您的密码:" + user.getPassword() +
                    "</p><p>请妥善保管您的密码，如有问题请及时联系网站客服，再次欢迎您的加入！！</p>", "text/html;charset=UTF-8");

            // 5.发送邮件——在网页上点击发送按钮
            ts.sendMessage(message, message.getAllRecipients());

            // 6.关闭连接对象，即关闭服务器上的连接资源
            ts.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```

- 编写Servlet.java

```java
package com.github.servlet;

import com.github.pojo.User;
import com.github.util.Sendmail;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.接收用户填写的表单数据
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        System.out.println(username+password+email);

        // 2.向用户邮箱发送邮件，注意发送邮件很耗时，所以我们启动一个子线程去做这件事，而用户则是直接跳转注册成功页面，以免降低用户体验
        User user = new User(username,password,email);
        // 获取子线程对象
        Sendmail sendmail = new Sendmail(user);
        // 启动子线程
        new Thread(sendmail).start();

        // 3.视图跳转
        req.setAttribute("message","注册成功！我们已经向您的邮箱发送了邮件，请您及时进行查收。由于网络原因，您收到邮件的时间存在延迟，敬请谅解~");
        req.getRequestDispatcher("info.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
```

- web.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
         version="4.0">
    
    <servlet>
        <servlet-name>RegisterServlet</servlet-name>
        <servlet-class>com.github.servlet.RegisterServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>RegisterServlet</servlet-name>
        <url-pattern>/RegisterServlet.do</url-pattern>
    </servlet-mapping>

</web-app>
```

- 运行测试，遇到如下报错。

![image-20210816220713042](img/File/image-20210816220713042.png)

- 检查Artifacts；

![image-20210816220834897](img/File/image-20210816220834897.png)

![image-20210816221739919](img/File/image-20210816221739919.png)

![image-20210816221810512](img/File/image-20210816221810512.png)

- ==再次运行，还是报错500==；
  - 将activation-1.1.jar、mail-1.4.7.jar导入到Tomcat中的lib文件夹中。

![image-20210816222056864](img/File/image-20210816222056864.png)

- 再次运行。

![image-20210816222219348](img/File/image-20210816222219348.png)

![image-20210816222304081](img/File/image-20210816222304081.png)

## 🎉🎉全剧终🎉🎉

