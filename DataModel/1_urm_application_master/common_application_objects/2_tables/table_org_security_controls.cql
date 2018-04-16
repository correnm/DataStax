/**
Ponemon Controls map 1:1 to controls we include for our threat defense

Modifications

*/


CREATE TABLE IF NOT EXISTS org_security_controls (
    group								text, 	//ex. RMF
	security_control_id					text,	 //ex. FW1 
    security_control 					text,	 //ex. Firewall
    score								decimal,
	category							text,
	description							text,	
	impacts_resistance_strength			boolean,
    impact_cost							decimal, //$ value gathered from appl_auth.cost_controls
	PRIMARY KEY (group, security_control_id) //same primary key for reference
) WITH COMMENT = 'an organization's list of security controls that supports access pattern: "For each organization's security control, what is the score and score and impact_cost?"' ;
