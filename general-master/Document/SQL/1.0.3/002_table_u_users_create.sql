/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.193
Source Server Version : 50543
Source Host           : 192.168.2.193:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2015-12-28 13:19:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for u_users
-- ----------------------------
DROP TABLE IF EXISTS `u_users`;
CREATE TABLE `u_users` (
  `u_users_id` varchar(40) NOT NULL COMMENT '主键',
  `username` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '密钥',
  `surname` varchar(20) NOT NULL COMMENT '姓名',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `mobile` varchar(20) COMMENT '移动电话',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`u_users_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
