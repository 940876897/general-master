-- ----------------------------
-- DELETE Records of p_permissions
-- ----------------------------
DELETE
FROM
	`p_permissions`
WHERE
	p_permissions_id IN (
		'81d7f14d-5246-11e7-a25f-0050569b72c9',
		'af3e2297-3b74-11e7-a94e-0050569b72c9',
		'af3fdbeb-3b74-11e7-a94e-0050569b72c9',
		'af41bc19-3b74-11e7-a94e-0050569b72c9',
		'af43ec1c-3b74-11e7-a94e-0050569b72c9',
		'af464789-3b74-11e7-a94e-0050569b72c9',
		'af4894c4-3b74-11e7-a94e-0050569b72c9',
		'af4acd82-3b74-11e7-a94e-0050569b72c9',
		'af4d302b-3b74-11e7-a94e-0050569b72c9',
		'af4f412d-3b74-11e7-a94e-0050569b72c9',
		'af517555-3b74-11e7-a94e-0050569b72c9',
		'af5393f4-3b74-11e7-a94e-0050569b72c9',
		'af55e51f-3b74-11e7-a94e-0050569b72c9',
		'af57fdaa-3b74-11e7-a94e-0050569b72c9',
		'af5a1343-3b74-11e7-a94e-0050569b72c9',
		'af5c495e-3b74-11e7-a94e-0050569b72c9',
		'af5f46f6-3b74-11e7-a94e-0050569b72c9',
		'af6171d5-3b74-11e7-a94e-0050569b72c9',
		'af6381df-3b74-11e7-a94e-0050569b72c9',
		'eb725b67-f168-11e7-be74-0050569b08a8',
		'eb72ffa5-f168-11e7-be74-0050569b08a8',
		'eb740366-f168-11e7-be74-0050569b08a8',
		'eb7494f2-f168-11e7-be74-0050569b08a8',
		'eb7556fe-f168-11e7-be74-0050569b08a8',
		'eb75f551-f168-11e7-be74-0050569b08a8',
		'eb76a03d-f168-11e7-be74-0050569b08a8',
		'eb779703-f168-11e7-be74-0050569b08a8',
		'eb788893-f168-11e7-be74-0050569b08a8',
		'eb7a9ad4-f168-11e7-be74-0050569b08a8',
		'eb7b6e22-f168-11e7-be74-0050569b08a8',
		'eb7c0641-f168-11e7-be74-0050569b08a8',
		'eb7d09f5-f168-11e7-be74-0050569b08a8',
		'eb7e615c-f168-11e7-be74-0050569b08a8',
		'eb7f4850-f168-11e7-be74-0050569b08a8',
		'eb802346-f168-11e7-be74-0050569b08a8',
		'eb8110d1-f168-11e7-be74-0050569b08a8',
		'eb81f529-f168-11e7-be74-0050569b08a8',
		'eb82c39e-f168-11e7-be74-0050569b08a8',
		'eb836e77-f168-11e7-be74-0050569b08a8',
		'eb8462cb-f168-11e7-be74-0050569b08a8',
		'eb851ff7-f168-11e7-be74-0050569b08a8',
		'eb85c4ab-f168-11e7-be74-0050569b08a8',
		'eb86cd10-f168-11e7-be74-0050569b08a8',
		'eb8798fa-f168-11e7-be74-0050569b08a8',
		'eb89074d-f168-11e7-be74-0050569b08a8',
		'ed9b5bdb-f168-11e7-be74-0050569b08a8',
		'ed9c0097-f168-11e7-be74-0050569b08a8',
		'ed9e802b-f168-11e7-be74-0050569b08a8'
	);


