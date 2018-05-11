INSERT INTO last_key (
	last_key_id,
	table_name,
	column_name,
	last_key_value
)
VALUES
	(
		'87fd378f-4261-11e5-a6af-0050569b08a8',
		'vc_project_software',
		'software_id',
		0
	) ON DUPLICATE KEY UPDATE last_key_value = last_key_value + 0;