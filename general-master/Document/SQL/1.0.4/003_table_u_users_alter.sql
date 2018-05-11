START TRANSACTION;



-- 创建和原表相同的临时表保存原表数据
DROP TABLE  IF EXISTS `tmp_u_users`;
CREATE TABLE `tmp_u_users` (
  `u_users_id` varchar(40) NOT NULL COMMENT '主键',
  `username` varchar(200) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `salt` varchar(100) NOT NULL COMMENT '密钥',
  `surname` varchar(200) NOT NULL COMMENT '姓名',
  `email` varchar(100) NOT NULL COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`u_users_id`,`surname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





-- 将原表数据保存到临时表中
SELECT 'Begin to copy orignal talbe data to tmp table...' AS '';
INSERT INTO tmp_u_users(
	  `u_users_id`,
	  `username`,
	  `password`,
	  `salt`,
	  `surname`,
	  `email`,
	  `mobile`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`   
) SELECT  
	 `u_users_id`,
	  `username`,
	  `password`,
	  `salt`,
	  `surname`,
	  `email`,
	  `mobile`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`   
FROM `u_users`;
SELECT 'Copy end!' AS '';
 
-- 修改了原表表名
DROP TABLE  IF EXISTS `u_users`;
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
  PRIMARY KEY (`u_users_id`,`surname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





-- 删除原表所有记录并将临时表记录回填到原表
SELECT 'Begin to copy data into target table from tmp table...' AS '';
INSERT INTO `u_users`(
	`u_users_id`,
	  `username`,
	  `password`,
	  `salt`,
	  `surname`,
	  `email`,
	  `mobile`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`,
	  `app_key`
) 
SELECT 
	`u_users_id`,
	  `username`,
	  `password`,
	  `salt`,
	  `surname`,
	  `email`,
	  `mobile`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`,
		'V'
FROM `tmp_u_users`;
SELECT 'Copy end!' AS '';

-- 删除临时表
DROP TABLE IF EXISTS `tmp_u_users`;
 
COMMIT;