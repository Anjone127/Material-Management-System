/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.6.15 : Database - mrp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`mrp` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `mrp`;

/*Table structure for table `accounts` */

DROP TABLE IF EXISTS `accounts`;

CREATE TABLE `accounts` (
  `id` varchar(32) NOT NULL,
  `people_id` varchar(32) DEFAULT NULL COMMENT '客户或供应商id',
  `order_id` varchar(32) DEFAULT NULL COMMENT '账目对应的订单',
  `amount` double DEFAULT NULL COMMENT '账目总金额',
  `date` datetime DEFAULT NULL,
  `type` varchar(32) DEFAULT NULL COMMENT '账目类型 0：应收1 ：应付',
  `state` varchar(32) DEFAULT NULL COMMENT '状态0：未完成 1：已完成',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账目表';

/*Data for the table `accounts` */

insert  into `accounts`(`id`,`people_id`,`order_id`,`amount`,`date`,`type`,`state`,`remark`) values ('35503745176111e79559408d5cfa8d4a','d12402e0ffcc11e6ac32408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a',6836,'2017-04-02 12:59:53','1','0',''),('36ca6f18176111e79559408d5cfa8d4a','d12402e0ffcc11e6ac32408d5cfa8d4a','5454b9560bb111e7b6d0408d5cfa8d4a',865,'2017-04-02 12:59:56','1','0',''),('3a92fb9a176111e79559408d5cfa8d4a','b58dd8080bae11e7b6d0408d5cfa8d4a','90733bc60f0611e7b7fc408d5cfa8d4a',3000,'2017-04-02 13:00:02','0','0',''),('3bf85a29176111e79559408d5cfa8d4a','b58dd8080bae11e7b6d0408d5cfa8d4a','e68f39230baf11e7b6d0408d5cfa8d4a',4999,'2017-04-02 13:00:04','0','1','');

/*Table structure for table `customer` */

DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `id` varchar(32) NOT NULL,
  `number` varchar(30) NOT NULL COMMENT '客户编号',
  `name` varchar(32) NOT NULL COMMENT '客户名称',
  `address` varchar(50) NOT NULL COMMENT '客户地址',
  `linkman` varchar(20) NOT NULL COMMENT '联系人',
  `tel` varchar(20) NOT NULL COMMENT '电话',
  `remark` varchar(100) NOT NULL COMMENT '备注',
  `money` double NOT NULL COMMENT '应收款',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户信息表';

/*Data for the table `customer` */

insert  into `customer`(`id`,`number`,`name`,`address`,`linkman`,`tel`,`remark`,`money`) values ('b58dd8080bae11e7b6d0408d5cfa8d4a','C0001','贝贝','明德楼1 217','贝贝','15167191234','',0);

/*Table structure for table `inventory_log` */

DROP TABLE IF EXISTS `inventory_log`;

CREATE TABLE `inventory_log` (
  `id` varchar(32) NOT NULL,
  `date` datetime DEFAULT NULL COMMENT '日期',
  `type` varchar(1) DEFAULT NULL COMMENT '0原材料入库   1生产消耗 2库存盘点  3产品入库  4产品销售',
  `inventory_name` varchar(100) DEFAULT NULL COMMENT '详情描述',
  `inventory_id` varchar(32) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `inventory_log` */

insert  into `inventory_log`(`id`,`date`,`type`,`inventory_name`,`inventory_id`,`amount`) values ('2174c037174011e79559408d5cfa8d4a','2017-04-02 09:03:06','0','Gigabyte/技嘉 B85M-D3V 台式机电脑主板 1150针脚','1130aedeffcf11e6ac32408d5cfa8d4a',1),('217531dd174011e79559408d5cfa8d4a','2017-04-02 09:03:06','0','AMD FX8350 原装盒装','3dcaf740ffce11e6ac32408d5cfa8d4a',1),('21759825174011e79559408d5cfa8d4a','2017-04-02 09:03:06','0','技嘉（GIGABYTE）X99-Gaming G1 WIFI主板 (Intel X99/LGA2011-3)','40852a44ffcf11e6ac32408d5cfa8d4a',1),('2175f96a174011e79559408d5cfa8d4a','2017-04-02 09:03:06','0','金士顿 DDR3 8GB','677b76c3ffcf11e6ac32408d5cfa8d4a',1),('2178716b174011e79559408d5cfa8d4a','2017-04-02 09:03:07','0','金士顿DDR4 2133 8GB台式机内存 骇客神条Fury系列 HX421C14FB/8','91efc83affcf11e6ac32408d5cfa8d4a',1),('2178dc13174011e79559408d5cfa8d4a','2017-04-02 09:03:07','0','Kingston 金士顿 4GB DDR3','98f558d8ffd011e6ac32408d5cfa8d4a',1),('2179569c174011e79559408d5cfa8d4a','2017-04-02 09:03:07','0','三星850 EVO系列 120G 2.5英寸 SATA-3固态硬盘(MZ-75E120B/CN)','ba4559a0ffcf11e6ac32408d5cfa8d4a',1),('2179c86d174011e79559408d5cfa8d4a','2017-04-02 09:03:07','0','酷冷至尊 暴雪T2 Plus （RR-UCP-S9C2）CPU散热器','c78ac072ffce11e6ac32408d5cfa8d4a',1),('217a3412174011e79559408d5cfa8d4a','2017-04-02 09:03:07','0','CoolerMaster 酷冷至尊 暴风S400 RR-UAH-L9C2','e0edb5d3ffce11e6ac32408d5cfa8d4a',1),('235cbfae174011e79559408d5cfa8d4a','2017-04-02 09:03:10','0','Gigabyte/技嘉 B85M-D3V 台式机电脑主板 1150针脚','1130aedeffcf11e6ac32408d5cfa8d4a',1),('235d175d174011e79559408d5cfa8d4a','2017-04-02 09:03:10','0','三星850 EVO系列 120G 2.5英寸 SATA-3固态硬盘(MZ-75E120B/CN)','ba4559a0ffcf11e6ac32408d5cfa8d4a',1),('2566c54f0f0611e7b7fc408d5cfa8d4a','2017-03-22 21:47:53','2','九州风神 玄冰智能','1c5dfbf6015911e7ac0e408d5cfa8d4a',8),('257c1fd1174011e79559408d5cfa8d4a','2017-04-02 09:03:13','4','i7 7700k GTX1060 游戏主机','8f829cca019411e7ac0e408d5cfa8d4a',1),('270b42ba174011e79559408d5cfa8d4a','2017-04-02 09:03:16','4','i7 7700k GTX1060 游戏主机','8f829cca019411e7ac0e408d5cfa8d4a',1),('2bb3f1090f0611e7b7fc408d5cfa8d4a','2017-03-22 21:48:04','2','影驰/Galaxy GTX750ti 骁将 2G D5 128位','1881d441015b11e7ac0e408d5cfa8d4a',4),('361438f2176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','Gigabyte/技嘉 B85M-D3V 台式机电脑主板 1150针脚','1130aedeffcf11e6ac32408d5cfa8d4a',1),('3614c6a2176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','AMD FX8350 原装盒装','3dcaf740ffce11e6ac32408d5cfa8d4a',1),('361549cb176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','技嘉（GIGABYTE）X99-Gaming G1 WIFI主板 (Intel X99/LGA2011-3)','40852a44ffcf11e6ac32408d5cfa8d4a',1),('3615b926176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','金士顿 DDR3 8GB','677b76c3ffcf11e6ac32408d5cfa8d4a',1),('3616220d176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','金士顿DDR4 2133 8GB台式机内存 骇客神条Fury系列 HX421C14FB/8','91efc83affcf11e6ac32408d5cfa8d4a',1),('3616b709176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','Kingston 金士顿 4GB DDR3','98f558d8ffd011e6ac32408d5cfa8d4a',1),('3617338a176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','三星850 EVO系列 120G 2.5英寸 SATA-3固态硬盘(MZ-75E120B/CN)','ba4559a0ffcf11e6ac32408d5cfa8d4a',1),('36179c75176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','酷冷至尊 暴雪T2 Plus （RR-UCP-S9C2）CPU散热器','c78ac072ffce11e6ac32408d5cfa8d4a',1),('3617f61e176111e79559408d5cfa8d4a','2017-04-02 12:59:54','0','CoolerMaster 酷冷至尊 暴风S400 RR-UAH-L9C2','e0edb5d3ffce11e6ac32408d5cfa8d4a',1),('38159b2b176111e79559408d5cfa8d4a','2017-04-02 12:59:58','0','Gigabyte/技嘉 B85M-D3V 台式机电脑主板 1150针脚','1130aedeffcf11e6ac32408d5cfa8d4a',1),('38161035176111e79559408d5cfa8d4a','2017-04-02 12:59:58','0','三星850 EVO系列 120G 2.5英寸 SATA-3固态硬盘(MZ-75E120B/CN)','ba4559a0ffcf11e6ac32408d5cfa8d4a',1),('3b461719176111e79559408d5cfa8d4a','2017-04-02 13:00:03','4','i7 7700k GTX1060 游戏主机','8f829cca019411e7ac0e408d5cfa8d4a',1),('3cbb9cf1176111e79559408d5cfa8d4a','2017-04-02 13:00:06','4','i7 7700k GTX1060 游戏主机','8f829cca019411e7ac0e408d5cfa8d4a',1),('7717fb2e0ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','Gigabyte/技嘉 B85M-D3V 台式机电脑主板 1150针脚','1130aedeffcf11e6ac32408d5cfa8d4a',1),('7718886f0ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','AMD FX8350 原装盒装','3dcaf740ffce11e6ac32408d5cfa8d4a',1),('771940750ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','技嘉（GIGABYTE）X99-Gaming G1 WIFI主板 (Intel X99/LGA2011-3)','40852a44ffcf11e6ac32408d5cfa8d4a',1),('7719cb5f0ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','金士顿 DDR3 8GB','677b76c3ffcf11e6ac32408d5cfa8d4a',1),('772315140ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','金士顿DDR4 2133 8GB台式机内存 骇客神条Fury系列 HX421C14FB/8','91efc83affcf11e6ac32408d5cfa8d4a',1),('772397e30ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','Kingston 金士顿 4GB DDR3','98f558d8ffd011e6ac32408d5cfa8d4a',1),('7723fb420ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','三星850 EVO系列 120G 2.5英寸 SATA-3固态硬盘(MZ-75E120B/CN)','ba4559a0ffcf11e6ac32408d5cfa8d4a',1),('77245f330ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','酷冷至尊 暴雪T2 Plus （RR-UCP-S9C2）CPU散热器','c78ac072ffce11e6ac32408d5cfa8d4a',1),('7724b90a0ee111e7b7fc408d5cfa8d4a','2017-03-22 17:25:19','0','CoolerMaster 酷冷至尊 暴风S400 RR-UAH-L9C2','e0edb5d3ffce11e6ac32408d5cfa8d4a',1),('82155d0f148411e79a12408d5cfa8d4a','2017-03-29 21:35:01','2','爱国者T3分体式防尘台式电脑机箱','83013fa7ffd111e6ac32408d5cfa8d4a',2),('b7ce725a0f0611e7b7fc408d5cfa8d4a','2017-03-22 21:51:59','3','i7 7700k GTX1060 游戏主机','8f829cca019411e7ac0e408d5cfa8d4a',1),('fe7a4f450f0511e7b7fc408d5cfa8d4a','2017-03-22 21:46:48','2','i7 7700k GTX1060 游戏主机','8f829cca019411e7ac0e408d5cfa8d4a',4);

/*Table structure for table `m_order` */

DROP TABLE IF EXISTS `m_order`;

CREATE TABLE `m_order` (
  `id` varchar(32) NOT NULL,
  `number` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `supplier_number` varchar(32) DEFAULT NULL COMMENT '供应商编号',
  `address` varchar(200) DEFAULT NULL COMMENT '发货地址',
  `total_money` double DEFAULT NULL COMMENT '订单总金额',
  `cancel_money` double DEFAULT NULL COMMENT '退货总金额',
  `state` varchar(1) DEFAULT NULL COMMENT '0  未审核 1 待发货 2发货中 3 订单完成',
  `start_time` datetime DEFAULT NULL COMMENT '下单时间',
  `final_time` datetime DEFAULT NULL COMMENT '最后操作时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `m_order` */

insert  into `m_order`(`id`,`number`,`supplier_number`,`address`,`total_money`,`cancel_money`,`state`,`start_time`,`final_time`,`remark`) values ('073528ee0ee111e7b7fc408d5cfa8d4a','O2017322','S0001','2',6836,0,'1','2017-03-22 17:22:11','2017-03-22 17:22:11',''),('361b8fdc186f11e7814a408d5cfa8d4a','O20170403','S0003','1',100,0,'0','2017-04-03 21:12:39','2017-04-03 21:12:39','无'),('5454b9560bb111e7b6d0408d5cfa8d4a','O0001','S0001','123',865,0,'3','2017-03-18 16:03:11','2017-03-18 16:03:11','123');

/*Table structure for table `m_order_detail` */

DROP TABLE IF EXISTS `m_order_detail`;

CREATE TABLE `m_order_detail` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单id',
  `material_id` varchar(32) DEFAULT NULL COMMENT '物料id',
  `material_number` varchar(32) DEFAULT NULL COMMENT '物料编号',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `cancel_amount` int(11) DEFAULT NULL COMMENT '退货数量',
  `price` double DEFAULT NULL COMMENT '物料单价金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

/*Data for the table `m_order_detail` */

insert  into `m_order_detail`(`id`,`order_id`,`material_id`,`material_number`,`amount`,`cancel_amount`,`price`) values ('00e165bdff4711e6a107408d5cfa8d4a','fd7a82c2ff4611e6a107408d5cfa8d4a','62380dcaff4611e6a107408d5cfa8d4a','222',2,0,22),('152de63c0ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','1130aedeffcf11e6ac32408d5cfa8d4a','M0005',1,0,405),('15e5e9b50ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','3dcaf740ffce11e6ac32408d5cfa8d4a','M0001',1,0,1225),('1669ba440ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','40852a44ffcf11e6ac32408d5cfa8d4a','M0016',1,0,3600),('17234c850ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','677b76c3ffcf11e6ac32408d5cfa8d4a','M0006',1,0,340),('17e1cbfb0ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','91efc83affcf11e6ac32408d5cfa8d4a','M0007',1,0,390),('1866b30e0ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','98f558d8ffd011e6ac32408d5cfa8d4a','M0011',1,0,187),('193c9f680ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','ba4559a0ffcf11e6ac32408d5cfa8d4a','M0008',1,0,460),('1a0578850ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','c78ac072ffce11e6ac32408d5cfa8d4a','M0003',1,0,99),('1ad4175d0ee111e7b7fc408d5cfa8d4a','073528ee0ee111e7b7fc408d5cfa8d4a','e0edb5d3ffce11e6ac32408d5cfa8d4a','M0004',1,0,130),('47ed7e11186f11e7814a408d5cfa8d4a','361b8fdc186f11e7814a408d5cfa8d4a','1c5dfbf6015911e7ac0e408d5cfa8d4a','M0019',2,0,50),('68a917710bb711e7b6d0408d5cfa8d4a','5454b9560bb111e7b6d0408d5cfa8d4a','1130aedeffcf11e6ac32408d5cfa8d4a','M0005',1,0,405),('69c499360bce11e7b6d0408d5cfa8d4a','5454b9560bb111e7b6d0408d5cfa8d4a','ba4559a0ffcf11e6ac32408d5cfa8d4a','M0008',1,0,460);

/*Table structure for table `material` */

DROP TABLE IF EXISTS `material`;

CREATE TABLE `material` (
  `id` varchar(32) NOT NULL,
  `supplier_number` varchar(32) NOT NULL COMMENT '供应商编号',
  `number` varchar(32) DEFAULT NULL COMMENT '编号',
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `price` double DEFAULT NULL COMMENT '价格',
  `type` varchar(1) DEFAULT NULL COMMENT '原材料种类 0 处理器 1散热器 2 主板 3 显卡 4 内存 5 硬盘 6 机箱 7 电源',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`),
  UNIQUE KEY `number_2` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料表';

/*Data for the table `material` */

insert  into `material`(`id`,`supplier_number`,`number`,`name`,`price`,`type`,`description`) values ('0293a305015b11e7ac0e408d5cfa8d4a','S0004','M0022','影驰GTX750-2GD5骁将',785,'0','品牌	影驰	型号	GTX750-2GD5骁将 上市时间	2014	货源类别	现货	类型	专业级 芯片厂方	nVIDIA	芯片型号	GTX750	核心位宽	128bit 显卡插槽	PCI Express 2.0	接口	DVI-I,HDMI,Display Port	显存容量	2GB'),('1130aedeffcf11e6ac32408d5cfa8d4a','S0001','M0005','Gigabyte/技嘉 B85M-D3V 台式机电脑主板 1150针脚',405,'2','品牌	Gigabyte/技嘉	型号	B85M-D3V	货源类别	现货 适用类型	台式机	主板架构	microATX	芯片厂商	intel 芯片组系列	Intel B85	CPU插槽类型	LGA 1150	支持CPU类型	Core i7/Core i5/Core i3/Pentium/Celeron'),('1881d441015b11e7ac0e408d5cfa8d4a','S0004','M0023','影驰/Galaxy GTX750ti 骁将 2G D5 128位',770,'3','品牌	影驰	型号	GTX1750TI	上市时间	2014 货源类别	现货	类型	游戏级	芯片厂方	nVIDIA 芯片型号	GTX750TI	核心位宽	128bit	显卡插槽	PCI EXPRESS 接口	VGA/HDMI/DVI	显存容量	2GB	显存类型	DDR5'),('1c5dfbf6015911e7ac0e408d5cfa8d4a','S0003','M0019','九州风神 玄冰智能',50,'1','品牌	Deepcool/九州风神	型号	玄冰智能 上市时间	2010	货源类别	现货	适用配件类型	CPU 适用品牌型号	Intel Socket LGA 115X/775 AMD...	类型	散热风扇	材质	铜 轴承类型	液压轴承	转速	9001600	噪音	21'),('3dcaf740ffce11e6ac32408d5cfa8d4a','S0001','M0001','AMD FX8350 原装盒装',1225,'0','品牌	AMD 型号	FX-8350 类型	CPU 核心数量	八核 核心代号	Piledriver 接口类型	Socket AM3+ 主频	4.0GHz 二级缓存	8MB 三级缓存	8M 制程工艺	32纳米 功率	125W 64位支持	是 特性	内存频率支持双通道 DDR3 1866MHz'),('40852a44ffcf11e6ac32408d5cfa8d4a','S0001','M0016','技嘉（GIGABYTE）X99-Gaming G1 WIFI主板 (Intel X99/LGA2011-3)',3600,'3','品牌	Gigabyte/技嘉	型号	X99-Gaming G1 WIFI	货源类别	现货 适用类型	台式机	主板架构	Ext ATX	芯片厂商	intel 芯片组系列	INTEL X99	CPU插槽类型	LGA 2011-3	前端总线频率	1333（MHz） 板载网卡	有板载网卡	显卡	集成显卡	支持内存类型	DDR3'),('60914b7fffd111e6ac32408d5cfa8d4a','S0002','M0012','炫影台式机电脑',145,'0','品牌	炫影	型号	炫影	上市时间	2016 货源类别	现货	适用类型	台式机	机箱样式	立式 兼容主板	microATX,ATX	材质	SPCC+钢化玻璃+亚克力	标配电源	无标配电源 前置接口	USB 3.0	尺寸	40*18.5*42.5（cm）	产品重量	5（KG）'),('677b76c3ffcf11e6ac32408d5cfa8d4a','S0001','M0006','金士顿 DDR3 8GB',340,'0','品牌	金士顿	型号	骇客神条 Fury系列	货源类别	现货 适用类型	台式机	内存类型	DDRIII	内存频率	DDR3 1600 产品重量	0.02（KG）	最快出货时间	1-3天	是否支持一件代发'),('83013fa7ffd111e6ac32408d5cfa8d4a','S0002','M0013','爱国者T3分体式防尘台式电脑机箱',109,'0','品牌	Aigo/爱国者	型号	T3	上市时间	2016 货源类别	现货	适用类型	台式机	机箱样式	立式 兼容主板	microATX,ATX	材质	SPCC	标配电源	无标配电源 前置接口	USB 3.0	尺寸	41*18*45（cm）	产品重量	6（KG）'),('8e0b881dffce11e6ac32408d5cfa8d4a','S0001','M0002','AMD A10-7700K',888,'0','品牌	AMD 系列	APU 型号	A10-7700K 类型	CPU 核心数量	四核 核心代号	Kaveri 接口类型	FM2+ 主频	3.4GHz Hyper Transports频率	2000MHz 倍频	17.5 二级缓存	4MB 功率	95W 指令集	MMX（+），3DNOW!（+），SSE, SSE2, SSE3, x86-64 64位支持	是'),('91efc83affcf11e6ac32408d5cfa8d4a','S0001','M0007','金士顿DDR4 2133 8GB台式机内存 骇客神条Fury系列 HX421C14FB/8',390,'4','品牌	金士顿	型号	HX421C14FB/8	适用类型	台式机 内存类型	DDR4	内存频率	其他	产品重量	0.2（KG） 最快出货时间	1-3天	是否支持一件代发	支持	发票	其他'),('98f558d8ffd011e6ac32408d5cfa8d4a','S0001','M0011','Kingston 金士顿 4GB DDR3',187,'4','品牌	金士顿	型号	KVR16N11S8/4-SPBK	货源类别	现货 适用类型	台式机	内存类型	DDRIII	内存频率	DDR3 1600 插脚数目	240PIN	产品重量	0.03（KG）	最快出货时间	1-3天 是否支持一件代发	支持'),('a3332697015c11e7ac0e408d5cfa8d4a','S0005','M0024','希捷500G 硬盘',208,'5','品牌	希捷	型号	ST500DM002	上市时间	2014 货源类别	现货	类型	桌面存储	接口	SATA接口 盘体尺寸	3.5（寸）	转速	7200（rpm）	缓存容量	16MB 质保	一年	产品重量	0.38（KG）	最快出货时间	1-3天'),('aac72a0effd111e6ac32408d5cfa8d4a','S0002','M0014','批发爱国者魔兽450W台式机电脑电源ATX主机机箱电源支持独显背线',90,'7','品牌	Aigo/爱国者	型号	魔兽450	上市时间	2015 货源类别	现货	适用类型	台式机	电源标准	ATX 12V 2.31 适用CPU范围	AMD INTEL	PFC类型	主动式PFC	额定功率	250W-300W（W） 认证	CCC	产品重量	2（KG）	最快出货时间	1-3天'),('ba4559a0ffcf11e6ac32408d5cfa8d4a','S0001','M0008','三星850 EVO系列 120G 2.5英寸 SATA-3固态硬盘(MZ-75E120B/CN)',460,'5','品牌	Samsung/三星	型号	MZ-75E120B/CN	货源类别	现货 类型	SSD便携固态移动硬盘	接口	SATA接口	盘体尺寸	2.5（寸） 质保	三年	产品重量	0.03（KG）	最快出货时间	1-3天 是否支持一件代发	支持	容量	120GB'),('c02d928f015911e7ac0e408d5cfa8d4a','S0004','M0020','Asus/华硕 PRIME B250M-K DDR4台式机游戏主板 支持7500 1151针',580,'2','品牌	华硕	型号	B250	上市时间	2017 货源类别	现货	适用类型	台式机	主板架构	microATX 芯片厂商	intel	芯片组系列	B250	CPU插槽类型	INTEL 1151 支持CPU类型	六代全系列	前端总线频率	2000（MHz）	板载声卡	带板载声卡'),('c78ac072ffce11e6ac32408d5cfa8d4a','S0001','M0003','酷冷至尊 暴雪T2 Plus （RR-UCP-S9C2）CPU散热器',99,'1','主体 品牌	 Coolermaster 酷冷至尊 型号	暴雪T2 Plus CPU散热器RR-UCP-S9C2 类别	CPU 风扇 散热器 规格 风扇尺寸	92*92*25mm 散热片尺寸	93*80*140mm 产品尺寸	 131.6（长） x 72.5（宽） x 152.3（高） 散热片材质	  2根热管 / 铝鳍片 转 速 2200RPM±10% 风流量	30CFM 噪音值	20dBA（Max.） 散热片材质	 2根热管 / 铝鳍片 散热方式	风冷 产品尺寸	93 x80 x 140mm  适用范围	 intel LGA1156/1155/775 系列CPU插槽；AMD FM1/AM3+/AM3/AM2+/AM2系列CPU插槽'),('c8385709015811e7ac0e408d5cfa8d4a','S0003','M0017','intel CPU 酷睿I7 7700K',2200,'0','品牌	Intel/英特尔	型号	I7 7700K 上市时间	2017	货源类别	现货	适用类型	台式机 CPU系列	酷睿I7	插槽类型	LGA	主频	4.2G 外频	200MHz	一级缓存	8KB	二级缓存	256KB'),('ce322edf015a11e7ac0e408d5cfa8d4a','S0004','M0021','影驰 GTX1060-3G',1570,'3','品牌	影驰	型号	GTX1060	上市时间	2016 货源类别	现货	类型	发烧级	芯片厂方	nVIDIA 芯片型号	GTX1060	核心位宽	192bit	显卡插槽	PCI EXPRESS 接口	HDMI、双DVI、DP	显存容量	3GB	显存类型	DDR5'),('decd70ad015d11e7ac0e408d5cfa8d4a','S0005','M0025','320G SATA 硬盘 串口',119,'5','品牌	其它	型号	320G	货源类别	现货 类型	桌面存储	接口	SATA接口	盘体尺寸	3.5（寸） 转速	7200（rpm）	缓存容量	8MB	质保	一年 产品重量	0.38（KG）	最快出货时间	1-3天	是否支持一件代发	支持'),('df695a13015811e7ac0e408d5cfa8d4a','S0003','M0018','inte CPU l酷睿I7 7700 3.6G',1928,'0','品牌	Intel/英特尔	型号	I7 7700 上市时间	2017	货源类别	现货	适用类型	台式机 CPU系列	酷睿I7	插槽类型	LGA	主频	3.6G 外频	133MHz	一级缓存	8KB	二级缓存	256KB'),('e0edb5d3ffce11e6ac32408d5cfa8d4a','S0001','M0004','CoolerMaster 酷冷至尊 暴风S400 RR-UAH-L9C2',130,'1','主体 品牌	 Coolermaster 酷冷至尊 型号	暴风S400 类别	CPU 风扇 散热器 规格 风扇尺寸	100X100X25mm 散热片尺寸	130.4X96.75X79mm 产品尺寸	 131.6（长） x 72.5（宽） x 152.3（高） 材质	  4根热管/铝鳍片 转 速 1800± 10%RPM 风流量	55.2cfm 噪音值	20dBA（Max.） 散热片材质	 四根热管+铝鳍片 电源接口	3pin 散热方式	风冷 总重量	699 g 产品尺寸	10 x 10 x 2.5 cm  适用范围	 Intel LGA775/1156/1366架构及AMD AM2/AM3架构'),('e3a50feeffd111e6ac32408d5cfa8d4a','S0002','M0015','Aigo/爱国者额定500W电竞500电源 电脑台式机电源',265,'7','品牌	Aigo/爱国者	型号	电竞500	货源类别	现货 适用类型	台式机	电源标准	ATX 12V 2.31	PFC类型	主动式PFC 额定功率	500W以上（W）	认证	CCC,80 Plus	产品重量	2（KG） 最快出货时间	1-3天'),('f5b6ba0effcf11e6ac32408d5cfa8d4a','S0001','M0009','Samsung/三星 固态硬盘 850 EVO系列 1TB 2.5英寸 MZ-75E1T0B/CN',2580,'5','品牌	Samsung/三星	型号	MZ-75E1T0B/CN	接口类型	其他 硬盘尺寸	2.5英寸	写入数据传输率	520（MB/s）	读取数据传输率	540（MB/s） 容量	1TB');

/*Table structure for table `material_inventory` */

DROP TABLE IF EXISTS `material_inventory`;

CREATE TABLE `material_inventory` (
  `id` varchar(32) NOT NULL,
  `material_id` varchar(32) DEFAULT NULL COMMENT '物料id',
  `material_number` varchar(32) DEFAULT NULL COMMENT '物料number',
  `amount` int(11) DEFAULT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料库存表';

/*Data for the table `material_inventory` */

insert  into `material_inventory`(`id`,`material_id`,`material_number`,`amount`) values ('0293dc00015b11e7ac0e408d5cfa8d4a','0293a305015b11e7ac0e408d5cfa8d4a','M0022',1),('11330552ffcf11e6ac32408d5cfa8d4a','1130aedeffcf11e6ac32408d5cfa8d4a','M0005',8),('18820597015b11e7ac0e408d5cfa8d4a','1881d441015b11e7ac0e408d5cfa8d4a','M0023',4),('1c5e34de015911e7ac0e408d5cfa8d4a','1c5dfbf6015911e7ac0e408d5cfa8d4a','M0019',6),('3dcb4fcaffce11e6ac32408d5cfa8d4a','3dcaf740ffce11e6ac32408d5cfa8d4a','M0001',3),('40856543ffcf11e6ac32408d5cfa8d4a','40852a44ffcf11e6ac32408d5cfa8d4a','M00056',3),('60919270ffd111e6ac32408d5cfa8d4a','60914b7fffd111e6ac32408d5cfa8d4a','M0012',0),('677bad09ffcf11e6ac32408d5cfa8d4a','677b76c3ffcf11e6ac32408d5cfa8d4a','M0006',3),('83016b63ffd111e6ac32408d5cfa8d4a','83013fa7ffd111e6ac32408d5cfa8d4a','M0013',0),('8e0bb2e4ffce11e6ac32408d5cfa8d4a','8e0b881dffce11e6ac32408d5cfa8d4a','M0002',0),('91effff9ffcf11e6ac32408d5cfa8d4a','91efc83affcf11e6ac32408d5cfa8d4a','M0007',1),('98f58e0cffd011e6ac32408d5cfa8d4a','98f558d8ffd011e6ac32408d5cfa8d4a','M0011',3),('a3334b9c015c11e7ac0e408d5cfa8d4a','a3332697015c11e7ac0e408d5cfa8d4a','M0024',-2),('aac75e25ffd111e6ac32408d5cfa8d4a','aac72a0effd111e6ac32408d5cfa8d4a','M0014',0),('ba458b62ffcf11e6ac32408d5cfa8d4a','ba4559a0ffcf11e6ac32408d5cfa8d4a','M0008',7),('c02dc5d7015911e7ac0e408d5cfa8d4a','c02d928f015911e7ac0e408d5cfa8d4a','M0020',-2),('c78af9caffce11e6ac32408d5cfa8d4a','c78ac072ffce11e6ac32408d5cfa8d4a','M0003',3),('c8388205015811e7ac0e408d5cfa8d4a','c8385709015811e7ac0e408d5cfa8d4a','M0017',-2),('ce32588d015a11e7ac0e408d5cfa8d4a','ce322edf015a11e7ac0e408d5cfa8d4a','M0021',-2),('decdd1e0015d11e7ac0e408d5cfa8d4a','decd70ad015d11e7ac0e408d5cfa8d4a','M0025',0),('df698a47015811e7ac0e408d5cfa8d4a','df695a13015811e7ac0e408d5cfa8d4a','M0018',0),('e0edf494ffce11e6ac32408d5cfa8d4a','e0edb5d3ffce11e6ac32408d5cfa8d4a','M0004',3),('e3a5432cffd111e6ac32408d5cfa8d4a','e3a50feeffd111e6ac32408d5cfa8d4a','M0015',-2),('f5b6f38effcf11e6ac32408d5cfa8d4a','f5b6ba0effcf11e6ac32408d5cfa8d4a','M0009',0);

/*Table structure for table `p_order` */

DROP TABLE IF EXISTS `p_order`;

CREATE TABLE `p_order` (
  `id` varchar(32) NOT NULL,
  `number` varchar(32) DEFAULT NULL COMMENT '订单编号',
  `customer_number` varchar(32) DEFAULT NULL COMMENT '顾客编号',
  `address` varchar(100) DEFAULT NULL COMMENT '收货地址',
  `total_money` double DEFAULT NULL COMMENT '订单总金额',
  `cancel_money` double DEFAULT NULL COMMENT '退货总金额',
  `state` varchar(1) DEFAULT NULL COMMENT '0 未审核 1 未发货 2 发货中 3 订单完成 4已撤销',
  `start_time` datetime DEFAULT NULL COMMENT '下单时间',
  `final_time` datetime DEFAULT NULL COMMENT '最后操作时间',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单表';

/*Data for the table `p_order` */

insert  into `p_order`(`id`,`number`,`customer_number`,`address`,`total_money`,`cancel_money`,`state`,`start_time`,`final_time`,`remark`) values ('90733bc60f0611e7b7fc408d5cfa8d4a','O20173222150','C0001','明德楼1 217',3000,0,'3','2017-03-22 21:50:53','2017-03-22 21:50:53','2'),('e68f39230baf11e7b6d0408d5cfa8d4a','O0001','C0001','明德楼1 217',4999,0,'3','2017-03-18 00:00:00','2017-03-18 00:00:00','123');

/*Table structure for table `p_order_detail` */

DROP TABLE IF EXISTS `p_order_detail`;

CREATE TABLE `p_order_detail` (
  `id` varchar(32) NOT NULL,
  `order_id` varchar(32) DEFAULT NULL COMMENT '订单id',
  `product_id` varchar(32) DEFAULT NULL COMMENT '产品id',
  `product_number` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `amount` int(11) DEFAULT NULL COMMENT '数量',
  `cancel_amount` int(11) DEFAULT NULL COMMENT '退货数量',
  `price` double DEFAULT NULL COMMENT '产品单价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单详情表';

/*Data for the table `p_order_detail` */

insert  into `p_order_detail`(`id`,`order_id`,`product_id`,`product_number`,`amount`,`cancel_amount`,`price`) values ('4b1e13110c7111e79b99408d5cfa8d4a','e68f39230baf11e7b6d0408d5cfa8d4a','8f829cca019411e7ac0e408d5cfa8d4a','P0001',1,0,4999),('afd7336b0f0611e7b7fc408d5cfa8d4a','90733bc60f0611e7b7fc408d5cfa8d4a','8f829cca019411e7ac0e408d5cfa8d4a','P0001',1,0,3000);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` varchar(32) NOT NULL,
  `number` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `price` double NOT NULL COMMENT '价格',
  `cost` double DEFAULT NULL COMMENT '成本价',
  `typeid` varchar(32) DEFAULT NULL COMMENT '种类id',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品表';

/*Data for the table `product` */

insert  into `product`(`id`,`number`,`name`,`price`,`cost`,`typeid`,`description`) values ('8f829cca019411e7ac0e408d5cfa8d4a','P0001','i7 7700k GTX1060 游戏主机',3000,11405,'6b599ba2018711e7ac0e408d5cfa8d4a','');

/*Table structure for table `product_detail` */

DROP TABLE IF EXISTS `product_detail`;

CREATE TABLE `product_detail` (
  `id` varchar(32) NOT NULL,
  `product_id` varchar(32) DEFAULT NULL,
  `material_id` varchar(32) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `product_detail` */

insert  into `product_detail`(`id`,`product_id`,`material_id`,`amount`) values ('4fec8481333611e7b954408d5cfa8d4a','8f829cca019411e7ac0e408d5cfa8d4a','c8385709015811e7ac0e408d5cfa8d4a',5),('5d8a1b7b333611e7b954408d5cfa8d4a','8f829cca019411e7ac0e408d5cfa8d4a','1130aedeffcf11e6ac32408d5cfa8d4a',1);

/*Table structure for table `product_inventory` */

DROP TABLE IF EXISTS `product_inventory`;

CREATE TABLE `product_inventory` (
  `id` varchar(32) NOT NULL,
  `product_id` varchar(32) DEFAULT NULL COMMENT '产品id',
  `product_number` varchar(32) DEFAULT NULL COMMENT '产品编号',
  `amount` int(11) DEFAULT NULL COMMENT '库存库存',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='物料库存表';

/*Data for the table `product_inventory` */

insert  into `product_inventory`(`id`,`product_id`,`product_number`,`amount`) values ('8f89839f019411e7ac0e408d5cfa8d4a','8f829cca019411e7ac0e408d5cfa8d4a','P0001',0);

/*Table structure for table `product_type` */

DROP TABLE IF EXISTS `product_type`;

CREATE TABLE `product_type` (
  `id` varchar(32) NOT NULL,
  `number` varchar(32) NOT NULL COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `description` varchar(100) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='产品类别';

/*Data for the table `product_type` */

insert  into `product_type`(`id`,`number`,`name`,`description`) values ('6092bc0b018711e7ac0e408d5cfa8d4a','0001','入门游戏主机','无'),('6b599ba2018711e7ac0e408d5cfa8d4a','0002','高端游戏主机','无'),('f2ee6dc6018611e7ac0e408d5cfa8d4a','0000','办公主机','无');

/*Table structure for table `production` */

DROP TABLE IF EXISTS `production`;

CREATE TABLE `production` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_number` varchar(32) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL COMMENT '0待生产 1 生产完成 2已撤销',
  `date` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `production` */

insert  into `production`(`id`,`product_number`,`amount`,`state`,`date`,`finish_time`) values (1,'P0001',1,'1','2017-03-18 19:47:59',NULL),(2,'P0001',20,'0','2017-04-04 08:43:54',NULL);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `role_name` varchar(32) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`role_name`,`description`) values ('1','系统管理员','admin',NULL),('2','进货管理员','purchase',NULL),('3','销售管理员','sale',NULL),('4','生产管理员','production',NULL),('5','账款管理员','account',NULL);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `id` varchar(32) NOT NULL,
  `number` varchar(30) NOT NULL COMMENT '编号',
  `name` varchar(32) NOT NULL COMMENT '名称',
  `address` varchar(50) NOT NULL COMMENT '地址',
  `linkman` varchar(20) NOT NULL COMMENT '联系人',
  `tel` varchar(20) NOT NULL COMMENT '电话',
  `url` varchar(100) DEFAULT NULL COMMENT '网站',
  `remark` varchar(100) NOT NULL COMMENT '备注',
  `money` double NOT NULL COMMENT '应收款',
  PRIMARY KEY (`id`),
  UNIQUE KEY `number` (`number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应商信息表';

/*Data for the table `supplier` */

insert  into `supplier`(`id`,`number`,`name`,`address`,`linkman`,`tel`,`url`,`remark`,`money`) values ('420323c5ffd111e6ac32408d5cfa8d4a','S0002','佛山市润通嘉华电子有限公司','广东省 佛山 太平洋数码广场A场219','林永青 先生','020-87549866','','机箱 电源 散热器 键鼠套装批发店',0),('49c418d1015811e7ac0e408d5cfa8d4a','S0003','北京英红诚信电子科技发展中心','中国北京海淀区海淀镇西草场1号硅谷电脑市场1C16号','朱国保 先生','010-82851469','','北京英红诚信电子科技 经销批发的CPU、电脑配件、CPU',0),('6abb6263015c11e7ac0e408d5cfa8d4a','S0005','石牌茗叠电脑经营部','中国 广东 广州市天河区 石牌西太平洋数码广场','滕冬英 女士','86-020-85261529','https://shop1446223686127.1688.com','广州市天河区石牌茗叠电脑经营部 专业批发硬盘存储,畅销消费者市场，享有较高的地位，公司与多家零售商和代理商建立了长期稳定的合作关系。',0),('a30d4f5a015911e7ac0e408d5cfa8d4a','S0004','东莞市樟木头腾宇电脑经营部','中国 广东 东莞市 樟木头荔景山庄珊瑚阁GA','叶丽华 先生','86-07690-87786526','','华硕主板 asus主板 ddr4主板',0),('d12402e0ffcc11e6ac32408d5cfa8d4a','S0001','深圳市鹏城泰克计算机有限公司','广东省深圳市福田区福华路嘉汇新城嘉祥阁7B','林先生','0755-83759952',NULL,'公司的主营产品有：电脑、电脑周边产品及数码产品。包括：移动硬盘，笔记本硬盘，台式机硬盘，硬盘盒，服务器硬盘，数码产品，办公产品，网络产品，兼容机，品牌机等。代理品牌：希捷，西数，日立，HP等。',0);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) NOT NULL COMMENT '账号',
  `user_password` varchar(32) NOT NULL COMMENT '密码',
  `user_password_salt` varchar(32) NOT NULL COMMENT '密码盐值',
  `user_name` varchar(32) DEFAULT NULL COMMENT '姓名',
  `user_role_id` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `user` */

insert  into `user`(`id`,`user_id`,`user_password`,`user_password_salt`,`user_name`,`user_role_id`) values ('1a10e84d07de11e7852e408d5cfa8d4a','Purchase','123456','1231','张三','2'),('46a5d82b088611e78865408d5cfa8d4a','Production','123456','1231','王五','4'),('6c18104a03cb11e787f5408d5cfa8d4a','Anjone','123456','1231','管佳一','1'),('e3bc8a3507e611e7852e408d5cfa8d4a','Sale','123456','1231','李四','3');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
