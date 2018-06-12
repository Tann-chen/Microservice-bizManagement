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

