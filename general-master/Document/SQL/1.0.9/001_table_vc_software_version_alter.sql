START TRANSACTION;

DELIMITER //
drop procedure if exists add_latest_system_version; 
create procedure add_latest_system_version()
BEGIN
DECLARE  CurrentDatabase VARCHAR(100);
SELECT DATABASE() INTO CurrentDatabase;

IF EXISTS 
(select column_name from information_schema.columns 
where TABLE_SCHEMA = CurrentDatabase  and TABLE_NAME = 'vc_software_version' and COLUMN_NAME = 'minimum_support_system_version')
THEN  
   ALTER TABLE vc_software_version DROP COLUMN minimum_support_system_version; 
END IF;
   ALTER TABLE `vc_software_version` ADD COLUMN `minimum_support_system_version`  varchar(40) NULL COMMENT '此版本支持的最低系统版本' AFTER `current_max_zip`;


IF EXISTS 
(select column_name from information_schema.columns 
where TABLE_SCHEMA = CurrentDatabase  and TABLE_NAME = 'version_info' and COLUMN_NAME = 'minimum_support_system_version')
THEN  
   ALTER TABLE version_info DROP COLUMN minimum_support_system_version; 
END IF;
   ALTER TABLE `version_info` ADD COLUMN `minimum_support_system_version`  varchar(40) NULL COMMENT '此版本支持的最低系统版本' AFTER `software_update_time`;
END;
//

 call add_latest_system_version();

 drop procedure if exists add_latest_system_version; 
 
COMMIT;