/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : mso

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-11-03 11:12:47
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
INSERT INTO `areas` VALUES ('5f86008e-addd-11e8-8784-00ff9269c067', '13', '克罗地亚', '2018-09-01 19:51:25', '2018-09-01 19:51:25');
INSERT INTO `areas` VALUES ('60f375ef-835b-11e8-a8fa-00ff9269c067', '10', '加拿大', '2018-07-09 17:34:47', '2018-07-09 17:34:47');
INSERT INTO `areas` VALUES ('71fca1a6-835b-11e8-a8fa-00ff9269c067', '12', '新加坡', '2018-07-09 17:35:15', '2018-07-09 17:35:15');
INSERT INTO `areas` VALUES ('8ff7377b-80f2-11e8-a8fa-00ff9269c067', '6', '西班牙', '2018-07-06 15:59:43', '2018-07-06 15:59:43');
INSERT INTO `areas` VALUES ('9b298208-831d-11e8-a8fa-00ff9269c067', '7', '法国', '2018-07-09 10:12:35', '2018-07-09 10:12:35');
INSERT INTO `areas` VALUES ('dbb85c92-80f1-11e8-a8fa-00ff9269c067', '2', '美国', '2018-07-06 15:54:41', '2018-07-06 15:54:41');
INSERT INTO `areas` VALUES ('ebe6fc5d-8340-11e8-a8fa-00ff9269c067', '8', '阿拉伯', '2018-07-09 14:25:23', '2018-07-09 14:25:23');
INSERT INTO `areas` VALUES ('f34d4e9e-8342-11e8-a8fa-00ff9269c067', '9', '瑞士', '2018-07-09 14:39:55', '2018-07-09 14:39:55');
INSERT INTO `areas` VALUES ('f8a6107e-80f1-11e8-a8fa-00ff9269c067', '3', '日本', '2018-07-06 15:55:29', '2018-07-06 15:55:29');

-- ----------------------------
-- Table structure for banner
-- ----------------------------
DROP TABLE IF EXISTS `banner`;
CREATE TABLE `banner` (
  `id` varchar(255) NOT NULL COMMENT '轮播图id',
  `title` varchar(255) DEFAULT NULL COMMENT '轮播图title',
  `type` int(11) DEFAULT NULL COMMENT '轮播图类型：1：电影，2：小册',
  `href` varchar(255) DEFAULT NULL COMMENT '轮播图图片地址',
  `redirect` varchar(255) DEFAULT NULL COMMENT '录播图跳转地址',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of banner
-- ----------------------------

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
-- Records of blog
-- ----------------------------

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
  `introUrl` varchar(255) DEFAULT NULL COMMENT '小册介绍markdown地址',
  `book_type` int(11) DEFAULT NULL COMMENT '小册的类型',
  `price` decimal(10,0) DEFAULT NULL COMMENT '小册价格',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of books
-- ----------------------------

-- ----------------------------
-- Table structure for book_feedback
-- ----------------------------
DROP TABLE IF EXISTS `book_feedback`;
CREATE TABLE `book_feedback` (
  `id` varchar(255) NOT NULL,
  `feed_type` int(11) DEFAULT NULL COMMENT '一键反馈类型：1电影，2小册',
  `type` int(11) DEFAULT NULL COMMENT '1：功能，2：程序，3：其他',
  `content` text,
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of book_feedback
-- ----------------------------

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
-- Records of book_type
-- ----------------------------
INSERT INTO `book_type` VALUES ('f3837c79-df0a-11e8-b8fd-00ff9269c067', '1', '前端', '2018-11-03 09:50:32', '2018-11-03 09:50:32');

-- ----------------------------
-- Table structure for chapters
-- ----------------------------
DROP TABLE IF EXISTS `chapters`;
CREATE TABLE `chapters` (
  `id` varchar(255) NOT NULL,
  `title` varchar(255) DEFAULT NULL COMMENT '章节题目',
  `href` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL COMMENT '这章需要的阅读的时间',
  `browser` int(255) DEFAULT NULL COMMENT '浏览次数',
  `create_time` datetime DEFAULT NULL,
  `modify_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of chapters
-- ----------------------------

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
INSERT INTO `movie` VALUES ('3a0c0b40e99b43038b2f89056d1a99ae', '2', 'http://yuelinghunyu.oss-cn-beijing.aliyuncs.com/data/1535983972641.jpg?Expires=1851343972&OSSAccessKeyId=LTAIuw9k1cverkbk&Signature=JjPp/oWTsT0pww9ipF/qcpIQTnU=', '链接: https://pan.baidu.com/s/1vfwS1Kn8-hXVhSaRds3_yg 密码: wvfv', '科幻、机甲、动作、异种', '环太平洋', '1', '10.00', '0', '1', '0', '2018-09-03 22:13:28', '2018-09-03 22:13:28');
INSERT INTO `movie` VALUES ('96f9f36cb45d40c5a540b0bc904e1ecb', '2', 'http://yuelinghunyu.oss-cn-beijing.aliyuncs.com/data/1535984020474.jpg?Expires=1851344018&OSSAccessKeyId=LTAIuw9k1cverkbk&Signature=Trr9vgqnlnlvXFPVEgs9Hf3ucLw=', '链接: https://pan.baidu.com/s/11yPX4j6g2pfiaDlLI5Xb6g 密码: w6fk', '动物，喜剧', '神探狗笨吉', '7', '10.00', '0', '2', '0', '2018-09-03 22:14:52', '2018-09-03 22:14:52');

-- ----------------------------
-- Table structure for payers
-- ----------------------------
DROP TABLE IF EXISTS `payers`;
CREATE TABLE `payers` (
  `id` varchar(255) NOT NULL,
  `wechat_id` varchar(255) NOT NULL,
  `wechat_name` varchar(255) DEFAULT NULL,
  `logo` varchar(255) DEFAULT NULL,
  `books` varchar(255) DEFAULT NULL,
  `payDate` date DEFAULT NULL,
  PRIMARY KEY (`id`,`wechat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of payers
-- ----------------------------

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
-- Records of person
-- ----------------------------
INSERT INTO `person` VALUES ('4ab18ca799ef479bbf93ce2a8d08e435', 'djjiang', '8370b5d9ee114b7cfacd7512a14b9908', 'http://i0.sinaimg.cn/dy/slidenews/26_img/2018_17/18496_643212_606522.jpg', '2018-04-30 17:51:30');
INSERT INTO `person` VALUES ('fce80edd2ab44867a1a5445c71116315', 'xingxingfa', '6164287b6541761f7ccc308388e830fc', 'http://n.sinaimg.cn/ent/4_img/upload/c65c9414/67/w667h1000/20180430/qADA-fzvpatr5234547.jpg', '2018-04-30 17:54:12');

-- ----------------------------
-- Table structure for sales
-- ----------------------------
DROP TABLE IF EXISTS `sales`;
CREATE TABLE `sales` (
  `id` varchar(255) NOT NULL COMMENT '购买记录表id',
  `wechat_id` varchar(255) DEFAULT NULL COMMENT '用户的id',
  `movie_id` varchar(255) DEFAULT NULL COMMENT '电影id',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '购买时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of sales
-- ----------------------------

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
INSERT INTO `types` VALUES ('40e419ee-df0a-11e8-b8fd-00ff9269c067', '1', '史诗', '2018-11-03 09:45:32', '2018-11-03 09:45:32');

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

-- ----------------------------
-- Records of users
-- ----------------------------
