# Maven

> 参考[狂神的视频](https://www.bilibili.com/video/BV12J411M7Sj?p=5)，在这种方式更利于日常工作开发，有必要学习一下，进行如下记录。2020.10.24

> **为什么要学习这个技术？**

1. 在Java Web开发中，需要使用大量的jar包，**我们手动去导入，这种操作很麻烦，PASS！！！**

2. 如何能够让一个东西自动帮我导入和配置这个jar包。

   由此，Maven诞生了！

## 1 Maven项目架构管理工具

> 在Javaweb的学习中，就是用来就是方便导入jar包的！

> Maven的核心思想：**约定大于配置**。

- 有约束，不要去违反。

> Maven会规定好你该如何去编写自己的Java代码，必须要按照具体规范来。

## 2 下载安装Maven

> 官网：https://maven.apache.org/

![1603527338094](img/maven/01.png)

![1603527548720](img/maven/02.png)

> 下载完成后，解压即可；**建议将电脑上的所有环境都放在一个文件夹下，方便后期管理**。

## 3 配置环境变量

> **环境不进行配置也可以使用，但建议配置环境！！！**
>
> 在电脑的系统环境变量中，进行如下配置：

- 变量名：M2_HOME；变量值：你的maven目录下的bin目录。

![1603528065845](img/maven/03.png)

- 变量名：MAVEN_HOME；变量值：你的maven的目录。

![1603528132546](img/maven/04.png)

- 在系统的path中配置：

  ```java
  %MAVEN_HOME%\bin
  ```

![1603528264123](img/maven/05.png)

> 测试Maven是否安装成功，在cmd命令行输入：**mvn -version**，如下图则表示成功。

![1603528748180](img/maven/06.png)

## 4 阿里云镜像

- 镜像：[mirrors](https://developer.aliyun.com/article/512821)
  - 作用：加速我们的下载
- 国内建议使用阿里云的镜像。
- 在setttins.xml文件中找到<mirrors></mirrors>标签对,进行修改： 

![1603529236147](img/maven/07.png)

![1603529311973](img/maven/08.png)

```xml
<mirror>
    <id>nexus-aliyun</id>
    <mirrorOf>*</mirrorOf>
    <name>Nexus aliyun</name>
    <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror> 
```

## 5 本地仓库

> 本地仓库：在本地的仓库。

> **建立一个本地仓库：**localRepository

![1603529977818](img/maven/09.png)

![1603529857152](img/maven/10.png)

```xml
<localRepository>F:\Java\apache-maven-3.6.3\maven-repo</localRepository>
```

## 6 在IDEA中使用Maven

1. 启动IDEA

2. 创建一个MavenWeb项目


![1603531279758](img/maven/11.png)

![1603531573183](img/maven/12.png)

![1603532094975](img/maven/13.png)

###  新建maven项目时，Run中报警告：No archetype found in remote catalog. Defaulting to internal catalo在远程目录中没有找到原型。默认为内部编录。 

3. 等待项目初始化完毕。最后运行结果如下图：

![1603532235409](img/maven/14.png)

> 如上图所示，根据网络诸多结论，有两种方法，方法一：

```
在创建maven时，添加如下：
archetypeCatalog=internal
```

![1603533652591](img/maven/14-2.png)

![1603533356822](img/maven/15.png)

![1603533714482](img/maven/16.png)

> 另一种方法：**在maven的VM Options加上-DarchetypeCatalog=internal参数，如下：**  

![1603533820283](img/maven/17.png)

> **其实写这么多，忘了说一句，这个报错其实可以不用管，因为不论报错是否出现，只要出现如下图，就表示成功！！！**

![15](img/maven/18.png)

4. 观察maven仓库中多了什么东西？

![1603534127064](img/maven/19.png)

5. IDEA中的Maven设置

> 注意：IDEA项目创建成功后，看一眼Maven的配置。

![1603534235082](img/maven/20.png)

> IDEA中配置，Maven注意经常在IDEA中会出现一个问题就是项目自动创建完成后，它这个Maven Home会使用IDEA默认，如果发先了这个问题，手动改为本地的。

6. 到这里，Maven在IDEA中的配置和使用就OK了!

## 7 创建一个普通的Maven项目

![1603534613058](img/maven/21.png)

![1603534899611](img/maven/22.png)

> 这个只有在Web应用下才会有！

![1603534995974](img/maven/23.png)

---

### 创建中报错：Cannot resolve plugin org.apache.maven.plugins:maven-jar-plugin

![1603535152253](img/maven/24.png)

> 主要原因是本地maven的配置文件和仓库地址不一致。 

![1603535227793](img/maven/25.png)

![1603535273054](img/maven/26.png)

![1603536246393](img/maven/25-2.png)

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

## 8 标记文件夹功能

![1603536408719](img/maven/27.png)

![1603536489112](img/maven/28.png)

## 9 在 IDEA中配置Tomcat

### 9 在 IDEA中配置Tomcat

![1603539394443](img/maven/29.png)

![1603536877235](img/maven/30.png)

![1603538116795](img/maven/31.png)

> 解决警告问题。必须要的配置：**为什么会有这个问题：我们访问一个网站，需要指定一个文件夹名字；**

![1603538940350](img/maven/33.png)

![1603539497683](img/maven/34.png)

> 中间的地址，可以自定义。它是在浏览器中的访问地址。

![1603539589793](img/maven/35.png)

## 10 pom文件

> pom.xml是Maven的核心配置文件。

```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>github.subei</groupId>
  <artifactId>javaweb-01-maven</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>javaweb-01-maven Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

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
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
  </dependencies>

  <build>
    <finalName>javaweb-01-maven</finalName>
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

![1603541196785](img/maven/36.png)

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

## 11 解决遇到的问题

1. [Tomcat闪退](https://blog.csdn.net/qq904069486/article/details/80909780)

3. IDEA中每次都要重复配置Maven；在IDEA中的全局默认配置中去配置。
   
![1603540419408](img/maven/37-2.png)
   
![1603540293063](img/maven/38.png)
   
3. maven默认web项目中的web.xml版本问题

   ![1603539920417](img/maven/39.png)

4. 替换为webapp4.0版本和tomcat一致

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

5. Maven仓库的使用

   地址：https://mvnrepository.com/

   ![1603539750748](img/maven/40.png)

![1603539779464](img/maven/41.png)

![1603539803371](img/maven/42.png)

![1603539859940](img/maven/43.png)