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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='定时任务';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mpl_task`
--

LOCK TABLES `mpl_task` WRITE;
/*!40000 ALTER TABLE `mpl_task` DISABLE KEYS */;
/*!40000 ALTER TABLE `mpl_task` ENABLE KEYS */;
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
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
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
INSERT INTO `mpl_menu` VALUES (1,'权限分配','2','1',66,'role','xigua','1',NULL,NULL,NULL,NULL,NULL,'2','权限分配'),(8,'资源管理','2','1',66,'menu','xigua','10',NULL,NULL,NULL,NULL,NULL,'2','资源管理'),(53,'用户管理','2','1',66,'user','form','','2019-10-18 18:52:40','root',NULL,NULL,'','2','用户管理'),(57,'delete','3','1',53,'/user/getUser','table','','2019-10-22 15:16:42','root',NULL,NULL,'','2','删除'),(59,'resetPassword','3','1',53,'/user/resetPassword','password','','2019-10-24 15:14:24','root',NULL,NULL,'','2','重置密码'),(60,'add','3','1',53,'/user/addMenu','xigua','','2019-10-24 17:57:40','root',NULL,NULL,'','1','添加'),(61,'batchdelete','3','1',53,'/user/delete','xigua','','2019-10-24 18:39:29','root',NULL,NULL,'','3','批量删除'),(63,'export','3','1',53,'export','','','2019-10-25 15:14:46','root',NULL,NULL,'','3','批量导出'),(65,'update','3','1',53,'/user/update','','','2019-10-25 16:03:35','root',NULL,NULL,'','2','修改'),(66,'管理模块','1','1',0,'管理模块','','','2019-11-06 10:57:27','root',NULL,NULL,'','2','管理模块'),(67,'多级目录','1','1',0,'多级目录','','','2019-11-06 11:09:05','root',NULL,NULL,'','2','多级目录'),(68,'一级','1','1',67,'一级','','','2019-11-06 11:12:19','root',NULL,NULL,'','2','一级'),(69,'二级','1','1',68,'二级','','','2019-11-06 11:12:33','root',NULL,NULL,'','2','二级'),(70,'三级','1','1',69,'三级','','','2019-11-06 11:12:46','root',NULL,NULL,'','2','三级'),(71,'四级','1','1',70,'四级','','','2019-11-06 11:13:00','root',NULL,NULL,'','2','四级'),(72,'五级','1','1',71,'五级','','','2019-11-06 11:13:15','root',NULL,NULL,'','2','五级'),(73,'资源管理2号','2','1',71,'opo2','','','2019-11-06 11:15:46','root',NULL,NULL,'','2','资源管理2号');
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
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
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
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-11-07 18:04:31
