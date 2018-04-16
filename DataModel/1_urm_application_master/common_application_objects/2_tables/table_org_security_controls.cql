/**
Ponemon Controls map 1:1 to controls we include for our threat defense

Modifications

*/


CREATE TABLE IF NOT EXISTS org_security_controls (
    security_control_group				text, 	//ex. RMF
	security_control_id					text,	 //ex. CLS 
    security_control 					text,	 //ex. Data Classification
    score								decimal,  // ex.2
	category							text,	 //	ex. Low
	description							text,	//ex. internal/external tags only
	apply_impact_cost					boolean, // true/false if cost is applied or not
    impact_cost							decimal, //$ value gathered from appl_auth.cost_controls
	cost_type							text, //primary or secondary
	PRIMARY KEY (security_control_group, security_control_id) //same primary key for reference
) WITH COMMENT = 'an organization''s list of security controls that supports access pattern: "For each organization''s security control, what is the score and score and impact_cost?"' ;
