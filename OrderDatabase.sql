-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: sql.freedb.tech    Database: freedb_OrderDatabase
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Meals`
--

DROP TABLE IF EXISTS `Meals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Meals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `type` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Meals`
--

LOCK TABLES `Meals` WRITE;
/*!40000 ALTER TABLE `Meals` DISABLE KEYS */;
INSERT INTO `Meals` VALUES (3,'Cevapi (5 kom)',3,100,'Main dish'),(4,'Margarita',3,250,'Main dish'),(6,'Capricciosa',4,250,'Main dish'),(7,'Margarita',6,500,'Main dish'),(8,'Capricciosa',8,500,'Main dish'),(9,'Funghi',4,250,'Main dish'),(10,'Funghi',8,500,'Main dish'),(11,'Pizza 4 Vrste Sira',5,250,'Main dish'),(12,'Pizza 4 Vrste SIra',10,500,'Main dish'),(13,'Cevapi (10 kom)',6,250,'Main dish'),(14,'Pasta Karbonara',8,300,'Main dish'),(15,'Pasta Bolognese',7,300,'Main dish'),(16,'Pomfrit',1,50,'Appetizer'),(17,'Pomfrit',2,200,'Appetizer'),(18,'Cockta',2,250,'Drink'),(19,'Coca-Cola',2,250,'Drink'),(20,'Tesanjski kiseljak',1,330,'Drink'),(21,'Cappy Narandza',2.5,330,'Drink'),(22,'Cappy Jabuka',2.5,330,'Drink'),(23,'Chocolate cake',3,100,'Desert'),(24,'Muss cake',3,100,'Desert'),(25,'Ferrero cake',4,100,'Desert'),(26,'Palacinci nutella',4.5,200,'Desert');
/*!40000 ALTER TABLE `Meals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders`
--

DROP TABLE IF EXISTS `Orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idUser` int NOT NULL,
  `dateOfOrder` datetime NOT NULL,
  `price` double NOT NULL,
  `confirmationEmail` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idOrder_UNIQUE` (`id`),
  KEY `idUser_idx` (`idUser`),
  CONSTRAINT `idUser` FOREIGN KEY (`idUser`) REFERENCES `Users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders`
--

LOCK TABLES `Orders` WRITE;
/*!40000 ALTER TABLE `Orders` DISABLE KEYS */;
INSERT INTO `Orders` VALUES (29,18,'2023-02-19 20:41:44',6,NULL,NULL),(30,18,'2023-02-19 21:26:47',18,NULL,NULL);
/*!40000 ALTER TABLE `Orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Orders_Meals`
--

DROP TABLE IF EXISTS `Orders_Meals`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Orders_Meals` (
  `id` int NOT NULL AUTO_INCREMENT,
  `idOrder` int NOT NULL,
  `idMeal` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idOrder_Meal_UNIQUE` (`id`),
  KEY `idMeal_idx` (`idMeal`),
  KEY `idOrder_idx` (`idOrder`),
  CONSTRAINT `idMeal` FOREIGN KEY (`idMeal`) REFERENCES `Meals` (`id`),
  CONSTRAINT `idOrder` FOREIGN KEY (`idOrder`) REFERENCES `Orders` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Orders_Meals`
--

LOCK TABLES `Orders_Meals` WRITE;
/*!40000 ALTER TABLE `Orders_Meals` DISABLE KEYS */;
INSERT INTO `Orders_Meals` VALUES (61,29,7),(62,30,12),(63,30,14);
/*!40000 ALTER TABLE `Orders_Meals` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `Users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `password` varchar(45) NOT NULL,
  `address` varchar(45) DEFAULT NULL,
  `telephoneNumber` varchar(45) NOT NULL,
  `username` varchar(50) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idUser_UNIQUE` (`id`),
  UNIQUE KEY `telephoneNumber_UNIQUE` (`telephoneNumber`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Users`
--

LOCK TABLES `Users` WRITE;
/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
INSERT INTO `Users` VALUES (13,'admin','admin',NULL,'admin',NULL,'007','admin'),(17,'Ahmed','Ljubuncic','ahmed@gmail.com','a','Sarajevo','062456789','aljubuncic'),(18,'a','a',NULL,'a',NULL,'0','a');
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-20 15:29:57
