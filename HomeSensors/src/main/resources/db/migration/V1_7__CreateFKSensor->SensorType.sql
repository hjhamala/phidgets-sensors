ALTER TABLE `sensors`.`sensor` 
ADD COLUMN `sensor_type_id` INT(11) NULL AFTER `description`;
-- Database should only have analog alarms;
UPDATE sensor set sensor.sensor_type_id = 1 where id > 0;
-- Add analog alarm
insert into sensor_type(id,description) values (1, "Phidgets analog alarm");
-- create fk dependency between sensor and sensor_type
ALTER TABLE `sensors`.`sensor` 
ADD INDEX `fk_sensor_1_idx` (`sensor_type_id` ASC);
ALTER TABLE `sensors`.`sensor` 
ADD CONSTRAINT `fk_sensor_1`
  FOREIGN KEY (`sensor_type_id`)
  REFERENCES `sensors`.`sensor_type` (`id`)
  ON DELETE CASCADE
  ON UPDATE CASCADE;

