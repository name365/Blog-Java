# SpringCloud快速入门

## 1.前言

> 学习前提

- 熟练使用SpringBoot 微服务快速开发框架
- 了解过Dubbo + Zookeeper 分布式基础
- 电脑配置内存不低于8G(个人是16G)

![image-20220210221321005](img/01/image-20220210221321005.png)

> SpringCloud五大组件

- 参考CSDN博文：https://blog.csdn.net/weixin_41217541/article/details/104718834

![img](img/01/springcloud.png)

| 组件           | 选型                    | 备注                                                         |
| -------------- | ----------------------- | ------------------------------------------------------------ |
| 网关           | Zuul                    |                                                              |
| 服务注册与发现 | Eureka Consul zookeeper |                                                              |
| 服务调用       | Feign                   | 根据注解和选择机器,拼接Url地址,发起请求 简化服务调用         |
| 负载均衡       | Ribbon                  | 服务调用负载均衡，配合Feign和Euraka使用                      |
| 断路器         | Hystrix                 | 隔离、熔断以及降级的一个框架 服务线程池隔离，实现不同服务的调度隔离，避免服务雪崩 |

- Eureka:服务启动时,Eureka会将服务注册到EurekaService,并且EurakeClient还可以返回过来从EurekaService拉去注册表,从而知道服务在哪里。
- Ribbon:服务间发起请求的时候,基于Ribbon服务做到负载均衡,从一个服务的对台机器中选择一台。
- Feign:基于fegin的动态代理机制,根据注解和选择机器,拼接Url地址,发起请求。
- Hystrix:发起的请求是通过Hystrix的线程池来走,不同的服走不同的线程池,实现了不同的服务调度隔离,避免服务雪崩的问题。
- Zuul:如果前端后端移动端调用后台系统,统一走zull网关进入,有zull网关转发请求给对应的服务。

> 常见面试题

1. 什么是微服务？

2. 微服务之间是如何独立通讯的？

3. SpringCloud 和 Dubbo有哪些区别？ 

4. SpringBoot和SpringCloud，请你谈谈对他们的理解 

5. 什么是服务熔断？什么是服务降级 

6. 微服务的优缺点是分别是什么？说下你在项目开发中遇到的坑 

7. 你所知道的微服务技术栈有哪些？请列举一二 

8. eureka和zookeeper都可以提供服务注册与发现的功能，请说说两个的区别？

## 2.微服务概述

> 什么是微服务？

微服务（Microservice Architecture）是近几年流行的一种架构思想，关于它的概念很 难一言以蔽之。究竟什么是微服务呢？我们在此引用 ThoughtWorks 公司的首席科学家 Martin Fowler 于2014年提出的一段话：

![img](img/01/format.png)

- 原文：https://martinfowler.com/articles/microservices.html 
- 汉化：https://www.cnblogs.com/liuning8023/p/4493156.html

- 就目前而言，对于微服务，业界并没有一个统一的，标准的定义 

- 但通常而言，微服务架构是一种架构模式，或者说是一种架构风格， 它提倡将单一的应用程序划分成一组小的服务，每个服务运行在其独立的自己的进程内，服务之间互相协调，互相配置，为用户提供最终价值。服务之间采用轻量级的通信机制互相沟通，每个服务都围绕着具体的业务进行构建，并且能够被 独立的部署到生产环境中，另外，应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言，工具对其进行构建，可以有一个非常轻量级的集中式管理来 协调这些服务，可以使用不同的语言来编写服务，也可以使用不同的数据存储； 

- 可能有的人觉得官方的话太过生涩，我们从技术维度来理解下：
- 微服务化的核心就是将传统的一站式应用，根据业务拆分成一个一个的服务，彻底地去耦合，每一个微服务提供单个业务功能的服务，一个服务做一件事情，从技术角度看就是一种小而独立的处理过程，类似进程的概念，能够自行单独启动或销毁，拥有自己独立的数据库。

> 微服务与微服务架构：

**微服务** 

- 强调的是服务的大小，他关注的是某一个点，是具体解决某一个问题/提供落地对应服务的一个服务应用，狭义的看，可以看做是IDEA中的一个个微服务工程，或者Moudel 

**微服务架构** 

- 一种新的架构形式，Martin Fowler，2014提出！ 

- 微服务架构是一种架构模式，它提倡将单一应用程序划分成一组小的服务，服务之间互相协调，互相配合，为用户提供最终价值。每个服务运行在其独立的进程中，服务于服务间采用轻量级的通信机制互相协作，每个服务都围绕着具体的业务进行构建，并且能够被独立的部署到生产环境中，另外，应尽量避免统一的，集中式的服务管理机制，对具体的一个服务而言，应根据业务上下文，选择合适的语言，工具对其进行构建。

**微服务优缺点**

- 优点：
  - 每个服务足够内聚，足够小，代码容易理解，这样能聚焦一个指定的业务功能或业务需求； 
  - 开发简单，开发效率提高，一个服务可能就是专一的只干一件事； 
  - 微服务能够被小团队单独开发，这个小团队是2~5人的开发人员组成； 
  - 微服务是松耦合的，是有功能意义的服务，无论是在开发阶段或部署阶段都是独立的。 微服务能使用不同的语言开发。 
  - 易于和第三方集成，微服务允许容易且灵活的方式集成自动部署，通过持续集成工具，如jenkins， Hudson，bamboo 
  - 微服务易于被一个开发人员理解，修改和维护，这样小团队能够更关注自己的工作成果。无需通过合作才能体现价值。 
  - 微服务允许你利用融合最新技术。 微服务只是业务逻辑的代码，不会和HTML，CSS或其他界面混合 
  - 每个微服务都有自己的存储能力，可以有自己的数据库，也可以有统一数据库

- 缺点： 
  - 开发人员要处理分布式系统的复杂性 
  - 多服务运维难度，随着服务的增加，运维的压力也在增大 
  - 系统部署依赖 
  - 服务间通信成本 
  - 数据一致性 
  - 系统集成测试 
  - 性能监控.....

> 微服务技术栈有哪些？

| 微服务条目                               | 落地技术                                                     |
| ---------------------------------------- | ------------------------------------------------------------ |
| 服务开发                                 | SpringBoot,Spring,SpringMVC                                  |
| 服务配置与管理                           | Netflix公司的Archaius、阿里的Diamond等                       |
| 服务注册与发现                           | Eureka、Consul、Zookeeper等                                  |
| 服务调用                                 | Rest、RPC、gRPC                                              |
| 服务熔断器                               | Hystrix、Envoy等                                             |
| 负载均衡                                 | Ribbon、Nginx等                                              |
| 服务接口调用（客户端调用服务的简化工具） | Feign等                                                      |
| 消息队列                                 | Kafka、RabbitMQ、ActiveMQ等                                  |
| 服务配置中心管理                         | SpringCloudConfig、Chef等                                    |
| 服务路由（API网关）                      | 服务监控                                                     |
| 服务监控                                 | Zabbix、Nagios、Metrics、Specatator等                        |
| 全链路追踪                               | Zipkin、Brave、Dapper等                                      |
| 服务部署                                 | Docker、OpenStack、Kubernetes等                              |
| 数据流操作开发包                         | SpringCloud Stream(封装与Redis，Rabbit，Kafka等发 送接收消息) |
| 事件消息总线                             | SpringCloud Bus                                              |

- Spring Cloud Alibaba

![Spring Cloud Alibaba](img/01/Spring%20Cloud%20Alibaba.png)

> 为什么选择SpringCloud作为微服务架构？

1、选型依据 

- 整体解决方案和框架成熟度 
- 社区热度 
- 可维护性 
- 学习曲线 

2、当前各大IT公司用的微服务架构有哪些？ 

- 阿里：dubbo+HFS 
- 京东：JSF 
- 新浪：Motan 
- 当当网 DubboX 
- ....... 

3、各微服务框架对比

| 功能点/ 服务框架 | Netflix/SpringCloud                                          | Motan                                                        | gRPC                        | Thrift   | Dubbo/DubboX                        |
| ---------------- | ------------------------------------------------------------ | ------------------------------------------------------------ | --------------------------- | -------- | ----------------------------------- |
| 功能定位         | 完整的微服务框架                                             | RPC框架，但整合了ZK或Consul，实现集群环境的基本服务注册/发现 | RPC框 架                    | RPC框架  | 服务框架                            |
| 支持Rest         | 是，Ribbon支持多种可插拔的序列化选择                         | 否                                                           | 否                          | 否       | 否                                  |
| 支持RPC          | 否                                                           | 是（Hession2）                                               | 是                          | 是       | 是                                  |
| 支持多语言       | 是（Rest形式）？                                             | 否                                                           | 是                          | 是       | 否                                  |
| 负载均衡         | 是（服务端zuul+客户端 Ribbon），zuul-服务，动态路由，云端负载均衡 Eureka（针对中间层服务器） | 是（客户端）                                                 | 否                          | 否       | 是（客户端）                        |
| 配置服务         | Netfix Archaius，Spring Cloud Config Server集中 配置         | 是（zookeeper提供）                                          | 否                          | 否       | 否                                  |
| 服务调用链监控   | 是（zuul），zuul提供边缘服务，API网关                        | 否                                                           | 否                          | 否       | 否                                  |
| 高可用/容错      | 是（服务端Hystrix+客户端Ribbon）                             | 是（客户端）                                                 | 否                          | 否       | 是（客户端）                        |
| 典型应用案例     | Netflix                                                      | Sina                                                         | Google                      | Facebook |                                     |
| 社区活跃程度     | 高                                                           | 一般                                                         | 高                          | 一般     | 2017年后重新开始维护，之前中断了5年 |
| 学习难度         | 中断                                                         | 低                                                           | 高                          | 高       | 低                                  |
| 文档丰富程度     | 高                                                           | 一般                                                         | 一般                        | 一般     | 高                                  |
| 其他             | Spring Cloud Bus为我们 的应用程序带来了更多管理端点          | 支持降级                                                     | Netflix 内部在开发集成 gRPC | IDL定义  | 实践的公司比较 多                   |

## 3.SpringCloud入门

> SpringCloud是什么？

