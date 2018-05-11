DELETE FROM r_roles WHERE r_roles_id = '40cc2a46-18cf-4de4-9486-fb192266c503';
INSERT INTO `r_roles` (`r_roles_id`, `role_name`, `app_key`, `createdby`, `createdon`, `modifiedby`, `modifiedon`, `deletion_state`, `description`) 
VALUES ('40cc2a46-18cf-4de4-9486-fb192266c503', '超级管理员', 'PT', 'script', NOW(), 'script', 
NOW(), '0', '超级管理员');