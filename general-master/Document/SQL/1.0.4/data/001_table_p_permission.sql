DELETE FROM `p_permissions` WHERE permission IN('U:ALL','A:ALL','R:ALL');

INSERT INTO `p_permissions` (`p_permissions_id`, `permission`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`,`app_key`)
 VALUES (UUID(), 'U:ALL', 'script', NOW(), 'script', NOW(), '0', '','RX');
 
INSERT INTO `p_permissions` (`p_permissions_id`, `permission`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`,`app_key`)
 VALUES (UUID(), 'A:ALL', 'script', NOW(), 'script', NOW(), '0', '','RX');

INSERT INTO `p_permissions` (`p_permissions_id`, `permission`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`,`app_key`)
 VALUES (UUID(), 'R:ALL', 'script', NOW(), 'script', NOW(), '0', '','RX');
 