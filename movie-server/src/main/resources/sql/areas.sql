/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : movies

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-29 09:20:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for areas
-- ----------------------------
DROP TABLE IF EXISTS `areas`;
CREATE TABLE `areas` (
  `id` varchar(255) NOT NULL,
  `area` int(11) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of areas
-- ----------------------------
INSERT INTO `areas` VALUES ('0b76d581-80f2-11e8-a8fa-00ff9269c067', '4', '韩国', '2018-07-06 15:56:01', '2018-07-06 15:56:01');
INSERT INTO `areas` VALUES ('1d5a8245-8344-11e8-a8fa-00ff9269c067', '1', '中国', '2018-07-09 14:48:15', '2018-07-09 14:49:01');
INSERT INTO `areas` VALUES ('411ca7f7-80f2-11e8-a8fa-00ff9269c067', '5', '泰国', '2018-07-06 15:57:31', '2018-07-06 15:57:31');
INSERT INTO `areas` VALUES ('60f375ef-835b-11e8-a8fa-00ff9269c067', '10', '加拿大', '2018-07-09 17:34:47', '2018-07-09 17:34:47');
INSERT INTO `areas` VALUES ('71fca1a6-835b-11e8-a8fa-00ff9269c067', '12', '新加坡', '2018-07-09 17:35:15', '2018-07-09 17:35:15');
INSERT INTO `areas` VALUES ('8ff7377b-80f2-11e8-a8fa-00ff9269c067', '6', '西班牙', '2018-07-06 15:59:43', '2018-07-06 15:59:43');
INSERT INTO `areas` VALUES ('9b298208-831d-11e8-a8fa-00ff9269c067', '7', '法国', '2018-07-09 10:12:35', '2018-07-09 10:12:35');
INSERT INTO `areas` VALUES ('dbb85c92-80f1-11e8-a8fa-00ff9269c067', '2', '美国', '2018-07-06 15:54:41', '2018-07-06 15:54:41');
INSERT INTO `areas` VALUES ('ebe6fc5d-8340-11e8-a8fa-00ff9269c067', '8', '阿拉伯', '2018-07-09 14:25:23', '2018-07-09 14:25:23');
INSERT INTO `areas` VALUES ('f34d4e9e-8342-11e8-a8fa-00ff9269c067', '9', '瑞士', '2018-07-09 14:39:55', '2018-07-09 14:39:55');
INSERT INTO `areas` VALUES ('f8a6107e-80f1-11e8-a8fa-00ff9269c067', '3', '日本', '2018-07-06 15:55:29', '2018-07-06 15:55:29');
