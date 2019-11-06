-- MySQL dump 10.13  Distrib 5.6.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: smart
-- ------------------------------------------------------
-- Server version	5.6.42

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `iprecord`
--

DROP TABLE IF EXISTS `iprecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `iprecord` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `ip` bigint(20) NOT NULL COMMENT 'ip(long类型)',
  `func` varchar(60) DEFAULT NULL COMMENT '功能',
  `country` varchar(50) DEFAULT '其他' COMMENT '国家',
  `city` varchar(50) DEFAULT NULL COMMENT '城市',
  `region_name` varchar(50) DEFAULT NULL COMMENT '地区',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `status` varchar(100) NOT NULL DEFAULT '0' COMMENT '状态(0未解析归属地,1已解析)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `iprecord_time_idx` (`time`,`ip`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=103 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iprecord`
--

LOCK TABLES `iprecord` WRITE;
/*!40000 ALTER TABLE `iprecord` DISABLE KEYS */;
INSERT INTO `iprecord` VALUES (2,1880188801,'[Ljava.lang.annotation.Annotation;@15ec5dea','中国','深圳市','广东','2019-09-30 17:54:11','1'),(3,1880188801,'login','中国','深圳市','广东1','2019-09-30 18:01:54','2'),(4,1942259817,'login','浙江省杭州市 电信','杭州市','浙江省','2019-10-07 18:29:38','3'),(5,1942259817,'login','其他',NULL,NULL,'2019-10-07 18:59:02','0'),(6,1942259817,'login','其他',NULL,NULL,'2019-10-07 19:19:14','0'),(7,1942259817,'login','其他',NULL,NULL,'2019-10-07 23:00:55','0'),(8,1880188801,'login','其他',NULL,NULL,'2019-10-08 09:45:15','0'),(9,1880188801,'login','其他',NULL,NULL,'2019-10-08 10:20:51','0'),(10,1880188801,'login','其他',NULL,NULL,'2019-10-08 10:23:19','0'),(11,1880188801,'login','其他',NULL,NULL,'2019-10-08 10:28:26','0'),(12,1880188801,'login','其他',NULL,NULL,'2019-10-08 10:28:38','0'),(13,1880188801,'login','其他',NULL,NULL,'2019-10-08 10:32:16','0'),(14,1880188801,'login','其他',NULL,NULL,'2019-10-08 10:32:33','0'),(15,1880188801,'login','其他',NULL,NULL,'2019-10-08 11:02:10','0'),(16,1880188801,'login','其他',NULL,NULL,'2019-10-08 15:50:28','0'),(17,1880188801,'login','其他',NULL,NULL,'2019-10-08 15:57:36','0'),(18,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:07:23','0'),(19,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:07:34','0'),(20,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:09:10','0'),(21,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:09:32','0'),(22,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:10:07','0'),(23,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:15:21','0'),(24,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:16:38','0'),(25,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:17:36','0'),(26,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:18:31','0'),(27,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:18:46','0'),(28,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:21:21','0'),(29,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:21:53','0'),(30,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:22:07','0'),(31,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:25:57','0'),(32,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:27:43','0'),(33,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:29:05','0'),(34,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:30:00','0'),(35,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:31:19','0'),(36,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:52:04','0'),(37,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:53:34','0'),(38,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:55:56','0'),(39,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:56:21','0'),(40,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:58:48','0'),(41,1880188801,'login','其他',NULL,NULL,'2019-10-09 11:59:14','0'),(42,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:00:22','0'),(43,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:01:08','0'),(44,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:03:29','0'),(45,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:05:47','0'),(46,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:08:12','0'),(47,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:13:30','0'),(48,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:13:46','0'),(49,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:15:12','0'),(50,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:16:17','0'),(51,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:17:11','0'),(52,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:18:04','0'),(53,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:19:13','0'),(54,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:20:03','0'),(55,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:20:13','0'),(56,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:21:00','0'),(57,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:22:19','0'),(58,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:22:32','0'),(59,1880188801,'login','其他',NULL,NULL,'2019-10-09 12:32:28','0'),(60,1880188801,'login','其他',NULL,NULL,'2019-10-09 14:24:05','0'),(61,1880188801,'login','其他',NULL,NULL,'2019-10-09 14:25:49','0'),(62,1880188801,'login','其他',NULL,NULL,'2019-10-09 14:27:56','0'),(63,1880188801,'login','其他',NULL,NULL,'2019-10-09 14:27:58','0'),(64,1880188801,'login','其他',NULL,NULL,'2019-10-09 14:35:25','0'),(65,1880188801,'login','其他',NULL,NULL,'2019-10-09 14:41:50','0'),(66,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:03:38','0'),(67,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:03:38','0'),(68,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:03:39','0'),(69,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:03:39','0'),(70,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:03:54','0'),(71,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:03:54','0'),(72,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:03:57','0'),(73,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:06:11','0'),(74,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:15:32','0'),(75,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:23:52','0'),(76,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:24:00','0'),(77,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:35:53','0'),(78,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:36:40','0'),(79,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:36:47','0'),(80,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:37:00','0'),(81,1880188801,'login','其他',NULL,NULL,'2019-10-09 15:48:58','0'),(82,1880188801,'login','其他',NULL,NULL,'2019-10-09 18:18:39','0'),(83,1880188801,'login','其他',NULL,NULL,'2019-10-09 18:19:20','0'),(84,1880188801,'login','其他',NULL,NULL,'2019-10-09 18:21:05','0'),(91,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-18 11:35:24','0'),(92,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-21 10:13:07','0'),(93,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-22 11:28:12','0'),(94,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-23 11:14:54','0'),(95,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-24 10:47:51','0'),(96,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-25 11:09:00','0'),(97,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-30 14:39:29','0'),(98,1880188801,'ipAddress','中国','杭州','浙江省','2019-10-31 18:55:17','0'),(99,1880188801,'ipAddress','中国','杭州','浙江省','2019-11-01 12:16:03','0'),(100,1880188801,'ipAddress','中国','杭州','浙江省','2019-11-04 12:19:07','0'),(101,1880188801,'ipAddress','中国','杭州','浙江省','2019-11-05 16:22:23','0'),(102,1880188801,'ipAddress','中国','杭州','浙江省','2019-11-06 09:55:55','0');
/*!40000 ALTER TABLE `iprecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_btu`
--

DROP TABLE IF EXISTS `mpl_btu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_btu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  `url` varchar(251) DEFAULT NULL COMMENT 'url',
  `btu_name` varchar(50) DEFAULT NULL COMMENT '按钮名称',
  `icon` varchar(251) DEFAULT NULL COMMENT '按钮图标',
  `sequence` varchar(50) DEFAULT NULL COMMENT '显示顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '修改人',
  `ramark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='按钮权限';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_btu`
--

LOCK TABLES `mpl_btu` WRITE;
/*!40000 ALTER TABLE `mpl_btu` DISABLE KEYS */;
/*!40000 ALTER TABLE `mpl_btu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_dic`
--

DROP TABLE IF EXISTS `mpl_dic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_dic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `table_name` varchar(100) NOT NULL COMMENT '表名',
  `code` varchar(20) DEFAULT NULL COMMENT '字典名称',
  `value` varchar(20) DEFAULT NULL COMMENT '字典值',
  `text` varchar(100) DEFAULT NULL COMMENT '字典显示文本',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='数据字典';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_dic`
--

LOCK TABLES `mpl_dic` WRITE;
/*!40000 ALTER TABLE `mpl_dic` DISABLE KEYS */;
INSERT INTO `mpl_dic` VALUES (1,'mpl_menu','isShow','1','是','2019-10-11'),(2,'mpl_menu','isShow','2','否','2019-10-11'),(3,'mpl_menu','type','1','目录','2019-10-11'),(4,'mpl_menu','type','2','菜单','2019-10-11'),(5,'mpl_role','status','0','正常','2019-10-11'),(6,'mpl_role','status','1','冻结','2019-10-11'),(7,'mpl_role','status','2','无效','2019-10-11'),(8,'mpl_menu','type','3','按钮','2019-10-22'),(9,'mpl_menu','btnType','1','上面','2019-10-24'),(10,'mpl_menu','btnType','2','右面','2019-10-24'),(11,'mpl_menu','btnType','3','下面','2019-10-24');
/*!40000 ALTER TABLE `mpl_dic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_menu`
--

DROP TABLE IF EXISTS `mpl_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `type` char(1) DEFAULT NULL COMMENT '类型(1模块  2菜单 3按钮)',
  `is_show` char(1) DEFAULT '1' COMMENT '是否显示',
  `parent_id` bigint(11) DEFAULT '0' COMMENT '父菜单id',
  `url` varchar(251) NOT NULL COMMENT '菜单url',
  `icon` varchar(251) NOT NULL DEFAULT 'xigua' COMMENT '图标',
  `sort` varchar(50) DEFAULT NULL COMMENT '显示顺序',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(50) DEFAULT NULL COMMENT '修改人',
  `ramark` varchar(100) DEFAULT NULL COMMENT '备注',
  `btn_type` char(1) NOT NULL DEFAULT '2' COMMENT '1.独立操作  2,每行生成一个需要绑定id 3批量操作',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=76 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='菜单';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_menu`
--

LOCK TABLES `mpl_menu` WRITE;
/*!40000 ALTER TABLE `mpl_menu` DISABLE KEYS */;
INSERT INTO `mpl_menu` VALUES (1,'权限分配','2','1',66,'role','xigua','1',NULL,NULL,NULL,NULL,NULL,'2','权限分配'),(8,'资源管理','2','1',66,'menu','xigua','10',NULL,NULL,NULL,NULL,NULL,'2','资源管理'),(53,'用户管理','2','1',66,'user','form','','2019-10-18 18:52:40','root',NULL,NULL,'','2','用户管理'),(57,'delete','3','1',53,'/user/getUser','table','','2019-10-22 15:16:42','root',NULL,NULL,'','2','删除'),(59,'resetPassword','3','1',53,'/user/resetPassword','password','','2019-10-24 15:14:24','root',NULL,NULL,'','2','重置密码'),(60,'add','3','1',53,'/user/addMenu','xigua','','2019-10-24 17:57:40','root',NULL,NULL,'','1','添加'),(61,'batchdelete','3','1',53,'/user/delete','xigua','','2019-10-24 18:39:29','root',NULL,NULL,'','3','批量删除'),(63,'export','3','1',53,'export','','','2019-10-25 15:14:46','root',NULL,NULL,'','3','批量导出'),(65,'update','3','1',53,'/user/update','','','2019-10-25 16:03:35','root',NULL,NULL,'','2','修改'),(66,'管理模块','1','1',0,'管理模块','','','2019-11-06 10:57:27','root',NULL,NULL,'','2','管理模块'),(67,'多级目录','1','1',0,'多级目录','','','2019-11-06 11:09:05','root',NULL,NULL,'','2','多级目录'),(68,'一级','1','1',67,'一级','','','2019-11-06 11:12:19','root',NULL,NULL,'','2','一级'),(69,'二级','1','1',68,'二级','','','2019-11-06 11:12:33','root',NULL,NULL,'','2','二级'),(70,'三级','1','1',69,'三级','','','2019-11-06 11:12:46','root',NULL,NULL,'','2','三级'),(71,'四级','1','1',70,'四级','','','2019-11-06 11:13:00','root',NULL,NULL,'','2','四级'),(72,'五级','1','1',71,'五级','','','2019-11-06 11:13:15','root',NULL,NULL,'','2','五级'),(73,'资源管理2号','2','1',71,'opo','','','2019-11-06 11:15:46','root',NULL,NULL,'','2','资源管理2号');
/*!40000 ALTER TABLE `mpl_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_role`
--

DROP TABLE IF EXISTS `mpl_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_role` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(80) DEFAULT NULL COMMENT '角色名称',
  `status` varchar(10) DEFAULT NULL COMMENT '是否启用(0有效,1冻结,2,无效)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(65) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(65) DEFAULT NULL COMMENT '修改人',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_role`
--

LOCK TABLES `mpl_role` WRITE;
/*!40000 ALTER TABLE `mpl_role` DISABLE KEYS */;
INSERT INTO `mpl_role` VALUES (1,'SuperAdmin','0',NULL,NULL,NULL,NULL,NULL),(42,'TT ','0','2019-10-11 14:23:53','29',NULL,NULL,NULL),(43,'11','0','2019-10-18 15:04:31','29',NULL,NULL,NULL);
/*!40000 ALTER TABLE `mpl_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_role_menu`
--

DROP TABLE IF EXISTS `mpl_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=125 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色菜单关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_role_menu`
--

LOCK TABLES `mpl_role_menu` WRITE;
/*!40000 ALTER TABLE `mpl_role_menu` DISABLE KEYS */;
INSERT INTO `mpl_role_menu` VALUES (44,1,1),(45,1,3),(46,1,4),(47,1,2),(48,1,5),(49,1,7),(81,1,8),(88,1,38),(89,1,39),(90,1,40),(92,1,43),(93,1,44),(94,1,45),(95,1,46),(96,1,47),(97,1,48),(98,1,49),(99,1,50),(100,1,51),(101,1,52),(102,1,53),(103,1,54),(104,1,55),(105,1,56),(106,1,57),(107,1,58),(108,1,59),(109,1,60),(110,1,61),(111,1,62),(112,1,63),(113,1,64),(114,1,65),(115,1,66),(116,1,67),(117,1,68),(118,1,69),(119,1,70),(120,1,71),(121,1,72),(122,1,73),(123,1,74),(124,1,75);
/*!40000 ALTER TABLE `mpl_role_menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_task`
--

DROP TABLE IF EXISTS `mpl_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_task` (
  `job_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_name` varchar(100) NOT NULL COMMENT '任务名称',
  `cron_expression` varchar(100) NOT NULL COMMENT 'cron表达式',
  `job_group` varchar(100) NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(100) NOT NULL COMMENT '目标字符串',
  `concurrency` char(2) NOT NULL DEFAULT '0' COMMENT '是否允许并发  0不允许 1允许',
  `status` char(1) NOT NULL DEFAULT '0' COMMENT '状态(0正常 1停止)',
  `count` int(11) NOT NULL DEFAULT '0' COMMENT '执行次数',
  `misfire_policy` varchar(100) DEFAULT NULL COMMENT '执行策略',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_task`
--

LOCK TABLES `mpl_task` WRITE;
/*!40000 ALTER TABLE `mpl_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `mpl_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_task_log`
--

DROP TABLE IF EXISTS `mpl_task_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_task_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `job_id` varchar(100) NOT NULL COMMENT 'job_id',
  `job_name` varchar(100) DEFAULT NULL COMMENT '任务名称',
  `cron_expression` varchar(100) DEFAULT NULL COMMENT 'corn表达式',
  `time_consuming` bigint(20) DEFAULT NULL COMMENT '耗时',
  `job_group` varchar(100) DEFAULT NULL COMMENT '任务组名',
  `invoke_target` varchar(100) DEFAULT NULL COMMENT '执行目标',
  `msg` varchar(100) DEFAULT NULL COMMENT '提示',
  `error` varchar(500) DEFAULT NULL COMMENT '异常',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='任务执行日志';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_task_log`
--

LOCK TABLES `mpl_task_log` WRITE;
/*!40000 ALTER TABLE `mpl_task_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `mpl_task_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_user`
--

DROP TABLE IF EXISTS `mpl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_user` (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(64) DEFAULT NULL COMMENT '用户密码',
  `password1` varchar(64) DEFAULT NULL COMMENT '用户上次密码',
  `password2` varchar(64) DEFAULT NULL COMMENT '用户上上次密码',
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `portrait` varchar(130) DEFAULT NULL COMMENT '用户头像',
  `email` varchar(50) DEFAULT NULL COMMENT '用户邮箱',
  `last_time` datetime DEFAULT NULL COMMENT '用户最后一次登录时间',
  `status` int(11) DEFAULT '1' COMMENT '用户是否有效(1有效,2冻结,3无效)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(65) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(65) DEFAULT NULL COMMENT '修改人',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_user`
--

LOCK TABLES `mpl_user` WRITE;
/*!40000 ALTER TABLE `mpl_user` DISABLE KEYS */;
INSERT INTO `mpl_user` VALUES (29,'root','202cb962ac59075b964b07152d234b70','031b4af5197ec30a926f48cf40e11a7dbc470048a21e4003b7a3c07c5dab1baa','031b4af5197ec30a926f48cf40e11a7dbc470048a21e4003b7a3c07c5dab1baa',NULL,NULL,NULL,'2019-05-22 17:37:02',0,'2019-05-20 17:47:22','root','2019-05-21 16:26:54','29',NULL),(33,'oto','42ba3162efc15be97bd1a0260a1d5968','202cb962ac59075b964b07152d234b70',NULL,NULL,NULL,NULL,NULL,0,NULL,NULL,NULL,NULL,NULL),(34,'8888','1c395a8dce135849bd73c6dba3b54809',NULL,NULL,NULL,NULL,NULL,NULL,0,'2019-10-21 18:21:47','root',NULL,NULL,NULL),(35,'88886','eb71b5c7a81460346ecbf2bd351be8b3',NULL,NULL,'','','',NULL,0,'2019-10-21 18:27:47','root',NULL,NULL,NULL);
/*!40000 ALTER TABLE `mpl_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mpl_user_role`
--

DROP TABLE IF EXISTS `mpl_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mpl_user_role` (
  `id` int(50) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(50) DEFAULT NULL COMMENT '用户id',
  `role_id` int(50) DEFAULT NULL COMMENT '角色编号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色和用户关联';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_user_role`
--

LOCK TABLES `mpl_user_role` WRITE;
/*!40000 ALTER TABLE `mpl_user_role` DISABLE KEYS */;
INSERT INTO `mpl_user_role` VALUES (1,29,1);
/*!40000 ALTER TABLE `mpl_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_blob_triggers`
--

DROP TABLE IF EXISTS `qrtz_blob_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_blob_triggers`
--

LOCK TABLES `qrtz_blob_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_blob_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_blob_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_calendars`
--

DROP TABLE IF EXISTS `qrtz_calendars`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_calendars`
--

LOCK TABLES `qrtz_calendars` WRITE;
/*!40000 ALTER TABLE `qrtz_calendars` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_calendars` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_cron_triggers`
--

DROP TABLE IF EXISTS `qrtz_cron_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_cron_triggers`
--

LOCK TABLES `qrtz_cron_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_cron_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_cron_triggers` VALUES ('smart-scheduler','TASK_JOB_SMARTnull','lwqtest','0/1 * * * * ?','Asia/Shanghai');
/*!40000 ALTER TABLE `qrtz_cron_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_fired_triggers`
--

DROP TABLE IF EXISTS `qrtz_fired_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_fired_triggers`
--

LOCK TABLES `qrtz_fired_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_fired_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_fired_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_job_details`
--

DROP TABLE IF EXISTS `qrtz_job_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_job_details`
--

LOCK TABLES `qrtz_job_details` WRITE;
/*!40000 ALTER TABLE `qrtz_job_details` DISABLE KEYS */;
INSERT INTO `qrtz_job_details` VALUES ('smart-scheduler','TASK_JOB_SMARTnull','lwqtest',NULL,'com.smart.quartz.util.NotConcurrentJob','0','0','0','0','��\0sr\0org.quartz.JobDataMap���迩��\0\0xr\0&org.quartz.utils.StringKeyDirtyFlagMap�����](\0Z\0allowsTransientDataxr\0org.quartz.utils.DirtyFlagMap�.�(v\n�\0Z\0dirtyL\0mapt\0Ljava/util/Map;xpsr\0java.util.HashMap���`�\0F\0\nloadFactorI\0	thresholdxp?@\0\0\0\0\0w\0\0\0\0\0\0t\0TASK_JOB_SMARTsr\0\Zcom.smart.db.model.MplTask\0\0\0\0\0\0\0\0	L\0concurrencyt\0Ljava/lang/String;L\0countt\0Ljava/lang/Integer;L\0cronExpressionq\0~\0	L\0invokeTargetq\0~\0	L\0jobGroupq\0~\0	L\0jobIdt\0Ljava/lang/Long;L\0jobNameq\0~\0	L\0\rmisfirePolicyq\0~\0	L\0statusq\0~\0	xpt\00pt\0\r0/1 * * * * ?t\0HelloJob.addTaskt\0lwqtestpt\0testt\00px\0');
/*!40000 ALTER TABLE `qrtz_job_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_locks`
--

DROP TABLE IF EXISTS `qrtz_locks`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_locks`
--

LOCK TABLES `qrtz_locks` WRITE;
/*!40000 ALTER TABLE `qrtz_locks` DISABLE KEYS */;
INSERT INTO `qrtz_locks` VALUES ('smart-scheduler','TRIGGER_ACCESS');
/*!40000 ALTER TABLE `qrtz_locks` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_paused_trigger_grps`
--

DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_paused_trigger_grps`
--

LOCK TABLES `qrtz_paused_trigger_grps` WRITE;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_paused_trigger_grps` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_scheduler_state`
--

DROP TABLE IF EXISTS `qrtz_scheduler_state`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_scheduler_state`
--

LOCK TABLES `qrtz_scheduler_state` WRITE;
/*!40000 ALTER TABLE `qrtz_scheduler_state` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_scheduler_state` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simple_triggers`
--

DROP TABLE IF EXISTS `qrtz_simple_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simple_triggers`
--

LOCK TABLES `qrtz_simple_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simple_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simple_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_simprop_triggers`
--

DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_simprop_triggers`
--

LOCK TABLES `qrtz_simprop_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` DISABLE KEYS */;
/*!40000 ALTER TABLE `qrtz_simprop_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qrtz_triggers`
--

DROP TABLE IF EXISTS `qrtz_triggers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`) USING BTREE,
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qrtz_triggers`
--

LOCK TABLES `qrtz_triggers` WRITE;
/*!40000 ALTER TABLE `qrtz_triggers` DISABLE KEYS */;
INSERT INTO `qrtz_triggers` VALUES ('smart-scheduler','TASK_JOB_SMARTnull','lwqtest','TASK_JOB_SMARTnull','lwqtest',NULL,1568192800000,1568192799000,5,'ERROR','CRON',1568189151000,0,NULL,0,'');
/*!40000 ALTER TABLE `qrtz_triggers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'smart'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-06 15:03:19
