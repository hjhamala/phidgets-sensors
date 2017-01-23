ALTER TABLE `sensors`.`sensor` 
ADD COLUMN `active` TINYINT NOT NULL DEFAULT 0 AFTER `gain_factor`,
ADD COLUMN `alert_min` INT(11) NULL AFTER `active`,
ADD COLUMN `alert_max` INT(11) NULL AFTER `alert_min`;
