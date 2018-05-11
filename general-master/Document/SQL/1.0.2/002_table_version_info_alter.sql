START TRANSACTION;



-- 创建和原表相同的临时表保存原表数据
DROP TABLE IF EXISTS `tmp_version_info`;
CREATE TABLE `tmp_version_info` (
  `version_info_id` varchar(40) NOT NULL COMMENT '主键',
  `software_id` int(11) DEFAULT NULL,
  `app_version_number` varchar(40) DEFAULT NULL COMMENT '应用版本号［x.y.z］',
  `app_download_url` varchar(200) DEFAULT NULL COMMENT '应用下载地址',
  `zip_version_number` varchar(40) DEFAULT NULL COMMENT 'zip版本号［x.y.z］',
  `zip_download_url` varchar(200) DEFAULT NULL COMMENT 'zip下载地址',
  `app_desc` varchar(500) DEFAULT NULL,
  `zip_desc` varchar(500) DEFAULT NULL,
  `latest_force_update_version` varchar(40) DEFAULT NULL,
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`version_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 将原表数据保存到临时表中
SELECT 'Begin to copy orignal talbe data to tmp table...' AS '';
INSERT INTO tmp_version_info(
	  `version_info_id`,
	  `software_id`,
	  `app_version_number`,
	  `app_download_url`,
	  `zip_version_number`,
	  `zip_download_url`,
	  `app_desc`,
	  `zip_desc`,
	  `latest_force_update_version`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`
) SELECT  
	  `version_info_id`,
	  `software_id`,
	  `app_version_number`,
	  `app_download_url`,
	  `zip_version_number`,
	  `zip_download_url`,
	  `app_desc`,
	  `zip_desc`,
	  `latest_force_update_version`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`
FROM `version_info`;
SELECT 'Copy end!' AS '';
 
 
DROP TABLE IF EXISTS `version_info`;
CREATE TABLE `version_info` (
  `version_info_id` varchar(40) NOT NULL COMMENT '主键',
  `software_id` int(11) DEFAULT NULL,
  `app_version_number` varchar(40) DEFAULT NULL COMMENT '应用版本号［x.y.z］',
  `app_download_url` varchar(200) DEFAULT NULL COMMENT '应用下载地址',
  `zip_version_number` varchar(40) DEFAULT NULL COMMENT 'zip版本号［x.y.z］',
  `zip_download_url` varchar(200) DEFAULT NULL COMMENT 'zip下载地址',
  `app_desc` varchar(500) DEFAULT NULL COMMENT 'app备注',
  `zip_desc` varchar(500) DEFAULT NULL COMMENT 'zip备注',
  `latest_force_update_version` varchar(40) DEFAULT NULL COMMENT '最新强制更新版本',
  `zip_version_state` char(1) DEFAULT 'A' COMMENT '资源更新类型：S静默更新,A全包更新,I增量更新',
  `software_update_time` datetime NOT NULL COMMENT '软件更新时间，用于和软件版本同步',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`version_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 删除原表所有记录并将临时表记录回填到原表
SELECT 'Begin to copy data into target table from tmp table...' AS '';
INSERT INTO `version_info`(
	`version_info_id`,
	  `software_id`,
	  `app_version_number`,
	  `app_download_url`,
	  `zip_version_number`,
	  `zip_download_url`,
	  `app_desc`,
	  `zip_desc`,
	  `latest_force_update_version`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`
) 
SELECT 
	`version_info_id`,
	  `software_id`,
	  `app_version_number`,
	  `app_download_url`,
	  `zip_version_number`,
	  `zip_download_url`,
	  `app_desc`,
	  `zip_desc`,
	  `latest_force_update_version`,
	  `createdby`,
	  `createdon`,
	  `modifiedby`,
	  `modifiedon`,
	  `deletion_state`,
	  `description`
FROM `tmp_version_info`;
SELECT 'Copy end!' AS '';

-- 删除临时表
DROP TABLE IF EXISTS `tmp_version_info`;
 
COMMIT;