- Spring官网:https://spring.io/
- 地址：[Spring (xy2401.com)](https://spring.io.xy2401.com/)

![image-20220210213122366](img/01/image-20220210213122366.png)

![img](img/01/diagram-distributed-systems.svg)

- SpringCloud, 基于SpringBoot提供了一套微服务解决方案，包括服务注册与发现，配置中心，全链路监控，服务网关，负载均衡，熔断器等组件，除了基于NetFlix的开源组件做高度抽象封装之外，还有一些选型中立的开源组件。 

- SpringCloud利用SpringBoot的开发便利性，巧妙地简化了分布式系统基础设施的开发，SpringCloud为开发人员提供了快速构建分布式系统的一些工具，==包括配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等==，他们都可以用SpringBoot的开发风格做到一键启动和部署。
- SpringBoot并没有重复造轮子，它只是将目前各家公司开发的比较成熟，经得起实际考研的服务框架组合起来，通过SpringBoot风格进行再封装，屏蔽掉了复杂的配置和实现原理，最终给开发者留出了一套简单易懂，易部署和易维护的分布式系统开发工具包。
- SpringCloud是分布式微服务架构下的一站式解决方案，是各个微服务架构落地技术的集合体，俗称微 服务全家桶。

> SpringCloud和SpringBoot关系

- SpringBoot专注于快速方便的开发单个个体微服务。
- SpringCloud是关注全局的微服务协调整理治理框架，它将SpringBoot开发的一个个单体微服务整合并管理起来，为各个微服务之间提供：配置管理，服务发现，断路器，路由，微代理，事件总线，全局锁，决策竞选，分布式会话等等集成服务。
- SpringBoot可以离开SpringClooud独立使用，开发项目，但是SpringCloud离不开SpringBoot，属于依赖关系。
- SpringBoot专注于快速、方便的开发单个个体微服务，SpringCloud关注全局的服务治理框架。

> ##### 分布式+服务治理Dubbo

- 目前成熟的互联网架构，应用服务化拆分 + 消息中间件。

![image-20220214150037631](img/01/image-20220211113445309.png)

> Dubbo和SpringCloud对比

- 可以看一下社区活跃度：
  - https://github.com/dubbo
  - https://github.com/spring-cloud
- 对比结果：

|              | Dubbo         | SpringCloud                  |
| ------------ | ------------- | ---------------------------- |
| 服务注册中心 | Zookeeper     | Spring Cloud Netfilx Eureka  |
| 服务调用方式 | RPC           | REST API                     |
| 服务监控     | Dubbo-monitor | Spring Boot Admin            |
| 断路器       | 不完善        | Spring Cloud Netfilx Hystrix |
| 服务网关     | 无            | Spring Cloud Netfilx Zuul    |
| 分布式配置   | 无            | Spring Cloud Config          |
| 服务跟踪     | 无            | Spring Cloud Sleuth          |
| 消息总栈     | 无            | Spring Cloud Bus             |
| 数据流       | 无            | Spring Cloud Stream          |
| 批量任务     | 无            | Spring Cloud Task            |

- ==最大区别：SpringCloud抛弃了Dubbo的RPC通信，采用的是基于HTTP的REST方式==。 

- 严格来说，这两种方式各有优劣。虽然从一定程度上来说，后者牺牲了服务调用的性能，但也避免了上 面提到的原生RPC带来的问题。而且REST相比RPC更为灵活，服务提供方和调用方的依赖只依靠一纸契约，不存在代码级别的强依赖，这在强调快速演化的微服务环境下，显得更加合适。 

> 品牌机与组装机的区别 

- 很明显，Spring Cloud的功能比DUBBO更加强大，涵盖面更广，而且作为Spring的拳头项目，它也能够与Spring Framework、Spring Boot、Spring Data、Spring Batch等其他Spring项目完美融合，这些对 于微服务而言是至关重要的。使用Dubbo构建的微服务架构就像组装电脑，各环节我们的选择自由度很 高，但是最终结果很有可能因为一条内存质量不行就点不亮了，总是让人不怎么放心，但是如果你是一名高手，那这些都不是问题；而Spring Cloud就像品牌机，在Spring Source的整合下，做了大量的兼容性测试，保证了机器拥有更高的稳定性，但是如果要在使用非原装组件外的东西，就需要对其基础有足够的了解。 

> 社区支持与更新力度 

- 最为重要的是，DUBBO停止了5年左右的更新，虽然2017.7重启了。对于技术发展的新需求，需要由开发者自行拓展升级（比如当当网弄出了DubboX），这对于很多想要采用微服务架构的中小软件组织，显然是不太合适的，中小公司没有这么强大的技术能力去修改Dubbo源码+周边的一整套解决方案，并不是每一个公司都有阿里的大牛+真实的线上生产环境测试过。 
- 设计模式+微服务拆分思想。

> 总结： 

- 曾风靡国内的开源 RPC 服务框架Dubbo在重启维护后，令许多用户为之雀跃，但同时，也迎来了一些质疑的声音。互联网技术发展迅速，Dubbo是否还能跟上时代？Dubbo 与 Spring Cloud相比又有何优势和差异？是否会有相关举措保证Dubbo的后续更新频率？ 
- 人物：Dubbo重启维护开发的刘军，主要负责人之一。
  - 刘军，阿里巴巴中间件高级研发工程师，主导了Dubbo 重启维护以后的几个发版计划，专注于高性能 RPC 框架和微服务相关领域。曾负责网易考拉RPC框架的研发及指导在内部使用，参与了服务治理平台、分布式跟踪系统、分布式一致性框架等从无到有的设计与开发过程。 
- ==解决的问题域不一样：Dubbo的定位是一款RPC框架，Spring Cloud的目标是微服务架构下的一站式解决方案==。

> SpringCloud能干嘛？

- Distributed/versioned configuration （分布式/版本控制配置） 
- Service registration and discovery（服务注册与发现） 
- Routing（路由） 
- Service-to-service calls（服务到服务的调用） 
- Load balancing（负载均衡配置） 
- Circuit Breakers（断路器） 
- Distributed messaging（分布式消息管理） 
- ....

> SpringCloud在哪获取？

- 官网：http://projects.spring.io/spring-cloud/
- 它的版本号有点特别：

![image-20220210215814438](img/01/image-20220210215814438.png)

- Spring Cloud是一个由众多独立子项目组成的大型综合项目，每个子项目有不同的发行节奏，都维护着自己的发布版本号。Spring Cloud通过一个资源清单BOM（Bill of Materials）来管理每个版本的子项目清单。为避免与子项目的发布号混淆，所以`没有采用版本号的方式，而是通过命名的方式`。
- 这些版本名称的命名方式`采用了伦敦地铁站的名称，同时根据字母表的顺序来对应版本时间顺序`，比如：最早的Release版本：Angel，第二个Release版本：Brixton，然后是Camden、Dalston、Edgware，Finchley,目前最新的是Hoxton 版本。 

> 自学参考： 

- SpringCloud Netflix 中文文档：[https://springcloud.cc/spring-cloud-netflix.html](https://springcloud.cc/spring-cloud-netflix.html)
- SpringCloud 中文API文档(官方文档翻译版)：[https://springcloud.cc/spring-cloud-dalston.html](https://springcloud.cc/spring-cloud-dalston.html)
- SpringCloud中国社区：[http://docs.springcloud.cn/](http://docs.springcloud.cn/)
- SpringCloud中文网：[https://springcloud.cc](https://springcloud.cc)

## 4.Rest微服务构建

>介绍

- 使用一个Dept部门模块做一个微服务通用案例。 
- Consumer消费者（Client）通过REST调用Provider提供者（Server）提供的服务。 
- 回忆Spring，SpringMVC，MyBatis等以往学习的知识。。。 
- Maven的分包分模块架构复习

```xml
一个简单的Maven模块结构是这样的：
-- app-parent：一个父项目（app-parent）聚合很多子项目（app-util，app-dao，appweb...）
|-- pom.xml
|
|-- app-core
||----pom.xml
|
|-- app-web
||----pom.xml
......
```

一个父工程带着多个子Module子模块。

SpringCloud父工程（Project）下初次带着3个子模块（Module）

- springcloud-api 【封装的整体entity / 接口 / 公共配置等】 
- springcloud-provider-dept-8001【服务提供者】 
- springcloud-consumer-dept-80【服务消费者】

> SpringCloud版本选择

| SpringBoot | SpringCloud             | 关系                                     |
| ---------- | ----------------------- | ---------------------------------------- |
| 1.2.x      | Angel版本(天使)         | 兼容SpringBoot1.2x                       |
| 1.3.x      | Brixton版本(布里克斯顿) | 兼容SpringBoot1.3x，也兼容SpringBoot1.4x |
| 1.4.x      | Camden版本(卡姆登)      | 兼容SpringBoot1.4x，也兼容SpringBoot1.5x |
| 1.5.x      | Dalston版本(多尔斯顿)   | 兼容SpringBoot1.5x，不兼容SpringBoot2.0x |
| 1.5.x      | Edgware版本(埃奇韦尔)   | 兼容SpringBoot1.5x，不兼容SpringBoot2.0x |
| 2.0.x      | Finchley版本(芬奇利)    | 兼容SpringBoot2.0x，不兼容SpringBoot1.5x |
| 2.1.x      | Greenwich版本(格林威治) |                                          |

- **实际开发版本关系**

| spring-boot-starter-parent |              | spring-cloud-dependencles |              |
| -------------------------- | ------------ | ------------------------- | ------------ |
| **版本号**                 | **发布日期** | **版本号**                | **发布日期** |
| 1.5.2.RELEASE              | 2017-03      | Dalston.RC1               | 2017-x       |
| 1.5.9.RELEASE              | 2017-11      | Edgware.RELEASE           | 2017-11      |
| 1.5.16.RELEASE             | 2018-04      | Edgware.SR5               | 2018-10      |
| 1.5.20.RELEASE             | 2018-09      | Edgware.SR5               | 2018-10      |
| 2.0.2.RELEASE              | 2018-05      | Fomchiey.BULD-SNAPSHOT    | 2018-x       |
| 2.0.6.RELEASE              | 2018-10      | Fomchiey-SR2              | 2018-10      |
| 2.1.4.RELEASE              | 2019-04      | Greenwich.SR1             | 2019-03      |

- **使用后两个**

> 创建父工程

- 新建父工程Maven项目 springcloud-parent，切记Packageing是pom模式。
- 主要是定义POM文件，将后续各个子模块公用的jar包等统一提取出来，类似一个抽象父类。

![image-20220211120018667](img/01/image-20220211120018667.png)

- pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.github</groupId>
    <artifactId>springcloud</artifactId>
    <version>1.0-SNAPSHOT</version>
    <modules>
    </modules>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <junit.version>4.13.2</junit.version>
        <log4j.version>1.2.17</log4j.version>
        <lombok.version>1.18.22</lombok.version>
    </properties>

    <!--打包方式  pom-->
    <packaging>pom</packaging>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-alibaba-dependencies</artifactId>
                <version>0.2.0.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--springCloud的依赖-->
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-dependencies</artifactId>
                <version>Greenwich.SR1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--SpringBoot-->
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>2.1.4.RELEASE</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
            <!--数据库-->
            <dependency>
                <groupId>mysql</groupId>
                <artifactId>mysql-connector-java</artifactId>
                <version>5.1.47</version>
            </dependency>
            <dependency>
                <groupId>com.alibaba</groupId>
                <artifactId>druid</artifactId>
                <version>1.1.10</version>
            </dependency>
            <!--SpringBoot 启动器-->
            <dependency>
                <groupId>org.mybatis.spring.boot</groupId>
                <artifactId>mybatis-spring-boot-starter</artifactId>
                <version>1.3.2</version>
            </dependency>
            <!--日志测试~-->
            <dependency>
                <groupId>ch.qos.logback</groupId>
                <artifactId>logback-core</artifactId>
                <version>1.2.3</version>
            </dependency>
            <dependency>
                <groupId>junit</groupId>
                <artifactId>junit</artifactId>
                <version>${junit.version}</version>
            </dependency>
            <dependency>
                <groupId>log4j</groupId>
                <artifactId>log4j</artifactId>
                <version>${log4j.version}</version>
            </dependency>
            <dependency>
                <groupId>org.projectlombok</groupId>
                <artifactId>lombok</artifactId>
                <version>${lombok.version}</version>
            </dependency>
        </dependencies>
    </dependencyManagement>

</project>
```

> 创建api公共模块

- 新建springcloud-api模块

![image-20220213093123243](img/01/image-20220213093123243.png)

- 可以观察发现，在父工程中多了一个Modules。

![image-20220213093440880](img/01/image-20220213093440880.png)

- 编写springcloud-api 的 pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>com.github</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <!--当前Module的名字-->
    <artifactId>springcloud-api</artifactId>

    <!--当前Module需要到的jar包，按自己需求添加，如果父项目已经包含了，可以不用写版本号-->
    <dependencies>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
        </dependency>
    </dependencies>
    
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

</project>
```

- 创建部门数据库脚本，数据库名：springcloud

```mysql
CREATE TABLE dept ( deptno BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, dname VARCHAR ( 60 ), db_source VARCHAR ( 60 ) );

INSERT INTO dept ( dname, db_source )
VALUES
	(
		'开发部',
	DATABASE ());
INSERT INTO dept ( dname, db_source )
VALUES
	(
		'人事部',
	DATABASE ());
INSERT INTO dept ( dname, db_source )
VALUES
	(
		'财务部',
	DATABASE ());
INSERT INTO dept ( dname, db_source )
VALUES
	(
		'市场部',
	DATABASE ());
INSERT INTO dept ( dname, db_source )
VALUES
	(
		'运维部',
	DATABASE ());
	
SELECT
	* 
FROM
	dept;
```

- 编写实体类，注意：实体类都序列化！

```java
package com.github.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@NoArgsConstructor
@Data
@Accessors(chain = true) // 链式写法
public class Dept implements Serializable {
    // Dept(实体类) orm mysql->Dept(表) 类表关系映射
    private Long deptno; // 主键
    private String dname; // 部门名称
    // 来自哪个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存到多个不同的数据库
    private String db_source;
    public Dept(String dname) {
        this.dname = dname;
    }

    /*
    链式写法：
        Dept dept = new Dept()
        dept.setDeptno(11L).setDname("school").setDb_source("DB01");
     */
}
```

> 创建provider模块

- 新建springcloud-provider-dept-8001模块 
- 编辑pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>com.github</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springcloud-provider-dept-8001</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!--引入自定义的模块，我们就可以使用这个模块中的类了-->
        <dependency>
            <groupId>com.github</groupId>
            <artifactId>springcloud-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>druid</artifactId>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
        </dependency>
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jetty</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-test</artifactId>
        </dependency>
        <!-- spring-boot-devtools热部署 -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>

</project>
```

- 编辑 application.yml

```yaml
server:
  port: 8001
# mybatis的配置
mybatis:
  config-location: classpath:mybatis/mybatis-config.xml
  type-aliases-package: com.github.pojo
  mapper-locations:
    - classpath:mybatis/mapper/**/*.xml
# spring的相关配置
spring:
  application:
    name: springcloud-provider-dept
  datasource:
    type: com.alibaba.druid.pool.DruidDataSource # 数据源
    driver-class-name: org.gjt.mm.mysql.Driver # mysql驱动
    url: jdbc:mysql://localhost:3306/springcloud #数据库名称
    username: root
    password: root
    dbcp2:
      min-idle: 5 #数据库连接池的最小维持连接数
      initial-size: 5 #初始化连接数
      max-total: 5 #最大连接数
      max-wait-millis: 200 #等待连接获取的最大超时时间
```

- 根据配置新建mybatis-config.xml文件

![image-20220213102426606](img/01/image-20220213102426606.png)

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    
    <settings>
        <!--开启二级缓存-->
        <setting name="cacheEnabled" value="true"/>
    </settings>
    
</configuration>
```

- 编写部门的dao接口

```java
@Mapper
public interface DeptDao {
    public boolean addDept(Dept dept); // 添加一个部门
    public Dept queryById(Long id); // 根据id查询部门
    public List<Dept> queryAll(); // 查询所有部门
}
```

- 接口对应的Mapper.xml文件 mybatis\mapper\DeptMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.github.dao.DeptDao">
    
    <insert id="addDept" parameterType="Dept">
        insert into dept (dname,db_source) values (#{dname},DATABASE());
    </insert>
    
    <select id="queryById" resultType="Dept" parameterType="Long">
        select deptno,dname,db_source from dept where deptno = #{deptno};
    </select>
    
    <select id="queryAll" resultType="Dept">
        select deptno,dname,db_source from dept;
    </select>
</mapper>
```

- 创建Service服务层接口

```java
public interface DeptService {
    public boolean addDept(Dept dept); // 添加一个部门
    public Dept queryById(Long id); // 根据id查询部门
    public List<Dept> queryAll(); // 查询所有部门
}
```

- ServiceImpl实现类

```java
@Service
public class DeptServiceImpl implements DeptService {
    // 自动注入
    @Autowired
    private DeptDao deptDao;
    @Override
    public boolean addDept(Dept dept) {
        return deptDao.addDept(dept);
    }
    @Override
    public Dept queryById(Long id) {
        return deptDao.queryById(id);
    }
    @Override
    public List<Dept> queryAll() {
        return deptDao.queryAll();
    }
}
```

- ServiceImpl实现类

```java
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService service;
    // @RequestBody
    // 如果参数是放在请求体中，传入后台的话，那么后台要用@RequestBody才能接收到
    @PostMapping("/add")
    public boolean addDept(@RequestBody Dept dept) {
        return service.addDept(dept);
    }
    @GetMapping("/get/{id}")
    public Dept get(@PathVariable("id") Long id) {
        return service.queryById(id);
    }
    @GetMapping("/list")
    public List<Dept> queryAll() {
        return service.queryAll();
    }
}
```

- 编写DeptProvider的主启动类

```java
@SpringBootApplication
public class DeptProvider8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001.class,args);
    }
}
```

- 启动测试，注意编写细节：

![image-20220213104354049](img/01/image-20220213104354049.png)

![image-20220213103228463](img/01/image-20220213103228463.png)

> 创建consumer模块

- 新建springcloud-consumer-dept-80模块 
- 编辑pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>com.github</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springcloud-consumer-dept-80</artifactId>
    <description>部门微服务消费者</description>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <dependency>
            <groupId>com.github</groupId>
            <artifactId>springcloud-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>
    
</project>
```

- application.yml配置文件

```yaml
server:
  port: 80
```

- 新建一个ConfigBean包注入 RestTemplate！

```java
@Configuration
public class ConfigBean {

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
```

- 创建Controller包，编写DeptConsumerController类

```java
@RestController
public class DeptConsumerController {
    // 理解：消费者，不应该有service层
    // 使用RestTemplate访问restful接口非常的简单粗暴且无脑
    // （url，requestMap，ResponseBean.class） 这三个参数分别代表
    // REST请求地址，请求参数，Http响应转换被转换成的对象类型

    @Autowired
    private RestTemplate restTemplate;

    private static final String REST_URL_PREFIX = "http://localhost:8001";
    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return
                restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return
                restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }
    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return
                restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }
}
```

- 了解RestTemplate：
  - RestTemplate提供了多种便捷访问远程Http服务的方法，是一种简单便捷的访问restful服务模板类，是Spring提供的用于访问Rest服务的客户端模板工具集。
  - 使用RestTemplate访问restful接口非常的简单粗暴且无脑 （url，requsetMap，ResponseBean.class）这三个参数分别代表REST请求地址，请求参数，Http响应转换被转换成的对象类型。

- 主启动类

```java
@SpringBootApplication
public class DeptConsumer80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80.class,args);
    }
}
```

- 测试访问，`先启动服务方：DeptProvider8001，再启动消费方：DeptConsumer80`：

![image-20220213112941611](img/01/image-20220213112941611.png)

![image-20220213115009217](img/01/image-20220213115009217.png)

## 5.Eureka服务注册与发现

> 什么是[Eureka](https://baike.baidu.com/item/Eureka/22402835)？

- Eureka（服务发现框架）
- Netflix 在设计Eureka时，遵循的就是API原则！
- CAP原则又称CAP定理，指的是在一个分布式系统中，一致性（Consistency）、可用性（Availability）、分区容错性（Partition tolerance）。CAP 原则指的是，这三个要素最多只能同时实现两点，不可能三者兼顾。
- Eureka是Netflix的一个子模块，也是核心模块之一。Eureka是一个基于REST的服务，用于定位服务，以实现云端中间层服务发现和故障转移，服务注册与发现对于微服务来说是非常重要的，有了服务发现 与注册，只需要使用服务的标识符，就可以访问到服务，而不需要修改服务调用的配置文件了，功能类 似于Dubbo的注册中心，比如Zookeeper。

>Eureka的基本架构：

- Springcloud 封装了Netflix公司开发的Eureka模块来实现服务注册与发现 (对比Zookeeper)。
- SpringCloud将它集成在其子项目spring-cloud-netflix中，以实现SpringCloud的服务发现功能。
- Eureka采用了C-S的架构设计，EurekaServer作为服务注册功能的服务器，他是服务注册中心。
- 而系统中的其他微服务，使用Eureka的客户端连接到EurekaServer并维持心跳连接。这样系统的维护人员就可以通过EurekaServer来监控系统中各个微服务是否正常运行，Springcloud 的一些其他模块 (比如Zuul) 就可以通过EurekaServer来发现系统中的其他微服务，并执行相关的逻辑。

![image-20220213163150726](img/02/image-20220213163150726.png)

- 和Dubbo架构对比：

![//imgs/architecture.png](img/02/architecture.png)

- Eureka包含两个组件：Eureka Server和Eureka Client。
- Eureka Server提供服务注册服务，各个节点启动后，会在Eureka Server中进行注册，这样EurekaServer中的服务注册表中将会存储所有可用服务节点的信息，服务节点的信息可以在界面中直观的看到。
- Eureka Client是一个[java](https://baike.baidu.com/item/java/85979)客户端，用于简化与Eureka Server的交互，客户端同时也就是一个内置的、使用轮询(round-robin)负载算法的[负载均衡器](https://baike.baidu.com/item/负载均衡器/8852239)。
- 在应用启动后，将会向Eureka Server发送心跳,默认周期为30秒，如果Eureka Server在多个心跳周期内没有接收到某个节点的心跳，Eureka Server将会从服务注册表中把这个服务节点移除(默认90秒)。

> 三大角色：

- Eureka Server：提供服务的注册于发现。 
- Service Provider：将自身服务注册到Eureka中，从而使消费方能够找到。 
- Service Consumer：服务消费方从Eureka中获取注册服务列表，从而找到消费服务。

> 服务构建

- 建立springcloud-eureka-7001模块 
- 编辑pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>springcloud</artifactId>
        <groupId>com.github</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>springcloud-eureka-7001</artifactId>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <dependencies>
        <!-- eureka-server服务端 -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka-server</artifactId>
            <version>1.4.6.RELEASE</version>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>

</project>
```

- application.yml

```yaml
server:
  port: 7001

# Eureka配置
eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    register-with-eureka: false #是否将自己注册到Eureka服务器中，本身是服务器，无需注册
    fetch-registry: false #false表示自己端就是注册中心，职责是维护服务实例，并不需要检索服务
    service-url: 
      defaultzone: http://${eureka.instance.hostname}:${server.port}/eureka/
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个defaultzone地址
```

- 编写主启动类

```java
package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer // EurekaServer服务端启动类，接受其他微服务注册进来
public class EurekaServer7001 {
    public static void main(String[] args){
        SpringApplication.run(EurekaServer7001.class,args);
    }
}
```

- 启动，访问测试：

![image-20220213203915542](img/02/image-20220213203915542.png)

- System Status：系统信息；
- DS Replicas：服务器副本； 
- Instances currently registered with Eureka：已注册的微服务列表；
- General Info：一般信息；
- Instance Info：实例信息。

> Service Provider

- 将 8001 的服务入驻到 7001 的eureka中！ 

1. 修改8001服务的pom文件，增加eureka的支持！

```xml
<!--将服务的provider注册到eureka中-->
<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-eureka -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-eureka</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>
```

2. yaml 中配置 eureka 的支持

```yaml
# Eureka配置：配置服务注册中心地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/
```

3. 8001 的主启动类注解支持

```java
@SpringBootApplication
@EnableEurekaClient // 本服务启动之后会自动注册进Eureka中！
public class DeptProvider8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProvider8001.class,args);
    }
}
```

- 截止目前：服务端也有了，客户端也有了，`启动7001，再启动8001，测试访问`：

![image-20220213210328915](img/02/image-20220213210328915.png)

> actuator与注册微服务信息完善

- 主机名称：服务名称修改

![image-20220213210438955](img/02/image-20220213210438955.png)

- 在8001的yaml中修改一下配置。

```yaml
# Eureka配置：配置服务注册中心地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/
  instance:
    instance-id: springcloud-provider-dept8001 # 与client平级
```

- 重启，刷新后查看结果！

![image-20220213210743283](img/02/image-20220213210743283.png)

- 访问信息有IP信息提示

![image-20220213210821598](img/02/image-20220213210821598.png)

- yaml中在增加一个配置:

```yaml
# Eureka配置：配置服务注册中心地址
eureka:
  client:
    service-url:
      defaultZone: http://localhost:7001/eureka/
  instance:
    instance-id: springcloud-provider-dept8001 # 与client平级
    prefer-ip-address: true # true表示访问路径可以显示IP地址
```

- 现在点击info，出现ERROR页面:

![image-20220213211159537](img/02/image-20220213211159537.png)

- 修改8001的pom文件，新增依赖！

```xml
<!--actuator监控信息完善-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

- 然后回到8001的yaml配置文件中修改增加信息：

```yaml
# info配置
info:
  app.name: subei-springcloud  # 项目的名称
  company.name: www.subeily.top  # 公司的名称
```

- 重启项目测试：7001、8001

![image-20220214112924279](img/02/image-20220214112924279.png)

- 这里`没出来的可以将spring boot版本降级即可`！
- 如果还没出来，可以添加如下配置：

```yaml
management:
  endpoints:
    web:
      exposure:
        include: "*"
```

> Eureka的自我保护机制：好死不如赖活着

- 之前出现的这些红色情况，没出现的，修改一个服务名，故意制造错误！

![image-20220214115519422](img/02/image-20220214115519422.png)

- 一句话总结就是：某时刻某一个微服务不可用，eureka不会立即清理，依旧会对该微服务的信息进行保存！
- 默认情况下，当eureka server在一定时间内没有收到实例的心跳，便会把该实例从注册表中删除（==默认是90秒==），但是，如果短时间内丢失大量的实例心跳，便会触发eureka server的自我保护机制，比如在开发测试时，需要频繁地重启微服务实例，但是我们很少会把eureka server一起重启（因为在开发过程中不会修改eureka注册中心），==当一分钟内收到的心跳数大量减少时，会触发该保护机制。可以在eureka管理界面看到Renews threshold和Renews(last min)，当后者（最后一分钟收到的心跳数）小于前者（心跳阈值）的时候，触发保护机制==，会出现红色的警告：`EMERGENCY!EUREKA MAY BE INCORRECTLY CLAIMING INSTANCES ARE UP WHEN THEY'RE NOT.RENEWALS ARE LESSER THAN THRESHOLD AND HENCE THE INSTANCES ARE NOT BEGING EXPIRED JUST TO BE SAFE.`
- 从警告中可以看到，eureka认为虽然收不到实例的心跳，但它认为实例还是健康的，eureka会保护这些实例，不会把它们从注册表中删掉。
- 该保护机制的目的是避免网络连接故障，在发生网络故障时，微服务和注册中心之间无法正常通信，但服务本身是健康的，不应该注销该服务，如果eureka因网络故障而把微服务误删了，那即使网络恢复了，该微服务也不会重新注册到eureka server了，因为只有在微服务启动的时候才会发起注册请求，后面只会发送心跳和服务列表请求，这样的话，该实例虽然是运行着，但永远不会被其它服务所感知。所以，eureka server在短时间内丢失过多的客户端心跳时，会进入自我保护模式，该模式下，eureka会保护注册表中的信息，不在注销任何微服务，当网络故障恢复后，eureka会自动退出保护模式。自我保护模式可以让集群更加健壮。
- 但是我们在开发测试阶段，需要频繁地重启发布，如果触发了保护机制，则旧的服务实例没有被删除，这时请求有可能跑到旧的实例中，而该实例已经关闭了，这就导致请求错误，影响开发测试。
- 所以，在开发测试阶段，可以把自我保护模式关闭，只需在eureka server配置文件中加上如下配置即可：`eureka.server.enable-self-preservation=false`【不推荐关闭自我保护机制】

- 详细可以参考：[博客](https://blog.csdn.net/wudiyong22/article/details/80827594)

> 8001服务发现Discovery

- 对于注册进eureka里面的微服务，可以通过服务发现来获得该服务的信息。【对外暴露服务】 
- 修改springcloud-provider-dept-8001工程中的DeptController，并新增一个方法。

![image-20220214125858655](img/02/image-20220214125858655.png)

```java
/**
  * DiscoveryClient 可以用来获取一些配置的信息，得到具体的微服务！
  */
@Autowired
private DiscoveryClient client;

/**
  * 获取一些注册进来的微服务的信息~
  * @return
  */
@GetMapping("/discovery")
public Object discovery() {
    // 获取微服务列表的清单
    List<String> services = client.getServices();
    System.out.println("discovery=>services:" + services);
    // 得到一个具体的微服务信息,通过具体的微服务id，applicaioinName；
    List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT");
    for (ServiceInstance instance : instances) {
        System.out.println(
            instance.getHost() + "\t" + // 主机名称
            instance.getPort() + "\t" + // 端口号
            instance.getUri() + "\t" + // uri
            instance.getServiceId() // 服务id
        );
    }
    return this.client;
}
```

- 主启动类增加一个注解：

![image-20220214130051064](img/02/image-20220214130051064.png)

- 启动Eureka服务，启动8001提供者，访问测试 http://localhost:8001/dept/discovery 

![image-20220214131406463](img/02/image-20220214131406463.png)

- 后台输出：

![image-20220214131422446](img/02/image-20220214131422446.png)

> consumer访问服务：

- 进入springcloud-consumer-dept-80，修改DeptConsumerController增加一个方法

```java
@GetMapping("/consumer/dept/discovery")
public Object discovery(){
    return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discovery",Object.class);
}
```

- 启动 80 项目进行测试！`先启动8001服务，再启动80`

![image-20220214132540222](img/02/image-20220214132540222.png)

> Eureka：集群环境配置

- 集群配置分析：

![image-20220214134108853](img/02/image-20220214134108853.png)

- 新建工程springcloud-eureka-7002、springcloud-eureka-7003；
- 为pom.xml添加依赖 (与springcloud-eureka-7001相同)

```xml
<dependencies>
    <!-- eureka-server服务端 -->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka-server</artifactId>
        <version>1.4.6.RELEASE</version>
    </dependency>
    <!--热部署-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
</dependencies>
```

- application.yml配置(与springcloud-eureka-7001相同)

```yaml
server:
  port: 7003

# Eureka配置
eureka:
  instance:
    hostname: localhost #eureka服务端的实例名称
  client:
    register-with-eureka: false #是否将自己注册到Eureka服务器中，本身是服务器，无需注册
    fetch-registry: false #false表示自己端就是注册中心，职责是维护服务实例，并不需要检索服务
    service-url:
      defaultzone: http://${eureka.instance.hostname}:${server.port}/eureka/
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个defaultzone地址
```

- 主启动类(与springcloud-eureka-7001相同)

```java
@SpringBootApplication
@EnableEurekaServer // EurekaServer服务端启动类，接受其他微服务注册进来
public class EurekaServer7003 {
    public static void main(String[] args){
        SpringApplication.run(EurekaServer7003.class,args);
    }
}
```

- 集群成员相互关联，修改映射配置 , windows域名映射。
- 配置一些自定义本机名字，找到本机hosts文件并打开：

![image-20220214135915497](img/02/image-20220214135915497.png)

- 在hosts文件最后加上，要访问的本机名称，默认是localhost

![image-20220214140136658](img/02/image-20220214140136658.png)

- 修改application.yml的配置，如图为springcloud-eureka-7001配置，springcloud-eureka-7002/springcloud-eureka-7003同样分别修改为其对应的名称即可。

![image-20220214140235957](img/02/image-20220214140235957.png)

- 在集群中使springcloud-eureka-7001关联springcloud-eureka-7002、springcloud-eureka-7003

- 完整的springcloud-eureka-7001下的application.yml如下

```yaml
server:
  port: 7001

# Eureka配置
eureka:
  instance:
    hostname: eureka7001.com #eureka服务端的实例名称
  client:
    register-with-eureka: false #是否将自己注册到Eureka服务器中，本身是服务器，无需注册
    fetch-registry: false #false表示自己端就是注册中心，职责是维护服务实例，并不需要检索服务
    service-url:
      # 单机 defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个defaultZone地址
      defaultZone: http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
```

- 7002

```yaml
server:
  port: 7002

# Eureka配置
eureka:
  instance:
    hostname: eureka7002.com #eureka服务端的实例名称
  client:
    register-with-eureka: false #是否将自己注册到Eureka服务器中，本身是服务器，无需注册
    fetch-registry: false #false表示自己端就是注册中心，职责是维护服务实例，并不需要检索服务
    service-url:
      # 单机 defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个defaultZone地址
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7003.com:7003/eureka/
```

- 7003

```yaml
server:
  port: 7003

# Eureka配置
eureka:
  instance:
    hostname: eureka7003.com #eureka服务端的实例名称
  client:
    register-with-eureka: false #是否将自己注册到Eureka服务器中，本身是服务器，无需注册
    fetch-registry: false #false表示自己端就是注册中心，职责是维护服务实例，并不需要检索服务
    service-url:
      # 单机 defaultZone: http://${eureka.instance.hostname}:${server.port}/eureka/
      # 设置与Eureka Server交互的地址查询服务和注册服务都需要依赖这个defaultZone地址
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/
```

- 将8001微服务发布到1台eureka集群配置中，发现在集群中的其余注册中心也可以看到，但是平时我们保险起见，都发布！`即通过springcloud-provider-dept-8001下的yml配置文件，修改Eureka配置：配置服务注册中心地址`：

```yaml
# Eureka配置：配置服务注册中心地址
eureka:
  client:
    service-url:
      # 注册中心地址7001-7003
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
  instance:
    instance-id: springcloud-provider-dept-8001 # 与client平级
 #   prefer-ip-address: true # true表示访问路径可以显示IP地址
```

- 启动集群测试！`7001、7002、7003、8001都要启动哦`！

![image-20220214141214593](img/02/image-20220214141214593.png)

> 对比Zookeeper

1. 回顾CAP原则

  - RDBMS (MySQL、Oracle、sqlServer) ===> ACID

  - NoSQL (Redis、MongoDB) ===> CAP

2. ACID是什么？
  - A (Atomicity) 原子性
  - C (Consistency) 一致性
  - I (Isolation) 隔离性
  - D (Durability) 持久性
3. CAP是什么?
  - C (Consistency) 强一致性
  - A (Availability) 可用性
  - P (Partition tolerance) 分区容错性

4. CAP的三进二：CA、AP、CP
5. CAP理论的核心
  - 一个分布式系统不可能同时很好的满足一致性，可用性和分区容错性这三个需求；
  - 根据CAP原理，将NoSQL数据库分成了满足CA原则，满足CP原则和满足AP原则三大类；
    - CA：单点集群，满足一致性，可用性的系统，通常可扩展性较差；
    - CP：满足一致性，分区容错的系统，通常性能不是特别高；
    - AP：满足可用性，分区容错的系统，通常可能对一致性要求低一些；

> 作为服务注册中心，Eureka比Zookeeper好在哪里？

- 著名的CAP理论指出，一个分布式系统不可能同时满足C (一致性) 、A (可用性) 、P (容错性)，由于分区容错性P再分布式系统中是必须要保证的，因此我们只能再A和C之间进行权衡。
  - Zookeeper 保证的是 CP —> 满足一致性，分区容错的系统，通常性能不是特别高；
  - Eureka 保证的是 AP —> 满足可用性，分区容错的系统，通常可能对一致性要求低一些；
- `Zookeeper保证的是CP`
  - 当向注册中心查询服务列表时，我们可以容忍注册中心返回的是几分钟以前的注册信息，但不能接收服务直接down掉不可用。也就是说，服务注册功能对可用性的要求要高于一致性。但zookeeper会出现这样一种情况，当master节点因为网络故障与其他节点失去联系时，剩余节点会重新进行leader选举。问题在于，选举leader的时间太长，30-120s，且选举期间整个zookeeper集群是不可用的，这就导致在选举期间注册服务瘫痪。在云部署的环境下，因为网络问题使得zookeeper集群失去master节点是较大概率发生的事件，虽然服务最终能够恢复，但是，漫长的选举时间导致注册长期不可用，是不可容忍的。

- `Eureka保证的是AP`
  - Eureka看明白了这一点，因此在设计时就优先保证可用性。Eureka各个节点都是平等的，几个节点挂掉不会影响正常节点的工作，剩余的节点依然可以提供注册和查询服务。而Eureka的客户端在向某个Eureka注册时，如果发现连接失败，则会自动切换至其他节点，只要有一台Eureka还在，就能保住注册服务的可用性，只不过查到的信息可能不是最新的，除此之外，Eureka还有之中自我保护机制，如果在15分钟内超过85%的节点都没有正常的心跳，那么Eureka就认为客户端与注册中心出现了网络故障，此时会出现以下几种情况：
    1. Eureka不在从注册列表中移除因为长时间没收到心跳而应该过期的服务；
    2. Eureka仍然能够接受新服务的注册和查询请求，但是不会被同步到其他节点上 (即保证当前节点依然可用)；
    3. 当网络稳定时，当前实例新的注册信息会被同步到其他节点中。
- 因此，==Eureka可以很好的应对因网络故障导致部分节点失去联系的情况，而不会像zookeeper那样使整个注册服务瘫痪==。

## 6.Ribbon：负载均衡(基于客户端)

> Ribbon是什么？

- Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具。 

- 简单的说，Ribbon是Netflix发布的开源项目，主要功能是提供客户端的软件负载均衡算法，将NetFlix的中间层服务连接在一起。Ribbon的客户端组件提供一系列完整的配置项如：连接超时、重试等等。简单的说，就是在配置文件中列出LoadBalancer（简称LB：负载均衡）后面所有的机器，Ribbon会自动的帮助你基于某种规则（如简单轮询，随机连接等等）去连接这些机器。我们也很容易使用Ribbon实现自定义的负载均衡算法！

> Ribbon能干嘛？

![image-20220214150845147](img/03/image-20220214150845147.png)

- LB，即负载均衡 (LoadBalancer) ，在微服务或分布式集群中经常用的一种应用。
- 负载均衡简单的说就是将用户的请求平摊的分配到多个服务上，从而达到系统的HA (高用)。
- 常见的负载均衡软件有 Nginx、Lvs等等。
- Dubbo、SpringCloud 中均给我们提供了负载均衡，SpringCloud 的负载均衡算法可以自定义。
- 负载均衡简单分类：
  - 集中式LB
    - 即在服务的提供方和消费方之间使用独立的LB设施，如Nginx(反向代理服务器)，由该设施负责把访问请求通过某种策略转发至服务的提供方！
  - 进程式 LB
    - 将LB逻辑集成到消费方，消费方从服务注册中心获知有哪些地址可用，然后自己再从这些地址中选出一个合适的服务器。
    - `Ribbon 就属于进程内LB`，它只是一个类库，集成于消费方进程，消费方通过它来获取到服务提供方的地址！
- Ribbon的github地址：https://github.com/NetFlix/ribbon

> 集成Ribbon

- springcloud-consumer-dept-80向pom.xml中添加Ribbon和Eureka依赖

  ```xml
  <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-ribbon -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-ribbon</artifactId>
      <version>1.4.7.RELEASE</version>
  </dependency>
  <!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-eureka -->
  <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-starter-eureka</artifactId>
      <version>1.4.7.RELEASE</version>
  </dependency>
  ```

- 在application.yml文件中配置Eureka

  ```yaml
  server:
    port: 80
  
  # Eureka配置
  eureka:
    client:
      register-with-eureka: false # false表示不向注册中心注册自己
      service-url:
        defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
  ```

- 主启动类加上@EnableEurekaClient注解，开启Eureka

  ```java
  // Ribbon 和 Eureka 整合以后，客户端可以直接调用，不用关心IP地址和端口号
  @SpringBootApplication
  @EnableEurekaClient // 开启Eureka 客户端
  public class DeptConsumer80 {
      public static void main(String[] args) {
          SpringApplication.run(DeptConsumer80.class,args);
      }
  }
  ```

- 自定义Spring配置类：ConfigBean.java 配置负载均衡实现RestTemplate

  ```java
  @Bean
  @LoadBalanced // Spring Cloud Ribbon是基于Netflix Ribbon实现的一套客户端负载均衡的工具
  public RestTemplate getRestTemplate(){
      return new RestTemplate();
  }
  ```

- 修改conroller：DeptConsumerController.java

  ```java
  /**
       * 服务提供方地址前缀
       * <p>
       * Ribbon:我们这里的地址，应该是一个变量，通过服务名来访问
       */
  //    private static final String REST_URL_PREFIX = "http://localhost:8001";
  private static final String REST_URL_PREFIX = "http://SPRINGCLOUD-PROVIDER-DEPT";
  ```

![image-20220219173650912](img/03/image-20220219173650912.png)

- 先启动3个Eureka集群后，再启动springcloud-provider-dept-8001并注册进eureka；
- 启动 DeptConsumerRibbon80；

![image-20220219174149545](img/03/image-20220219174149545.png)

- 测试 
  - http://localhost/consumer/dept/get/1 
  - http://localhost/consumer/dept/list

![image-20220219173905938](img/03/image-20220219173905938.png)

- ==Ribbon和Eureka整合后Consumer可以直接调用服务而不用再关心地址和端口号==！

----

> Ribbon负载均衡

- 架构说明：

![image-20220219175148658](img/03/image-20220219175148658.png)

- Ribbon在工作时分成两步：
  - 第一步先选择EurekaServer，它优先选择在同一个区域内负载均衡较少的Server。 
  - 第二步在根据用户指定的策略，在从server去到的服务注册列表中选择一个地址。 
  - 其中Ribbon提供了多种策略，比如轮询（默认），随机和根据响应时间加权重,,,等等

- 测试： 
  - 参考springcloud-provider-dept-8001，新建两份，分别为8002,8003！ 
  - 参照springcloud-provider-dept-8001，依次为另外两个Moudle添加pom.xml依赖 、resourece下的mybatis和application.yml配置，Java代码；
  - 全部复制完毕，修改启动类名称，修改端口号名称！

![image-20220219175309813](img/03/image-20220219175309813.png)

- 新建8002/8003数据库，各自微服务分别连接各自的数据库，复制springcloud！ 
  - 新建springcloud02 
  - 新建springcloud03

![image-20220219203740822](img/03/image-20220219203740822.png)

- 修改8002/8003各自的YML文件 
  - 端口 
  - 数据库连接 
  - 实例名也需要修改

```yaml
  instance:
    instance-id: springcloud-provider-dept-8003 # 与client平级
```

- 对外暴露的统一的服务实例名【三个服务名字必须一致！】

```yaml
application:
  name: springcloud-provider-dept
```

- 启动3个Eureka集群配置区 

- 启动3个Dept微服务并都测试通过 
  - http://localhost:8001/dept/list 
  - http://localhost:8002/dept/list 
  - http://localhost:8003/dept/list 
  
  ![image-20220220193255505](img/03/image-20220220193255505.png)
  
- 启动springcloud-consumer-dept-80 

- 客户端通过Ribbon完成负载均衡并访问上一步的Dept微服务 
  - http://localhost/consumer/dept/list 
  - 多刷新几次注意观察结果！
  
  ![image-20220220193217642](img/03/image-20220220193217642.png)
  
- 总结：
  - Ribbon其实就是一个软负载均衡的客户端组件，他可以和其他所需请求的客户端结合使用，和Eureka结合只是其中的一个实例。

> Ribbon核心组件IRule

- IRule：根据特定算法从服务列表中选取一个要访问的服务！
- RoundRobinRule【轮询】 
- RandomRule【随机】 
- AvailabilityFilterRule【会先过滤掉由于多次访问故障而处于断路器跳闸的服务，还有并发的连接 数量超过阈值的服务，然后对剩余的服务列表按照轮询策略进行访问】 
- WeightedResponseTimeRule【根据平均响应时间计算所有服务的权重，响应时间越快服务权重越大，被选中的概率越高，刚启动时如果统计信息不足，则使用RoundRobinRule策略，等待统计信息足够，会切换到WeightedResponseTimeRule】 
- RetryRule【先按照RoundRobinRule的策略获取服务，如果获取服务失败，则在指定时间内会进行重试，获取可用的服务】 
- BestAvailableRule【会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，然后选择一个并发量最小的服务】 
- ZoneAvoidanceRule【默认规则，复合判断server所在区域的性能和server的可用性选择服务器】
- 查看分析源码： 
  1. IRule 
  2. ILoadBalancer
  3. AbstractLoadBalancer
  4. AbstractLoadBalancerRule：这个抽象父类十分重要！核心
  5. RoundRobinRule 

![image-20220222220612536](img/03/image-20220222220612536.png)

分析一下方法：

```java
public Server choose(ILoadBalancer lb, Object key) {
    if (lb == null) {
        log.warn("no load balancer");
        return null;
    } else {
        Server server = null;
        int count = 0;

        while(true) {
            if (server == null && count++ < 10) {
                List<Server> reachableServers = lb.getReachableServers();
                List<Server> allServers = lb.getAllServers();
                int upCount = reachableServers.size();
                int serverCount = allServers.size();
                if (upCount != 0 && serverCount != 0) {
                    int nextServerIndex = this.incrementAndGetModulo(serverCount);
                    server = (Server)allServers.get(nextServerIndex);
                    if (server == null) {
                        Thread.yield();
                    } else {
                        if (server.isAlive() && server.isReadyToServe()) {
                            return server;
                        }

                        server = null;
                    }
                    continue;
                }

                log.warn("No up servers available from load balancer: " + lb);
                return null;
            }

            if (count >= 10) {
                log.warn("No available alive servers after 10 tries from load balancer: " + lb);
            }

            return server;
        }
    }
}
```

- 切换为随机策略实现试试，在ConfigBean中添加方法。

```java
/**
     * IRule:
     * RoundRobinRule 轮询策略
     * RandomRule 随机策略
     * AvailabilityFilteringRule ： 会先过滤掉，跳闸，访问故障的服务~，对剩下的进行轮询~
     * RetryRule ： 会先按照轮询获取服务~，如果服务获取失败，则会在指定的时间内进行，重试
     */
@Bean
public IRule myRule() {
    return new RandomRule();//使用随机策略
    // return new RoundRobinRule();//使用轮询策略
    // return new AvailabilityFilteringRule();//使用轮询策略
    // return new RetryRule();//使用轮询策略
}
```

- 重启80服务进行访问测试，查看运行结果！【注意，可能服务长时间不使用会崩】 
- 访问测试：http://localhost/consumer/dept/list

![image-20220222221100037](img/03/image-20220222221100037.png)

- 测试：new RetryRule() 算法 
- RetryRule【先按照RoundRobinRule的策略获取服务，如果获取服务失败，则在指定时间内会进行重试，获取可用的服务】
  1. 在运行期间关闭掉一个服务提供者8002 
  2. 消费者再次测试！发现404后继续访问测试！看结果！！！ 
- 其余的不再挨个测试了，大家有时间可以去测试玩玩； 
- 现在有一个新的需求，我们不需要这些默认的算法，我们需要自己重新定义，这该怎么办呢？

> 自定义Ribbon：

- 修改springcloud-consumer-dept-ribbon-80 
- 主启动类添加@RibbonClient注解；
- 在启动该微服务的时候就能去加载我们自定义的Ribbon配置类，从而使配置类生效，例如:

```java
@RibbonClient(name="SPRINGCLOUD-PROVIDER-DEPT",configuration=MySelfRule.class)
```

**注意配置细节**:

- [官方文档](https://docs.spring.io/spring-cloud-netflix/docs/2.2.11.BUILD-SNAPSHOT/reference/html/#customizing-the-ribbon-client)明确给出了警告： 
- 这个自定义配置类不能放在@ComponentScan所扫描的当前包以及子包下，否则我们自定义的这个配置类就会被所有的Ribbon客户端所共享，也就是说达不到特殊化定制的目的了！

![image-20220222231901088](img/03/image-20220222231901088.png)

![image-20220222231927958](img/03/image-20220222231927958.png)

**步骤**：

1. 由于有以上配置细节原因，我们建立一个包：com.github.myrul，在这里新建一个自定义规则的Rubbion类。

![image-20220222232030128](img/03/image-20220222232030128.png)

```java
@Configuration
public class MySelfRule {

    /**
     * IRule:
     * RoundRobinRule 轮询策略
     * RandomRule 随机策略
     * AvailabilityFilteringRule ： 会先过滤掉，跳闸，访问故障的服务~，对剩下的进行轮询~
     * RetryRule ： 会先按照轮询获取服务~，如果服务获取失败，则会在指定的时间内进行，重试
     */
    @Bean
    public IRule myRule() {
        return new RandomRule();//使用随机策略
        // return new RoundRobinRule();//使用轮询策略
        // return new AvailabilityFilteringRule();//使用轮询策略
        // return new RetryRule();//使用轮询策略
    }
}
```

2. 在主启动类上配置自定义的Ribbon。

```java
@SpringBootApplication
@EnableEurekaClient // 开启Eureka 客户端
@RibbonClient(name="SPRINGCLOUD-PROVIDER-DEPT",configuration= MySelfRule.class)
public class DeptConsumer80 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumer80.class,args);
    }
}
```

3. 启动所有项目，访问测试，查看编写的随机算法，现在是否生效！

![image-20220223145416584](img/03/image-20220223145416584.png)

> 自定义规则深度解析 

1. 问题：依旧轮询策略，但是加上新需求，每个服务器要求被调用5次，就是以前每一个机器一次，现在每个机器5次； 
2. 解析源码：RandomRule.java ， IDEA直接点击进去，复制出来，变成我们自己的类 MyRondomRule

![image-20220222232628236](img/03/image-20220222232628236.png)

- 分析阅读源码：

```java
public class RandomRule extends AbstractLoadBalancerRule {
    public RandomRule() {
    }

    @SuppressWarnings({"RCN_REDUNDANT_NULLCHECK_OF_NULL_VALUE"})
    // ILoadBalancer选择的随机算法
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                // 查看线程是否中断了
                if (Thread.interrupted()) {
                    return null;
                }

                // Reachable： 可及；可到达；够得到
                List<Server> upList = lb.getReachableServers(); // 活着的服务
                List<Server> allList = lb.getAllServers(); // 获取所有的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                // 生成区间随机数！
                int index = this.chooseRandomInt(serverCount);
                // 从活着的服务中，随机取出一个
                server = (Server)upList.get(index);
                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    // 随机
    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
```

- 参考源码修改为我们需求要求的MyRondomRule.java

```java
package com.github.myrule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MyRondomRule extends AbstractLoadBalancerRule {
    public MyRondomRule() {
    }

    // total = 0 当total数等于5以后，我们指针才能往下走
    // index = 0 当前对外提供服务的服务器地址
    // 如果total等于5，则index+1，将total重置为0即可！
    // 问题：我们只有3台机器，所有total>3 则将total置为0；
    private int total = 0; //总共被调用的次数
    private int currentIndex = 0; //当前提供服务的机器序号！
    // ILoadBalancer选择的随机算法
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        } else {
            Server server = null;

            while(server == null) {
                // 查看线程是否中断了
                if (Thread.interrupted()) {
                    return null;
                }

                // Reachable： 可及；可到达；够得到
                List<Server> upList = lb.getReachableServers(); // 活着的服务
                List<Server> allList = lb.getAllServers(); // 获取所有的服务
                int serverCount = allList.size();
                if (serverCount == 0) {
                    return null;
                }

                // 生成区间随机数！
//                int index = this.chooseRandomInt(serverCount);
                // 从活着的服务中，随机取出一个
//                server = (Server)upList.get(index);

                // =====================================
                if (total<5){
                    server = upList.get(currentIndex);
                    total++;
                }else {
                    total = 0;
                    currentIndex++;
                    if (currentIndex>=upList.size()){
                        currentIndex = 0;
                    }
                    server = upList.get(currentIndex);
                }
                // =====================================

                if (server == null) {
                    Thread.yield();
                } else {
                    if (server.isAlive()) {
                        return server;
                    }

                    server = null;
                    Thread.yield();
                }
            }

            return server;
        }
    }

    // 随机
    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public Server choose(Object key) {
        return this.choose(this.getLoadBalancer(), key);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig clientConfig) {
    }
}
```

- 调用，在我们自定义的IRule方法中返回刚才我们写好的随机算法类。

```java
@Configuration
public class MySelfRule {

    @Bean
    public IRule myRule(){
        // Ribbon默认是轮询，可以自定义为随机算法
        return new MyRondomRule();
    }
}
```

- 测试实现，`会连续五次之后才会切换轮询`：

![image-20220223145454446](img/03/image-20220223145454446.png)

## 7.Feign：负载均衡(基于服务端)

> 简介

- Feign是声明式Web Service客户端，它让微服务之间的调用变得更简单，类似controller调用service。SpringCloud集成了Ribbon和Eureka，可以使用Feigin提供负载均衡的http客户端。

- **只需要创建一个接口，然后添加注解即可**！

Feign，主要是社区版，大家都习惯面向接口编程。这个是很多开发人员的规范。调用微服务访问两种方法：

1. 微服务名字 【ribbon】
2. 接口和注解 【feign】

**Feign能干什么？**

- Feign旨在使编写Java Http客户端变得更容易
- 前面在使用**Ribbon** + **RestTemplate**时，利用**RestTemplate**对Http请求的封装处理，形成了一套模板化的调用方法。但是在实际开发中，由于对服务依赖的调用可能不止一处，往往一个接口会被多处调用，所以通常都会针对每个微服务自行封装一个客户端类来包装这些依赖服务的调用。所以，**Feign**在此基础上做了进一步的封装，由他来帮助我们定义和实现依赖服务接口的定义，在Feign的实现下，我们只需要创建一个接口并使用注解的方式来配置它 (类似以前Dao接口上标注Mapper注解，现在是一个微服务接口上面标注一个Feign注解)，即可完成对服务提供方的接口绑定，简化了使用Spring Cloud Ribbon 时，自动封装服务调用客户端的开发量。

**Feign默认集成了Ribbon**

- 利用**Ribbon**维护了MicroServiceCloud-Dept的服务列表信息，并且通过轮询实现了客户端的负载均衡，而与**Ribbon**不同的是，通过**Feign**只需要定义服务绑定接口且以声明式的方法，优雅而简单的实现了服务调用。

> Feign的使用步骤：

1. 参考springcloud-consumer-dept-ribbon-80 
2. 新建springcloud-consumer-dept-feign-80 
   1. 修改主启动类名称；
   2. 将springcloud-consumer-dept-80的内容都拷贝到feign项目中；
   3. 删除myrule文件夹；
   4. 修改主启动类的名称为 FeignDeptConsumer80；

![image-20220223150519033](img/03/image-20220223150519033.png)

3. springcloud-consumer-dept-feign-80修改pom.xml，添加对Feign的支持。

```xml
<!--Feign的依赖-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-feign</artifactId>
    <version>1.4.6.RELEASE</version>
</dependency>
```

4. 修改springcloud-api工程 
   1. pom.xml添加feign的支持 
   2. 新建一个Service包

![image-20220223150729085](img/03/image-20220223150729085.png)

- 编写接口 DeptClientService，并增加新的注解@FeignClient。

```java
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT")
public interface DeptClientService {
    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id); //根据id查询部门
   
    @GetMapping("/dept/list")
    public List<Dept> queryAll(); //查询所有部门
    
    @PostMapping(value = "/dept/add")
    public boolean addDept(Dept dept); //添加一个部门
    
}
```

- 记得清理一下mvn。

5. springcloud-consumer-dept-feign-80工程修改Controller，添加上一步新建的DeptClientService。

```java
@RestController
public class DeptConsumerController {

    /**
     * 理解：消费者，不应该有service层~
     * RestTemplate .... 供我们直接调用就可以了！ 注册到Spring中
     * (地址：url, 实体：Map ,Class<T> responseType)
     * <p>
     * 提供多种便捷访问远程http服务的方法，简单的Restful服务模板~
     */
    @Autowired
    private DeptClientService service;

    /**
     * 消费方添加部门信息
     * @param dept
     * @return
     */
    @RequestMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return this.service.addDept(dept);
    }

    /**
     * 消费方根据id查询部门信息
     * @param id
     * @return
     */
    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return this.service.queryById(id);
    }

    /**
     * 消费方查询部门信息列表
     * @return
     */
    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        return this.service.queryAll();
    }

}
```

6. microservicecloud-consumer-dept-feign工程修改主启动类，开启Feign使用！

```java
@SpringBootApplication
@EnableEurekaClient
// feign客户端注解,并指定要扫描的包以及配置接口DeptClientService
@EnableFeignClients(basePackages = {"com.github.service"})
// 切记不要加这个注解，不然会出现404访问不到
// @ComponentScan("com.github.service")
public class FeignDeptConsumer80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignDeptConsumer80.class, args);
    }
}
```

7. 测试！

- 启动eureka集群；
- 启动8001，8002，8003 
- 启动feign客户端 
- 测试： http://localhost/consumer/dept/list 

![image-20220223153152855](img/03/image-20220223153152855.png)

==结论：Feign自带负载均衡配置项==。

> 小结

- Feign通过接口的方法调用Rest服务 ( 之前是Ribbon+RestTemplate )；
- 该请求发送给Eureka服务器 （http://MICROSERVICECLOUD-PROVIDER-DEPT/dept/list）；
- 通过Feign直接找到服务接口，由于在进行服务调用的时候融合了Ribbon技术，所以也支持负载均衡作用！ 
- feign其实不是做负载均衡的,负载均衡是ribbon的功能,feign只是集成了ribbon而已,但是负载均衡的功能还是feign内置的ribbon再做,而不是feign。feign的作用的替代RestTemplate,性能比较低，但是可以使代码可读性很强。

## 8.Hystrix：服务熔断

> 分布式系统面临的问题：

- 复杂分布式体系结构中的应用程序有数十个依赖关系，每个依赖关系在某些时候将不可避免的失败！

![Hystrix](img/04/Hystrix.png)

**服务雪崩** 

- 多个微服务之间调用的时候，假设微服务A调用微服务B和微服务C，微服务B和微服务C又调用其他的微服务，这就是所谓的 “扇出”、如果扇出的链路上某个微服务的调用响应时间过长或者不可用，对微服务A的调用就会占用越来越多的系统资源，进而引起系统崩溃，所谓的“雪崩效应”。

![image-20220223181908141](img/04/image-20220223181908141.png)

- 对于高流量的应用来说，单一的后端依赖可能会导致所有服务器上的所有资源都在几十秒内饱和。比失败更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，备份队列，线程和其他系统资源紧张，导致整个系统发生更多的级联故障，**这些都表示需要对故障和延迟进行隔离和管理，以达到单个依赖关系的失败而不影响整个应用程序或系统运行**。
- 此时，我们需要**弃车保帅**！

> 什么是Hystrix？

![hystrix-logo-tagline-640](img/04/hystrix-logo-tagline-640.png)

- **Hystrix**是一个应用于处理分布式系统的延迟和容错的开源库，在分布式环境中，许多服务依赖项中的一些不可避免地会失败。Hystrix 是一个库，它通过添加延迟容错和容错逻辑来帮助您控制这些分布式服务之间的交互。Hystrix 通过隔离服务之间的访问点、停止它们之间的级联故障并提供回退选项来做到这一点，所有这些都可以提高系统的整体弹性。

- “**断路器**”本身是一种开关装置，当某个服务单元发生故障之后，通过断路器的故障监控 (类似熔断保险丝) ，**向调用方返回一个服务预期的，可处理的备选响应 (FallBack) ，而不是长时间的等待或者抛出调用方法无法处理的异常，这样就可以保证了服务调用方的线程不会被长时间，不必要的占用**，从而避免了故障在分布式系统中的蔓延，乃至雪崩。

![image-20220223182133992](img/04/image-20220223182133992.png)

**Hystrix的历史**

- Hystrix 源于 Netflix API 团队于 2011 年开始的弹性工程工作。2012 年，Hystrix 不断发展和成熟，Netflix 内部的许多团队都采用了它。如今，Netflix 每天通过 Hystrix 执行数百亿个线程隔离和数千亿个信号量隔离调用。这极大地提高了正常运行时间和恢复能力。

----

> Hystrix 有什么用？

Hystrix 旨在执行以下操作：

- 通过第三方客户端库访问（通常通过网络）依赖关系，保护和控制延迟和故障。
- 停止复杂分布式系统中的级联故障。
- 快速失败并迅速恢复。
- 尽可能回退并优雅降级。
- 实现近乎实时的监控、警报和操作控制。

----

> Hystrix 解决了什么问题？

- 复杂分布式架构中的应用程序有几十个依赖项，每个依赖项都不可避免地会在某个时候失败。如果主机应用程序没有与这些外部故障隔离开来，它就有被它们一起关闭的风险。

- 例如，对于依赖于 30 个服务且每个服务的正常运行时间为 99.99% 的应用程序，您可以期待以下内容：

> 99.99 30 = 99.7% 的正常运行时间
> 10 亿次请求的 0.3% = 3,000,000 次故障 2
> 小时以上的停机时间/月，即使所有依赖项都具有出色的正常运行时间。

- 现实通常更糟。

- 即使所有依赖项都表现良好，**如果您不对整个系统进行弹性设计**，即使对数十项服务中的每项服务造成 0.01% 的停机时间的总影响也相当于可能每月停机数小时。

------

- 当一切正常时，请求流程可能如下所示：

![soa-1-640](img/04/soa-1-640.png)

- 当许多后端系统之一变得潜在时，它可以阻止整个用户请求：

![soa-2-640](img/04/soa-2-640.png)

- 在大流量的情况下，单个后端依赖变得潜在可能会导致所有服务器上的所有资源在几秒钟内变得饱和。

- 应用程序中通过网络或客户端库中可能导致网络请求的每个点都是潜在故障的根源。比故障更糟糕的是，这些应用程序还可能导致服务之间的延迟增加，这会备份队列、线程和其他系统资源，从而导致整个系统出现更多的级联故障。

![soa-3-640](img/04/soa-3-640.png)

- 当通过第三方客户端执行网络访问时，这些问题会更加严重——一个“黑匣子”，其中实现细节被隐藏并且可以随时更改，并且每个客户端库的网络或资源配置都不同，并且通常难以监控和改变。

- 更糟糕的是传递依赖，它执行潜在的昂贵或容易出错的网络调用，而没有被应用程序显式调用。

- 网络连接失败或降级。服务和服务器出现故障或变慢。新的库或服务部署会改变行为或性能特征。客户端库有错误。

- 所有这些都代表了需要隔离和管理的故障和延迟，以便单个故障依赖项无法关闭整个应用程序或系统。

> Hystrix 的设计原则是什么？

Hystrix 通过以下方式工作：

- 防止任何单个依赖项用完所有容器（例如 Tomcat）用户线程。
- 减轻负载并快速失败，而不是排队。
- 在可行的情况下提供回退，以保护用户免于失败。
- 使用隔离技术（例如隔板、泳道和断路器模式）来限制任何一种依赖项的影响。
- 通过近乎实时的指标、监控和警报优化发现时间
- 通过配置更改的低延迟传播和对 Hystrix 大部分方面的动态属性更改的支持来优化恢复时间，这允许您使用低延迟反馈循环进行实时操作修改。
- 防止整个依赖客户端执行中的故障，而不仅仅是网络流量中的故障。

>Hystrix 如何实现其目标？

- Hystrix 通过以下方式做到这一点：
  - 将所有对外部系统（或“依赖项”）的调用包装在一个`HystrixCommand`或`HystrixObservableCommand`对象中，该对象通常在单独的线程中执行（这是[命令模式](http://en.wikipedia.org/wiki/Command_pattern)的一个示例）。
  - 超时调用时间超过您定义的阈值。有一个默认值，但是对于大多数依赖项，您可以通过“属性”自定义设置这些超时，以便它们略高于每个依赖项测量的 99.5 %性能。
  - 为每个依赖项维护一个小型线程池（或信号量）；如果它已满，发往该依赖项的请求将立即被拒绝而不是排队。
  - 测量成功、失败（客户端抛出的异常）、超时和线程拒绝。
  - 如果服务的错误百分比超过阈值，则手动或自动触发断路器以在一段时间内停止对特定服务的所有请求。
  - 当请求失败、被拒绝、超时或短路时执行回退逻辑。
  - 近乎实时地监控指标和配置更改。
- 当您使用 Hystrix 包装每个底层依赖项时，如上图所示的架构将更改为类似于下图。每个依赖项相互隔离，限制在发生延迟时它可以饱和的资源，并包含在回退逻辑中，该逻辑决定当依赖项中发生任何类型的故障时做出什么响应：

![soa-4-isolation-640](img/04/soa-4-isolation-640.png)

- 详细了解[它的工作原理](https://github.com/Netflix/Hystrix/wiki/How-it-Works)和[使用方法](https://github.com/Netflix/Hystrix/wiki/How-To-Use)。

- ==以上概念参考于官方资料：[https://github.com/Netflix/Hystrix/wiki](https://github.com/Netflix/Hystrix/wiki)==。

> 服务熔断

**什么是服务熔断**?

- **熔断机制是赌赢雪崩效应的一种微服务链路保护机制**。

- 当扇出链路的某个微服务不可用或者响应时间太长时，会进行服务的降级，**进而熔断该节点微服务的调用，快速返回错误的响应信息**。检测到该节点微服务调用响应正常后恢复调用链路。在SpringCloud框架里熔断机制通过Hystrix实现。Hystrix会监控微服务间调用的状况，当失败的调用到一定阀值缺省是**5秒内20次调用失败，就会启动熔断机制**。
- 熔断机制的注解是：`@HystrixCommand`。

**服务熔断解决如下问题**：

- 当所依赖的对象不稳定时，能够起到快速失败的目的；
- 快速失败后，能够根据一定的算法动态试探所依赖对象是否恢复。

**入门案例**：

- 参考springcloud-provider-dept-8001，新建springcloud-provider-dept-hystrix-8001，将之前8001的所有东西拷贝一份。

1. 修改pom，添加Hystrix的依赖。

```xml
<!-- https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-hystrix -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-hystrix</artifactId>
    <version>1.4.7.RELEASE</version>
</dependency>
```

2. 修改yml，修改eureka实例的id。

![image-20220223205606543](img/04/image-20220223205606543.png)

3. 修改DeptController 

- @HystrixCommand报异常后如何处理

```java
// 一旦调用服务方法失败并抛出了错误信息后
// 会自动调用HystrixCommand标注好的fallbackMethod调用类中指定方法
@HystrixCommand(fallbackMethod = "processHystrix_Get")
```

- 源码内容

```java
@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService service;

    // 一旦调用服务方法失败并抛出了错误信息后
    // 会自动调用HystrixCommand标注好的fallbackMethod调用类中指定方法

    /**
     * 如果根据id查询出现异常,则走hystrixGet这段备选代码
     * @param id
     * @return
     */
    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "hystrixGet")
    public Dept get(@PathVariable("id") Long id) {
        Dept dept = service.queryById(id);
        if (dept==null){
            throw new RuntimeException("该id:"+id+"没有对应的的信息");
        }
        return dept;
    }

    /**
     * 根据id查询备选方案(熔断)
     * @param id
     * @return
     */
    public Dept hystrixGet(@PathVariable("id") Long id){
        return new Dept().setDeptno(id)
                .setDname("这个id=>"+id+",没有对应的信息,null---@Hystrix~")
                .setDb_source("在MySQL中没有这个数据库");
    }

}
```

- 修改主启动类添加新注解 @EnableCircuitBreaker，修改主启动类的名称为 DeptProviderHystrix8001

```java
@SpringBootApplication
@EnableEurekaClient // 本服务启动之后会自动注册进Eureka中！
@EnableDiscoveryClient // 开启服务发现客户端的注解，可以用来获取一些配置的信息，得到具体的微服务
@EnableCircuitBreaker //对hystrix 熔断机制的支持 【==========new=======】

public class DeptProviderHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix8001.class,args);
    }
}
```

- 测试 
  1. 启动Eureka集群;
  2. 启动主启动类 DeptProviderHystrix8001;
  3. 启动客户端 springcloud-consumer-dept-80;
  4. 访问 http://localhost/consumer/dept/get/111

![image-20220223210806933](img/04/image-20220223210806933.png)

- 使用熔断后，当访问一个不存在的id时，前台页展示数据如下:

![image-20220223210917714](img/04/image-20220223210917714.png)

- 而不使用熔断的springcloud-provider-dept–8001模块访问相同地址会出现下面状况:

![image-20220223211146475](img/04/image-20220223211146475.png)

> 服务降级

**什么是服务降级**?

- 服务降级是指当服务器压力剧增的情况下，根据实际业务情况及流量，对一些服务和页面有策略的不处理，或换种简单的方式处理，从而释放服务器资源以保证核心业务正常运作或高效运作。说白了，**就是尽可能的把系统资源让给优先级高的服务**。

- 资源有限，而请求是无限的。如果在并发高峰期，不做服务降级处理，一方面肯定会影响整体服务的性能，严重的话可能会导致宕机某些重要的服务不可用。所以，一般在高峰期，为了保证核心功能服务的可用性，都要对某些服务降级处理。比如当双11活动时，把交易无关的服务统统降级，如查看蚂蚁深林，查看历史订单等等。

**服务降级主要用于什么场景呢**？

- 当整个微服务架构整体的负载超出了预设的上限阈值或即将到来的流量预计将会超过预设的阈值时，为了保证重要或基本的服务能正常运行，可以将一些不重要或不紧急的服务或任务进行服务的 延迟使用或暂停使用。

- 降级的方式可以根据业务来，可以延迟服务，比如延迟给用户增加积分，只是放到一个缓存中，等服务平稳之后再执行；或者在粒度范围内关闭服务，比如关闭相关文章的推荐。

![image-20220223211917403](img/04/image-20220223211917403.png)

- 由上图可得，**当某一时间内服务A的访问量暴增，而B和C的访问量较少，为了缓解A服务的压力，这时候需要B和C暂时关闭一些服务功能，去承担A的部分服务，从而为A分担压力，叫做服务降级**。

**服务降级需要考虑的问题**

- 1）那些服务是核心服务，哪些服务是非核心服务
- 2）那些服务可以支持降级，那些服务不能支持降级，降级策略是什么
- 3）除服务降级之外是否存在更复杂的业务放通场景，策略是什么？

**自动降级分类**

1. 超时降级：主要配置好超时时间和超时重试次数和机制，并使用异步机制探测回复情况。

2. 失败次数降级：主要是一些不稳定的api，当失败调用次数达到一定阀值自动降级，同样要使用异步机制探测回复情况。

3. 故障降级：比如要调用的远程服务挂掉了（网络故障、DNS故障、http服务返回错误的状态码、rpc服务抛出异常），则可以直接降级。降级后的处理方案有：默认值（比如库存服务挂了，返回默认现货）、兜底数据（比如广告挂了，返回提前准备好的一些静态页面）、缓存（之前暂存的一些缓存数据）。

4. 限流降级：秒杀或者抢购一些限购商品时，此时可能会因为访问量太大而导致系统崩溃，此时会使用限流来进行限制访问量，当达到限流阀值，后续请求会被降级；降级后的处理方案可以是：排队页面（将用户导流到排队页面等一会重试）、无货（直接告知用户没货了）、错误页（如活动太火爆了，稍后重试）。

**入门案例**

- 修改springcloud-api工程，根据已经有的DeptClientService接口新建一个实现了FallbackFactory接口的类DeptClientServiceFallbackFactory。
- 【注意：这个类上需要@Component注解！！！】

```java
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory {

    @Override
    public Object create(Throwable throwable) {
        return new DeptClientService() {
            @Override
            public Dept queryById(Long id) {
                return new Dept().setDeptno(id)
                        .setDname("该ID:" + id + "没有对应的信息，Consumer客户端提供的降级信息，此刻服务Provider已经关闭！")
                        .setDb_source("No this database in MySQL.");
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
```

- 修改springcloud-api工程，DeptClientService接口在注解@FeignClient中添加fallbackFactory属性值。

```java
@Component // 注册到spring容器中
// @FeignClient:微服务客户端注解,value:指定微服务的名字,这样就可以使Feign客户端直接找到对应的微服务
// fallbackFactory指定降级配置类
@FeignClient(value = "SPRINGCLOUD-PROVIDER-DEPT",fallbackFactory = DeptClientServiceFallbackFactory.class)
public interface DeptClientService {
    @GetMapping("/dept/get/{id}")
    public Dept queryById(@PathVariable("id") Long id); //根据id查询部门

    @GetMapping("/dept/list")
    public List<Dept> queryAll(); //查询所有部门

    @PostMapping(value = "/dept/add")
    public boolean addDept(Dept dept); //添加一个部门

}
```

- springcloud-api工程`mvn clean install`；
- springcloud-consumer-dept-feign-80工程修改YML；

![image-20220224135408649](img/04/image-20220224135408649.png)

**测试** 

1. 启动eureka集群； 

2. 启动 springcloud-provider-dept-hystrix-8001；

3. 启动 springcloud-consumer-dept-feign-80；

4. 正常访问测试：http://localhost/consumer/dept/get/1；

   ![image-20220224140041962](img/04/image-20220224140041962.png)

5. 故意关闭微服务 springcloud-provider-dept-hystrix-8001；

6. 客户端自己调用提示：http://localhost/consumer/dept/get/1 

   - 此时服务端provider已经down了，但是我们做了服务降级处理，让客户端在服务端不可用时 也会获得提示信息而不会挂起耗死服务器。

![在这里插入图片描述](img/04/2021062920504276.png)

> 服务熔断和降级的区别

- **服务熔断—>服务端**：一般是某个服务故障或者异常引起，类似现实世界中的“保险丝”，当某个异常条件被触发，直接熔断整个服务，而不是一直等到此服务超时！
- **服务降级—>客户端**：从整体网站请求负载考虑，当某个服务熔断或者关闭之后，服务将不再被调用，此时在客户端，我们可以准备一个 FallBackFactory ，返回一个默认的值(缺省值)。会导致整体的服务下降，但是好歹能用，比直接挂掉强。
- 触发原因不太一样，服务熔断一般是某个服务（下游服务）故障引起，而服务降级一般是从整体负荷考虑；管理目标的层次不太一样，熔断其实是一个框架级的处理，每个微服务都需要（无层级之分），而降级一般需要对业务有层级之分（比如降级一般是从最外围服务开始）。
- 实现方式不太一样，服务降级具有代码侵入性(由控制器完成/或自动降级)，熔断一般称为**自我熔断**。

![img](img/04/1641655-20200203200950188-1978691598.png)

**熔断，降级，限流**：

- 限流：限制并发的请求访问量，超过阈值则拒绝；

- 降级：服务分优先级，牺牲非核心服务（不可用），保证核心服务稳定；从整体负荷考虑；

- 熔断：依赖的下游服务故障触发熔断，避免引发本系统崩溃；系统自动执行和恢复

> Dashboard 流监控

- 除了隔离依赖服务的调用以外，Hystrix还提供了准实时的调用监控（Hystrix Dashboard），Hystrix会持续地记录所有通过Hystrix发起的请求的执行信息，并以统计报表和图形的形式展示给用户，包括每秒执行多少请求，多少成功，多少失败等等。 
- Netflix通过hystrix-metrics-event-stream项目实现了对以上指标的监控，SpringCloud也提供了Hystrix Dashboard的整合，对监控内容转化成可视化界面！

![img](img/05/diagram-distributed-systems.svg)

1. 新建工程springcloud-consumer-hystrix-dashboard-9001
2. 导入pom依赖

```xml
<dependencies>
    <!--Hystrix依赖-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix</artifactId>
        <version>1.4.6.RELEASE</version>
    </dependency>
    <!--dashboard依赖-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-hystrix-dashboard</artifactId>
        <version>1.4.6.RELEASE</version>
    </dependency>
    <!--Ribbon-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-ribbon</artifactId>
        <version>1.4.6.RELEASE</version>
    </dependency>
    <!--Eureka-->
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-eureka</artifactId>
        <version>1.4.6.RELEASE</version>
    </dependency>
    <!--实体类+web-->
    <dependency>
        <groupId>com.github</groupId>
        <artifactId>springcloud-api</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!--热部署-->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
    </dependency>
</dependencies>
```

3. 添加主启动类

```java
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumerDashBoardApp9001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerDashBoardApp9001.class,args);
    }
}
```

4. 添加配置文件

```yaml
server:
  port: 9001

hystrix:
  dashboard:
    proxy-stream-allow-list: localhost
```

5. 给springcloud-provider-dept-hystrix-8001模块下的主启动类添加如下代码,添加监控

```java
@SpringBootApplication
@EnableEurekaClient // 本服务启动之后会自动注册进Eureka中！
@EnableDiscoveryClient // 开启服务发现客户端的注解，可以用来获取一些配置的信息，得到具体的微服务
@EnableCircuitBreaker //对hystrix 熔断机制的支持 【==========new=======】

public class DeptProviderHystrix8001 {
    public static void main(String[] args) {
        SpringApplication.run(DeptProviderHystrix8001.class,args);
    }

    //增加一个 Servlet
    @Bean
    public ServletRegistrationBean hystrixMetricsStreamServlet(){
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new HystrixMetricsStreamServlet());
        //访问该页面就是监控页面
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        return registrationBean;
    }
}
```

6. 测试一

   - 启动springcloud-consumer-hystrix-dashboard-9001监控服务

   - 访问：http://localhost:9001/hystrix

     ![image-20220224200936842](img/05/image-20220224200936842.png)

   - 启动3个Eureka集群

   - 启动springcloud-provider-dept-hystrix-8001

   - 进入监控页面，访问：

     - http://localhost:8001/dept/get/1
     - http://localhost:8001/actuator/hystrix.stream 【查看1秒一动的数据流】

![image-20220224201345128](img/05/image-20220224201345128.png)

7. 监控测试
   - 多次刷新 http://localhost:8001/dept/get/1
   - 观察监控窗口，就是那个豪猪页面；
   - 添加监控地址：

![image-20220224201635630](img/05/image-20220224201635630.png)

- Delay: 该参数用来控制服务器上轮询监控信息的延迟时间，默认为2000毫秒，可以通过配置该属性来降低客户端的网络和CPU消耗 
- Title：该参数对应了头部标题HystrixStream之后的内容，默认会使用具体监控实例URL，可以通过配置该信息来展示更合适的标题。
- 监控结果：

![image-20220224201808829](img/05/image-20220224201808829.png)

- 如何看：

  - 7色：绿 > 蓝 > 青 > 黄 > 紫 > 红

    ![image-20220224201906162](img/05/image-20220224201906162.png)

  - 一圈：

    - 实心圆：公有两种含义，他通过颜色的变化代表了实例的健康程度。
    - 它的健康程度从 绿色<黄色<橙色<红色 递减。
    - 该实心圆除了颜色的变化之外，它的大小也会根据实例的请求流量发生变化，流量越大，该实心圆就越大，所以通过该实心圆的展示，就可以在大量的实例中快速发现故障实例和高压力实例。

    ![image-20220224202128990](img/05/image-20220224202128990.png)

  - 一线：

    - 曲线:用来记录2分钟内流量的相对变化,可以通过它来观察到流量的上升和下降趋势;

    ![image-20220224202232243](img/05/image-20220224202232243.png)

  - 整图说明：

  ![image-20220224203656549](img/05/image-20220224203656549.png)

- 搞懂一个才能看懂复杂的

![img](img/05/159998-20180606170211195-1652996172.png)



## 9.Zull路由网关

> 概述

**什么是zuul**?

- Zull包含了对请求的**路由**(用来跳转的)和**过滤**两个最主要功能：

- 其中**路由功能负责将外部请求转发到具体的微服务实例上，是实现外部访问统一入口的基础**，而过**滤器功能则负责对请求的处理过程进行干预，是实现请求校验，服务聚合等功能的基础**。Zuul和Eureka进行整合，将Zuul自身注册为Eureka服务治理下的应用，同时从Eureka中获得其他服务的消息，也即以后的访问微服务都是通过Zuul跳转后获得。

![image-20220224210505339](img/05/image-20220224210505339.png)

- **注意**：Zuul 服务最终还是会注册进 Eureka

- **提供**：代理 + 路由 + 过滤 三大功能！

> Zuul 能干嘛？

- 路由
- 过滤

官方文档：https://github.com/Netflix/zuul/

**入门案例**

1. 新建springcloud-zuul模块，并导入依赖

```xml
    <dependencies>
        <!--导入zuul依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-zuul</artifactId>
            <version>1.4.6.RELEASE</version>
        </dependency>
        <!--Hystrix依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-hystrix</artifactId>
            <version>1.4.6.RELEASE</version>
        </dependency>
        <!--dashboard依赖-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix-dashboard</artifactId>
            <version>2.2.10.RELEASE</version>
        </dependency>
        <!--Ribbon-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-ribbon</artifactId>
            <version>1.4.6.RELEASE</version>
        </dependency>
        <!--Eureka-->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-eureka</artifactId>
            <version>1.4.6.RELEASE</version>
        </dependency>
        <!--实体类+web-->
        <dependency>
            <groupId>com.github</groupId>
            <artifactId>springcloud-api</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--热部署-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
        </dependency>
    </dependencies>
```

- application.yml

```yml
server:
  port: 4399
spring:
  application:
    name: springcloud-zuul



#Eureka的配置，服务注册到哪里
eureka:
  client:
    service-url:
      defaultZone: http://eureka7001.com:7001/eureka/,http://eureka7002.com:7002/eureka/,http://eureka7003.com:7003/eureka/
  instance:
    instance-id: zuul4399.com
    prefer-ip-address: true #改为true后默认显示的是ip地址而不再是localhost  方便团队不同人使用

# info配置
info:
  app.name: subei-springcloud  # 项目的名称
  company.name: www.subeily.top  # 公司的名称

# zull 路由网关配置
zuul:
  # 路由相关配置
  # 原来访问路由 eg:http://subeily.com:4399/springcloud-provider-dept/dept/get/2
  # zull路由配置后访问路由 eg: http://subeily.com:4399/subeily/mydept/dept/get/2
  routes:
    mydept.serviceId: springcloud-provider-dept # eureka注册中心的服务提供方路由名称
    mydept.path: /mydept/** # 将eureka注册中心的服务提供方路由名称 改为自定义路由名称
  ignored-services: "*" # 不能再使用这个路径访问了，*： 忽略,隐藏全部的服务名称~
  prefix: /subeily # 设置公共的前缀
```

- hosts修改

  - hosts文件的位置在C:\Windows\System32\drivers\etc\hosts

  ```html
  127.0.0.1 		 subeily.com
  ```

- 主启动类

```java
@SpringBootApplication
@EnableZuulProxy // 开启Zuul
public class SpringCloudZuulApp4399 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudZuulApp4399.class,args);
    }
}
```

- 启动：

  - 三个Eureka集群
  - 服务提供者springcloud-provider-dept-8001
  - zuul服务
  - 访问：http://localhost:7001/

  ![image-20220224212021657](img/05/image-20220224212021657.png)

- 可以看出Zull路由网关被注册到Eureka注册中心中了！

- 测试：

  - 不用路由：http://localhost:8001/dept/get/2 

    ![image-20220224212247260](img/05/image-20220224212247260.png)

  - 使用路由：http://subeily.com:4399/springcloud-provider-dept/dept/get/2

    - 网关/微服务名字/具体的服务


![image-20220224212934768](img/05/image-20220224212934768.png)

- 上图是没有经过Zull路由网关配置时，服务接口访问的路由，可以看出直接用微服务(服务提供方)名称去访问，这样不安全，不能将微服务名称暴露！
- 所以经过Zull路由网关配置后，访问封装好的url：http://subeily.com:4399/subeily/mydept/dept/get/2

![image-20220224213221611](img/05/image-20220224213221611.png)

- 微服务名称被替换并隐藏，换成了我们自定义的微服务名称mydept，同时加上了前缀subeily，这样就做到了对路由访问的加密处理。

- 详情参考springcloud中文社区zuul组件:[Spring Cloud Greenwich 中文文档 参考手册 中文版](https://www.springcloud.cc/spring-cloud-greenwich.html#_router_and_filter_zuul)

## 10.Spring Cloud Config 分布式配置

> 概述

**分布式系统面临的–配置文件问题**

- 微服务意味着要将单体应用中的业务拆分成一个个子服务，每个服务的粒度相对较小，因此系统中会出现大量的服务，由于每个服务都需要必要的配置信息才能运行，所以一套集中式的，动态的配置管理设施是必不可少的。spring cloud提供了configServer来解决这个问题，我们每一个微服务自己带着一个application.yml，那上百个的配置文件修改起来，令人头疼！

**SpringCloud config分布式配置中心**

![img](img/06/springcloud10.png)

- spring cloud config 为微服务架构中的微服务提供集中化的外部支持，配置服务器为各个不同微服务应用的所有环节提供了一个**中心化的外部配置**。
- spring cloud config 分为**服务端**和**客户端**两部分。
- 服务端也称为 **分布式配置中心**，它是一个独立的微服务应用，用来连接配置服务器并为客户端提供获取配置信息，加密，解密信息等访问接口。
- 客户端则是**通过指定的配置中心来管理应用资源，以及与业务相关的配置内容，并在启动的时候从配置中心获取和加载配置信息**。配置服务器默认采用git来存储配置信息，这样就有助于对环境配置进行版本管理。并且可用通过git客户端工具来方便的管理和访问配置内容。

**spring cloud config 分布式配置中心能干嘛？**

- 集中式管理配置文件
- 不同环境，不同配置，动态化的配置更新，分环境部署，比如 /dev /test /prod /beta /release
- 运行期间动态调整配置，不再需要在每个服务部署的机器上编写配置文件，服务会向配置中心统一拉取配置自己的信息
- 当配置发生变动时，服务不需要重启，即可感知到配置的变化，并应用新的配置
- 将配置信息以REST接口的形式暴露

**spring cloud config 分布式配置中心与GitHub整合**

- 由于spring cloud config 默认使用git来存储配置文件 (也有其他方式，比如自持SVN 和本地文件)，但是最推荐的还是git，而且使用的是 http/https 访问的形式。

> 服务端

- 新建模块 springcloud-config-server-3344，导入依赖





- 编写配置文件





## 思维导图



## 🎉结束啦🎉





























































































































