-- ----------------------------
-- Records of p_permissions
-- ----------------------------
INSERT INTO `p_permissions` VALUES ('81d7f14d-5246-11e7-a25f-0050569b72c9', 'dynamichomepage', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af3e2297-3b74-11e7-a94e-0050569b72c9', 'childuserandpatient', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af3fdbeb-3b74-11e7-a94e-0050569b72c9', 'blackname', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af41bc19-3b74-11e7-a94e-0050569b72c9', 'addpatient', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af43ec1c-3b74-11e7-a94e-0050569b72c9', 'registered', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af464789-3b74-11e7-a94e-0050569b72c9', 'medicalrecord', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af4894c4-3b74-11e7-a94e-0050569b72c9', 'report', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af4acd82-3b74-11e7-a94e-0050569b72c9', 'outpatientpayment', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af4d302b-3b74-11e7-a94e-0050569b72c9', 'cardrecharge', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af4f412d-3b74-11e7-a94e-0050569b72c9', 'hospitalpayment', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af517555-3b74-11e7-a94e-0050569b72c9', 'message', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af5393f4-3b74-11e7-a94e-0050569b72c9', 'deptdoctor', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af55e51f-3b74-11e7-a94e-0050569b72c9', 'feedbackconfig', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af57fdaa-3b74-11e7-a94e-0050569b72c9', 'hospitaldept', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af5a1343-3b74-11e7-a94e-0050569b72c9', 'content', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af5c495e-3b74-11e7-a94e-0050569b72c9', 'business', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af5f46f6-3b74-11e7-a94e-0050569b72c9', 'behavior', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af6171d5-3b74-11e7-a94e-0050569b72c9', 'businessuserandpatient', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('af6381df-3b74-11e7-a94e-0050569b72c9', 'feedback', 'PT', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb725b67-f168-11e7-be74-0050569b08a8', 'P:1', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb72ffa5-f168-11e7-be74-0050569b08a8', 'P:2', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb740366-f168-11e7-be74-0050569b08a8', 'P:4', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7494f2-f168-11e7-be74-0050569b08a8', 'P:16', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7556fe-f168-11e7-be74-0050569b08a8', 'P:32', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb75f551-f168-11e7-be74-0050569b08a8', 'S:1', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb76a03d-f168-11e7-be74-0050569b08a8', 'S:2', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb779703-f168-11e7-be74-0050569b08a8', 'S:4', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb788893-f168-11e7-be74-0050569b08a8', 'S:16', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7a9ad4-f168-11e7-be74-0050569b08a8', 'S:32', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7b6e22-f168-11e7-be74-0050569b08a8', 'V:2', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7c0641-f168-11e7-be74-0050569b08a8', 'V:4', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7d09f5-f168-11e7-be74-0050569b08a8', 'V:32', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7e615c-f168-11e7-be74-0050569b08a8', 'U:1', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb7f4850-f168-11e7-be74-0050569b08a8', 'U:2', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb802346-f168-11e7-be74-0050569b08a8', 'U:4', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb8110d1-f168-11e7-be74-0050569b08a8', 'U:8', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb81f529-f168-11e7-be74-0050569b08a8', 'U:16', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb82c39e-f168-11e7-be74-0050569b08a8', 'U:32', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb836e77-f168-11e7-be74-0050569b08a8', 'R:1', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb8462cb-f168-11e7-be74-0050569b08a8', 'R:2', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb851ff7-f168-11e7-be74-0050569b08a8', 'R:4', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb85c4ab-f168-11e7-be74-0050569b08a8', 'R:8', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb86cd10-f168-11e7-be74-0050569b08a8', 'R:16', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb8798fa-f168-11e7-be74-0050569b08a8', 'R:32', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('eb89074d-f168-11e7-be74-0050569b08a8', 'R:64', 'V', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('ed9b5bdb-f168-11e7-be74-0050569b08a8', 'U:ALL', 'RX', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('ed9c0097-f168-11e7-be74-0050569b08a8', 'A:ALL', 'RX', 'script', NOW(), 'script', NOW(), '0', '');
INSERT INTO `p_permissions` VALUES ('ed9e802b-f168-11e7-be74-0050569b08a8', 'R:ALL', 'RX', 'script', NOW(), 'script', NOW(), '0', '');
