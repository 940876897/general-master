-- ----------------------------
-- DELETE Records of r_roles
-- ----------------------------
DELETE
FROM
	`r_roles`
WHERE
	r_roles_id IN (
		'40cc2a46-18cf-4de4-9486-fb192266c503',
		'4102db92-fba2-11e5-9448-507b9d24b9d1',
		'd6c579a0-b99d-11e5-a6af-0050569b08a8',
		'ecb5c389-4f9a-454b-ac73-57be360611ca'
	);

-- ----------------------------
-- Records of r_roles
-- ----------------------------
INSERT INTO `r_roles` VALUES ('40cc2a46-18cf-4de4-9486-fb192266c503', '超级管理员', 'PT', 'script', NOW(), 'script', NOW(), '0', '超级管理员');
INSERT INTO `r_roles` VALUES ('4102db92-fba2-11e5-9448-507b9d24b9d1', '超级管理员', 'RX', 'script', NOW(), 'script', NOW(), '0', 'script');
INSERT INTO `r_roles` VALUES ('d6c579a0-b99d-11e5-a6af-0050569b08a8', '超级管理员', 'V', 'script', NOW(), 'script', NOW(), '0', '系统管理员');
INSERT INTO `r_roles` VALUES ('ecb5c389-4f9a-454b-ac73-57be360611ca', '项目负责人', 'RX', 'script', NOW(), 'script', NOW(), '0', '负责应用（区域平台项目或者单医院项目）的配置等管理，例如大众版和医护版；');
