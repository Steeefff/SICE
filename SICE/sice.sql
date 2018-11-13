CREATE DATABASE  IF NOT EXISTS `sice` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sice`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sice
-- ------------------------------------------------------
-- Server version	5.7.16-log

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
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cursos` (
  `idcurso` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `idIdioma` int(11) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `requisito` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcurso`),
  KEY `FK_CUR_IDI_idx` (`idIdioma`),
  KEY `FK_CUR_CUR_idx` (`requisito`),
  CONSTRAINT `FK_CUR_CUR` FOREIGN KEY (`requisito`) REFERENCES `cursos` (`idcurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_CUR_IDI` FOREIGN KEY (`idIdioma`) REFERENCES `idiomas` (`idIdioma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cursos`
--

LOCK TABLES `cursos` WRITE;
/*!40000 ALTER TABLE `cursos` DISABLE KEYS */;
/*!40000 ALTER TABLE `cursos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `generos` (
  `idGenero` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `generos`
--

LOCK TABLES `generos` WRITE;
/*!40000 ALTER TABLE `generos` DISABLE KEYS */;
INSERT INTO `generos` VALUES (1,'Masculino','Esto es un hombre'),(2,'Femenino','Esto es una mujer'),(3,'Otro','');
/*!40000 ALTER TABLE `generos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupos` (
  `idGrupos` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `idCurso` int(11) NOT NULL,
  `idProfesor` varchar(45) NOT NULL,
  `horaInicio` varchar(45) DEFAULT NULL,
  `horaSalida` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idGrupos`),
  KEY `FK_GRU_CUR_idx` (`idCurso`),
  KEY `FK_GRU_PRO_idx` (`idProfesor`),
  CONSTRAINT `FK_GRU_CUR` FOREIGN KEY (`idCurso`) REFERENCES `cursos` (`idcurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_GRU_PER_PROF` FOREIGN KEY (`idProfesor`) REFERENCES `personas` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupos`
--

LOCK TABLES `grupos` WRITE;
/*!40000 ALTER TABLE `grupos` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `idiomas`
--

DROP TABLE IF EXISTS `idiomas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `idiomas` (
  `idIdioma` int(11) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idIdioma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `idiomas`
--

LOCK TABLES `idiomas` WRITE;
/*!40000 ALTER TABLE `idiomas` DISABLE KEYS */;
INSERT INTO `idiomas` VALUES (1,'Inglés'),(2,'Portugues');
/*!40000 ALTER TABLE `idiomas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `matriculas`
--

DROP TABLE IF EXISTS `matriculas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `matriculas` (
  `idMatriculas` int(11) NOT NULL,
  `idEstudiante` varchar(45) NOT NULL,
  `idGrupo` int(11) NOT NULL,
  `fechaPago` varchar(45) DEFAULT NULL,
  `aprobado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMatriculas`,`idEstudiante`,`idGrupo`),
  KEY `FK_MAT_PER_idx` (`idEstudiante`),
  KEY `Fk_MAT_GRU_idx` (`idGrupo`),
  CONSTRAINT `FK_MAT_PER` FOREIGN KEY (`idEstudiante`) REFERENCES `personas` (`identificacion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `Fk_MAT_GRU` FOREIGN KEY (`idGrupo`) REFERENCES `grupos` (`idGrupos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `matriculas`
--

LOCK TABLES `matriculas` WRITE;
/*!40000 ALTER TABLE `matriculas` DISABLE KEYS */;
/*!40000 ALTER TABLE `matriculas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personas` (
  `identificacion` varchar(45) NOT NULL,
  `nombre` varchar(45) DEFAULT NULL,
  `apellido1` varchar(45) DEFAULT NULL,
  `apellido2` varchar(45) DEFAULT NULL,
  `telefono` int(8) DEFAULT NULL,
  `genero` int(11) DEFAULT NULL,
  `direccion` varchar(500) DEFAULT NULL,
  `fechaNacimiento` varchar(45) DEFAULT NULL,
  `correo` varchar(45) DEFAULT NULL,
  `contraseña` varchar(45) DEFAULT NULL,
  `idTipoPersona` int(11) DEFAULT NULL,
  `idioma` int(11) DEFAULT NULL,
  `Habilitado` int(1) DEFAULT '1' COMMENT '1 - Activo\n0 - Inactivo',
  PRIMARY KEY (`identificacion`),
  KEY `FK_PER_TIPER_idx` (`idTipoPersona`),
  KEY `FK_PER_GEN_idx` (`genero`),
  KEY `FK_PER_IDI_idx` (`idioma`),
  CONSTRAINT `FK_PER_GEN` FOREIGN KEY (`genero`) REFERENCES `generos` (`idGenero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PER_IDI` FOREIGN KEY (`idioma`) REFERENCES `idiomas` (`idIdioma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PER_TIPER` FOREIGN KEY (`idTipoPersona`) REFERENCES `tipopersonas` (`idTipoPersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personas`
--

LOCK TABLES `personas` WRITE;
/*!40000 ALTER TABLE `personas` DISABLE KEYS */;
INSERT INTO `personas` VALUES ('201890486','Esteban','Rodriguez','Solera',78392612,2,'San vicente de carrillo','02/11/2000','esteban@esteban.com',NULL,2,2,1),('207890567','Miguel','Lopez','Madrigal',87563456,2,'San Jose','dd/MM/yyyy','migles@hotmail.com',NULL,2,1,1),('38934','sjskl','dskjdndskj','djd',8328923,1,'dkjhfhfd','dd/MM/yyyy','aslkkls',NULL,2,1,1),('402190497','Stefanny','Villalobos','Uva',87453715,2,'San Rafael de Alajuela','15/06/1993','fany-9315@hotmail.com','',2,1,1),('402190598','Anita','Villalobos','Jimenez',67453214,1,'San Antonio de Belen ','dd/MM/yyyy','anita@anita.com',NULL,2,1,1),('409280487','Camila','Gonsalez','Jimenez',84758697,1,'wdhfjl','dd/MM/yyyy','camila@camila.com',NULL,2,1,1),('489','kc','kj','lj',86,2,'jl','3/10/2000','hj',NULL,2,1,1),('504210597','dos','dos','dos',55555555,3,'jbdsvkjsf','01/11/2000','dos@dos.com',NULL,2,1,1),('5544','jjl','kklk','klk',797,2,'kjhjk','dd/MM/yyyy','gkj',NULL,2,1,1),('60345678','Mateo','Viquez','Amaranto',67890987,3,'Escazu de San Jose Avenida 5','dd/MM/yyyy','matama@gmail.com',NULL,2,2,1),('8289HK72HDJSN','Juan','Mendez','Guido',8384929,3,'San Jose, Barrio escalante','23/11/2000','juan@gmail.com',NULL,2,1,1);
/*!40000 ALTER TABLE `personas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipopersonas`
--

DROP TABLE IF EXISTS `tipopersonas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipopersonas` (
  `idTipoPersonas` int(11) NOT NULL,
  `rol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idTipoPersonas`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipopersonas`
--

LOCK TABLES `tipopersonas` WRITE;
/*!40000 ALTER TABLE `tipopersonas` DISABLE KEYS */;
INSERT INTO `tipopersonas` VALUES (1,'Estudiante'),(2,'Profesor'),(3,'Adminidstrativo'),(4,'Gerente');
/*!40000 ALTER TABLE `tipopersonas` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-11-12 22:17:21
