CREATE DATABASE  IF NOT EXISTS `sice` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sice`;
-- MySQL dump 10.13  Distrib 8.0.13, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: sice
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
-- Position to start replication or point-in-time recovery from
--

-- CHANGE MASTER TO MASTER_LOG_FILE='DESKTOP-15DI8I9-bin.000054', MASTER_LOG_POS=155;

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `cursos` (
  `idcurso` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idIdioma` int(11) NOT NULL,
  `estado` int(1) NOT NULL,
  PRIMARY KEY (`idcurso`),
  UNIQUE KEY `idcurso_UNIQUE` (`idcurso`),
  KEY `idIdioma_idx` (`idIdioma`),
  CONSTRAINT `idIdioma` FOREIGN KEY (`idIdioma`) REFERENCES `idiomas` (`ididioma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
INSERT INTO `cursos` VALUES ('C001','INGLÉS 1',1,1),('C002','INGLÉS 2',1,1),('C003','INGLÉS 3',1,1);
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `generos` (
  `idGenero` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (1,'Masculino','Esto es un hombre',1),(2,'Femenino','Esto es una mujer',1),(3,'Otro','',1),(4,'Transgenero',NULL,1);
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grupos` (
  `idgrupos` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idcurso` varchar(10) NOT NULL,
  `idProfesor` varchar(20) NOT NULL,
  `horaInicio` varchar(45) NOT NULL,
  `estado` int(1) NOT NULL,
  `dia` varchar(15) NOT NULL,
  PRIMARY KEY (`idgrupos`),
  UNIQUE KEY `idgrupos_UNIQUE` (`idgrupos`),
  KEY `idProfesor_idx` (`idProfesor`),
  KEY `idcurso_idx` (`idcurso`),
  CONSTRAINT `idProfesor` FOREIGN KEY (`idProfesor`) REFERENCES `personas` (`identificacion`),
  CONSTRAINT `idcurso` FOREIGN KEY (`idcurso`) REFERENCES `cursos` (`idcurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
INSERT INTO `grupos` VALUES ('G001','GRUPO 1','C002','222222222','13:00',1,'Lunes'),('G002','GRUPO 2','C003','333333333','14:02',1,'Martes'),('G003','GRUPO 3','C003','333333333','15:30',1,'Miércoles');
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idiomas`
--

DROP TABLE IF EXISTS `idiomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `idiomas` (
  `idIdioma` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idIdioma`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idiomas`
--

LOCK TABLES `idiomas` WRITE;
/*!40000 ALTER TABLE `idiomas` DISABLE KEYS */;
INSERT INTO `idiomas` VALUES (1,'Inglés',1),(2,'Portugues',1),(3,'Alemán',1),(4,'Chino',1),(5,'chinesse',1),(6,'latin',1);
/*!40000 ALTER TABLE `idiomas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idiomasprofesor`
--

DROP TABLE IF EXISTS `idiomasprofesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `idiomasprofesor` (
  `identificacion` varchar(20) NOT NULL,
  `idIdioma` int(11) NOT NULL,
  KEY `FK_IDIPRO_PER_idx` (`identificacion`),
  KEY `FK_IDIPRO_IDI_idx` (`idIdioma`),
  CONSTRAINT `FK_IDIPRO_PER` FOREIGN KEY (`identificacion`) REFERENCES `personas` (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idiomasprofesor`
--

LOCK TABLES `idiomasprofesor` WRITE;
/*!40000 ALTER TABLE `idiomasprofesor` DISABLE KEYS */;
INSERT INTO `idiomasprofesor` VALUES ('504120597',0),('504120597',2),('504120597',0),('222222222',2),('333333333',1),('333333333',2),('333333333',3),('111111111',1),('111111111',2);
/*!40000 ALTER TABLE `idiomasprofesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matriculas`
--

DROP TABLE IF EXISTS `matriculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `matriculas` (
  `idMatriculas` int(11) NOT NULL AUTO_INCREMENT,
  `idEstudiante` varchar(20) NOT NULL,
  `nombreGrupo` varchar(45) NOT NULL,
  `fechaDeMatricula` date NOT NULL,
  `fechaProxPago` date NOT NULL,
  PRIMARY KEY (`idMatriculas`),
  KEY `idEstudianteMatriculas_idx` (`idEstudiante`),
  CONSTRAINT `idEstudianteMatriculas` FOREIGN KEY (`idEstudiante`) REFERENCES `personas` (`identificacion`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matriculas`
--

LOCK TABLES `matriculas` WRITE;
/*!40000 ALTER TABLE `matriculas` DISABLE KEYS */;
INSERT INTO `matriculas` VALUES (6,'504120598','GRUPO 3','2019-04-21','2019-04-28'),(11,'504120598','GRUPO 2','2019-04-27','2019-05-15'),(12,'504120598','GRUPO 1','2019-04-27','2019-05-18'),(15,'504120599','GRUPO 2','2019-04-28','2019-04-27');
/*!40000 ALTER TABLE `matriculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `pagos` (
  `idEstudiante` varchar(20) NOT NULL,
  `numeroRecibo` varchar(20) NOT NULL,
  `observacion` varchar(100) DEFAULT NULL,
  `fechaDelPago` date NOT NULL,
  `fechaPagada` date NOT NULL,
  KEY `idEstudiante_idx` (`idEstudiante`),
  CONSTRAINT `idEstudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `personas` (`identificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pagos`
--

LOCK TABLES `pagos` WRITE;
/*!40000 ALTER TABLE `pagos` DISABLE KEYS */;
INSERT INTO `pagos` VALUES ('504120598','R001','NI','2019-04-27','2019-04-28'),('504120598','R002','NI','2019-04-27','2019-05-15');
/*!40000 ALTER TABLE `pagos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `personas` (
  `identificacion` varchar(20) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido1` varchar(45) DEFAULT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `telefono` int(9) DEFAULT NULL,
  `genero` int(11) DEFAULT NULL,
  `direccion` varchar(500) DEFAULT NULL,
  `fechaNacimiento` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  `idTipoPersona` int(11) DEFAULT NULL,
  `Habilitado` int(1) DEFAULT '1' COMMENT '1 - Activo\n0 - Inactivo',
  PRIMARY KEY (`identificacion`),
  KEY `FK_PER_TIPER_idx` (`idTipoPersona`),
  KEY `FK_PER_GEN_idx` (`genero`),
  CONSTRAINT `FK_PER_GEN` FOREIGN KEY (`genero`) REFERENCES `generos` (`idgenero`),
  CONSTRAINT `FK_PER_TIPER` FOREIGN KEY (`idTipoPersona`) REFERENCES `tipopersonas` (`idtipopersonas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES ('111111111','Marisela','Montero','Varela',88888888,2,'Tilarán','18/03/1976','mariselamv@gmail.com',NULL,2,1),('222222222','Orlando','Monge','Elizondo',888888888,1,'Tilarán, El Dos','28/02/1984','orlando@gmail.com',NULL,2,1),('333333333','Marlon','Monge','Montero',888888888,1,'Tilarán, El Dos','12/12/1997','marlon@hotmail.com',NULL,2,1),('504120597','Melany','Monge','Montero',85985075,2,'Alajuela','26/09/1996','melany9674@hotmail.com',NULL,2,1),('504120598','Michelles','Ugarte','Monge',44444444,1,'Alajuela','01/03/2011','michelle@hotmail.com',NULL,1,1),('504120599','Christopher','Ugarte','Vega',88888888,2,'Turrialba','01/03/2002','Christopher@gmail.com',NULL,1,1);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `requisitoscursos`
--

DROP TABLE IF EXISTS `requisitoscursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `requisitoscursos` (
  `idCursoAgregado` varchar(10) NOT NULL,
  `idCursoRequisito` varchar(10) NOT NULL,
  KEY `idCursoAgregado_idx` (`idCursoAgregado`),
  KEY `idCursoRequisito_idx` (`idCursoRequisito`),
  CONSTRAINT `idCursoAgregado` FOREIGN KEY (`idCursoAgregado`) REFERENCES `cursos` (`idcurso`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `idCursoRequisito` FOREIGN KEY (`idCursoRequisito`) REFERENCES `cursos` (`idcurso`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `requisitoscursos`
--

LOCK TABLES `requisitoscursos` WRITE;
/*!40000 ALTER TABLE `requisitoscursos` DISABLE KEYS */;
INSERT INTO `requisitoscursos` VALUES ('C003','C001'),('C003','C002'),('C002','C001');
/*!40000 ALTER TABLE `requisitoscursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopersonas`
--

DROP TABLE IF EXISTS `tipopersonas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `tipopersonas` (
  `idTipoPersonas` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `rol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipoPersonas`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopersonas`
--

LOCK TABLES `tipopersonas` WRITE;
/*!40000 ALTER TABLE `tipopersonas` DISABLE KEYS */;
INSERT INTO `tipopersonas` VALUES (1,'Estudiante'),(2,'Profesor'),(3,'Adminidstrativo'),(4,'Gerente'),(5,'Desarrolladora');
/*!40000 ALTER TABLE `tipopersonas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `vista_estudiantes`
--

DROP TABLE IF EXISTS `vista_estudiantes`;
/*!50001 DROP VIEW IF EXISTS `vista_estudiantes`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `vista_estudiantes` AS SELECT 
 1 AS `identificacion`,
 1 AS `nombre`,
 1 AS `apellido1`,
 1 AS `apellido2`,
 1 AS `telefono`,
 1 AS `genero`,
 1 AS `direccion`,
 1 AS `fechaNacimiento`,
 1 AS `correo`,
 1 AS `contraseña`,
 1 AS `idTipoPersona`,
 1 AS `Habilitado`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_mantenimientocursos`
--

DROP TABLE IF EXISTS `vista_mantenimientocursos`;
/*!50001 DROP VIEW IF EXISTS `vista_mantenimientocursos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `vista_mantenimientocursos` AS SELECT 
 1 AS `idcurso`,
 1 AS `nombreCurso`,
 1 AS `idIdioma`,
 1 AS `estado`,
 1 AS `nombreIdioma`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_mantenimientogrupos`
--

DROP TABLE IF EXISTS `vista_mantenimientogrupos`;
/*!50001 DROP VIEW IF EXISTS `vista_mantenimientogrupos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `vista_mantenimientogrupos` AS SELECT 
 1 AS `idgrupos`,
 1 AS `nombreGrupo`,
 1 AS `idcurso`,
 1 AS `idProfesor`,
 1 AS `horaInicio`,
 1 AS `estado`,
 1 AS `dia`,
 1 AS `nombreProfesor`,
 1 AS `apellido1`,
 1 AS `apellido2`,
 1 AS `nombreCurso`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_matriculas`
--

DROP TABLE IF EXISTS `vista_matriculas`;
/*!50001 DROP VIEW IF EXISTS `vista_matriculas`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `vista_matriculas` AS SELECT 
 1 AS `idEstudiante`,
 1 AS `idcurso`,
 1 AS `nombrecurso`,
 1 AS `nombreIdioma`,
 1 AS `nombreGrupo`,
 1 AS `nombreProfesor`,
 1 AS `apellido1`,
 1 AS `apellido2`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_pagos`
--

DROP TABLE IF EXISTS `vista_pagos`;
/*!50001 DROP VIEW IF EXISTS `vista_pagos`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `vista_pagos` AS SELECT 
 1 AS `idEstudiante`,
 1 AS `nombre`,
 1 AS `apellido1`,
 1 AS `apellido2`,
 1 AS `numeroRecibo`,
 1 AS `observacion`,
 1 AS `fechaDelPago`,
 1 AS `fechaPagada`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `vista_profesores`
--

DROP TABLE IF EXISTS `vista_profesores`;
/*!50001 DROP VIEW IF EXISTS `vista_profesores`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8mb4;
/*!50001 CREATE VIEW `vista_profesores` AS SELECT 
 1 AS `identificacion`,
 1 AS `nombre`,
 1 AS `apellido1`,
 1 AS `apellido2`,
 1 AS `telefono`,
 1 AS `genero`,
 1 AS `direccion`,
 1 AS `fechaNacimiento`,
 1 AS `correo`,
 1 AS `contraseña`,
 1 AS `idTipoPersona`,
 1 AS `Habilitado`*/;
SET character_set_client = @saved_cs_client;

--
-- Dumping routines for database 'sice'
--

--
-- Final view structure for view `vista_estudiantes`
--

/*!50001 DROP VIEW IF EXISTS `vista_estudiantes`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_estudiantes` AS select `personas`.`identificacion` AS `identificacion`,`personas`.`nombre` AS `nombre`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`personas`.`telefono` AS `telefono`,`personas`.`genero` AS `genero`,`personas`.`direccion` AS `direccion`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`correo` AS `correo`,`personas`.`contraseña` AS `contraseña`,`personas`.`idTipoPersona` AS `idTipoPersona`,`personas`.`Habilitado` AS `Habilitado` from `personas` where (`personas`.`idTipoPersona` = 1) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_mantenimientocursos`
--

/*!50001 DROP VIEW IF EXISTS `vista_mantenimientocursos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_mantenimientocursos` AS select `cursos`.`idcurso` AS `idcurso`,`cursos`.`nombre` AS `nombreCurso`,`cursos`.`idIdioma` AS `idIdioma`,`cursos`.`estado` AS `estado`,`idiomas`.`nombre` AS `nombreIdioma` from (`cursos` join `idiomas`) where (`cursos`.`idIdioma` = `idiomas`.`idIdioma`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_mantenimientogrupos`
--

/*!50001 DROP VIEW IF EXISTS `vista_mantenimientogrupos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_mantenimientogrupos` AS select `grupos`.`idgrupos` AS `idgrupos`,`grupos`.`nombre` AS `nombreGrupo`,`grupos`.`idcurso` AS `idcurso`,`grupos`.`idProfesor` AS `idProfesor`,`grupos`.`horaInicio` AS `horaInicio`,`grupos`.`estado` AS `estado`,`grupos`.`dia` AS `dia`,`personas`.`nombre` AS `nombreProfesor`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`cursos`.`nombre` AS `nombreCurso` from ((`grupos` join `personas`) join `cursos`) where ((`grupos`.`idcurso` = `cursos`.`idcurso`) and (`grupos`.`idProfesor` = `personas`.`identificacion`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_matriculas`
--

/*!50001 DROP VIEW IF EXISTS `vista_matriculas`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_matriculas` AS select `matriculas`.`idEstudiante` AS `idEstudiante`,`cursos`.`idcurso` AS `idcurso`,`cursos`.`nombre` AS `nombrecurso`,`idiomas`.`nombre` AS `nombreIdioma`,`grupos`.`nombre` AS `nombreGrupo`,`personas`.`nombre` AS `nombreProfesor`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2` from ((((`matriculas` join `grupos`) join `cursos`) join `idiomas`) join `personas`) where ((`matriculas`.`nombreGrupo` = `grupos`.`nombre`) and (`grupos`.`idcurso` = `cursos`.`idcurso`) and (`cursos`.`idIdioma` = `idiomas`.`idIdioma`) and (`grupos`.`idProfesor` = `personas`.`identificacion`)) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_pagos`
--

/*!50001 DROP VIEW IF EXISTS `vista_pagos`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_pagos` AS select `pagos`.`idEstudiante` AS `idEstudiante`,`personas`.`nombre` AS `nombre`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`pagos`.`numeroRecibo` AS `numeroRecibo`,`pagos`.`observacion` AS `observacion`,`pagos`.`fechaDelPago` AS `fechaDelPago`,`pagos`.`fechaPagada` AS `fechaPagada` from (`pagos` join `personas`) where (`pagos`.`idEstudiante` = `personas`.`identificacion`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `vista_profesores`
--

/*!50001 DROP VIEW IF EXISTS `vista_profesores`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `vista_profesores` AS select `personas`.`identificacion` AS `identificacion`,`personas`.`nombre` AS `nombre`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`personas`.`telefono` AS `telefono`,`personas`.`genero` AS `genero`,`personas`.`direccion` AS `direccion`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`correo` AS `correo`,`personas`.`contraseña` AS `contraseña`,`personas`.`idTipoPersona` AS `idTipoPersona`,`personas`.`Habilitado` AS `Habilitado` from `personas` where (`personas`.`idTipoPersona` = 2) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-28 17:26:32
