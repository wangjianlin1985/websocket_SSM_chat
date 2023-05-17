/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50505
Source Host           : 127.0.0.1:3306
Source Database       : ssm_sakila

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2020-01-08 19:18:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `staff`
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `staff_id` tinyint(3) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `last_name` varchar(45) CHARACTER SET utf8 NOT NULL,
  `address_id` smallint(5) unsigned NOT NULL,
  `picture` blob DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 DEFAULT NULL,
  `store_id` tinyint(3) unsigned NOT NULL,
  `active` tinyint(1) NOT NULL DEFAULT 1,
  `username` varchar(16) CHARACTER SET utf8 NOT NULL,
  `password` varchar(40) COLLATE utf8_bin DEFAULT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  PRIMARY KEY (`staff_id`),
  KEY `idx_fk_store_id` (`store_id`),
  KEY `idx_fk_address_id` (`address_id`),
  CONSTRAINT `fk_staff_address` FOREIGN KEY (`address_id`) REFERENCES `address` (`address_id`) ON UPDATE CASCADE,
  CONSTRAINT `fk_staff_store` FOREIGN KEY (`store_id`) REFERENCES `store` (`store_id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('1', 'Mike', 'Hillyer', '3', null, 'Mike.Hillyer@sakilastaff.com', '1', '1', 'xiaowang', '1234', '2019-08-22 13:46:53');
INSERT INTO `staff` VALUES ('2', 'Jon', 'Stephens', '4', null, 'Jon.Stephens@sakilastaff.com', '2', '1', 'yuanma', '1234', '2019-08-22 13:47:21');
INSERT INTO `staff` VALUES ('3', 'TOM', 'DSA', '3', null, 'DF', '1', '1', 'TOM', '1234', '2016-11-11 11:08:10');
