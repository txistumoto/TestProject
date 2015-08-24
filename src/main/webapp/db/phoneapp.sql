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

-- Volcando estructura de base de datos para phoneapp
-- DROP DATABASE IF EXISTS `phoneapp`;
-- CREATE DATABASE IF NOT EXISTS `phoneapp` 
CREATE DATABASE IF NOT EXISTS `phoneapp` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `phoneapp`;

-- Volcando estructura para tabla phoneapp.apks
-- DROP TABLE IF EXISTS `apks`;
CREATE TABLE IF NOT EXISTS `apks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `file` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `pack` varchar(50) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- Volcando estructura para disparador phoneapp.apks_BINS
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `apks_BINS` BEFORE INSERT ON `apks` FOR EACH ROW BEGIN
SET NEW.update_date =  NOW();
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;


-- Volcando estructura para disparador phoneapp.apks_BUPD
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `apks_BUPD` BEFORE UPDATE ON `apks` FOR EACH ROW BEGIN
SET NEW.update_date =  NOW();
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

-- Volcando datos para la tabla phoneapp.apks: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `apks` DISABLE KEYS */;
INSERT INTO `apks` (`id`, `file`, `name`, `pack`, `creation_date`, `update_date`) VALUES
	(1, 'PreReqAgile.apk', 'sqs.prereqagile', 'PreReqAgileActivity', current_timestamp, current_timestamp);
/*!40000 ALTER TABLE `apks` ENABLE KEYS */;
