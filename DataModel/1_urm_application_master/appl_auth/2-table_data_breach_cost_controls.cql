/**
Modifications

*/

CREATE TABLE IF NOT EXISTS appl_auth.data_breach_cost_controls (
    country						text, 		//ex. US
	year						int,		
	security_control_id			text,	 	//ex. FW1
    security_control 			text,	 	//ex. Firewall
    impact_cost					decimal, 	//$ value gathered from Ponemon Figure 7
	cost_type					text, 		//Primary or Secondary
	PRIMARY KEY (country, year, security_control_id)
) WITH COMMENT = 'Master list of the cost of each security control by county gathered from the Ponemon Data Breach Cost report.'
