CREATE TYPE setting_info(
description 		text,
constrained 		boolean,
data_type 			text,
min_value 			int,
max_value 			int,
default_value 		int,
allowed_settings 	frozen<set<text>>,
setting_code 		text,
);