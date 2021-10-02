/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.23 : Database - zshop
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`zshop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `zshop`;

/*Table structure for table `t_cart` */

DROP TABLE IF EXISTS `t_cart`;

CREATE TABLE `t_cart` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '购物车主键id',
  `customer_id` int(11) unsigned DEFAULT NULL COMMENT '客户的id',
  `product_id` int(11) unsigned DEFAULT NULL COMMENT '商品的id',
  `product_num` int(100) unsigned DEFAULT NULL COMMENT '商品数量',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '商品加入购物车的时间',
  `total_price` double DEFAULT NULL COMMENT '某一购物车商品总价',
  `status` tinyint(1) DEFAULT NULL COMMENT '表示购物车中的某个商品是否有效,默认有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

/*Data for the table `t_cart` */

insert  into `t_cart`(`id`,`customer_id`,`product_id`,`product_num`,`create_time`,`total_price`,`status`) values (1,14,29,1,'2019-04-06 15:15:11',0.1,0),(2,14,52,1,'2019-04-06 15:20:25',0.1,0),(3,14,31,1,'2019-04-06 15:21:11',0.1,0),(4,14,50,1,'2019-04-06 15:22:02',0.1,0),(5,14,31,1,'2019-04-06 15:23:34',0.1,0),(6,14,31,1,'2019-04-06 15:27:14',0.1,0),(7,14,55,1,'2019-04-06 15:56:27',0.1,1),(8,17,18,1,'2019-04-06 16:20:53',0.1,0),(9,14,18,1,'2019-04-07 12:20:26',0.1,1),(10,14,31,1,'2019-04-07 14:33:23',0.1,0),(11,14,50,1,'2019-04-07 14:34:09',0.1,0),(12,14,18,1,'2019-04-07 17:07:52',0.1,0),(13,14,31,1,'2019-04-07 17:19:41',0.1,0);

/*Table structure for table `t_customer` */

DROP TABLE IF EXISTS `t_customer`;

CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `login_name` varchar(20) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `regist_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `t_customer` */

