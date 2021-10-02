###  ** jx_shop（农产品商城==一个商城项目）** 


###  **一、介绍** 


由于本人的能力有限，比如快递的发货功能、其他第三方支付、流量的引入等这些功能还不够充分完善，除此之外系统的用户界面不是太美观，在客户端访问的性能以及分布式架构的设计等方面还存在很多欠缺，还比如说可以使用CDN内容分发网络进行系统的加速、使用OSS云存储存放大文件等等。

完成项目的同时，入门了阿里云的各项产品、尝试了Tomcat+Nginx实现集群搭建、SpringSession集成Session共享功能，不过没有集成到此项目中，是另一个练手的demo。
这里使用的是Nginx+vsftpd实现的图片的保存，但是我推荐使用阿里云的OSS云存储。


 **希望大家可以随手给个Star，感激之情难以言表！** 

 **我的CSDN** ：[CSDN](https://blog.csdn.net/qq_38663663)
 **我的B站** ：[B站](https://space.bilibili.com/380898843)
 **我的博客** ：[Coding技术栈](http://www.jiaxuan.fun)

###  **二、这是一个农产品商店（其实可以看做商城，任意商品）** 

基于【SSM】【Maven】【BootStrap】【MySQL】【BootStrap】技术，使用IntelliJ IDEA开发工具。
使用IntelliJ IDEA工具，基于Maven，运行Spring+SpringMVC+MyBatis框架技术实现商城系统

###  **三、软件架构** 

目前项目基本实现的功能有，商城后端：用户登录、商品管理、商品类型管理、客户管理以及系统用户管理（订单管理功能后续有空实现...）；商城前端：商品页面展示、商品详情页、加入购物车、立即购买、我的订单、购物车、个人中心（这里实现比较简单）、客户注册、客户登录、订单管理、订单支付（支付宝和微信）、收货地址管理等

![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/165959_f8c3a5a6_4804494.jpeg "1.jpg")

###  **四、安装教程** 


软件+环境的配置和安装： **推荐使用docker** 

我的学习笔记：[我的docker学习笔记1](https://blog.csdn.net/qq_38663663/article/details/106430885)

 **sql文件在项目里边。** 

数据库：Mysql
技术组合：Spring、SpringMvc、Mybatis、Redis、Jsp
前端框架：BootStrap3
Lombok
其它：HttpClient4.x、Google ZXing 生成二维码、微信支付、支付宝沙箱模拟支付、聚合数据接口（短信）开发、文件上传 FTP 服务器等

IDEA 2018.3.5
Maven 3
JDK8
Mysql 5.7+
Win7 64位系统
项目部署：阿里云服务器



###  **五、项目截图** 

![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173226_13333849_4804494.png "QQ截图20200531171402.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173037_4a850505_4804494.png "QQ截图20200531171227.png")


![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173135_a6d47661_4804494.png "QQ截图20200531171239.png")


 **注：这个架构是在慕课网学习的，具体的全套视频教程会在QQ群发布。** 
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173150_175f2a38_4804494.png "QQ截图20200531171250.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173208_5ff30d53_4804494.png "QQ截图20200531171340.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173216_65ab32bf_4804494.png "QQ截图20200531171351.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173301_1c2db5d3_4804494.png "QQ截图20200531171422.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173309_428b0b79_4804494.png "QQ截图20200531171430.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173317_73302558_4804494.png "QQ截图20200531171439.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173326_10d376d3_4804494.png "QQ截图20200531171448.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173339_99c3f7f0_4804494.png "QQ截图20200531171507.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173349_6750b81b_4804494.png "QQ截图20200531171515.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173358_1505c004_4804494.png "QQ截图20200531171523.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173406_f732ab22_4804494.png "QQ截图20200531171532.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173414_c287c063_4804494.png "QQ截图20200531171539.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173421_2c6982de_4804494.png "QQ截图20200531171549.png")


###  **六、阿里云相关** 


1、阿里云服务器以及域名的配置
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173627_3646e664_4804494.png "QQ截图20200531171559.png")

![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173635_f6d8592e_4804494.png "QQ截图20200531171605.png")

2、搭建的VSFTPD文件服务器
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/173718_723dfe16_4804494.png "QQ截图20200531171615.png")
![输入图片说明](https://images.gitee.com/uploads/images/2020/0531/175839_3cd76426_4804494.png "QQ截图20200531171625.png")

具体的视频课程我会上传到B站和QQ群中。

3、Tomcat集群用虚拟机尝试搭建，具体的全套视频教程也会在B站中上传。[视频教程](https://www.bilibili.com/video/BV1tp4y1C7xj)

4、项目中涉及支付宝支付，由于是沙箱环境支付，需要下载，沙箱环境的支付宝钱包（https://sandbox.alipaydev.com/user/downloadApp.htm）
微信支付申请不下来。