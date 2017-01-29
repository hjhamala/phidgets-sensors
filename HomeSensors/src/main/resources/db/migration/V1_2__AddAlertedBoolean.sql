ALTER TABLE `sensors`.`sensor` 
ADD COLUMN `alerted` TINYINT NULL DEFAULT 0 AFTER `alert_max`;
