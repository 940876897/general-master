-- ----------------------------
-- Table structure for last_key
-- ----------------------------
DROP TABLE IF EXISTS `last_key`;
CREATE TABLE `last_key` (
  `last_key_id` varchar(40) NOT NULL,
  `table_name` varchar(200) DEFAULT NULL,
  `column_name` varchar(200) DEFAULT NULL,
  `last_key_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`last_key_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vc_project
-- ----------------------------
DROP TABLE IF EXISTS `vc_project`;
CREATE TABLE `vc_project` (
  `vc_project_id` varchar(40) NOT NULL COMMENT '主键',
  `project_name` varchar(40) NOT NULL COMMENT '项目名称',
  `hospital_name` varchar(50) NOT NULL COMMENT '医院名称',
  `createdby` varchar(40) NOT NULL COMMENT '新建者',
  `createdon` datetime NOT NULL COMMENT '新建日期',
  `modifiedby` varchar(40) NOT NULL COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '修改日期',
  `deletion_state` char(1) NOT NULL COMMENT '删除状态,0未删除，1已删除',
  `description` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`vc_project_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for vc_project_software
-- ----------------------------
DROP TABLE IF EXISTS `vc_project_software`;
CREATE TABLE `vc_project_software` (
  `vc_project_software_id` varchar(40) NOT NULL COMMENT '主键',
  `vc_project_id` varchar(40) NOT NULL COMMENT 'vc_project表主键',
  `software_name` varchar(40) NOT NULL COMMENT '软件名称（如，掌上浙一）',
  `common_name` varchar(40) DEFAULT NULL COMMENT '通用名称（如，zszy）',
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

-- ----------------------------
-- Table structure for vc_software_version
-- ----------------------------
DROP TABLE IF EXISTS `vc_software_version`;
CREATE TABLE `vc_software_version` (
  `vc_software_version_id` varchar(40) NOT NULL COMMENT '主键',
  `vc_project_software_id` varchar(40) NOT NULL COMMENT 'vc_project_software表主键',
  `version_number` varchar(40) DEFAULT NULL COMMENT '掌上医院版本号',
  `app_version_number` varchar(40) DEFAULT NULL COMMENT '应用版本号［x.y.z］',
  `release_time` datetime DEFAULT NULL COMMENT '发布时间',
  `app_download_url` varchar(200) DEFAULT NULL COMMENT '应用下载地址',
  `app_force_update` char(1) DEFAULT NULL COMMENT '强制更新，F：强制更新；R：提醒更新；',
  `zip_version_number` varchar(40) DEFAULT NULL COMMENT 'zip版本号［x.y.z］',
  `zip_desc` varchar(500) DEFAULT NULL COMMENT 'zip描述',
  `app_desc` varchar(500) DEFAULT NULL COMMENT '市场描述',
  `zip_download_url` varchar(200) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'zip下载地址',
  `is_off_shelves` char(1) DEFAULT NULL COMMENT '上架状态,0未上架，1已上架，2已下架',
  `current_max_app` varchar(40) DEFAULT NULL COMMENT '添加该条记录时的最大app版本号',
  `current_max_zip` varchar(40) DEFAULT NULL COMMENT '添加该条记录时的最大zip版本号',
  `minimum_support_system_version` varchar(40) DEFAULT NULL COMMENT '此版本支持的最低系统版本',
  `createdby` varchar(40) NOT NULL COMMENT '新建者',
  `createdon` datetime NOT NULL COMMENT '新建日期',
  `modifiedby` varchar(40) NOT NULL COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '修改日期',
  `is_zip` char(1) NOT NULL COMMENT '状态,0应用，1资源,2应用和资源',
  `deletion_state` char(1) NOT NULL COMMENT '删除状态,0未删除，1已删除',
  `description` varchar(500) DEFAULT NULL COMMENT '备注',
  `zip_force_update` char(1) DEFAULT 'A' COMMENT '资源更新类型：S静默更新,A全包更新,I增量更新',
  PRIMARY KEY (`vc_software_version_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for version_info
-- ----------------------------
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
  `minimum_support_system_version` varchar(40) DEFAULT NULL COMMENT '此版本支持的最低系统版本',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`version_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
