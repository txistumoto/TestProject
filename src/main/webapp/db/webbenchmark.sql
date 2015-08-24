-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.6.16 - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             9.1.0.4867
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para webbenchmark
-- DROP DATABASE IF EXISTS `webbenchmark`;
-- CREATE DATABASE IF NOT EXISTS `webbenchmark` 
CREATE DATABASE IF NOT EXISTS `webbenchmark` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `webbenchmark`;

-- Volcando estructura para tabla webbenchmark.webs
-- DROP TABLE IF EXISTS `webs`;
CREATE TABLE IF NOT EXISTS `webs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `url` varchar(45) NOT NULL,
  `usability` decimal(10,2) DEFAULT NULL,
  `accessibility` decimal(10,2) DEFAULT NULL,
  `security`decimal(10,2) DEFAULT NULL,
  `functionality` decimal(10,2) DEFAULT NULL,
  `architecture_and_design` decimal(10,2) DEFAULT NULL,
  `html_standard` decimal(10,2) DEFAULT NULL,
  `other_requirements` decimal(10,2) DEFAULT NULL,
  `capacity` decimal(10,2) DEFAULT NULL,
  `availability` decimal(10,2) DEFAULT NULL,
  `credibility` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Volcando estructura para disparador webbenchmark.webs_BINS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `webs_BINS` BEFORE INSERT ON `webs` FOR EACH ROW BEGIN
SET NEW.update_date =  NOW();
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Volcando estructura para disparador webbenchmark.webs_BUPD
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `webs_BUPD` BEFORE UPDATE ON `webs` FOR EACH ROW BEGIN
SET NEW.update_date =  NOW();
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- Volcando datos para la tabla webbenchmark.webs: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `webs` DISABLE KEYS */;
INSERT INTO `webs` (`id`, `url`, `usability`, `accessibility`, `security`, `functionality`, `architecture_and_design`, `html_standard`, `other_requirements`, `capacity`, `availability`, `credibility`, `total`, `creation_date`, `update_date`) VALUES
	(1, 'http://www.sqs.es/', 70.00, 68.75, 100.00, 57.14, 66.67, 58.33, 100.00, 58.33, 100.00, 100.00, 68.94, current_timestamp, current_timestamp),
	(2, 'http://www.carsa.es/', 68.75, 65.63, 66.67, 75.00, 66.67, 75.00, 100.00, 83.33, 50.00, 77.78, 70.55, current_timestamp, current_timestamp),
	(3, 'http://www.cbt.es/', 72.62, 69.44, 25.00, 62.50, 63.64, 64.29, 60.00, 66.67, 0.00, 81.82, 68.39, current_timestamp, current_timestamp),
	(4, 'http://www.trimek.com/', 67.11, 61.11, 50.00, 80.00, 58.33, 84.62, 60.00, 25.00, 0.00, 77.78, 63.86, current_timestamp, current_timestamp),
	(5, 'http://www.datapixel.com/', 73.86, 78.38, 60.00, 66.67, 80.00, 76.92, 20.00, 33.33, 100.00, 100.00, 70.72, current_timestamp, current_timestamp),
	(6, 'http://www.innovalia.com/', 68.42, 77.14, 0.00, 83.33, 44.44, 50.00, 100.00, 100.00, 50.00, 100.00, 70.44, current_timestamp, current_timestamp);
/*!40000 ALTER TABLE `webs` ENABLE KEYS */;
