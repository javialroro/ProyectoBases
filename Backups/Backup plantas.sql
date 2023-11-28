-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: planta2
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
-- Dumping data for table `calendarios1`
--

LOCK TABLES `calendarios1` WRITE;
/*!40000 ALTER TABLE `calendarios1` DISABLE KEYS */;
INSERT INTO `calendarios1` VALUES (1,8,2,3,4.5,48,6),(2,9,2,3,4.5,45,6),(3,8,3,10,15,40,20),(4,8,3,20,30,40,40),(5,8,1,50,75,40,100);
/*!40000 ALTER TABLE `calendarios1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `departamentos1`
--

LOCK TABLES `departamentos1` WRITE;
/*!40000 ALTER TABLE `departamentos1` DISABLE KEYS */;
INSERT INTO `departamentos1` VALUES (1,'Produccion'),(2,'Compras'),(3,'Finanzas'),(4,'Gerencia'),(5,'Logistica'),(6,'Ventas');
/*!40000 ALTER TABLE `departamentos1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `empleados1`
--

LOCK TABLES `empleados1` WRITE;
/*!40000 ALTER TABLE `empleados1` DISABLE KEYS */;
INSERT INTO `empleados1` VALUES (1,'Luisa E. Fernández Durán','2019-02-15',NULL,1,1,10),(2,'Daniel Antonio Liranzo','2022-07-03',NULL,1,1,10),(3,'Aryam De Soto Peguero','2019-11-28',NULL,1,1,10),(4,'Rosa Mayra Tejada Acosta','2021-06-10',NULL,1,1,10),(5,'Elias Peguero Acosta','2022-03-22',NULL,1,1,10),(6,'Juana Garcia Martiño','2018-08-05',NULL,1,1,10),(7,'Eusebio Lluli Figueroa','2019-01-14',NULL,1,1,10),(8,'Beatico De La Rosa De La Rosa','2021-05-31',NULL,1,1,10),(9,'Luis Alejandro Ramírez Féliz','2018-09-19',NULL,1,1,10),(10,'Rachelle N. Guiliani Sánchez','2020-04-08',NULL,2,1,22),(11,'Maria Estela Carvajal Núñez','2022-10-12',NULL,1,1,20),(12,'Juan Carlos Hernández Devers','2020-12-25',NULL,1,1,20),(13,'Lourdes Encarnación','2019-06-03',NULL,1,1,20),(14,'Benito Napoleón De La Cruz','2020-02-20',NULL,1,1,20),(15,'Danyero Read Torres','2021-11-09',NULL,1,1,20),(16,'Lissette Alt. Evangelista N.','2020-07-17',NULL,1,1,20),(17,'Ana Jiménez','2018-03-30',NULL,1,1,20),(18,'Anneris R. Ureña Acosta','2020-09-08',NULL,1,1,20),(19,'Melida Alt. Castillo Mercedes','2021-04-25',NULL,1,1,20),(20,'Angel Alb. Alcantara M.','2018-01-11',NULL,2,1,22),(21,'Rolando A. Paula Núñez','2020-08-14',NULL,3,2,28),(22,'Lisbet Alt. Peralta Calderón','2022-05-05',NULL,3,1,28),(23,'Edita Alt. Peña Ureña','2021-10-29',NULL,3,6,28),(24,'Raquel B. Figuereo Sanchez','2018-12-10',NULL,3,6,28),(25,'Carlos Martin Burdier','2019-02-08',NULL,3,5,28),(26,'Narda Valenzuela','2020-07-23',NULL,3,5,28),(27,'Ana Dilsa Cuevas Scarfullery','2021-11-05',NULL,3,4,30),(28,'Elba Reynoso','2019-06-15',NULL,3,4,30),(29,'Yanet Mercedes Ramos Gonzalez','2021-04-01',NULL,3,4,30),(30,'Carlos Martin Burdier','2018-09-27',NULL,5,4,NULL);
/*!40000 ALTER TABLE `empleados1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `feriados1`
--

LOCK TABLES `feriados1` WRITE;
/*!40000 ALTER TABLE `feriados1` DISABLE KEYS */;
INSERT INTO `feriados1` VALUES (1,'2023-04-11','Y','Y','D','D','Y'),(2,'2023-05-01','Y','Y','D','D','Y'),(3,'2023-07-25','Y','Y','D','D','Y'),(4,'2023-08-15','Y','Y','D','D','Y'),(5,'2023-09-15','Y','Y','D','D','Y'),(6,'2023-01-01','N','Y','Y','Y','Y');
/*!40000 ALTER TABLE `feriados1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `lineplanillas`
--

LOCK TABLES `lineplanillas` WRITE;
/*!40000 ALTER TABLE `lineplanillas` DISABLE KEYS */;
INSERT INTO `lineplanillas` VALUES (1,0,30,'Carlos Martin Burdier','07:30:00','17:30:00','2023-11-17',10,2,8,550,499.565),(1,1,30,'Carlos Martin Burdier','07:30:00','05:30:00','2023-11-21',0,0,0,0,0),(1,2,30,'Carlos Martin Burdier','07:30:00','17:37:00','2023-11-22',11,3,8,625,567.6875),(1,3,30,'Carlos Martin Burdier','07:00:00','16:31:00','2023-11-24',10,2,8,550,499.565),(1,4,30,'Carlos Martin Burdier','07:30:00','08:01:00','2023-11-25',1,0,1,50,45.415);
/*!40000 ALTER TABLE `lineplanillas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `marcas1`
--

LOCK TABLES `marcas1` WRITE;
/*!40000 ALTER TABLE `marcas1` DISABLE KEYS */;
INSERT INTO `marcas1` VALUES (1,'2023-04-11','07:30:00','17:30:00'),(1,'2023-04-12','09:30:00','14:00:00'),(30,'2023-01-05','08:00:00','17:30:00'),(30,'2023-01-06','08:00:00','17:00:00'),(30,'2023-04-11','08:00:00','17:30:00'),(30,'2023-11-17','07:30:00','17:30:00'),(30,'2023-11-21','07:30:00','05:30:00'),(30,'2023-11-22','07:30:00','17:37:00'),(30,'2023-11-24','07:00:00','16:31:00'),(30,'2023-11-25','07:30:00','08:01:00');
/*!40000 ALTER TABLE `marcas1` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `planillas`
--

LOCK TABLES `planillas` WRITE;
/*!40000 ALTER TABLE `planillas` DISABLE KEYS */;
INSERT INTO `planillas` VALUES (1,'2023-11-01','2023-11-30','N');
/*!40000 ALTER TABLE `planillas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `tipo_empleado1`
--

LOCK TABLES `tipo_empleado1` WRITE;
/*!40000 ALTER TABLE `tipo_empleado1` DISABLE KEYS */;
INSERT INTO `tipo_empleado1` VALUES (1,'Operativo1',1),(2,'Operativo2',2),(3,'Administrativo',3),(4,'GerenteN1',4),(5,'Gerente',5);
/*!40000 ALTER TABLE `tipo_empleado1` ENABLE KEYS */;
UNLOCK TABLES;

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
CREATE DEFINER=`root`@`localhost` PROCEDURE `MARCAR ENTRADA`(IN idEmpleado int, IN fechaMarca date, IN entrada time)
BEGIN
    DECLARE v_calendario INT; -- Declare a local variable
    -- Assign the value of vista_planilla.calendario to the local variable
    SELECT calendario INTO v_calendario
    FROM vista_planilla
    WHERE id = idEmpleado;

    CASE
        WHEN v_calendario = 1 THEN
            IF DAYOFWEEK(fechaMarca) = 1 THEN
                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT = 'No se puede marcar entrada en domingo en este calendario';
            END IF;
            IF entrada < '06:00:00' THEN
                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT = 'No se puede marcar entrada antes de las 06:00:00';
            END IF;
            INSERT INTO MARCAS1 (id_empleado, fecha, hora_entrada, hora_salida)
            VALUES (idEmpleado, fechaMarca, entrada, NULL);
        WHEN v_calendario = 2 THEN
            IF DAYOFWEEK(fechaMarca) = 1 OR DAYOFWEEK(fechaMarca) = 7 THEN
                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT = 'No se puede marcar entrada en fin de semana';
            END IF;
            IF entrada < '06:00:00' THEN
                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT = 'No se puede marcar entrada antes de las 06:00:00';
            END IF;
            INSERT INTO MARCAS1 (id_empleado, fecha, hora_entrada, hora_salida)
            VALUES (idEmpleado, fechaMarca, entrada, NULL);
        WHEN v_calendario = 3 OR v_calendario = 4 OR v_calendario = 5 THEN
            IF DAYOFWEEK(fechaMarca) = 1 OR DAYOFWEEK(fechaMarca) = 7 THEN
                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT = 'No se puede marcar entrada en fin de semana';
            END IF;
            IF entrada < '08:00:00' THEN
                SIGNAL SQLSTATE '45000'
                    SET MESSAGE_TEXT = 'No se puede marcar entrada antes de las 08:00:00';
            END IF;
            INSERT INTO MARCAS1 (id_empleado, fecha, hora_entrada, hora_salida)
            VALUES (idEmpleado, fechaMarca, entrada, NULL);
        END CASE;
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
/*!50003 DROP PROCEDURE IF EXISTS `Planilla` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `Planilla`(IN calendarioSP int, IN fechaInicio date, IN fechaFin date)
BEGIN

SELECT
    vista_planilla.id,
    nombre,
    hora_entrada,
    hora_salida,
    marcas1.fecha,

    CASE
        WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
            HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
        ELSE
            0
        END AS horas_laboradas,
    CASE
        WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
        ELSE
            0
        END AS horas_extra,
    CASE
        WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
            CASE
                WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                    HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                ELSE
                    0
            END
            -
            CASE
                WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                        HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                ELSE
                    0
                END
        ELSE
            0
        END AS horas_normales,
    CASE
        WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
            CASE
                WHEN f.id IS NOT NULL THEN
                    CASE
                        WHEN calendario = 1 AND f.cal1 = 'D' THEN
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                    ) * c.salario_doble)+ 
                                ((CASE
                                    WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                        HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                    ELSE
                                        0
                                    END)*(c.salario_extra*2))
                        WHEN calendario = 2 AND f.cal2 = 'D' THEN
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                     ) * c.salario_doble)+
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                      ELSE
                                          0
                                    END)*(c.salario_extra*2))
                        WHEN (calendario = 3 OR calendario = 4 OR calendario = 5) AND f.cal3 = 'D' THEN
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                     ) * c.salario_doble)+
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                      ELSE
                                          0
                                    END)*(c.salario_extra*2))
                        ELSE
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                     ) * c.salario_hora) +
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                      ELSE
                                          0
                                    END)*(c.salario_extra))
                        END
                ELSE
                        ((CASE
                              WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                      CASE
                                          WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                          ELSE
                                              0
                                          END
                                      -
                                      CASE
                                          WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                  HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                          ELSE
                                              0
                                          END
                              ELSE
                                  0
                            END
                             ) * c.salario_hora) +
                        ((CASE
                              WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                              ELSE
                                  0
                            END)*(c.salario_extra))
                END
        ELSE
            0
        END AS salario_bruto,
    CASE
        WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
            CASE
                WHEN f.id IS NOT NULL THEN
                    CASE
                        WHEN calendario = 1 AND f.cal1 = 'D' THEN
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                     ) * c.salario_doble)+
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                      ELSE
                                          0
                                    END)*(c.salario_extra*2))
                        WHEN calendario = 2 AND f.cal2 = 'D' THEN
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                     ) * c.salario_doble)+
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                      ELSE
                                          0
                                    END)*(c.salario_extra*2))
                        WHEN (calendario = 3 OR calendario = 4 OR calendario = 5) AND f.cal3 = 'D' THEN
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                     ) * c.salario_doble)+
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                      ELSE
                                          0
                                    END)*(c.salario_extra*2))
                        ELSE
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                                  ELSE
                                                      0
                                                  END
                                              -
                                              CASE
                                                  WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                          HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                                  ELSE
                                                      0
                                                  END
                                      ELSE
                                          0
                                    END
                                     ) * c.salario_hora) +
                                ((CASE
                                      WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                      ELSE
                                          0
                                    END)*(c.salario_extra))
                        END
                ELSE
                        ((CASE
                              WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                      CASE
                                          WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 THEN
                                              HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))
                                          ELSE
                                              0
                                          END
                                      -
                                      CASE
                                          WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                                  HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                                          ELSE
                                              0
                                          END
                              ELSE
                                  0
                            END
                             ) * c.salario_hora) +
                        ((CASE
                              WHEN TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) > 1800 AND HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600)) > horas_laborables THEN
                                      HOUR(SEC_TO_TIME(CEIL(TIME_TO_SEC(TIMEDIFF(hora_salida, hora_entrada)) / 3600) * 3600))-horas_laborables
                              ELSE
                                  0
                            END)*(c.salario_extra))
                END
        ELSE
            0
        END  * 0.9083 AS salario_neto

FROM
    vista_planilla
        INNER JOIN marcas1 ON vista_planilla.id = marcas1.id_empleado
        INNER JOIN calendarios1 c ON vista_planilla.calendario = c.id
        LEFT JOIN feriados1 f ON marcas1.fecha = f.fecha
WHERE
        calendario = calendarioSP AND marcas1.fecha BETWEEN fechaInicio AND fechaFin;
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

-- Dump completed on 2023-11-27 18:58:16
