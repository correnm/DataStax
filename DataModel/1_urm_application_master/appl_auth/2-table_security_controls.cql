/**
Master threat defense worksheet
Ponemon Controls map 1:1 to controls we include for our threat defense
Modifications

*/

CREATE TABLE IF NOT EXISTS security_controls (
    security_control_group				text, //ex. RMF
	security_control_id					text, //ex. FW1
    security_control 					text, //ex. Firewall
	rating								list<frozen<security_control_ratings>>,					
    PRIMARY KEY (security_control_group, security_control_id)
) WITH COMMENT = 'Master list of security controls that supports access pattern: "What questions are included in a specific group (industry, RMF, etc.)?"' ;
