-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: planta1
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `empleados1`
--

DROP TABLE IF EXISTS `empleados1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleados1` (
  `id` int NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `fecha_ingreso` date DEFAULT NULL,
  `fecha_salida` date DEFAULT NULL,
  `salario_bruto` double DEFAULT NULL,
  `tipo` int DEFAULT NULL,
  `departamento` int DEFAULT NULL,
  `id_supervisor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo` (`tipo`),
  KEY `departamento` (`departamento`),
  KEY `fk_supervisor` (`id_supervisor`),
  CONSTRAINT `empleados1_ibfk_1` FOREIGN KEY (`tipo`) REFERENCES `tipo_empleado1` (`id`),
  CONSTRAINT `empleados1_ibfk_2` FOREIGN KEY (`departamento`) REFERENCES `departamentos1` (`id`),
  CONSTRAINT `fk_supervisor` FOREIGN KEY (`id_supervisor`) REFERENCES `empleados1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleados1`
--

LOCK TABLES `empleados1` WRITE;
/*!40000 ALTER TABLE `empleados1` DISABLE KEYS */;
INSERT INTO `empleados1` VALUES (1,'Luisa E. Fernández Durán','2019-02-15',NULL,NULL,1,1,10),(2,'Daniel Antonio Liranzo','2022-07-03',NULL,NULL,1,1,10),(3,'Aryam De Soto Peguero','2019-11-28',NULL,NULL,1,1,10),(4,'Rosa Mayra Tejada Acosta','2021-06-10',NULL,NULL,1,1,10),(5,'Elias Peguero Acosta','2022-03-22',NULL,NULL,1,1,10),(6,'Juana Garcia Martiño','2018-08-05',NULL,NULL,1,1,10),(7,'Eusebio Lluli Figueroa','2019-01-14',NULL,NULL,1,1,10),(8,'Beatico De La Rosa De La Rosa','2021-05-31',NULL,NULL,1,1,10),(9,'Luis Alejandro Ramírez Féliz','2018-09-19',NULL,NULL,1,1,10),(10,'Rachelle N. Guiliani Sánchez','2020-04-08',NULL,NULL,2,1,22),(11,'Maria Estela Carvajal Núñez','2022-10-12',NULL,NULL,1,1,20),(12,'Juan Carlos Hernández Devers','2020-12-25',NULL,NULL,1,1,20),(13,'Lourdes Encarnación','2019-06-03',NULL,NULL,1,1,20),(14,'Benito Napoleón De La Cruz','2020-02-20',NULL,NULL,1,1,20),(15,'Danyero Read Torres','2021-11-09',NULL,NULL,1,1,20),(16,'Lissette Alt. Evangelista N.','2020-07-17',NULL,NULL,1,1,20),(17,'Ana Jiménez','2018-03-30',NULL,NULL,1,1,20),(18,'Anneris R. Ureña Acosta','2020-09-08',NULL,NULL,1,1,20),(19,'Melida Alt. Castillo Mercedes','2021-04-25',NULL,NULL,1,1,20),(20,'Angel Alb. Alcantara M.','2018-01-11',NULL,NULL,2,1,22),(21,'Rolando A. Paula Núñez','2020-08-14',NULL,NULL,3,2,28),(22,'Lisbet Alt. Peralta Calderón','2022-05-05',NULL,NULL,3,1,28),(23,'Edita Alt. Peña Ureña','2021-10-29',NULL,NULL,3,6,28),(24,'Raquel B. Figuereo Sanchez','2018-12-10',NULL,NULL,3,6,28),(25,'Carlos Martin Burdier','2019-02-08',NULL,NULL,3,5,28),(26,'Narda Valenzuela','2020-07-23',NULL,NULL,3,5,28),(27,'Ana Dilsa Cuevas Scarfullery','2021-11-05',NULL,NULL,3,4,30),(28,'Elba Reynoso','2019-06-15',NULL,NULL,3,4,30),(29,'Yanet Mercedes Ramos Gonzalez','2021-04-01',NULL,NULL,3,4,30),(30,'Carlos Martin Burdier','2018-09-27',NULL,NULL,5,4,NULL);
/*!40000 ALTER TABLE `empleados1` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-21 18:20:22