insert  into `t_customer`(`id`,`name`,`login_name`,`password`,`phone`,`address`,`is_valid`,`regist_date`) values (14,'jx','xiaomo','E10ADC3949BA59ABBE56E057F20F883E','13641711060','上海浦东新区',1,'2019-04-06 15:13:58'),(15,'lhy','lhy','F4F58B66C406CE6670505F7A18B3FA20','15601762797','上海浦东新区',0,'2019-04-06 16:05:45'),(16,'test','test','E10ADC3949BA59ABBE56E057F20F883E','13988888888','上海徐汇区',0,'2019-04-06 16:08:14'),(17,'小红','xiaohong','E10ADC3949BA59ABBE56E057F20F883E','13132839249','上海浦东新区',0,'2019-04-06 16:15:29');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单的id',
  `order_number` varchar(300) DEFAULT NULL COMMENT '订单编号',
  `customer_id` int(11) DEFAULT NULL COMMENT '用户的id',
  `price` double DEFAULT NULL COMMENT '该笔订单的商品总价',
  `create_date` timestamp NULL DEFAULT NULL COMMENT '订单生成的时间',
  `product_number` int(11) DEFAULT NULL COMMENT '该笔订单中包含的所有商品数量',
  `status` tinyint(4) DEFAULT NULL COMMENT '订单的状态，默认0未支付未发货，1则表示支付未发货，2表示已支付未发货，2表示已支付已发货，3 表示已发货未收货，4表示订单完成交易',
  `address` varchar(200) DEFAULT NULL COMMENT '订单的收获地址',
  PRIMARY KEY (`id`),
  KEY `customer_id` (`customer_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`customer_id`) REFERENCES `t_customer` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`order_number`,`customer_id`,`price`,`create_date`,`product_number`,`status`,`address`) values (107,'201904060686452335',14,0.1,'2019-04-06 15:19:12',1,3,'上海市上海市浦东新区川沙新镇'),(108,'201904061129658616',14,0.1,'2019-04-06 15:20:31',1,2,'上海市上海市浦东新区川沙新镇'),(109,'201904060187320232',14,0.1,'2019-04-06 15:21:14',1,1,'上海市上海市浦东新区中山街888号'),(110,'201904060869626209',14,0.1,'2019-04-06 15:22:04',1,1,'上海市上海市浦东新区中山街888号'),(111,'201904060220985105',14,0.1,'2019-04-06 15:26:33',1,1,'上海市上海市浦东新区川沙新镇'),(112,'201904061582759120',14,0.1,'2019-04-06 15:27:15',1,4,'上海市上海市浦东新区中山街888号'),(113,'201904060479859511',17,0.1,'2019-04-06 16:33:43',1,0,'北京市北京市西城区中山街66611'),(114,'201904071827204931',14,0.1,'2019-04-07 14:33:27',1,0,'上海市上海市浦东新区川沙新镇'),(115,'201904072018353239',14,0.1,'2019-04-07 14:34:12',1,1,'上海市上海市浦东新区中山街888号'),(116,'201904071407195995',14,0.1,'2019-04-07 17:07:55',1,0,'上海市上海市浦东新区中山街888号'),(117,'201904070661052151',14,0.1,'2019-04-07 17:19:44',1,1,'上海市上海市浦东新区川沙新镇');

/*Table structure for table `t_order_item` */

DROP TABLE IF EXISTS `t_order_item`;

CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '类目表id',
  `product_id` int(11) DEFAULT NULL COMMENT '商品id',
  `num` int(11) DEFAULT NULL COMMENT '类目商品数量',
  `price` double DEFAULT NULL COMMENT '类目商品小计',
  `order_id` int(11) DEFAULT NULL COMMENT '订单id',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `t_product` (`id`),
  CONSTRAINT `t_order_item_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `t_order_item` */

insert  into `t_order_item`(`id`,`product_id`,`num`,`price`,`order_id`) values (1,29,1,0.1,107),(2,52,1,0.1,108),(3,31,1,0.1,109),(4,50,1,0.1,110),(5,31,1,0.1,111),(6,31,1,0.1,112),(7,18,1,0.1,113),(8,31,1,0.1,114),(9,50,1,0.1,115),(10,18,1,0.1,116),(11,31,1,0.1,117);

/*Table structure for table `t_product` */

DROP TABLE IF EXISTS `t_product`;

CREATE TABLE `t_product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `price` double DEFAULT NULL,
  `info` varchar(200) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  `product_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `product_type_id` (`product_type_id`),
  CONSTRAINT `t_product_ibfk_1` FOREIGN KEY (`product_type_id`) REFERENCES `t_product_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8;

/*Data for the table `t_product` */

insert  into `t_product`(`id`,`name`,`price`,`info`,`image`,`product_type_id`) values (18,'砂糖橘2',0.1,'又大又甜的砂糖橘，皮薄柔嫩，特别好吃。全国包邮！','https://www.xmlvhy.com/images/20190406/20190400610241843.jpg',16),(20,'砂糖橘3',0.1,'新鲜肉填，不打蜡纯天然，肉质细腻。就是好吃！！！','https://www.xmlvhy.com/images/20190406/20190400610240595.jpg',16),(29,'砂糖橘4',0.1,'不泡药不催熟，皮薄柔嫩，就是好吃！','https://www.xmlvhy.com/images/20190406/20190400610243128.jpg',16),(31,'砂糖橘6',0.1,'砂糖橘皮薄柔嫩，好吃的不得了呢！！！','https://www.xmlvhy.com/images/20190406/20190400610244734.jpg',16),(49,'贡柑',0.1,'非常的甜，肉质爽口。送亲戚送朋友，面子有！快点购买吧！','https://www.xmlvhy.com/images/20190406/20190400610251277.jpg',16),(50,'马蹄',0.1,'老家特产，纯天然食品。个大肉甜。五斤包邮哦！！！','https://www.xmlvhy.com/images/20190406/20190400610261038.jpg',1),(51,'芋头',0.1,'荔浦正宗芋头，个大肉质软。特别的香！！！','https://www.xmlvhy.com/images/20190406/20190400610263371.jpg',1),(52,'芋头1',0.1,'荔浦正宗芋头，个大肉质软。特别的香！！！','https://www.xmlvhy.com/images/20190406/20190400610265176.jpg',1),(53,'芋头2',0.1,'荔浦正宗芋头，个大肉质软。特别的香！！！','https://www.xmlvhy.com/images/20190406/20190400610271788.png',1),(54,'柠檬',0.1,'新鲜的柠檬，今日特价，五斤包邮','https://www.xmlvhy.com/images/20190406/20190400610294458.jpg',16),(55,'香蕉',0.1,'好吃又新鲜的香蕉，越南进口，快来抢购吧！','https://www.xmlvhy.com/images/20190406/20190400610304727.jpg',16),(56,'菠萝',0.1,'又香又甜的菠萝，包你满意，快来抢购吧！','https://www.xmlvhy.com/images/20190406/20190400610320587.jpg',16);

/*Table structure for table `t_product_type` */

DROP TABLE IF EXISTS `t_product_type`;

CREATE TABLE `t_product_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `t_product_type` */

insert  into `t_product_type`(`id`,`name`,`status`) values (1,'食品',1),(2,'衣服',0),(3,'数码',0),(4,'生活用品',0),(5,'家装',0),(6,'旅游',0),(7,'运动',0),(8,'电器',0),(9,'家居',0),(10,'配饰',0),(11,'裤子',0),(12,'包包',0),(13,'鞋子',0),(14,'内衣',0),(15,'裙子',0),(16,'新鲜水果',1);

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_name`) values (4,'客户经理'),(7,'普通用户'),(6,'最高管理员'),(5,'项目经理');

/*Table structure for table `t_shipping` */

DROP TABLE IF EXISTS `t_shipping`;

CREATE TABLE `t_shipping` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '收货地址的主键id',
  `customer_id` int(11) unsigned DEFAULT NULL COMMENT '客户id,地址绑定客户',
  `receiver_name` varchar(200) DEFAULT NULL COMMENT '收货人的姓名',
  `receiver_phone` varchar(100) DEFAULT NULL COMMENT '收货人的座机电话',
  `receiver_mobile` varchar(100) DEFAULT NULL COMMENT '收货人的手机号码',
  `receiver_city` varchar(50) DEFAULT NULL COMMENT '城市名称',
  `receiver_province` varchar(50) DEFAULT NULL COMMENT '省份名称',
  `receiver_district` varchar(100) DEFAULT NULL COMMENT '区, 区域, 县, 地域, 一带, 禺',
  `zip_code` varchar(50) DEFAULT NULL COMMENT '邮件编码',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(4) DEFAULT NULL COMMENT '收货地址标志：默认为0，1 表示默认的收获地址，备注：这里是预留字段',
  `address_detail` varchar(100) DEFAULT NULL COMMENT '收货地址的详情地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table `t_shipping` */

insert  into `t_shipping`(`id`,`customer_id`,`receiver_name`,`receiver_phone`,`receiver_mobile`,`receiver_city`,`receiver_province`,`receiver_district`,`zip_code`,`create_time`,`update_time`,`status`,`address_detail`) values (18,14,'jx','07737160120','13641711060','上海市','上海市','浦东新区','546604','2019-04-06 15:16:28','2019-04-06 15:16:28',1,'中山街888号'),(19,14,'小媛','07737160888','15601762797','上海市','上海市','浦东新区','10000112','2019-04-06 15:19:06','2019-04-06 15:19:06',1,'川沙新镇'),(20,17,'jx','07737160888','15601762797','北京市','北京市','西城区','10000112','2019-04-06 16:27:21','2019-04-06 16:29:17',1,'中山街66611'),(21,17,'jx11','07737160888','15601762797','天津市','天津市','河东区','10000112','2019-04-06 16:30:14','2019-04-06 16:30:19',0,'中山街11255'),(22,17,'jx11','07737160888','15601762797','北京市','北京市','丰台区','10000112','2019-04-06 16:30:36','2019-04-06 16:33:40',0,'中山街1125533');

/*Table structure for table `t_sysuser` */

DROP TABLE IF EXISTS `t_sysuser`;

CREATE TABLE `t_sysuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `login_name` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`),
  KEY `role_id` (`role_id`),
  CONSTRAINT `t_sysuser_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_sysuser` */

insert  into `t_sysuser`(`id`,`name`,`login_name`,`password`,`phone`,`email`,`is_valid`,`create_date`,`role_id`) values (1,'admin','admin','202CB962AC59075B964B07152D234B70','13641711060','1059224309@qq.com',0,'2019-04-06 14:57:40',6),(2,'xiaomo','xiaomo','F4F58B66C406CE6670505F7A18B3FA20','13641711060','1059224309@qq.com',1,'2019-04-06 14:59:08',6),(3,'test','test','098F6BCD4621D373CADE4E832627B4F6','15574157894','1059224309@qq.com',1,'2019-04-06 18:33:41',7);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
