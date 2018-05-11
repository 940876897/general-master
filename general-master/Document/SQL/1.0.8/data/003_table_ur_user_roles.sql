
DELETE FROM ur_users_roles WHERE `ur_users_roles_id` = 'f8af2c26-13b3-4d7a-b5ce-a53ed3e18248' or `ur_users_roles_id` = 'fc16f79a-0d60-4487-9159-aea504d91d23';
INSERT INTO `ur_users_roles` (`ur_users_roles_id`, `u_users_id`, `r_roles_id`, `createdby`, `createdon`, 
`modifiedby`, `modifiedon`, `deletion_state`, `description`) 
VALUES ('f8af2c26-13b3-4d7a-b5ce-a53ed3e18248', '1c333055-7fc1-4056-bf28-561838f8ac6d', '40cc2a46-18cf-4de4-9486-fb192266c503', 'script', NOW(), 'script', 
NOW(), '0', ''),
('fc16f79a-0d60-4487-9159-aea504d91d23', 'fe9209cd-687f-4539-9ea6-ce8b61b62f02', '40cc2a46-18cf-4de4-9486-fb192266c503', 'script', NOW(), 'script', 
NOW(), '0', '');