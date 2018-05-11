/*
Navicat MySQL Data Transfer

Source Server         : 192.168.2.193
Source Server Version : 50543
Source Host           : 192.168.2.193:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50543
File Encoding         : 65001

Date: 2015-12-28 13:30:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for rp_roles_permissions
-- ----------------------------
DROP TABLE IF EXISTS `rp_roles_permissions`;
CREATE TABLE `rp_roles_permissions` (
  `rp_roles_permissions_id` varchar(40) NOT NULL COMMENT '主键',
  `r_roles_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '外键角色id',
  `p_permissions_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '外键角色id',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`rp_roles_permissions_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
