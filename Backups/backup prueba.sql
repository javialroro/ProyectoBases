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
-- Table structure for table `calendarios1`
--

DROP TABLE IF EXISTS `calendarios1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `calendarios1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `horas_laborables` double DEFAULT NULL,
  `tipo_planilla` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `tipo_planilla` (`tipo_planilla`),
  CONSTRAINT `calendarios1_ibfk_1` FOREIGN KEY (`tipo_planilla`) REFERENCES `tipo_planilla1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendarios1`
--

LOCK TABLES `calendarios1` WRITE;
/*!40000 ALTER TABLE `calendarios1` DISABLE KEYS */;
INSERT INTO `calendarios1` VALUES (1,8,2),(2,9,2),(3,8,3),(4,8,3),(5,8,1);
/*!40000 ALTER TABLE `calendarios1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamentos1`
--

DROP TABLE IF EXISTS `departamentos1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamentos1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamentos1`
--

LOCK TABLES `departamentos1` WRITE;
/*!40000 ALTER TABLE `departamentos1` DISABLE KEYS */;
INSERT INTO `departamentos1` VALUES (1,'Produccion'),(2,'Compras'),(3,'Finanzas'),(4,'Gerencia'),(5,'Logistica'),(6,'Ventas');
/*!40000 ALTER TABLE `departamentos1` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Table structure for table `feriados1`
--

DROP TABLE IF EXISTS `feriados1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feriados1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `Se_Trabaja` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `pagado` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CAL1` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CAL2` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `CAL3` varchar(1) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feriados1`
--

LOCK TABLES `feriados1` WRITE;
/*!40000 ALTER TABLE `feriados1` DISABLE KEYS */;
INSERT INTO `feriados1` VALUES (1,'2023-04-11','Y','Y','D','D','Y'),(2,'2023-05-01','Y','Y','D','D','Y'),(3,'2023-07-25','Y','Y','D','D','Y'),(4,'2023-08-15','Y','Y','D','D','Y'),(5,'2023-09-15','Y','Y','D','D','Y'),(6,'2023-01-01','N','Y','Y','Y','Y');
/*!40000 ALTER TABLE `feriados1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marcas1`
--

DROP TABLE IF EXISTS `marcas1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marcas1` (
  `id_empleado` int NOT NULL,
  `fecha` date NOT NULL,
  `hora_entrada` time DEFAULT NULL,
  `hora_salida` time DEFAULT NULL,
  PRIMARY KEY (`id_empleado`,`fecha`),
  CONSTRAINT `marcas1_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleados1` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marcas1`
--

LOCK TABLES `marcas1` WRITE;
/*!40000 ALTER TABLE `marcas1` DISABLE KEYS */;
INSERT INTO `marcas1` VALUES (30,'2023-11-17','07:30:00','17:30:00'),(30,'2023-11-21','07:30:00','05:30:00'),(30,'2023-11-23','05:30:00',NULL);
/*!40000 ALTER TABLE `marcas1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_empleado1`
--

DROP TABLE IF EXISTS `tipo_empleado1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_empleado1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  `calendario` int DEFAULT NULL,
  `salario_hora` double DEFAULT NULL,
  `salario_extra` double DEFAULT NULL,
  `cantidad_horas` double DEFAULT NULL,
  `salario_doble` double DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `calendario` (`calendario`),
  CONSTRAINT `tipo_empleado1_ibfk_1` FOREIGN KEY (`calendario`) REFERENCES `calendarios1` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_empleado1`
--

LOCK TABLES `tipo_empleado1` WRITE;
/*!40000 ALTER TABLE `tipo_empleado1` DISABLE KEYS */;
INSERT INTO `tipo_empleado1` VALUES (1,'Operativo1',1,3,4.5,48,6),(2,'Operativo2',2,3,4.5,45,6),(3,'Administrativo',3,10,15,40,20),(4,'GerenteN1',4,20,30,40,40),(5,'Gerente',5,50,75,40,100);
/*!40000 ALTER TABLE `tipo_empleado1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_planilla1`
--

DROP TABLE IF EXISTS `tipo_planilla1`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_planilla1` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_planilla1`
--

LOCK TABLES `tipo_planilla1` WRITE;
/*!40000 ALTER TABLE `tipo_planilla1` DISABLE KEYS */;
INSERT INTO `tipo_planilla1` VALUES (1,'Mensual'),(2,'Semanal'),(3,'Quincenal');
/*!40000 ALTER TABLE `tipo_planilla1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'planta1'
--
/*!50003 DROP PROCEDURE IF EXISTS `MARCAR ENTRADA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MARCAR ENTRADA`(IN idEmpleado int, IN fechaMarca date, entrada time)
BEGIN
INSERT INTO MARCAS1 (id_empleado, fecha, hora_entrada, hora_salida)
VALUES(idEmpleado,fechaMarca,entrada,null);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `MARCAR_SALIDA` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `MARCAR_SALIDA`(idEmpleado int, fechaMarca date, salida time)
BEGIN
IF exists(select 1 from marcas1 where id_empleado = idEmpleado and fecha = fechaMarca and hora_salida is null) then
	update marcas1
    set hora_salida = salida
    where id_empleado = idEmpleado and fecha = fechaMarca and hora_salida is null;
else
SELECT 'No se puede actualizar la salida. Entrada no encontrada o salida ya establecida.';
end if;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-21 18:35:56
