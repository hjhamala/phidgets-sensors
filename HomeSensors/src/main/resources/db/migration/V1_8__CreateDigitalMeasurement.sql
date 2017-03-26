CREATE TABLE IF NOT EXISTS `digital_measurement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `value` TINYINT(1) NOT NULL,
  `sensor_id` int(11) NOT NULL,
  `date_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_digital_measurement_1_idx` (`sensor_id`),
  CONSTRAINT `fk_fk_digital_measurement_1` FOREIGN KEY (`sensor_id`) REFERENCES `sensor` (`id`) ON DELETE CASCADE
ON UPDATE CASCADE 
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
