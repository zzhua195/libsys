/*
Navicat MySQL Data Transfer

Source Server         : 本地Mysql
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : libsys

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2020-01-31 15:36:07
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bookCode` varchar(20) DEFAULT NULL COMMENT '编号',
  `bookName` varchar(20) DEFAULT NULL COMMENT '书名',
  `price` double(10,2) DEFAULT NULL COMMENT '价格',
  `author` varchar(10) DEFAULT NULL COMMENT '作者',
  `isBorrow` int(2) DEFAULT NULL COMMENT '0-未借;1-借出；',
  `type` int(2) DEFAULT NULL COMMENT '1-编程;2-小说;',
  `publishTime` date DEFAULT NULL COMMENT '出版时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'A001', 'java编程逻辑', '99.00', '马俊昌', '1', '1', '2020-01-07');
INSERT INTO `book` VALUES ('2', 'A002', 'java编程思想', '108.00', 'Eurk', '1', '1', '2020-01-12');
INSERT INTO `book` VALUES ('3', 'B001', 'blender建模', '54.30', 'zach', '0', '2', '2020-01-14');
INSERT INTO `book` VALUES ('4', 'B002', 'blender材质调节', '62.70', 'reihardt', '0', '2', '2020-01-05');
INSERT INTO `book` VALUES ('5', 'B003', 'blender渲染', '72.00', 'reihardt', '0', '2', '2020-01-06');
INSERT INTO `book` VALUES ('6', 'C001', 'C语言', '66.00', '谭浩强', '1', '3', '2020-01-12');
INSERT INTO `book` VALUES ('7', 'c002', '计算机网络', '77.00', '谢希仁', '1', '3', '2020-01-06');
INSERT INTO `book` VALUES ('8', 'c003', 'photoshop选择的艺术', '129.00', '关文涛', '1', '3', '2020-01-17');
INSERT INTO `book` VALUES ('9', 'c004', 'ps酷教程', '112.00', 'zzhua', '0', '3', '2020-01-06');

-- ----------------------------
-- Table structure for perm
-- ----------------------------
DROP TABLE IF EXISTS `perm`;
CREATE TABLE `perm` (
  `id` int(11) NOT NULL,
  `permCode` varchar(20) DEFAULT NULL,
  `permName` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of perm
-- ----------------------------
INSERT INTO `perm` VALUES ('1', 'book:list', '查询图书列表');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `roleName` varchar(10) DEFAULT NULL,
  `roleDesc` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '超级管理员', '管理员级别');
INSERT INTO `role` VALUES ('2', '普通用户', '用户级别');

-- ----------------------------
-- Table structure for role_perm
-- ----------------------------
DROP TABLE IF EXISTS `role_perm`;
CREATE TABLE `role_perm` (
  `rid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_perm
-- ----------------------------
INSERT INTO `role_perm` VALUES ('1', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) NOT NULL,
  `password` varchar(64) NOT NULL,
  `registerTime` datetime DEFAULT NULL,
  `lastLoginTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '0df61f955d8427f0eebd0ade7661ea9d', '2020-01-30 23:12:03', null);
INSERT INTO `user` VALUES ('2', 'zzhua', 'f01d880d15d5b3559d85a5f3b3c21ea1', '2020-01-31 00:31:29', null);

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
