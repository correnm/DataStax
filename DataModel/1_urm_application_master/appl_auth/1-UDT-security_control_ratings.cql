use keyspace appl_auth;

create type security_control_ratings(
score							decimal,	//5.45
category						text,		//Medium
description						text,		//not NGFW
apply_impact_cost				boolean,	//based on business logic
impacts_resistance_strength		boolean,   // based on business logic. 
cost_type						text,		//Primary or Secondary
);