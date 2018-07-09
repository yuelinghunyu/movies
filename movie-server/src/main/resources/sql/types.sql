/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : movies

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-09 19:14:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for types
-- ----------------------------
DROP TABLE IF EXISTS `types`;
CREATE TABLE `types` (
  `id` varchar(255) NOT NULL,
  `type` int(11) DEFAULT NULL COMMENT '1、史诗，2、科幻，3、剧情，4、惊悚，5、武侠，6、伦理，7、其他',
  `title` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of types
-- ----------------------------
INSERT INTO `types` VALUES ('71eedcce-8357-11e8-a8fa-00ff9269c067', '3', '剧情', '2018-07-09 17:06:37', '2018-07-09 18:51:56');
INSERT INTO `types` VALUES ('49ead424-8365-11e8-a8fa-00ff9269c067', '1', '史诗', '2018-07-09 18:45:43', '2018-07-09 18:50:50');
INSERT INTO `types` VALUES ('2073573c-8366-11e8-a8fa-00ff9269c067', '2', '玄幻', '2018-07-09 18:51:43', '2018-07-09 18:51:43');
INSERT INTO `types` VALUES ('32fe3103-8366-11e8-a8fa-00ff9269c067', '4', '惊悚', '2018-07-09 18:52:14', '2018-07-09 18:52:14');
INSERT INTO `types` VALUES ('385bbfe9-8366-11e8-a8fa-00ff9269c067', '5', '武侠', '2018-07-09 18:52:23', '2018-07-09 18:52:23');
INSERT INTO `types` VALUES ('3e766ecd-8366-11e8-a8fa-00ff9269c067', '6', '伦理', '2018-07-09 18:52:33', '2018-07-09 18:52:33');
INSERT INTO `types` VALUES ('782fe20d-8367-11e8-a8fa-00ff9269c067', '8', '其他', '2018-07-09 19:01:20', '2018-07-09 19:01:20');
INSERT INTO `types` VALUES ('9601e580-8367-11e8-a8fa-00ff9269c067', '7', '青春', '2018-07-09 19:02:10', '2018-07-09 19:02:10');
