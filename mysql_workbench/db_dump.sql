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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bicicleta`
--

LOCK TABLES `bicicleta` WRITE;
/*!40000 ALTER TABLE `bicicleta` DISABLE KEYS */;
INSERT INTO `bicicleta` VALUES (1,'CORRECTO',2),(2,'CORRECTO',2),(3,'CORRECTO',2),(4,'CORRECTO',3),(5,'CORRECTO',2),(6,'CORRECTO',2),(7,'REVISION',3),(8,'CORRECTO',3),(9,'CORRECTO',1),(10,'CORRECTO',1),(11,'CORRECTO',2);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estacion`
--

LOCK TABLES `estacion` WRITE;
/*!40000 ALTER TABLE `estacion` DISABLE KEYS */;
INSERT INTO `estacion` VALUES (1,'Plaza Mayor',2),(2,'Estación de tren',15),(3,'Universidad',20);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`id`, `nombre`, `primer_apellido`, `segundo_apellido`, `dni`, `email`, `direccion`, `fecha_nacimiento`, `telefono`, `contrasenha`, `metodo_pago`, `fecha_inicio_suscripcion`, `tipo_usuario`) VALUES (1,'Juan','Alfonso','Ramirez','41111111Y','a@a.a','a','2025-05-15','666666660','$2b$12$YVPWtgnr2qrV0Epb.owGue58eGWapAFc8Mi3edI9xMVZmCMx/uuoG','TARJETA','2025-05-15 22:20:34','NORMAL'),(2,'Laura','Gómez','Pérez','12345678A','laura@example.com','Calle A, 1','1990-05-10','600123456','$2b$12$o1iAa7fmN2aZDARqU6QNIuXSFiDJ6U82G2o5IQ20w96rbwB2kphaS','TARJETA','2025-05-15 22:20:34','NORMAL'),(3,'Carlos','Ruiz',NULL,'87654321B','carlos@example.com','Av. B, 2','1985-08-15','600654321','$2b$12$h8OJB57bGowswCW1jJbOS.rY7G7cSQyzV4pa9mujKBpkqwDRmqXvm','EFECTIVO','2025-05-15 22:20:34','NORMAL'),(4,'Ana','López','Martínez','11223344C','ana@example.com','Calle C, 3','2000-01-20','600111222','$2b$12$qi/loQmOcY15yugcTpcaYugb4K.zxc7WQN2PZ7lkdHGnr7bIlv2Da','TARJETA','2025-05-15 22:20:34','NORMAL');
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
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `viaje`
--

LOCK TABLES `viaje` WRITE;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` VALUES (1,1,1,'2025-05-10 08:00:00','2025-05-10 08:30:00',1,2),(1,2,4,'2025-05-11 09:15:00','2025-05-15 22:21:18',1,3),(1,4,4,'2025-05-16 12:38:23','2025-05-16 12:38:39',3,1),(1,5,4,'2025-05-16 12:38:27','2025-05-16 12:38:39',3,1),(1,6,4,'2025-05-16 12:38:30','2025-05-16 12:38:39',3,1),(1,7,8,'2025-05-16 12:38:44','2025-05-16 12:38:50',3,3),(1,8,1,'2025-05-16 12:38:53','2025-05-16 12:38:56',1,3),(1,9,2,'2025-05-16 12:52:24','2025-05-16 12:52:28',1,3),(1,10,1,'2025-05-16 12:54:02','2025-05-16 12:54:06',3,3),(1,11,7,'2025-05-16 12:54:15','2025-05-16 12:54:30',2,3),(1,12,3,'2025-05-16 12:54:46','2025-05-16 12:54:49',1,3),(1,13,1,'2025-05-16 12:54:55','2025-05-16 13:04:12',3,1),(1,14,4,'2025-05-16 13:02:30','2025-05-16 13:04:21',1,3),(1,15,10,'2025-05-16 13:04:04','2025-05-16 13:10:48',2,1),(1,16,1,'2025-05-16 13:04:17','2025-05-16 13:10:54',1,1),(1,17,11,'2025-05-16 13:10:50','2025-05-16 13:11:01',2,2),(1,18,2,'2025-05-16 13:10:58','2025-05-16 13:14:24',3,3),(1,19,11,'2025-05-16 13:11:06','2025-05-16 13:11:19',2,2),(1,20,11,'2025-05-16 13:11:11','2025-05-16 13:11:19',2,2),(1,21,2,'2025-05-16 13:11:15','2025-05-16 13:14:24',3,3),(1,22,1,'2025-05-16 13:14:28','2025-05-16 13:14:39',1,2),(1,23,2,'2025-05-16 13:16:20','2025-05-16 13:16:24',3,1),(1,24,1,'2025-05-16 13:18:15','2025-05-16 13:18:22',2,3),(1,25,2,'2025-05-16 13:19:06','2025-05-16 13:19:10',1,2),(1,26,1,'2025-05-16 13:20:48','2025-05-16 13:20:51',3,2),(1,27,1,'2025-05-16 13:32:33','2025-05-16 13:32:53',2,2),(1,28,5,'2025-05-16 13:32:56','2025-05-16 13:33:34',1,2),(1,29,1,'2025-05-16 14:37:02','2025-05-16 14:37:07',2,1),(1,30,2,'2025-05-16 16:38:45','2025-05-16 16:38:48',2,3),(1,31,1,'2025-05-16 16:38:53','2025-05-16 16:38:56',1,2),(1,32,6,'2025-05-16 16:38:57','2025-05-16 16:39:12',1,2),(1,33,1,'2025-05-16 16:41:09','2025-05-16 16:41:18',2,3),(1,34,1,'2025-05-16 17:06:37','2025-05-16 17:06:41',3,2),(1,35,2,'2025-05-16 17:06:48','2025-05-16 17:06:52',3,2),(1,36,3,'2025-05-16 18:03:40','2025-05-16 18:03:57',3,2),(2,3,2,'2025-05-09 17:00:00','2025-05-09 17:45:00',2,3);
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

-- Dump completed on 2025-05-16 18:10:05
