/**
Ponemon Controls map 1:1 to controls we include for our threat defense

Modifications

*/


CREATE TABLE IF NOT EXISTS business_value_security_controls (
    site_id								UUID,
	business_process_id					UUID,
	security_control_group								text, 	//ex. RMF
	security_control_id					text,	 //ex. FW1 
    security_control 					text,	 //ex. Firewall
    score								decimal,
	category							text,
	description							text,	
	impacts_resistance_strength			boolean,
    impact_cost							decimal, //$ value gathered from appl_auth.cost_controls
	PRIMARY KEY (site_id, business_process_id, security_control_group, security_control_id) //same primary key for reference
) WITH COMMENT = 'the business practices'' list of security controls that supports access pattern: "For each organization''s security control, what is the score and impact_cost?"' ;
