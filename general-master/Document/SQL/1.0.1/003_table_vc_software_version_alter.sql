START TRANSACTION;

-- 新增资源类型
alter table vc_software_version add `zip_force_update` char(1) DEFAULT 'A' COMMENT '资源更新类型：S静默更新,A全包更新,I增量更新';


COMMIT;