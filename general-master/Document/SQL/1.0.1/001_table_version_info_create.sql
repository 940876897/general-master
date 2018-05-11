DROP TABLE IF EXISTS `version_info`;
CREATE TABLE `version_info` (
  `version_info_id` varchar(40) NOT NULL COMMENT '主键',
  `software_id` int(11) DEFAULT NULL,
  `app_version_number` varchar(40) DEFAULT NULL COMMENT '应用版本号［x.y.z］',
  `app_download_url` varchar(200) DEFAULT NULL COMMENT '应用下载地址',
  `app_force_update` char(1) DEFAULT NULL COMMENT '强制更新{F：强制更新；R：提醒更新；N：不更新}',
  `zip_version_number` varchar(40) DEFAULT NULL COMMENT 'zip版本号［x.y.z］',
  `zip_download_url` varchar(200) DEFAULT NULL COMMENT 'zip下载地址',
  `app_desc` varchar(500) DEFAULT NULL,
  `zip_desc` varchar(500) DEFAULT NULL,
  `latest_force_update_version` varchar(40) DEFAULT NULL COMMENT '最新强制更新版本',
  `createdby` varchar(40) NOT NULL DEFAULT '' COMMENT '创建者',
  `createdon` datetime NOT NULL COMMENT '创建时间',
  `modifiedby` varchar(40) NOT NULL DEFAULT '' COMMENT '修改者',
  `modifiedon` datetime NOT NULL COMMENT '最后修改时间',
  `deletion_state` char(1) NOT NULL DEFAULT '0' COMMENT '删除状态',
  `description` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`version_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

