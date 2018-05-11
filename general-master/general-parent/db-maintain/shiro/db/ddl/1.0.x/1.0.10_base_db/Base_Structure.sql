-- ----------------------------
-- Table structure for p_permissions
-- ----------------------------
DROP TABLE IF EXISTS `p_permissions`;
CREATE TABLE `p_permissions` (
  `p_permissions_id` varchar(40) NOT NULL COMMENT '主键',
  `permission` varchar(100) NOT NULL COMMENT '权限字符串',
  `app_key` varchar(10) NOT NULL COMMENT '根据app_key验证不同APP的权限',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`p_permissions_id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for r_roles
-- ----------------------------
DROP TABLE IF EXISTS `r_roles`;
CREATE TABLE `r_roles` (
  `r_roles_id` varchar(40) NOT NULL COMMENT '主键',
  `role_name` varchar(200) NOT NULL COMMENT '角色名称',
  `app_key` varchar(10) NOT NULL COMMENT '根据app_key验证不同APP的权限',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`r_roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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

-- ----------------------------
-- Table structure for u_users
-- ----------------------------
DROP TABLE IF EXISTS `u_users`;
CREATE TABLE `u_users` (
  `u_users_id` varchar(40) NOT NULL COMMENT '主键',
  `username` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '密钥',
  `surname` varchar(200) NOT NULL COMMENT '姓名',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `app_key` varchar(10) NOT NULL COMMENT '根据app_key验证不同APP的权限',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  `id_card` varchar(20) DEFAULT NULL COMMENT '身份证号码',
  `employee_id` varchar(40) DEFAULT NULL COMMENT '工号',
  `type` varchar(2) DEFAULT NULL COMMENT '用户类型',
  PRIMARY KEY (`u_users_id`,`surname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for ur_users_roles
-- ----------------------------
DROP TABLE IF EXISTS `ur_users_roles`;
CREATE TABLE `ur_users_roles` (
  `ur_users_roles_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '主键',
  `u_users_id` varchar(40) NOT NULL DEFAULT '0' COMMENT '外键用户id',
  `r_roles_id` varchar(40) NOT NULL COMMENT '外键角色id',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`ur_users_roles_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
