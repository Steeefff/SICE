-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 20, 2019 at 05:40 PM
-- Server version: 5.7.21
-- PHP Version: 5.6.35

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sice`
--

-- --------------------------------------------------------

--
-- Table structure for table `cursos`
--

DROP TABLE IF EXISTS `cursos`;
CREATE TABLE IF NOT EXISTS `cursos` (
  `idcurso` varchar(10) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `idIdioma` int(11) NOT NULL,
  `estado` int(1) NOT NULL,
  PRIMARY KEY (`idcurso`),
  UNIQUE KEY `idcurso_UNIQUE` (`idcurso`),
  KEY `idIdioma_idx` (`idIdioma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cursos`
--

INSERT INTO `cursos` (`idcurso`, `nombre`, `idIdioma`, `estado`) VALUES
('C001', 'INGLÉS 1', 1, 1),
('C002', 'INGLÉS 2', 1, 1),
('C003', 'INGLÉS 3', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `generos`
--

DROP TABLE IF EXISTS `generos`;
CREATE TABLE IF NOT EXISTS `generos` (
  `idGenero` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `descripcion` varchar(500) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `generos`
--

INSERT INTO `generos` (`idGenero`, `nombre`, `descripcion`, `estado`) VALUES
(1, 'Masculino', 'Esto es un hombre', 1),
(2, 'Femenino', 'Esto es una mujer', 1),
(3, 'Otro', '', 1),
(4, 'Transgenero', NULL, 1);

-- --------------------------------------------------------

--
-- Table structure for table `grupos`
--

DROP TABLE IF EXISTS `grupos`;
CREATE TABLE IF NOT EXISTS `grupos` (
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
  KEY `idcurso_idx` (`idcurso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `grupos`
--

INSERT INTO `grupos` (`idgrupos`, `nombre`, `idcurso`, `idProfesor`, `horaInicio`, `estado`, `dia`) VALUES
('G001', 'GRUPO 1', 'C002', '222222222', '13:00', 1, 'Lunes'),
('G002', 'GRUPO 2', 'C003', '333333333', '14:02', 1, 'Martes'),
('G003', 'GRUPO 3', 'C003', '333333333', '15:30', 1, 'Miércoles');

-- --------------------------------------------------------

--
-- Table structure for table `idiomas`
--

