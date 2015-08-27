-- phpMyAdmin SQL Dump
-- version 4.4.13.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 27-08-2015 a las 12:02:18
-- Versión del servidor: 5.6.26
-- Versión de PHP: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;

--
-- Base de datos: `testproject`
--

CREATE DATABASE IF NOT EXISTS `testproject` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `testproject`;


-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apks`
--

CREATE TABLE IF NOT EXISTS `apks` (
  `id` int(11) NOT NULL,
  `file` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pack` varchar(50) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

--
-- Disparadores `apks`
--
DELIMITER $$
CREATE TRIGGER `apks_BINS` BEFORE INSERT ON `apks`
 FOR EACH ROW BEGIN
SET NEW.update_date =  NOW();
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `apks_BUPD` BEFORE UPDATE ON `apks`
 FOR EACH ROW BEGIN
SET NEW.update_date =  NOW();
END
$$
DELIMITER ;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `apks`
--
ALTER TABLE `apks`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `apks`
--
ALTER TABLE `apks`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
