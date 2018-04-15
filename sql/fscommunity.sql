/*
Navicat MySQL Data Transfer

Source Server         : home
Source Server Version : 50637
Source Host           : 192.168.2.207:3306
Source Database       : fscommunity

Target Server Type    : MYSQL
Target Server Version : 50637
File Encoding         : 65001

Date: 2018-03-28 16:51:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `name` varchar(25) DEFAULT NULL,
  `type` varchar(11) DEFAULT NULL,
  `content` text,
  `publish_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES ('1', '1', 'asfe', 'df', 'dasgrqar', '2018-03-28 11:35:31', '2018-03-28 11:35:36');
INSERT INTO `article` VALUES ('2', '1', 'gwa', 'fed', '我是tester', '2018-03-21 11:38:31', '2018-03-22 11:38:35');

-- ----------------------------
-- Table structure for gift
-- ----------------------------
DROP TABLE IF EXISTS `gift`;
CREATE TABLE `gift` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pic` varchar(255) DEFAULT NULL COMMENT '图像url',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `content` varchar(255) DEFAULT NULL COMMENT '礼品描述',
  `available` int(11) DEFAULT NULL COMMENT '礼品剩余数量',
  `total` int(11) DEFAULT NULL COMMENT '礼品总数',
  `puton_time` datetime DEFAULT NULL COMMENT '上架时间',
  `pulloff_time` datetime DEFAULT NULL COMMENT '下架时间',
  `pay_method` int(5) DEFAULT NULL COMMENT '支付方式：0积分，1金币',
  `cost` decimal(10,2) DEFAULT NULL COMMENT '兑换积分/金币数量',
  `is_pulloffed` int(255) DEFAULT NULL COMMENT '是否下架：0已下架，1未下架',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='礼品';

-- ----------------------------
-- Records of gift
-- ----------------------------
INSERT INTO `gift` VALUES ('1', null, 'afger', 'fdsahgtr', '32', '3234', '2018-03-21 14:58:20', '2018-03-31 14:58:24', '0', '32.00', '0');
INSERT INTO `gift` VALUES ('2', null, 'dsa', null, '4', null, null, null, null, null, null);

-- ----------------------------
-- Table structure for gift_exch_info
-- ----------------------------
DROP TABLE IF EXISTS `gift_exch_info`;
CREATE TABLE `gift_exch_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gift_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `user_name` varchar(15) DEFAULT NULL,
  `apply_time` datetime DEFAULT NULL,
  `exch_sum` int(5) DEFAULT NULL,
  `exch_state` int(5) DEFAULT NULL COMMENT '兑换状态：0已兑换，1未兑换',
  `obtain_time` datetime DEFAULT NULL COMMENT '实现兑换日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of gift_exch_info
-- ----------------------------
INSERT INTO `gift_exch_info` VALUES ('1', '1', '1', 'grs', '2018-03-21 11:36:57', null, '0', null);
INSERT INTO `gift_exch_info` VALUES ('2', '1', '2', 'we', '2018-03-29 15:10:30', null, null, null);

-- ----------------------------
-- Table structure for im
-- ----------------------------
DROP TABLE IF EXISTS `im`;
CREATE TABLE `im` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) DEFAULT NULL COMMENT '”private”or“sys“，"helper"（客服）',
  `content` varchar(255) DEFAULT NULL COMMENT '消息内容',
  `send_uid` int(11) DEFAULT NULL COMMENT '发送者id',
  `recv_uid` int(11) DEFAULT NULL COMMENT '接收者id',
  `is_admin` varchar(255) DEFAULT NULL COMMENT '是否是系统管理员发送，0表示不是，1是。默认为0',
  `createtime` datetime DEFAULT NULL COMMENT '消息产生时间',
  `has_read` varchar(255) DEFAULT NULL COMMENT '是否被阅读，1已阅，0没有',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息通知';

-- ----------------------------
-- Records of im
-- ----------------------------

-- ----------------------------
-- Table structure for op_log
-- ----------------------------
DROP TABLE IF EXISTS `op_log`;
CREATE TABLE `op_log` (
  `op_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '操作员名称',
  `target_id` int(11) DEFAULT NULL COMMENT '被操作对象id',
  `target_type` varchar(255) DEFAULT NULL COMMENT '被操作对象类型',
  `op_type` varchar(255) DEFAULT NULL COMMENT '操作类型',
  `op_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`op_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='管理员操作记录';

-- ----------------------------
-- Records of op_log
-- ----------------------------

