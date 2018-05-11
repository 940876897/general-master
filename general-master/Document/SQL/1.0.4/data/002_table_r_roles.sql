DELETE FROM r_roles WHERE r_roles_id = '4102db92-fba2-11e5-9448-507b9d24b9d1';
INSERT INTO `r_roles` (`r_roles_id`, `role_name`, `createdby`, `createdon`, `modifiedby`, 
`modifiedon`, `deletion_state`, `description`,`app_key`) VALUES 
('4102db92-fba2-11e5-9448-507b9d24b9d1', '超级管理员', 'script', 
NOW(), 'script', NOW(), '0', 'script','RX');

 
DELETE FROM r_roles WHERE r_roles_id = 'ecb5c389-4f9a-454b-ac73-57be360611ca';
 INSERT INTO `r_roles` (`r_roles_id`, `role_name`, `app_key`, `createdby`, `createdon`, `modifiedby`, `modifiedon`, `deletion_state`, `description`) VALUES ('ecb5c389-4f9a-454b-ac73-57be360611ca', '项目负责人', 'RX', 'script', '9999-12-31 23:59:59', 'script', '9999-12-31 23:59:59', '0', '负责应用（区域平台项目或者单医院项目）的配置等管理，例如大众版和医护版；');
