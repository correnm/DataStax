CREATE TABLE IF NOT EXISTS vmasc.application_settings
(
setting_group 		text,
setting_code		text,
description 		text,
constrained 		boolean,  // if constrained, then entries will be listed in the allowed_settings
data_type 			text,
min_value 			int,
max_value 			int,
default_value 		int,
current_value		int,
allowed_settings 	frozen<set<text>>,
PRIMARY KEY ((setting_group), setting_code)
) WITH CLUSTERING ORDER BY (setting_code ASC)
AND COMMENT='Manages application and configuration settings';

