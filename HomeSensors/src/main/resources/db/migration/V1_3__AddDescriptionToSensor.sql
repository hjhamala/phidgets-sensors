ALTER TABLE `sensors`.`sensor` 
ADD COLUMN `description` VARCHAR(60) NULL AFTER `alert_max`;
