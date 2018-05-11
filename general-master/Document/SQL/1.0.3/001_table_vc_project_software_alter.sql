ALTER TABLE `vc_project_software`
MODIFY COLUMN `common_name`  varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '通用名称（如，zszy）' AFTER `software_name`;

