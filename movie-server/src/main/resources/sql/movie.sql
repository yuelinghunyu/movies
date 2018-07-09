/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : movies

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2018-07-09 19:14:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` varchar(255) NOT NULL COMMENT 'movie的id',
  `area` int(11) DEFAULT NULL COMMENT '地域；1-大陆，2-欧美，3-日韩，4-泰国',
  `pic_url` varchar(500) DEFAULT NULL COMMENT '视频图片的地址',
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
INSERT INTO `movie` VALUES ('01507ddeb2fc4d359e1f5e61c738069e', '3', 'http://n.sinaimg.cn/ent/transform/487/w210h277/20180628/U-Sx-heqpwqy3257323.jpg', 'http://n.sinaimg.cn/ent/transform/487/w210h277/20180628/U-Sx-heqpwqy3257323.jpg', '一事无成的郑开司，参加了一场游轮上的神秘游戏来偿还巨款借款，游戏参与者要用“石头，剪刀，布”扑克为道具，夺取对手的星星标志。局中局、计中计', '动物世界', '1', '2.01', '4', '3', '0', '2018-07-05 16:58:16', '2018-07-05 16:58:16');
INSERT INTO `movie` VALUES ('549a52142dc446199e0086c5d40618d5', '3', 'http://n.sinaimg.cn/ent/transform/487/w210h277/20180628/U-Sx-heqpwqy3257323.jpg', 'http://n.sinaimg.cn/ent/transform/487/w210h277/20180628/U-Sx-heqpwqy3257323.jpg', '一事无成的郑开司，参加了一场游轮上的神秘游戏来偿还巨款借款，游戏参与者要用“石头，剪刀，布”扑克为道具，夺取对手的星星标志。局中局、计中计', '动物世界', '1', '2.01', '4', '3', '0', '2018-07-05 16:57:41', '2018-07-05 16:57:41');
INSERT INTO `movie` VALUES ('66ed9b9a1de54b12908aa6feed1ca0a2', '3', 'http://n.sinaimg.cn/ent/transform/487/w210h277/20180608/yUXU-hcscwxa6443859.jpg', 'http://n.sinaimg.cn/ent/transform/487/w210h277/20180608/yUXU-hcscwxa6443859.jpg', '知名画家创作的最后一幅作品“恶灵画像”后离奇死亡了，女设计师张琳和同伴早就听说过“恶灵画像”具有诅咒的传说-见者必死。强烈的猎奇心理驱使貌美性感', '姽婳', '1', '2.01', '4', '2', '0', '2018-07-05 15:01:45', '2018-07-05 15:01:45');
INSERT INTO `movie` VALUES ('gS1zzUusSB07nuiIExlUWcrPPyMnWKaf', '2', 'http://n.sinaimg.cn/ent/transform/487/w210h277/20180628/w4pJ-heqpwqy3656817.jpg', '12123', '《最后一球》的故事围绕着名为尤里的落魄球星和“流星队”这样一支无名的菜鸟球队展开，通过环环相扣的剧情以及激烈的矛盾冲突来体现主角个人和团队的成', '最后一球', '2', '5.00', '5', '2', '0', '2018-07-05 16:59:25', '2018-07-05 16:59:25');
INSERT INTO `movie` VALUES ('j3RYqctY4HCQ2HgMvzVHbwL0J3QvE3QE', '1', 'http://entdata-pic2.stor.sinaapp.com/2017061921/5947cef0237f1p2463041948_%E5%89%AF%E6%9C%AC.jpg', '1234', '根据真实社会事件改编，讲述了一位药店店主从印度代购治疗慢粒白血病的药获得极大利润，开始贩药敛财之道后良心发现的故事。', '我不是药神', '3', '2.00', '1', '2', '0', '2018-07-05 11:24:36', '2018-07-05 11:24:48');
