/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.193
Source Server Version : 50543
Source Host           : 192.168.2.193:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2015-12-31 12:19:16
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for r_roles
-- ----------------------------
DROP TABLE IF EXISTS `r_roles`;
CREATE TABLE `r_roles` (
  `r_roles_id` varchar(40) NOT NULL COMMENT '主键',
  `role_name` varchar(200) NOT NULL COMMENT '角色名称',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`r_roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
