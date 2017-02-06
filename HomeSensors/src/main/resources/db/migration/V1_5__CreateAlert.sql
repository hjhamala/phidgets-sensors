ALTER TABLE `sensors`.`analog_alarm` 
DROP COLUMN `email_sent`,
DROP COLUMN `alerted`;

CREATE TABLE `sensors`.`alert` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `analog_alarm_id` INT NULL,
  `date_time` DATETIME NULL,
  `value` INT NULL,
  `email_sent` TINYINT NULL, 
  PRIMARY KEY (`id`),
  INDEX `fk_alert_1_idx` (`analog_alarm_id` ASC),
  CONSTRAINT `fk_alert_1`
    FOREIGN KEY (`analog_alarm_id`)
    REFERENCES `sensors`.`analog_alarm` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
