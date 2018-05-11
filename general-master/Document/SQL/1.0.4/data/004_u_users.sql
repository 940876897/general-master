
DELETE FROM `u_users` WHERE `u_users_id` = '91680d1a-5b8e-45af-8bcd-c16d740d85db';
INSERT INTO `u_users` (`u_users_id`, `username`, `password`, `salt`, `surname`, `email`, `mobile`, 
`app_key`, `createdby`, `createdon`, `modifiedby`, `modifiedon`, `deletion_state`, `description`) 
VALUES ('91680d1a-5b8e-45af-8bcd-c16d740d85db', 'admin', 'b9282aa8e7b0b6225af9ea5ab610a0f1', 
'aff38f278e0e2b344bfaa4cd1a14c91a', '张赛', 'ucmed@zhuojianchina.com', '88851766', 
'RX', 'script', NOW(), 'script', NOW(), '0', '');
