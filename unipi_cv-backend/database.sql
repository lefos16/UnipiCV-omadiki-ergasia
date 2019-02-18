-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: localhost    Database: unipi_cv
-- ------------------------------------------------------
-- Server version	8.0.13

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
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (31),(31),(31);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `language` (
  `id_languages` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_languages`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `language`
--

LOCK TABLES `language` WRITE;
/*!40000 ALTER TABLE `language` DISABLE KEYS */;
INSERT INTO `language` VALUES (1,'java'),(2,'Assembly language'),(3,'C'),(4,'C++'),(5,'C#'),(6,'Fortran '),(7,'JSON'),(8,'JavaScript'),(9,'Kotlin'),(10,'MATLAB'),(11,'Python'),(12,'R'),(13,'SQL');
/*!40000 ALTER TABLE `language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platform`
--

DROP TABLE IF EXISTS `platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `platform` (
  `id_platforms` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_platforms`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platform`
--

LOCK TABLES `platform` WRITE;
/*!40000 ALTER TABLE `platform` DISABLE KEYS */;
INSERT INTO `platform` VALUES (1,'Eclipse'),(2,'IntelliJ IDEA'),(3,'NetBeans'),(4,'Microsoft Visual Studio'),(5,'PyCharm'),(6,'Code::Blocks'),(7,'Aptana Studio 3'),(8,'Komodo'),(9,'RubyMine'),(10,'Xcode'),(11,'Android Studio'),(12,'Unity3d');
/*!40000 ALTER TABLE `platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platform_keyword`
--

DROP TABLE IF EXISTS `platform_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `platform_keyword` (
  `id_platform_keyword` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_platform_keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platform_keyword`
--

LOCK TABLES `platform_keyword` WRITE;
/*!40000 ALTER TABLE `platform_keyword` DISABLE KEYS */;
INSERT INTO `platform_keyword` VALUES (2,'Business Analytics'),(3,'Cloud Services'),(4,'Data Analysis'),(5,'Data Mining'),(6,'Data Science'),(7,'Design'),(9,'Developer'),(10,'Hardware'),(11,'IT Security'),(12,'Security'),(13,'Software Development'),(14,'Web Development');
/*!40000 ALTER TABLE `platform_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `id_student` int(11) NOT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `cv` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `thesis` varchar(255) DEFAULT NULL,
  `id_user` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_student`),
  UNIQUE KEY `UK_gk5vu6ga9cu8ho09qs12cq91l` (`id_user`),
  CONSTRAINT `FKb4lfwbonj876jqkfv3syhp06o` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (9,'Avatars\\10\\prof1.jpg','CV\\10\\cv-example-1-en-gb.pdf','kostas@mail.gr','7.5','konstantinos ','211111111','Antoniou','pefectapp',10),(11,'Avatars\\12\\prof2.jpg','CV\\12\\cv-example-1-en-gb.pdf','Giannis@email.com','6','Giannis','3030303030','Simos','Android',12),(13,'','CV\\14\\cv-example-1-en-gb.pdf','fakas@mail.com','6.6','dimitris','69696969','fakas','Website',14),(25,'Avatars\\26\\prof4.jpg','CV\\26\\cv-example-1-en-gb.pdf','roula@email.com','8.5','roula','6969696969','stefa','data mining',26);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_language`
--

DROP TABLE IF EXISTS `student_has_language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_has_language` (
  `id_student` int(11) NOT NULL,
  `id_languages` int(11) NOT NULL,
  PRIMARY KEY (`id_student`,`id_languages`),
  KEY `FKdof8pxr7yhx53ghu0l7j47tov` (`id_languages`),
  CONSTRAINT `FKdof8pxr7yhx53ghu0l7j47tov` FOREIGN KEY (`id_languages`) REFERENCES `language` (`id_languages`),
  CONSTRAINT `FKi4nqe6i0gcttr9b3h8jlawebi` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_language`
--

LOCK TABLES `student_has_language` WRITE;
/*!40000 ALTER TABLE `student_has_language` DISABLE KEYS */;
INSERT INTO `student_has_language` VALUES (11,1),(13,1),(25,1),(25,2),(11,3),(13,4),(13,5),(9,9),(9,10),(11,10),(9,12);
/*!40000 ALTER TABLE `student_has_language` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_platform`
--

DROP TABLE IF EXISTS `student_has_platform`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_has_platform` (
  `id_student` int(11) NOT NULL,
  `id_platforms` int(11) NOT NULL,
  PRIMARY KEY (`id_student`,`id_platforms`),
  KEY `FK7npacvx0mpo1q9lh9r7xk58lo` (`id_platforms`),
  CONSTRAINT `FK7npacvx0mpo1q9lh9r7xk58lo` FOREIGN KEY (`id_platforms`) REFERENCES `platform` (`id_platforms`),
  CONSTRAINT `FKgq0rj9tggko83aft8mppkxwdw` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_platform`
--

LOCK TABLES `student_has_platform` WRITE;
/*!40000 ALTER TABLE `student_has_platform` DISABLE KEYS */;
INSERT INTO `student_has_platform` VALUES (9,1),(11,1),(13,1),(25,1),(9,2),(11,2),(13,4),(13,11);
/*!40000 ALTER TABLE `student_has_platform` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student_has_platform_keyword`
--

DROP TABLE IF EXISTS `student_has_platform_keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student_has_platform_keyword` (
  `id_student` int(11) NOT NULL,
  `id_platform_keyword` int(11) NOT NULL,
  PRIMARY KEY (`id_student`,`id_platform_keyword`),
  KEY `FKq1yphsj4udd7gmi7nfpl5q2e3` (`id_platform_keyword`),
  CONSTRAINT `FKotr89mrnm6ht0xbxaw171qh8l` FOREIGN KEY (`id_student`) REFERENCES `student` (`id_student`),
  CONSTRAINT `FKq1yphsj4udd7gmi7nfpl5q2e3` FOREIGN KEY (`id_platform_keyword`) REFERENCES `platform_keyword` (`id_platform_keyword`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student_has_platform_keyword`
--

LOCK TABLES `student_has_platform_keyword` WRITE;
/*!40000 ALTER TABLE `student_has_platform_keyword` DISABLE KEYS */;
INSERT INTO `student_has_platform_keyword` VALUES (11,2),(25,2),(9,3),(13,3),(25,3),(9,4),(11,4),(13,5),(9,11),(13,11),(11,13),(13,14);
/*!40000 ALTER TABLE `student_has_platform_keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` int(11) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_user`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (0,'1994',1,'lefos'),(10,'1994',2,'kostas'),(12,'1994',2,'gianis'),(14,'1994',2,'mitsos'),(26,'1994',2,'roula');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-15 12:12:51
