DELETE FROM rp_roles_permissions;
 INSERT INTO `rp_roles_permissions` 
(`rp_roles_permissions_id`, `r_roles_id`, `p_permissions_id`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`) 
SELECT
	UUID(),
	(SELECT r_roles_id as r_roles_id from r_roles where role_name = '超级管理员') as r_roles_id,
	p_permissions_id,
	'script',
	NOW(),
	'script',
	NOW(),
	'0',
	''
FROM
p_permissions
