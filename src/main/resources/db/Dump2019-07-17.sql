-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: cm
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `absence_types`
--

DROP TABLE IF EXISTS `absence_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `absence_types` (
  `id_absence` bigint(20) NOT NULL AUTO_INCREMENT,
  `absence_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id_absence`),
  UNIQUE KEY `UK_heu7kodmksenknjyhm1xy4vmx` (`absence_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absence_types`
--

LOCK TABLES `absence_types` WRITE;
/*!40000 ALTER TABLE `absence_types` DISABLE KEYS */;
INSERT INTO `absence_types` VALUES (1,'Holiday'),(2,'sick leave');
/*!40000 ALTER TABLE `absence_types` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `absences`
--

DROP TABLE IF EXISTS `absences`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `absences` (
  `id_absence` bigint(20) NOT NULL AUTO_INCREMENT,
  `absence_from_day` date NOT NULL,
  `absence_to_day` date NOT NULL,
  `approval_id` bigint(20) NOT NULL,
  `absence_type_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id_absence`),
  KEY `FK86gyic9upyardn70dk5tt33y7` (`approval_id`),
  KEY `FKt82cel7osjcg0wlacfeumixjf` (`absence_type_id`),
  KEY `FKgr5k35qap5dmawhcx8l6mnqkk` (`employee_id`),
  CONSTRAINT `FK86gyic9upyardn70dk5tt33y7` FOREIGN KEY (`approval_id`) REFERENCES `absences_approvals` (`id_absence_approval`),
  CONSTRAINT `FKgr5k35qap5dmawhcx8l6mnqkk` FOREIGN KEY (`employee_id`) REFERENCES `employers` (`id_employee`),
  CONSTRAINT `FKt82cel7osjcg0wlacfeumixjf` FOREIGN KEY (`absence_type_id`) REFERENCES `absence_types` (`id_absence`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absences`
--

