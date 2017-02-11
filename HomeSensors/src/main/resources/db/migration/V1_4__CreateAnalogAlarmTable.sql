CREATE TABLE `sensors`.`analog_alarm` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `alert_min` INT NULL,
  `alert_max` INT NULL,
  `alerted` TINYINT NULL,
  `email_sent` TINYINT NULL,
  `sensor_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_analog_alarm_1_idx` (`sensor_id` ASC),
  CONSTRAINT `fk_analog_alarm_1`
    FOREIGN KEY (`sensor_id`)
    REFERENCES `sensors`.`sensor` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO analog_alarm(alert_min,alert_max,sensor_id,alerted) 
	SELECT alert_min,alert_max,id,alerted FROM sensor;

ALTER TABLE `sensors`.`sensor` 
DROP COLUMN `alerted`,
DROP COLUMN `alert_max`,
DROP COLUMN `alert_min`;