DROP TABLE IF EXISTS `idiomas`;
CREATE TABLE IF NOT EXISTS `idiomas` (
  `idIdioma` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  PRIMARY KEY (`idIdioma`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `idiomas`
--

INSERT INTO `idiomas` (`idIdioma`, `nombre`, `estado`) VALUES
(1, 'Inglés', 1),
(2, 'Portugues', 1),
(3, 'Alemán', 1),
(4, 'Mandarín', 1),
(5, 'Japones', 1),
(6, 'Latin', 1);

-- --------------------------------------------------------

--
-- Table structure for table `idiomasprofesor`
--

DROP TABLE IF EXISTS `idiomasprofesor`;
CREATE TABLE IF NOT EXISTS `idiomasprofesor` (
  `identificacion` varchar(20) NOT NULL,
  `idIdioma` int(11) NOT NULL,
  KEY `FK_IDIPRO_PER_idx` (`identificacion`),
  KEY `FK_IDIPRO_IDI_idx` (`idIdioma`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `idiomasprofesor`
--

INSERT INTO `idiomasprofesor` (`identificacion`, `idIdioma`) VALUES
('504120597', 0),
('504120597', 2),
('504120597', 0),
('222222222', 2),
('333333333', 1),
('333333333', 2),
('333333333', 3),
('111111111', 1),
('111111111', 2);

-- --------------------------------------------------------

--
-- Table structure for table `matriculas`
--

DROP TABLE IF EXISTS `matriculas`;
CREATE TABLE IF NOT EXISTS `matriculas` (
  `idMatriculas` int(11) NOT NULL AUTO_INCREMENT,
  `idEstudiante` varchar(20) NOT NULL,
  `nombreGrupo` varchar(45) NOT NULL,
  `fechaDeMatricula` date NOT NULL,
  `fechaProxPago` date NOT NULL,
  PRIMARY KEY (`idMatriculas`),
  KEY `idEstudianteMatriculas_idx` (`idEstudiante`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `matriculas`
--

INSERT INTO `matriculas` (`idMatriculas`, `idEstudiante`, `nombreGrupo`, `fechaDeMatricula`, `fechaProxPago`) VALUES
(6, '504120598', 'GRUPO 3', '2019-04-21', '2019-04-28'),
(11, '504120598', 'GRUPO 2', '2019-04-27', '2019-05-15'),
(12, '504120598', 'GRUPO 1', '2019-04-27', '2019-05-18'),
(15, '504120599', 'GRUPO 2', '2019-04-28', '2019-04-27'),
(18, '504120599', 'GRUPO 2', '2019-05-05', '2019-05-06');

-- --------------------------------------------------------

--
-- Table structure for table `pagos`
--

DROP TABLE IF EXISTS `pagos`;
CREATE TABLE IF NOT EXISTS `pagos` (
  `idEstudiante` varchar(20) NOT NULL,
  `numeroRecibo` varchar(20) NOT NULL,
  `observacion` varchar(100) DEFAULT NULL,
  `fechaDelPago` date NOT NULL,
  `fechaPagada` date NOT NULL,
  KEY `idEstudiante_idx` (`idEstudiante`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pagos`
--

INSERT INTO `pagos` (`idEstudiante`, `numeroRecibo`, `observacion`, `fechaDelPago`, `fechaPagada`) VALUES
('504120598', 'R001', 'NI', '2019-04-27', '2019-04-28'),
('504120598', 'R002', 'NI', '2019-04-27', '2019-05-15'),
('504120599', '001', 'Pagó', '2019-05-05', '2019-04-27'),
('504120598', 'R003', 'Pagó atrasado', '2019-05-05', '2019-05-18');

-- --------------------------------------------------------

--
-- Table structure for table `permisos`
--

DROP TABLE IF EXISTS `permisos`;
CREATE TABLE IF NOT EXISTS `permisos` (
  `idPermiso` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(500) DEFAULT NULL,
  `matricular` int(11) DEFAULT NULL,
  `agregarCurso` int(11) DEFAULT NULL,
  `agregarGrupo` int(11) DEFAULT NULL,
  `agregarProfesor` int(11) DEFAULT NULL,
  `agregarEstudiante` int(11) DEFAULT NULL,
  `mantenimientoCurso` int(11) DEFAULT NULL,
  `mantenimientoGrupo` int(11) DEFAULT NULL,
  `mantenimientoProfesor` int(11) DEFAULT NULL,
  `mantenimientoEstudiante` int(11) DEFAULT NULL,
  `mantenimientoSistema` int(11) DEFAULT NULL,
  PRIMARY KEY (`idPermiso`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `permisos`
--

INSERT INTO `permisos` (`idPermiso`, `descripcion`, `matricular`, `agregarCurso`, `agregarGrupo`, `agregarProfesor`, `agregarEstudiante`, `mantenimientoCurso`, `mantenimientoGrupo`, `mantenimientoProfesor`, `mantenimientoEstudiante`, `mantenimientoSistema`) VALUES
(1, 'Estudiante', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(2, 'Profesor', 0, 0, 0, 0, 0, 0, 0, 0, 0, 0),
(3, 'Adminidstrativo', 0, 1, 1, 1, 1, 1, 1, 1, 1, 0),
(4, 'Gerente', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(5, 'Desarrolladora', 0, 1, 1, 1, 1, 1, 1, 1, 1, 1),
(9, 'Destriuir', 1, 0, 0, 0, 1, 0, 0, 0, 0, 0),
(10, 'Boom BOM BOOOM', 0, 0, 0, 0, 0, 0, 0, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;
CREATE TABLE IF NOT EXISTS `personas` (
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
  KEY `FK_PER_GEN_idx` (`genero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `personas`
--

INSERT INTO `personas` (`identificacion`, `nombre`, `apellido1`, `apellido2`, `telefono`, `genero`, `direccion`, `fechaNacimiento`, `correo`, `contraseña`, `idTipoPersona`, `Habilitado`) VALUES
('1', 'David Rodríguez', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '1', 5, 1),
('111111111', 'Marisela', 'Montero', 'Varela', 88888888, 2, 'Tilarán', '18/03/1976', 'mariselamv@gmail.com', NULL, 2, 1),
('2', 'Jorge Arguedas', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '22', 5, 1),
('222222222', 'Orlando', 'Monge', 'Elizondo', 888888888, 1, 'Tilarán, El Dos', '28/02/1984', 'orlando@gmail.com', NULL, 2, 1),
('3', 'Ana Sanchez', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '3', 3, 1),
('333333333', 'Marlon', 'Monge', 'Montero', 888888888, 1, 'Tilarán, El Dos', '12/12/1997', 'marlon@hotmail.com', NULL, 2, 1),
('5', 'Jose Calvo Tiger', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '5', 3, 1),
('504120597', 'Melany', 'Monge', 'Montero', 85985075, 2, 'Alajuela', '26/09/1996', 'melany9674@hotmail.com', NULL, 2, 1),
('504120598', 'Michelles', 'Ugarte', 'Monge', 44444444, 1, 'Alajuela', '01/03/2011', 'michelle@hotmail.com', NULL, 1, 1),
('504120599', 'Christopher', 'Ugarte', 'Vega', 88888888, 2, 'Turrialba', '01/03/2002', 'Christopher@gmail.com', NULL, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `requisitoscursos`
--

DROP TABLE IF EXISTS `requisitoscursos`;
CREATE TABLE IF NOT EXISTS `requisitoscursos` (
  `idCursoAgregado` varchar(10) NOT NULL,
  `idCursoRequisito` varchar(10) NOT NULL,
  KEY `idCursoAgregado_idx` (`idCursoAgregado`),
  KEY `idCursoRequisito_idx` (`idCursoRequisito`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `requisitoscursos`
--

INSERT INTO `requisitoscursos` (`idCursoAgregado`, `idCursoRequisito`) VALUES
('C003', 'C001'),
('C003', 'C002'),
('C002', 'C001');

-- --------------------------------------------------------

--
-- Table structure for table `tipopersonas`
--

DROP TABLE IF EXISTS `tipopersonas`;
CREATE TABLE IF NOT EXISTS `tipopersonas` (
  `idTipoPersonas` int(11) NOT NULL AUTO_INCREMENT,
  `rol` varchar(45) DEFAULT NULL,
  `idPermiso` int(11) DEFAULT NULL,
  PRIMARY KEY (`idTipoPersonas`),
  KEY `FK_PER_TIPERS_idx` (`idTipoPersonas`),
  KEY `FK_Permisos_idx` (`idPermiso`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tipopersonas`
--

INSERT INTO `tipopersonas` (`idTipoPersonas`, `rol`, `idPermiso`) VALUES
(1, 'Estudiante', NULL),
(2, 'Profesor', NULL),
(3, 'Adminidstrativo', 3),
(4, 'Gerente', 4),
(5, 'Desarrolladora', 5);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_estudiantes`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `vista_estudiantes`;
CREATE TABLE IF NOT EXISTS `vista_estudiantes` (
`identificacion` varchar(20)
,`nombre` varchar(45)
,`apellido1` varchar(45)
,`apellido2` varchar(45)
,`telefono` int(9)
,`genero` int(11)
,`direccion` varchar(500)
,`fechaNacimiento` varchar(45)
,`correo` varchar(45)
,`contraseña` varchar(45)
,`idTipoPersona` int(11)
,`Habilitado` int(1)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_mantenimientocursos`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `vista_mantenimientocursos`;
CREATE TABLE IF NOT EXISTS `vista_mantenimientocursos` (
`idcurso` varchar(10)
,`nombreCurso` varchar(45)
,`idIdioma` int(11)
,`estado` int(1)
,`nombreIdioma` varchar(45)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_mantenimientogrupos`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `vista_mantenimientogrupos`;
CREATE TABLE IF NOT EXISTS `vista_mantenimientogrupos` (
`idgrupos` varchar(10)
,`nombreGrupo` varchar(45)
,`idcurso` varchar(10)
,`idProfesor` varchar(20)
,`horaInicio` varchar(45)
,`estado` int(1)
,`dia` varchar(15)
,`nombreProfesor` varchar(45)
,`apellido1` varchar(45)
,`apellido2` varchar(45)
,`nombreCurso` varchar(45)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_matriculas`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `vista_matriculas`;
CREATE TABLE IF NOT EXISTS `vista_matriculas` (
`idEstudiante` varchar(20)
,`idcurso` varchar(10)
,`nombrecurso` varchar(45)
,`nombreIdioma` varchar(45)
,`nombreGrupo` varchar(45)
,`nombreProfesor` varchar(45)
,`apellido1` varchar(45)
,`apellido2` varchar(45)
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_pagos`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `vista_pagos`;
CREATE TABLE IF NOT EXISTS `vista_pagos` (
`idEstudiante` varchar(20)
,`nombre` varchar(45)
,`apellido1` varchar(45)
,`apellido2` varchar(45)
,`numeroRecibo` varchar(20)
,`observacion` varchar(100)
,`fechaDelPago` date
,`fechaPagada` date
);

-- --------------------------------------------------------

--
-- Stand-in structure for view `vista_profesores`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `vista_profesores`;
CREATE TABLE IF NOT EXISTS `vista_profesores` (
`identificacion` varchar(20)
,`nombre` varchar(45)
,`apellido1` varchar(45)
,`apellido2` varchar(45)
,`telefono` int(9)
,`genero` int(11)
,`direccion` varchar(500)
,`fechaNacimiento` varchar(45)
,`correo` varchar(45)
,`contraseña` varchar(45)
,`idTipoPersona` int(11)
,`Habilitado` int(1)
);

-- --------------------------------------------------------

--
-- Structure for view `vista_estudiantes`
--
DROP TABLE IF EXISTS `vista_estudiantes`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_estudiantes`  AS  select `personas`.`identificacion` AS `identificacion`,`personas`.`nombre` AS `nombre`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`personas`.`telefono` AS `telefono`,`personas`.`genero` AS `genero`,`personas`.`direccion` AS `direccion`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`correo` AS `correo`,`personas`.`contraseña` AS `contraseña`,`personas`.`idTipoPersona` AS `idTipoPersona`,`personas`.`Habilitado` AS `Habilitado` from `personas` where (`personas`.`idTipoPersona` = 1) ;

-- --------------------------------------------------------

--
-- Structure for view `vista_mantenimientocursos`
--
DROP TABLE IF EXISTS `vista_mantenimientocursos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_mantenimientocursos`  AS  select `cursos`.`idcurso` AS `idcurso`,`cursos`.`nombre` AS `nombreCurso`,`cursos`.`idIdioma` AS `idIdioma`,`cursos`.`estado` AS `estado`,`idiomas`.`nombre` AS `nombreIdioma` from (`cursos` join `idiomas`) where (`cursos`.`idIdioma` = `idiomas`.`idIdioma`) ;

-- --------------------------------------------------------

--
-- Structure for view `vista_mantenimientogrupos`
--
DROP TABLE IF EXISTS `vista_mantenimientogrupos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_mantenimientogrupos`  AS  select `grupos`.`idgrupos` AS `idgrupos`,`grupos`.`nombre` AS `nombreGrupo`,`grupos`.`idcurso` AS `idcurso`,`grupos`.`idProfesor` AS `idProfesor`,`grupos`.`horaInicio` AS `horaInicio`,`grupos`.`estado` AS `estado`,`grupos`.`dia` AS `dia`,`personas`.`nombre` AS `nombreProfesor`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`cursos`.`nombre` AS `nombreCurso` from ((`grupos` join `personas`) join `cursos`) where ((`grupos`.`idcurso` = `cursos`.`idcurso`) and (`grupos`.`idProfesor` = `personas`.`identificacion`)) ;

-- --------------------------------------------------------

--
-- Structure for view `vista_matriculas`
--
DROP TABLE IF EXISTS `vista_matriculas`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_matriculas`  AS  select `matriculas`.`idEstudiante` AS `idEstudiante`,`cursos`.`idcurso` AS `idcurso`,`cursos`.`nombre` AS `nombrecurso`,`idiomas`.`nombre` AS `nombreIdioma`,`grupos`.`nombre` AS `nombreGrupo`,`personas`.`nombre` AS `nombreProfesor`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2` from ((((`matriculas` join `grupos`) join `cursos`) join `idiomas`) join `personas`) where ((`matriculas`.`nombreGrupo` = `grupos`.`nombre`) and (`grupos`.`idcurso` = `cursos`.`idcurso`) and (`cursos`.`idIdioma` = `idiomas`.`idIdioma`) and (`grupos`.`idProfesor` = `personas`.`identificacion`)) ;

-- --------------------------------------------------------

--
-- Structure for view `vista_pagos`
--
DROP TABLE IF EXISTS `vista_pagos`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_pagos`  AS  select `pagos`.`idEstudiante` AS `idEstudiante`,`personas`.`nombre` AS `nombre`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`pagos`.`numeroRecibo` AS `numeroRecibo`,`pagos`.`observacion` AS `observacion`,`pagos`.`fechaDelPago` AS `fechaDelPago`,`pagos`.`fechaPagada` AS `fechaPagada` from (`pagos` join `personas`) where (`pagos`.`idEstudiante` = `personas`.`identificacion`) ;

-- --------------------------------------------------------

--
-- Structure for view `vista_profesores`
--
DROP TABLE IF EXISTS `vista_profesores`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `vista_profesores`  AS  select `personas`.`identificacion` AS `identificacion`,`personas`.`nombre` AS `nombre`,`personas`.`apellido1` AS `apellido1`,`personas`.`apellido2` AS `apellido2`,`personas`.`telefono` AS `telefono`,`personas`.`genero` AS `genero`,`personas`.`direccion` AS `direccion`,`personas`.`fechaNacimiento` AS `fechaNacimiento`,`personas`.`correo` AS `correo`,`personas`.`contraseña` AS `contraseña`,`personas`.`idTipoPersona` AS `idTipoPersona`,`personas`.`Habilitado` AS `Habilitado` from `personas` where (`personas`.`idTipoPersona` = 2) ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cursos`
--
ALTER TABLE `cursos`
  ADD CONSTRAINT `idIdioma` FOREIGN KEY (`idIdioma`) REFERENCES `idiomas` (`idIdioma`);

--
-- Constraints for table `grupos`
--
ALTER TABLE `grupos`
  ADD CONSTRAINT `idProfesor` FOREIGN KEY (`idProfesor`) REFERENCES `personas` (`identificacion`),
  ADD CONSTRAINT `idcurso` FOREIGN KEY (`idcurso`) REFERENCES `cursos` (`idcurso`);

--
-- Constraints for table `idiomasprofesor`
--
ALTER TABLE `idiomasprofesor`
  ADD CONSTRAINT `FK_IDIPRO_PER` FOREIGN KEY (`identificacion`) REFERENCES `personas` (`identificacion`);

--
-- Constraints for table `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `idEstudianteMatriculas` FOREIGN KEY (`idEstudiante`) REFERENCES `personas` (`identificacion`);

--
-- Constraints for table `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `idEstudiante` FOREIGN KEY (`idEstudiante`) REFERENCES `personas` (`identificacion`);

--
-- Constraints for table `personas`
--
ALTER TABLE `personas`
  ADD CONSTRAINT `FK_GENER` FOREIGN KEY (`genero`) REFERENCES `generos` (`idGenero`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `FK_Tipo_Persona` FOREIGN KEY (`idTipoPersona`) REFERENCES `tipopersonas` (`idTipoPersonas`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `requisitoscursos`
--
ALTER TABLE `requisitoscursos`
  ADD CONSTRAINT `idCursoAgregado` FOREIGN KEY (`idCursoAgregado`) REFERENCES `cursos` (`idcurso`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `idCursoRequisito` FOREIGN KEY (`idCursoRequisito`) REFERENCES `cursos` (`idcurso`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tipopersonas`
--
ALTER TABLE `tipopersonas`
  ADD CONSTRAINT `FK_Permisos` FOREIGN KEY (`idPermiso`) REFERENCES `permisos` (`idPermiso`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
