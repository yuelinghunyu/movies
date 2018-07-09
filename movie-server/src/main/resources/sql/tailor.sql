/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : movies

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-09 19:14:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tailor
-- ----------------------------
DROP TABLE IF EXISTS `tailor`;
CREATE TABLE `tailor` (
  `id` varchar(255) NOT NULL COMMENT '定制id',
  `wechat_id` varchar(255) DEFAULT NULL,
  `area` tinyint(4) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `description` text,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of tailor
-- ----------------------------
