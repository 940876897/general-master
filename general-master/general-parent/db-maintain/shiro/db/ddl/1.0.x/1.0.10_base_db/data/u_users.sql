-- ----------------------------
-- DELETE Records of u_users
-- ----------------------------
DELETE
FROM
	`u_users`
WHERE
	u_users_id IN (
		'1c333055-7fc1-4056-bf28-561838f8ac6d',
		'91680d1a-5b8e-45af-8bcd-c16d740d85db',
		'fe9209cd-687f-4539-9ea6-ce8b61b62f02',
		'ffd42da5-b359-11e5-b368-507b9d24b9d1'
	);

-- ----------------------------
-- Records of u_users
-- ----------------------------
INSERT INTO `u_users` VALUES ('1c333055-7fc1-4056-bf28-561838f8ac6d', 'admin', '0cf00269c4856f06e7dae704c5e9f34e', 'bb833ba3ac102444567eef78c4bfab25', '超级管理员', 'ucmed@zhuojianchina.com', '88851766', 'PT', 'script', NOW(), 'script', NOW(), '0', '', '', '', '');
INSERT INTO `u_users` VALUES ('91680d1a-5b8e-45af-8bcd-c16d740d85db', 'admin', 'b9282aa8e7b0b6225af9ea5ab610a0f1', 'aff38f278e0e2b344bfaa4cd1a14c91a', '张赛', 'ucmed@zhuojianchina.com', '88851766', 'RX', 'script', NOW(), 'script', NOW(), '0', '', '', '', '');
INSERT INTO `u_users` VALUES ('fe9209cd-687f-4539-9ea6-ce8b61b62f02', 'ptadmin', '883fa906beac7649681871e014eeb0e4', '4a620fa71885b51c05e127d741fb1de4', '超级管理员', 'ucmed@zhuojianchina.com', '88851766', 'PT', 'script', NOW(), 'script', NOW(), '0', '', '', '', '');
INSERT INTO `u_users` VALUES ('ffd42da5-b359-11e5-b368-507b9d24b9d1', 'admin', 'e53d181f9c3840ccb84849c53d297d09', 'e0ffa4224d90692ca63e235b372d1fe3', '超级管理员', 'ucmed@zhuojianchina.com', '88851766', 'V', 'script', NOW(), 'script', NOW(), '0', '', '', '', '');
