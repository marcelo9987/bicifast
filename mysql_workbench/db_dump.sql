-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: bicifast
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `bicicleta`
--

DROP TABLE IF EXISTS `bicicleta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bicicleta` (
  `id` int NOT NULL AUTO_INCREMENT,
  `estado` enum('CORRECTO','REVISION') NOT NULL DEFAULT 'CORRECTO',
  `estacion` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_bicicleta_estacion1_idx` (`estacion`),
  CONSTRAINT `fk_bicicleta_estacion1` FOREIGN KEY (`estacion`) REFERENCES `estacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicicleta`
--

LOCK TABLES `bicicleta` WRITE;
/*!40000 ALTER TABLE `bicicleta` DISABLE KEYS */;
INSERT INTO `bicicleta` VALUES (1,'CORRECTO',3),(2,'CORRECTO',10),(3,'CORRECTO',1),(4,'CORRECTO',2),(5,'CORRECTO',2),(6,'CORRECTO',5),(7,'CORRECTO',2),(8,'CORRECTO',2),(9,'CORRECTO',2),(10,'CORRECTO',2),(11,'CORRECTO',2),(12,'CORRECTO',2),(13,'CORRECTO',2),(14,'CORRECTO',2),(15,'CORRECTO',2),(16,'CORRECTO',2),(17,'CORRECTO',2),(18,'CORRECTO',2),(19,'CORRECTO',2),(20,'CORRECTO',2),(21,'CORRECTO',2),(22,'CORRECTO',2),(23,'CORRECTO',2);
/*!40000 ALTER TABLE `bicicleta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estacion`
--

DROP TABLE IF EXISTS `estacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estacion` (
  `id` int NOT NULL AUTO_INCREMENT,
  `ubicacion` tinytext NOT NULL,
  `aforo` tinyint unsigned DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacion`
--

LOCK TABLES `estacion` WRITE;
/*!40000 ALTER TABLE `estacion` DISABLE KEYS */;
INSERT INTO `estacion` VALUES (1,'8001 VILLA PARK DRIVE',1),(2,'86 boulevard Haussmann',24),(3,'1008 OAK STREET',3),(4,'P.O. BOX 406',4),(5,'ONE PENN\'S WAY',5),(6,'MAC N9301-041',6),(7,'Av Alfonso IX, 7',7),(8,'720 HIGHWAY 15 SOUTH',8),(9,'Hamngatan 2',9),(10,'3RD FLOOR',10),(11,'320 1ST STREET',11),(12,'Bahnhofstraße 23',12),(13,'Altenburger Straße 13',13),(14,'PIAZZA FILIPPO MEDA 4',14),(15,'1200 E. WARRENVILLE RD',15),(16,'PO BOX 7009',16),(17,'ul. Piłsudskiego 36',17),(18,'16 BOULEVARD DES ITALIENS',18),(19,'Balasta dambis 15',19),(20,'Heinemannstraße 15',20),(21,'4140 EAST STATE STREET',21),(22,'Tilžės g. 149',22),(23,'8001 VILLA PARK DRIVE',23),(24,'111 S. CASAVER',24),(25,'110 S FERRALL STREET',25),(26,'MAC N9301-041',26),(27,'12 PLACE DES ETATS UNIS',27),(28,'7800 E IMPERIAL HWY',28),(29,'Nieheimer Straße 14',29),(30,'1200 E. WARRENVILLE ROAD',30);
/*!40000 ALTER TABLE `estacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) NOT NULL,
  `primer_apellido` varchar(255) NOT NULL,
  `segundo_apellido` varchar(255) DEFAULT NULL,
  `dni` char(9) NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) NOT NULL,
  `fecha_nacimiento` date NOT NULL,
  `telefono` char(9) NOT NULL,
  `contrasenha` varchar(255) NOT NULL COMMENT 'BINARY extiende por 0x00 para completar, mientras que char trunca esp. vacios, por lo que es mas optima',
  `metodo_pago` enum('TARJETA','EFECTIVO') NOT NULL,
  `fecha_inicio_suscripcion` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `fecha_fin_suscripcion` datetime GENERATED ALWAYS AS ((`fecha_inicio_suscripcion` + interval 30 day)) VIRTUAL,
  `tipo_usuario` enum('ADMIN','MANT','NORMAL') NOT NULL DEFAULT 'NORMAL',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `dni_UNIQUE` (`dni`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nombre`, `primer_apellido`, `segundo_apellido`, `dni`, `email`, `direccion`, `fecha_nacimiento`, `telefono`, `contrasenha`, `metodo_pago`, `fecha_inicio_suscripcion`, `tipo_usuario`) VALUES (1,'Juan','Alfonso','Ramirez','41111111Y','a@a.a','a','2025-05-15','666666660','$2b$12$YVPWtgnr2qrV0Epb.owGue58eGWapAFc8Mi3edI9xMVZmCMx/uuoG','TARJETA','2025-05-15 16:57:51','NORMAL');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `viaje`
--

DROP TABLE IF EXISTS `viaje`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `viaje` (
  `usuario` int NOT NULL,
  `id` int NOT NULL AUTO_INCREMENT,
  `bicicleta` int NOT NULL,
  `hora_inicio` datetime NOT NULL,
  `hora_fin` datetime DEFAULT NULL,
  `origen` int NOT NULL,
  `destino` int DEFAULT NULL,
  PRIMARY KEY (`usuario`,`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `usuario_id_idx` (`usuario`),
  KEY `fk_viaje_estacion1_idx` (`origen`),
  KEY `fk_viaje_estacion2_idx` (`destino`),
  KEY `fk_bicicleta` (`bicicleta`),
  CONSTRAINT `fk_bicicleta` FOREIGN KEY (`bicicleta`) REFERENCES `bicicleta` (`id`),
  CONSTRAINT `fk_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `fk_viaje_estacion1` FOREIGN KEY (`origen`) REFERENCES `estacion` (`id`),
  CONSTRAINT `fk_viaje_estacion2` FOREIGN KEY (`destino`) REFERENCES `estacion` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES (1,1,1,'2025-05-15 16:59:01','2025-05-15 17:00:45',1,3),(1,2,2,'2025-05-15 16:59:17','2025-05-15 18:12:56',2,10);
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-15 18:36:40
