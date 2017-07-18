CREATE DATABASE  IF NOT EXISTS `inventory` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `inventory`;
-- MySQL dump 10.13  Distrib 5.6.19, for Win32 (x86)
--
-- Host: localhost    Database: inventory
-- ------------------------------------------------------
-- Server version	5.6.22-enterprise-commercial-advanced-log

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(20) COLLATE utf8_bin NOT NULL,
  `PASSWORD` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ACCOUNT_ID`),
  UNIQUE KEY `username_UNIQUE` (`USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;

--
-- Table structure for table `branch`
--

DROP TABLE IF EXISTS `branch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `branch` (
  `BRANCH_ID` int(11) NOT NULL,
  `BRANCH` varchar(25) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`BRANCH_ID`),
  UNIQUE KEY `Branch_UNIQUE` (`BRANCH`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `branch`
--

/*!40000 ALTER TABLE `branch` DISABLE KEYS */;
INSERT INTO `branch` VALUES (6,'Bacoor'),(9,'Cogeo'),(1,'Commonwealth'),(7,'Dau'),(2,'España'),(10,'Marikina'),(5,'Montalban'),(4,'Tanay'),(3,'Taytay'),(8,'Urdaneta');
/*!40000 ALTER TABLE `branch` ENABLE KEYS */;

--
-- Table structure for table `computers`
--

DROP TABLE IF EXISTS `computers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `computers` (
  `PC_ID` int(11) NOT NULL,
  `IP_ADDRESS` varchar(16) COLLATE utf8_bin NOT NULL,
  `PC_NAME` varchar(25) COLLATE utf8_bin NOT NULL,
  `STATUS` char(15) COLLATE utf8_bin NOT NULL,
  `LOCATION` varchar(30) COLLATE utf8_bin NOT NULL,
  `BRANCH` varchar(25) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`PC_ID`),
  KEY `FK_COMP-LOC_LOC_LOC_idx` (`LOCATION`),
  KEY `FK_COMP-BRCH_BRCH_BRCH_idx` (`BRANCH`),
  CONSTRAINT `FK_COMP-BRCH_BRCH_BRCH` FOREIGN KEY (`BRANCH`) REFERENCES `branch` (`BRANCH`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_COMP-LOC_LOC_LOC` FOREIGN KEY (`LOCATION`) REFERENCES `location` (`Locations`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `computers`
--

/*!40000 ALTER TABLE `computers` DISABLE KEYS */;
/*!40000 ALTER TABLE `computers` ENABLE KEYS */;

--
-- Table structure for table `equipments`
--

DROP TABLE IF EXISTS `equipments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipments` (
  `ITEM_ID` varchar(20) COLLATE utf8_bin NOT NULL,
  `ITEM_NAME` varchar(45) COLLATE utf8_bin NOT NULL,
  `IS_AVAILABLE` varchar(10) COLLATE utf8_bin NOT NULL,
  `LAST_BORROWED_BY` char(5) COLLATE utf8_bin DEFAULT NULL,
  `LOCATION` varchar(30) COLLATE utf8_bin NOT NULL,
  `BRANCH` varchar(25) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`ITEM_ID`),
  KEY `FK_LBB_IC_idx` (`LAST_BORROWED_BY`),
  KEY `FK_BRCH_BRCH_idx` (`BRANCH`),
  KEY `FK_LOC_LOC_idx` (`LOCATION`),
  CONSTRAINT `FK_BRCH_BRCH` FOREIGN KEY (`BRANCH`) REFERENCES `branch` (`BRANCH`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_LBB_IC` FOREIGN KEY (`LAST_BORROWED_BY`) REFERENCES `facultystaff` (`I_CODE`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_LOC_LOC` FOREIGN KEY (`LOCATION`) REFERENCES `location` (`Locations`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `equipments`
--

/*!40000 ALTER TABLE `equipments` DISABLE KEYS */;
/*!40000 ALTER TABLE `equipments` ENABLE KEYS */;

--
-- Table structure for table `facultystaff`
--

DROP TABLE IF EXISTS `facultystaff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `facultystaff` (
  `I_CODE` char(5) COLLATE utf8_bin NOT NULL,
  `LAST_NAME` char(20) COLLATE utf8_bin NOT NULL,
  `FIRST_NAME` char(20) COLLATE utf8_bin NOT NULL,
  `M_INIT` char(4) COLLATE utf8_bin DEFAULT NULL,
  `ADDRESS` varchar(60) COLLATE utf8_bin NOT NULL,
  `CONTACT_NUM` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `E-MAIL` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`I_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facultystaff`
--

/*!40000 ALTER TABLE `facultystaff` DISABLE KEYS */;
/*!40000 ALTER TABLE `facultystaff` ENABLE KEYS */;

--
-- Table structure for table `location`
--

DROP TABLE IF EXISTS `location`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `location` (
  `Locations` varchar(30) COLLATE utf8_bin NOT NULL,
  UNIQUE KEY `Locations_UNIQUE` (`Locations`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `location`
--

/*!40000 ALTER TABLE `location` DISABLE KEYS */;
INSERT INTO `location` VALUES ('Computer Laboratory'),('Library'),('Registrar'),('Room 201'),('Room 202'),('Room 203'),('Room 204'),('Room 205'),('Technical Laboratory');
/*!40000 ALTER TABLE `location` ENABLE KEYS */;

--
-- Dumping routines for database 'inventory'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-08 23:03:28
