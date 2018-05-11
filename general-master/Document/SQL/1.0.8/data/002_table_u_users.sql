DELETE FROM `u_users` WHERE `u_users_id` = '1c333055-7fc1-4056-bf28-561838f8ac6d' or `u_users_id` = 'fe9209cd-687f-4539-9ea6-ce8b61b62f02';
INSERT INTO `u_users` (`u_users_id`, `username`, `password`, `salt`, `surname`, `email`, 
`mobile`, `app_key`, `createdby`, `createdon`, `modifiedby`, `modifiedon`, `deletion_state`, `description`, `id_card`, `employee_id`, `type`) 
VALUES ('1c333055-7fc1-4056-bf28-561838f8ac6d', 'admin', '0cf00269c4856f06e7dae704c5e9f34e', 'bb833ba3ac102444567eef78c4bfab25', 
'超级管理员', 'ucmed@zhuojianchina.com', '88851766', 'PT', 'script', NOW(), 'script', NOW(), '0', '', '', '', ''),
('fe9209cd-687f-4539-9ea6-ce8b61b62f02', 'ptadmin', '883fa906beac7649681871e014eeb0e4', '4a620fa71885b51c05e127d741fb1de4', 
'超级管理员', 'ucmed@zhuojianchina.com', '88851766', 'PT', 'script', NOW(), 'script', NOW(), '0', '', '', '', '');





