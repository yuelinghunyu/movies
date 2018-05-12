/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : movies

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-04-30 20:58:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '后台管理人员id',
  `user_name` varchar(255) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('1', 'djjiang', 'Jdj12345', 'http://i0.sinaimg.cn/dy/slidenews/26_img/2018_17/18496_643212_606522.jpg', '2018-04-30 17:51:30');
INSERT INTO `person` VALUES ('2', 'xingxingfa', 'fxx12345', 'http://n.sinaimg.cn/ent/4_img/upload/c65c9414/67/w667h1000/20180430/qADA-fzvpatr5234547.jpg', '2018-04-30 17:54:12');
