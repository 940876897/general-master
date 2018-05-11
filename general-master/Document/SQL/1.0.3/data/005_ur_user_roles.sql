
DELETE FROM ur_users_roles;
INSERT INTO `ur_users_roles` (`ur_users_roles_id`, `u_users_id`, `r_roles_id`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`) 
SELECT
	UUID(),
	(SELECT u_users_id FROM u_users WHERE username = 'admin'),
	(SELECT r_roles_id FROM r_roles WHERE role_name = '超级管理员'),
	'script',
	NOW(),
	'script',
	NOW(),
	'0',
	'' ;