LOCK TABLES `absences` WRITE;
/*!40000 ALTER TABLE `absences` DISABLE KEYS */;
/*!40000 ALTER TABLE `absences` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `absences_approvals`
--

DROP TABLE IF EXISTS `absences_approvals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `absences_approvals` (
  `id_absence_approval` bigint(20) NOT NULL AUTO_INCREMENT,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id_absence_approval`),
  UNIQUE KEY `UK_dh83ershl3u0nkex7cs8eu9o5` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absences_approvals`
--

LOCK TABLES `absences_approvals` WRITE;
/*!40000 ALTER TABLE `absences_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `absences_approvals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comments`
--

DROP TABLE IF EXISTS `comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comments` (
  `id_comment` bigint(20) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `text` varchar(255) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  `workday_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id_comment`),
  KEY `FKrpd7ixqpv2xdkpa5ov3l8ydh1` (`employee_id`),
  KEY `FKb1f1da22ft46gh43llom0h0x9` (`workday_id`),
  CONSTRAINT `FKb1f1da22ft46gh43llom0h0x9` FOREIGN KEY (`workday_id`) REFERENCES `work_day` (`id_workday`),
  CONSTRAINT `FKrpd7ixqpv2xdkpa5ov3l8ydh1` FOREIGN KEY (`employee_id`) REFERENCES `employers` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comments`
--

LOCK TABLES `comments` WRITE;
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contracts`
--

DROP TABLE IF EXISTS `contracts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `contracts` (
  `id_contract` bigint(20) NOT NULL AUTO_INCREMENT,
  `contract_type` varchar(255) NOT NULL,
  PRIMARY KEY (`id_contract`),
  UNIQUE KEY `UK_r51hvxgoom1wdqcmw81hyhlf6` (`contract_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contracts`
--

LOCK TABLES `contracts` WRITE;
/*!40000 ALTER TABLE `contracts` DISABLE KEYS */;
INSERT INTO `contracts` VALUES (2,'UOP'),(1,'UZ');
/*!40000 ALTER TABLE `contracts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee_role`
--

DROP TABLE IF EXISTS `employee_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employee_role` (
  `employee_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`employee_id`,`role_id`),
  KEY `FKo0j1qho7koleb1op32qsxrmog` (`role_id`),
  CONSTRAINT `FKd6b118sckcm31ptgoroawcse8` FOREIGN KEY (`employee_id`) REFERENCES `employers` (`id_employee`),
  CONSTRAINT `FKo0j1qho7koleb1op32qsxrmog` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id_role`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee_role`
--

LOCK TABLES `employee_role` WRITE;
/*!40000 ALTER TABLE `employee_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `employee_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employers`
--

DROP TABLE IF EXISTS `employers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employers` (
  `id_employee` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `employee_details_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id_employee`),
  UNIQUE KEY `UK_dg2ojnh1u9l6jmjh41p18nawf` (`username`),
  KEY `FKkuitn4s9w8utt4c5d8xtflyqx` (`employee_details_id`),
  CONSTRAINT `FKkuitn4s9w8utt4c5d8xtflyqx` FOREIGN KEY (`employee_details_id`) REFERENCES `employers_details` (`id_employee_details`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employers`
--

LOCK TABLES `employers` WRITE;
/*!40000 ALTER TABLE `employers` DISABLE KEYS */;
/*!40000 ALTER TABLE `employers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employers_details`
--

DROP TABLE IF EXISTS `employers_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `employers_details` (
  `id_employee_details` bigint(20) NOT NULL AUTO_INCREMENT,
  `available_vacation_days` int(11) NOT NULL,
  `date_terminate_contract` date DEFAULT NULL,
  `hire_day` date NOT NULL,
  `salary` double NOT NULL,
  `total_vacation_days` int(11) NOT NULL,
  `contract_id` bigint(20) NOT NULL,
  `title_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id_employee_details`),
  KEY `FK5si6nd4k1ep9x0ptlyccdhm5v` (`contract_id`),
  KEY `FKl430cgqbgndoyxcqdoemhmus9` (`title_id`),
  CONSTRAINT `FK5si6nd4k1ep9x0ptlyccdhm5v` FOREIGN KEY (`contract_id`) REFERENCES `contracts` (`id_contract`),
  CONSTRAINT `FKl430cgqbgndoyxcqdoemhmus9` FOREIGN KEY (`title_id`) REFERENCES `job_title` (`id_title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employers_details`
--

LOCK TABLES `employers_details` WRITE;
/*!40000 ALTER TABLE `employers_details` DISABLE KEYS */;
/*!40000 ALTER TABLE `employers_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_title`
--

DROP TABLE IF EXISTS `job_title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `job_title` (
  `id_title` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id_title`),
  UNIQUE KEY `UK_84aimoe8bo1b9riwwlo64vfi9` (`title`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_title`
--

LOCK TABLES `job_title` WRITE;
/*!40000 ALTER TABLE `job_title` DISABLE KEYS */;
INSERT INTO `job_title` VALUES (3,'director'),(1,'employee'),(2,'manager');
/*!40000 ALTER TABLE `job_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payrolls`
--

DROP TABLE IF EXISTS `payrolls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `payrolls` (
  `id_payroll` bigint(20) NOT NULL AUTO_INCREMENT,
  `salary` double NOT NULL,
  `worked_days` int(11) NOT NULL,
  `worked_hours` double NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id_payroll`),
  KEY `FKi9ljg3pfdhru354sax14em7m9` (`employee_id`),
  CONSTRAINT `FKi9ljg3pfdhru354sax14em7m9` FOREIGN KEY (`employee_id`) REFERENCES `employers` (`id_employee`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payrolls`
--

LOCK TABLES `payrolls` WRITE;
/*!40000 ALTER TABLE `payrolls` DISABLE KEYS */;
/*!40000 ALTER TABLE `payrolls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `roles` (
  `id_role` bigint(20) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id_role`),
  UNIQUE KEY `UK_g50w4r0ru3g9uf6i6fr4kpro8` (`role`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (3,'ADMIN'),(2,'MANAGER'),(1,'USER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `work_day`
--

DROP TABLE IF EXISTS `work_day`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `work_day` (
  `id_workday` bigint(20) NOT NULL AUTO_INCREMENT,
  `booked_artist` varchar(255) DEFAULT NULL,
  `workday_date` date NOT NULL,
  `working_time` int(11) NOT NULL,
  `workday_importance_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_workday`),
  UNIQUE KEY `UK_o3v7oqr6w3xdxfwy1j2vy7tt9` (`workday_date`),
  KEY `FKfoeuf69uk259lcm18c92d2bue` (`workday_importance_id`),
  CONSTRAINT `FKfoeuf69uk259lcm18c92d2bue` FOREIGN KEY (`workday_importance_id`) REFERENCES `workday_importance` (`id_workday_importance`)
) ENGINE=InnoDB AUTO_INCREMENT=472 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `work_day`
--

LOCK TABLES `work_day` WRITE;
/*!40000 ALTER TABLE `work_day` DISABLE KEYS */;
INSERT INTO `work_day` VALUES (283,NULL,'2019-07-16',8,2),(284,NULL,'2019-07-17',8,2),(285,NULL,'2019-07-18',8,2),(286,NULL,'2019-07-19',8,2),(287,NULL,'2019-07-20',8,2),(288,NULL,'2019-07-21',8,2),(289,NULL,'2019-07-22',8,2),(290,NULL,'2019-07-23',8,2),(291,NULL,'2019-07-24',8,2),(292,NULL,'2019-07-25',8,2),(293,NULL,'2019-07-26',8,2),(294,NULL,'2019-07-27',8,2),(295,NULL,'2019-07-28',8,2),(296,NULL,'2019-07-29',8,2),(297,NULL,'2019-07-30',8,2),(298,NULL,'2019-07-31',8,2),(299,NULL,'2019-08-01',8,2),(300,NULL,'2019-08-02',8,2),(301,NULL,'2019-08-03',8,2),(302,NULL,'2019-08-04',8,2),(303,NULL,'2019-08-05',8,2),(304,NULL,'2019-08-06',8,2),(305,NULL,'2019-08-07',8,2),(306,NULL,'2019-08-08',8,2),(307,NULL,'2019-08-09',8,2),(308,NULL,'2019-08-10',8,2),(309,NULL,'2019-08-11',8,2),(310,NULL,'2019-08-12',8,2),(311,NULL,'2019-08-13',8,2),(312,NULL,'2019-08-14',8,2),(313,NULL,'2019-08-15',8,2),(314,NULL,'2019-08-16',8,2),(315,NULL,'2019-08-17',8,2),(316,NULL,'2019-08-18',8,2),(317,NULL,'2019-08-19',8,2),(318,NULL,'2019-08-20',8,2),(319,NULL,'2019-08-21',8,2),(320,NULL,'2019-08-22',8,2),(321,NULL,'2019-08-23',8,2),(322,NULL,'2019-08-24',8,2),(323,NULL,'2019-08-25',8,2),(324,NULL,'2019-08-26',8,2),(325,NULL,'2019-08-27',8,2),(326,NULL,'2019-08-28',8,2),(327,NULL,'2019-08-29',8,2),(328,NULL,'2019-08-30',8,2),(329,NULL,'2019-08-31',8,2),(330,NULL,'2019-09-01',8,2),(331,NULL,'2019-09-02',8,2),(332,NULL,'2019-09-03',8,2),(333,NULL,'2019-09-04',8,2),(334,NULL,'2019-09-05',8,2),(335,NULL,'2019-09-06',8,2),(336,NULL,'2019-09-07',8,2),(337,NULL,'2019-09-08',8,2),(338,NULL,'2019-09-09',8,2),(339,NULL,'2019-09-10',8,2),(340,NULL,'2019-09-11',8,2),(341,NULL,'2019-09-12',8,2),(342,NULL,'2019-09-13',8,2),(343,NULL,'2019-09-14',8,2),(344,NULL,'2019-09-15',8,2),(345,NULL,'2019-09-16',8,2),(346,NULL,'2019-09-17',8,2),(347,NULL,'2019-09-18',8,2),(348,NULL,'2019-09-19',8,2),(349,NULL,'2019-09-20',8,2),(350,NULL,'2019-09-21',8,2),(351,NULL,'2019-09-22',8,2),(352,NULL,'2019-09-23',8,2),(353,NULL,'2019-09-24',8,2),(354,NULL,'2019-09-25',8,2),(355,NULL,'2019-09-26',8,2),(356,NULL,'2019-09-27',8,2),(357,NULL,'2019-09-28',8,2),(358,NULL,'2019-09-29',8,2),(359,NULL,'2019-09-30',8,2),(360,NULL,'2019-10-01',8,2),(361,NULL,'2019-10-02',8,2),(362,NULL,'2019-10-03',8,2),(363,NULL,'2019-10-04',8,2),(364,NULL,'2019-10-05',8,2),(365,NULL,'2019-10-06',8,2),(366,NULL,'2019-10-07',8,2),(367,NULL,'2019-10-08',8,2),(368,NULL,'2019-10-09',8,2),(369,NULL,'2019-10-10',8,2),(370,NULL,'2019-10-11',8,2),(371,NULL,'2019-10-12',8,2),(372,NULL,'2019-10-13',8,2),(373,NULL,'2019-10-14',8,2),(374,NULL,'2019-10-15',8,2),(375,NULL,'2019-10-16',8,2),(380,NULL,'2019-10-17',8,2),(381,NULL,'2019-10-18',8,2),(382,NULL,'2019-10-19',8,2),(383,NULL,'2019-10-20',8,2),(384,NULL,'2019-10-21',8,2),(385,NULL,'2019-10-22',8,2),(386,NULL,'2019-10-23',8,2),(387,NULL,'2019-10-24',8,2),(388,NULL,'2019-10-25',8,2),(389,NULL,'2019-10-26',8,2),(390,NULL,'2019-10-27',8,2),(391,NULL,'2019-10-28',8,2),(392,NULL,'2019-10-29',8,2),(393,NULL,'2019-10-30',8,2),(394,NULL,'2019-10-31',8,2),(395,NULL,'2019-11-01',8,2),(396,NULL,'2019-11-02',8,2),(397,NULL,'2019-11-03',8,2),(398,NULL,'2019-11-04',8,2),(399,NULL,'2019-11-05',8,2),(400,NULL,'2019-11-06',8,2),(401,NULL,'2019-11-07',8,2),(402,NULL,'2019-11-08',8,2),(403,NULL,'2019-11-09',8,2),(404,NULL,'2019-11-10',8,2),(405,NULL,'2019-11-11',8,2),(406,NULL,'2019-11-12',8,2),(407,NULL,'2019-11-13',8,2),(408,NULL,'2019-11-14',8,2),(409,NULL,'2019-11-15',8,2),(410,NULL,'2019-11-16',8,2),(411,NULL,'2019-11-17',8,2),(412,NULL,'2019-11-18',8,2),(413,NULL,'2019-11-19',8,2),(414,NULL,'2019-11-20',8,2),(415,NULL,'2019-11-21',8,2),(416,NULL,'2019-11-22',8,2),(417,NULL,'2019-11-23',8,2),(418,NULL,'2019-11-24',8,2),(419,NULL,'2019-11-25',8,2),(420,NULL,'2019-11-26',8,2),(421,NULL,'2019-11-27',8,2),(422,NULL,'2019-11-28',8,2),(423,NULL,'2019-11-29',8,2),(424,NULL,'2019-11-30',8,2),(425,NULL,'2019-12-01',8,2),(426,NULL,'2019-12-02',8,2),(427,NULL,'2019-12-03',8,2),(428,NULL,'2019-12-04',8,2),(429,NULL,'2019-12-05',8,2),(430,NULL,'2019-12-06',8,2),(431,NULL,'2019-12-07',8,2),(432,NULL,'2019-12-08',8,2),(433,NULL,'2019-12-09',8,2),(434,NULL,'2019-12-10',8,2),(435,NULL,'2019-12-11',8,2),(436,NULL,'2019-12-12',8,2),(437,NULL,'2019-12-13',8,2),(438,NULL,'2019-12-14',8,2),(439,NULL,'2019-12-15',8,2),(440,NULL,'2019-12-16',8,2),(441,NULL,'2019-12-17',8,2),(442,NULL,'2019-12-18',8,2),(443,NULL,'2019-12-19',8,2),(444,NULL,'2019-12-20',8,2),(445,NULL,'2019-12-21',8,2),(446,NULL,'2019-12-22',8,2),(447,NULL,'2019-12-23',8,2),(448,NULL,'2019-12-24',8,2),(449,NULL,'2019-12-25',8,2),(450,NULL,'2019-12-26',8,2),(451,NULL,'2019-12-27',8,2),(452,NULL,'2019-12-28',8,2),(453,NULL,'2019-12-29',8,2),(454,NULL,'2019-12-30',8,2),(455,NULL,'2019-12-31',8,2),(456,NULL,'2020-01-01',8,2),(457,NULL,'2020-01-02',8,2),(458,NULL,'2020-01-03',8,2),(459,NULL,'2020-01-04',8,2),(460,NULL,'2020-01-05',8,2),(461,NULL,'2020-01-06',8,2),(462,NULL,'2020-01-07',8,2),(463,NULL,'2020-01-08',8,2),(464,NULL,'2020-01-09',8,2),(465,NULL,'2020-01-10',8,2),(466,NULL,'2020-01-11',8,2),(467,NULL,'2020-01-12',8,2),(468,NULL,'2020-01-13',8,2),(469,NULL,'2020-01-14',8,2),(470,NULL,'2020-01-15',8,2),(471,NULL,'2020-01-16',8,2);
/*!40000 ALTER TABLE `work_day` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workday_employee`
--

DROP TABLE IF EXISTS `workday_employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `workday_employee` (
  `workday_id` bigint(20) NOT NULL,
  `employee_id` bigint(20) NOT NULL,
  KEY `FKf9mogap9ok39ephyr9fwwiada` (`employee_id`),
  KEY `FKj4vrqq7u8p3ad6n1qqoolavk9` (`workday_id`),
  CONSTRAINT `FKf9mogap9ok39ephyr9fwwiada` FOREIGN KEY (`employee_id`) REFERENCES `employers` (`id_employee`),
  CONSTRAINT `FKj4vrqq7u8p3ad6n1qqoolavk9` FOREIGN KEY (`workday_id`) REFERENCES `work_day` (`id_workday`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workday_employee`
--

LOCK TABLES `workday_employee` WRITE;
/*!40000 ALTER TABLE `workday_employee` DISABLE KEYS */;
/*!40000 ALTER TABLE `workday_employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `workday_importance`
--

DROP TABLE IF EXISTS `workday_importance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `workday_importance` (
  `id_workday_importance` bigint(20) NOT NULL AUTO_INCREMENT,
  `importance_level` int(11) NOT NULL,
  PRIMARY KEY (`id_workday_importance`),
  UNIQUE KEY `UK_awbblcyii8xsjscsspqq11qd5` (`importance_level`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `workday_importance`
--

LOCK TABLES `workday_importance` WRITE;
/*!40000 ALTER TABLE `workday_importance` DISABLE KEYS */;
INSERT INTO `workday_importance` VALUES (1,1),(2,2),(3,3),(4,4);
/*!40000 ALTER TABLE `workday_importance` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-17 15:57:10
