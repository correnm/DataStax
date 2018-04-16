use keyspace appl_auth;

create type security_control_ratings(
score							decimal,	//5.45
category						text,		//Medium
description						text,		//not NGFW
impacts_resistance_strength		boolean		//true or false
);