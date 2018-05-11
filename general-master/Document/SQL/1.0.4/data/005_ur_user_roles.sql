
DELETE FROM ur_users_roles WHERE ur_users_roles_id = '068e2a51-fba3-11e5-9448-507b9d24b9d1';
INSERT INTO `ur_users_roles` (`ur_users_roles_id`, `u_users_id`, `r_roles_id`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`) 
SELECT
	'068e2a51-fba3-11e5-9448-507b9d24b9d1',
	'91680d1a-5b8e-45af-8bcd-c16d740d85db',
	'4102db92-fba2-11e5-9448-507b9d24b9d1',
	'script',
	NOW(),
	'script',
	NOW(),
	'0',
	'' ;