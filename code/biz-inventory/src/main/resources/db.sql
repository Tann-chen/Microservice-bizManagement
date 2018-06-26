CREATE TABLE `erpdb`.`inventory_commidity` (
  `id` INT(9) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(60) NULL,
  `commidity_type` VARCHAR(30) NULL,
  `quantity_unit` VARCHAR(30) NULL,
  `processing_period` INT(6) NULL,
  PRIMARY KEY (`id`))
  ENGINE=InnoDB;


  CREATE TABLE `erpdb`.`inventory_stock_in` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `batch_no` VARCHAR(30) NULL,
  `commidity_id` INT(9) NULL,
  `entry_time` DATETIME NULL,
  `receive_user` INT(9) NULL,
  PRIMARY KEY (`id`))
  ENGINE = Innodb;


  CREATE TABLE `erpdb`.`inventory_items` (
  `serial_id` INT(15) NOT NULL,
  `sku_no` VARCHAR(15) NULL,
  `commidity_id` INT(9) NULL,
  `stock_in_id` INT(11) NULL,
  `commidity_status` VARCHAR(30) NULL,
  `cost_per_item` DOUBLE(5,2) NULL,
  PRIMARY KEY (`serial_id`))
  ENGINE = InnoDB;

  ALTER TABLE `erpdb`.`inventory_items` RENAME TO  `erpdb`.`inventory_item` ;



  CREATE TABLE `erpdb`.`inventory_stock_out` (
  `id` INT(15) NOT NULL,
  `commidity_item_id` VARCHAR(45) NULL,
  `picked_time` DATETIME NULL,
  `picked_user` INT(9) NULL,
  `approved_user` INT(9) NULL,
  `purpose` VARCHAR(30) NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;

  ALTER TABLE `erpdb`.`inventory_commidity`
  RENAME TO  `erpdb`.`inventory_commodity` ;


  ALTER TABLE `erpdb`.`inventory_commodity`
  ADD COLUMN `is_available` TINYINT(1) NULL AFTER `processing_period`;

  ALTER TABLE `erpdb`.`inventory_item`
  ADD COLUMN `is_available` TINYINT(1) NULL AFTER `cost_per_item`;

  ALTER TABLE `erpdb`.`inventory_item`
  CHANGE COLUMN `commidity_status` `item_status` VARCHAR(30) NULL DEFAULT NULL ;









