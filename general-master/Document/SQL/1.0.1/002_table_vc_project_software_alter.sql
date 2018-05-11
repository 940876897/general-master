START TRANSACTION;



-- 创建和原表相同的临时表保存原表数据
DROP TABLE  IF EXISTS `tmp_vc_project_software`;
CREATE TABLE `tmp_vc_project_software` (
  `vc_project_software_id` varchar(40) NOT NULL COMMENT '主键',
  `vc_project_id` varchar(40) NOT NULL COMMENT 'vc_project表主键',
  `software_name` varchar(40) NOT NULL COMMENT '软件名称（如，掌上浙一）',
  `common_name` varchar(40) NOT NULL COMMENT '通用名称（如，zszy）',
  `software_type` varchar(20) NOT NULL COMMENT '软件类型（Android、iOS、Win8）',
  `software_id` int(11) NOT NULL COMMENT '软件ID',
  `createdby` varchar(40) NOT NULL COMMENT '新建者',
  `createdon` datetime NOT NULL COMMENT '新建日期',
  `modifiedby` varchar(40) NOT NULL COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '修改日期',
  `deletion_state` char(1) NOT NULL COMMENT '删除状态,0未删除，1已删除',
  `description` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`vc_project_software_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- 将原表数据保存到临时表中
SELECT 'Begin to copy orignal talbe data to tmp table...' AS '';
INSERT INTO tmp_vc_project_software(
	 vc_project_software_id,
	`vc_project_id`,
	`software_name`,
  `common_name`,
  `software_type`,
  `software_id`,
  `createdby`,
  `createdon`,
  `modifiedby`,
  `modifiedon`,
  `deletion_state`,
  `description`
) SELECT  
	 vc_project_software_id,
	`vc_project_id`,
	`software_name`,
  `common_name`,
  `software_type`,
  `software_id`,
  `createdby`,
  `createdon`,
  `modifiedby`,
  `modifiedon`,
  `deletion_state`,
  `description`
FROM `vc_project_software`;
SELECT 'Copy end!' AS '';
 
-- 修改了原表表名
DROP TABLE  IF EXISTS `vc_project_software`;
CREATE TABLE `vc_project_software` (
  `vc_project_software_id` varchar(40) NOT NULL COMMENT '主键',
  `vc_project_id` varchar(40) NOT NULL COMMENT 'vc_project表主键',
  `software_name` varchar(40) NOT NULL COMMENT '软件名称（如，掌上浙一）',
  `common_name` varchar(40) NOT NULL COMMENT '通用名称（如，zszy）',
  `software_type` varchar(20) NOT NULL COMMENT '软件类型（Android、iOS、Win8）',
  `software_id` int(11) NOT NULL COMMENT '软件ID',
  `has_update` char(1) NOT NULL,
  `createdby` varchar(40) NOT NULL COMMENT '新建者',
  `createdon` datetime NOT NULL COMMENT '新建日期',
  `modifiedby` varchar(40) NOT NULL COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '修改日期',
  `deletion_state` char(1) NOT NULL COMMENT '删除状态,0未删除，1已删除',
  `description` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`vc_project_software_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 删除原表所有记录并将临时表记录回填到原表
SELECT 'Begin to copy data into target table from tmp table...' AS '';
INSERT INTO `vc_project_software`(
	 vc_project_software_id,
	`vc_project_id`,
	`software_name`,
  `common_name`,
  `software_type`,
  `software_id`,
  `has_update`,
  `createdby`,
  `createdon`,
  `modifiedby`,
  `modifiedon`,
  `deletion_state`,
  `description`
) 
SELECT 
		 vc_project_software_id,
	`vc_project_id`,
	`software_name`,
  `common_name`,
  `software_type`,
  `software_id`,
  1 as `has_update`,
  `createdby`,
  `createdon`,
  `modifiedby`,
  `modifiedon`,
  `deletion_state`,
  `description`
FROM `tmp_vc_project_software`;
SELECT 'Copy end!' AS '';

-- 删除临时表
DROP TABLE IF EXISTS `tmp_vc_project_software`;
 
COMMIT;