USE erpdb;

DROP TABLE IF EXISTS `user_info`;

CREATE TABLE `user_info` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `avatar` blob,
  `password` varchar(90) DEFAULT NULL,
  `pwd_salt` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `last_login_time` datetime DEFAULT NULL,
  `job_status` varchar(10) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_permission`;

CREATE TABLE `user_permission` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `name` varchar(90) DEFAULT NULL,
  `resource_type` varchar(10) DEFAULT NULL,
  `resource_url` varchar(90) DEFAULT NULL,
  `permission` varchar(120) DEFAULT NULL,
  `parent_id` int(9) DEFAULT NULL,
  `parent_ids` varchar(90) DEFAULT NULL,
  `is_available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `user_relation_info_permission`;

CREATE TABLE `user_relation_info_permission` (
  `user_id` int(9) NOT NULL,
  `user_permission_id` int(9) NOT NULL,
  PRIMARY KEY (`user_id`,`user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



DROP TABLE IF EXISTS `user_relation_info_role`;

CREATE TABLE `user_relation_info_role` (
  `user_id` int(9) NOT NULL,
  `user_role_id` int(9) NOT NULL,
  PRIMARY KEY (`user_id`,`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_relation_role_permission`;

CREATE TABLE `user_relation_role_permission` (
  `user_role_id` int(9) NOT NULL,
  `user_permission_id` int(9) NOT NULL,
  PRIMARY KEY (`user_role_id`,`user_permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `role` varchar(90) DEFAULT NULL,
  `description` text,
  `is_available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- updated on 2018-06-11  9:00:11 --

ALTER TABLE `erpdb`.`user_info`
ADD COLUMN `is_locked` TINYINT(1) NULL AFTER `job_status`;

-- updated on 2018-06-16  14:34:11 --

CREATE TABLE `erpdb`.`user_module` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `is_available` TINYINT(1) DEFAULT 0,
  PRIMARY KEY (`id`));

INSERT INTO `erpdb`.`user_module` (`id`, `name`) VALUES ('1', 'user');
INSERT INTO `erpdb`.`user_module` (`id`, `name`) VALUES ('2', 'inventory');
INSERT INTO `erpdb`.`user_module` (`id`, `name`) VALUES ('3', 'accountancy');
INSERT INTO `erpdb`.`user_module` (`id`, `name`) VALUES ('4', 'human resource');
INSERT INTO `erpdb`.`user_module` (`id`, `name`) VALUES ('5', 'message');
INSERT INTO `erpdb`.`user_module` (`id`, `name`) VALUES ('6', 'task');
INSERT INTO `erpdb`.`user_module` (`id`, `name`) VALUES ('101', 'admin');

ALTER TABLE `erpdb`.`user_permission`
DROP COLUMN `is_available`,
DROP COLUMN `parent_ids`,
DROP COLUMN `parent_id`,
DROP COLUMN `permission`,
DROP COLUMN `resource_url`,
CHANGE COLUMN `name` `module_id` INT(3) NULL DEFAULT NULL ,
CHANGE COLUMN `resource_type` `permission` VARCHAR(10) NULL DEFAULT NULL ;

ALTER TABLE `erpdb`.`user_permission`
ADD COLUMN `is_available` TINYINT(1) NULL DEFAULT 1 AFTER `permission`;


-- june 20 10:00 --

ALTER TABLE `erpdb`.`user_module`
CHANGE COLUMN `id` `id` INT(3) NOT NULL ;






