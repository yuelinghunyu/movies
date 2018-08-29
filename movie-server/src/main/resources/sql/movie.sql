/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : movies

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-08-29 09:20:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` varchar(255) NOT NULL COMMENT 'movie的id',
  `area` int(11) DEFAULT NULL COMMENT '地域；1-大陆，2-欧美，3-日韩，4-泰国',
  `pic_url` varchar(1000) DEFAULT NULL COMMENT '视频图片的地址',
  `content` varchar(500) DEFAULT NULL COMMENT '视频的地址',
  `description` text COMMENT '视频描述',
  `title` varchar(255) DEFAULT NULL COMMENT '视频标题',
  `type` int(11) DEFAULT NULL COMMENT '1、史诗，2、科幻，3、剧情，4、惊悚，5、武侠，6、伦理，7、其他',
  `price` decimal(10,2) DEFAULT NULL,
  `count` int(11) DEFAULT '0' COMMENT '购买数量',
  `movie_type` int(11) DEFAULT '3' COMMENT '1、hot，2、classical，3、new',
  `is_free` int(11) DEFAULT '0' COMMENT '1、免费，0-不免费',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `modify_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('227a701c49fc49ca9f1a2c0d03c7f653', '2', 'http://yuelinghunyu.oss-cn-beijing.aliyuncs.com/data/1531381674951.jpg?Expires=1846741675&OSSAccessKeyId=LTAIuw9k1cverkbk&Signature=G6Zf9U/5tzaDZjWoG62if5BlcBY=', '链接: https://pan.baidu.com/s/1KJr_qSsX2s3HtFvMiOhO9Q 密码: tqfj', '摸都疯猿', '摸都疯猿', '2', null, '0', '1', '1', '2018-07-12 15:49:42', '2018-07-12 15:49:42');
