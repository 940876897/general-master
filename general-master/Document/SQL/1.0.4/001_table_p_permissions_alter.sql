START TRANSACTION;



-- 创建和原表相同的临时表保存原表数据
DROP TABLE  IF EXISTS `tmp_p_permissions`;
CREATE TABLE `tmp_p_permissions` (
  `p_permissions_id` varchar(40) NOT NULL COMMENT '主键',
  `permission` varchar(100) NOT NULL COMMENT '权限字符串',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`p_permissions_id`),
  UNIQUE KEY `idx_sys_permissions_permission` (`permission`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 将原表数据保存到临时表中
SELECT 'Begin to copy orignal talbe data to tmp table...' AS '';
INSERT INTO tmp_p_permissions(
	  `p_permissions_id` ,
	  `permission`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`  
) SELECT  
	 `p_permissions_id` ,
	  `permission`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`  
FROM `p_permissions`;
SELECT 'Copy end!' AS '';
 
-- 修改了原表表名
DROP TABLE  IF EXISTS `p_permissions`;
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



-- 删除原表所有记录并将临时表记录回填到原表
SELECT 'Begin to copy data into target table from tmp table...' AS '';
INSERT INTO `p_permissions`(
	 `p_permissions_id` ,
	  `permission`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`,
	  `app_key`
) 
SELECT 
	`p_permissions_id` ,
	  `permission`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`,
	  'V'
FROM `tmp_p_permissions`;
SELECT 'Copy end!' AS '';

-- 删除临时表
DROP TABLE IF EXISTS `tmp_p_permissions`;
 
COMMIT;