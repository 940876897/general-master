DELETE FROM rp_roles_permissions WHERE r_roles_id = '4102db92-fba2-11e5-9448-507b9d24b9d1';
 INSERT INTO `rp_roles_permissions` 
(`rp_roles_permissions_id`, `r_roles_id`, `p_permissions_id`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`) 
SELECT
	UUID(),
	'4102db92-fba2-11e5-9448-507b9d24b9d1' as r_roles_id,
	p_permissions_id,
	'script',
	NOW(),
	'script',
	NOW(),
	'0',
	''
FROM
p_permissions
WHERE permission IN('U:ALL','A:ALL','R:ALL');