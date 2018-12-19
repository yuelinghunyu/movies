/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mso

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-12-17 19:04:55
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
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` varchar(255) NOT NULL COMMENT '轮播图id',
  `title` varchar(255) DEFAULT NULL COMMENT '轮播图title',
  `type` int(4) DEFAULT NULL COMMENT '轮播图类型：1：电影，2：小册',
  `href` varchar(255) DEFAULT NULL COMMENT '轮播图图片地址',
  `redirect` varchar(255) DEFAULT NULL COMMENT '录播图跳转地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `id` varchar(255) NOT NULL COMMENT '博客id',
  `title` varchar(255) DEFAULT '' COMMENT '博客名称',
  `blog_type` int(11) DEFAULT NULL COMMENT '博客类型',
  `href` varchar(255) DEFAULT '' COMMENT '博客地址',
  `times` int(255) DEFAULT NULL COMMENT '浏览次数',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for books
-- ----------------------------
DROP TABLE IF EXISTS `books`;
CREATE TABLE `books` (
  `id` varchar(255) NOT NULL COMMENT '小册的id',
  `title` varchar(255) DEFAULT NULL COMMENT '小册标题',
  `logo` varchar(255) DEFAULT NULL COMMENT '小册图标',
  `author` varchar(255) DEFAULT NULL COMMENT '小册作者',
  `description` text COMMENT '小册描述',
  `intro_url` varchar(255) DEFAULT NULL COMMENT '小册介绍markdown地址',
  `book_type` int(11) DEFAULT NULL COMMENT '小册的类型',
  `price` decimal(10,0) DEFAULT NULL COMMENT '小册价格',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for book_type
-- ----------------------------
DROP TABLE IF EXISTS `book_type`;
CREATE TABLE `book_type` (
  `id` varchar(256) NOT NULL,
  `type_id` int(11) DEFAULT NULL,
  `type_title` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for chapters
-- ----------------------------
DROP TABLE IF EXISTS `chapters`;
CREATE TABLE `chapters` (
  `id` varchar(255) NOT NULL,
  `book_id` varchar(255) NOT NULL COMMENT '所属小册的id',
  `book_title` varchar(255) DEFAULT NULL COMMENT '所属小册的名称',
  `title` varchar(255) DEFAULT NULL COMMENT '章节题目',
  `href` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL COMMENT '这章需要的阅读的时间',
  `browser` int(255) DEFAULT NULL COMMENT '浏览次数',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`,`book_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for feed_back
-- ----------------------------
DROP TABLE IF EXISTS `feed_back`;
CREATE TABLE `feed_back` (
  `id` varchar(255) NOT NULL,
  `wechat_id` varchar(255) DEFAULT NULL,
  `wechat_name` varchar(255) DEFAULT NULL,
  `feed_type` int(11) DEFAULT NULL COMMENT '一键反馈类型：1电影，2小册',
  `type` int(11) DEFAULT NULL COMMENT '1：功能，2：程序，3：其他',
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` varchar(255) NOT NULL COMMENT 'movie的id',
  `area` int(11) DEFAULT NULL COMMENT '地域；1-大陆，2-欧美，3-日韩，4-泰国',
  `pic_url` varchar(1000) DEFAULT NULL COMMENT '视频图片的地址',
  `content` varchar(500) DEFAULT NULL COMMENT '视频的地址',
  `actor` varchar(255) DEFAULT '' COMMENT '主演',
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
-- Table structure for payers
-- ----------------------------
DROP TABLE IF EXISTS `payers`;
CREATE TABLE `payers` (
  `id` varchar(255) NOT NULL,
  `wechat_id` varchar(255) DEFAULT NULL,
  `wechat_name` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `books` varchar(255) DEFAULT NULL,
  `payDate` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person` (
  `id` varchar(255) NOT NULL COMMENT '后台管理人员id',
  `user_name` varchar(255) DEFAULT NULL,
  `pass_word` varchar(255) DEFAULT NULL,
  `img` varchar(1000) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` varchar(255) NOT NULL COMMENT '用户id',
  `wechat_id` varchar(255) DEFAULT NULL COMMENT '用户微信id',
  `wechat_name` varchar(255) DEFAULT NULL COMMENT '用户微信名',
  `wechat_logo` varchar(255) DEFAULT NULL COMMENT '微信用户图像',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
