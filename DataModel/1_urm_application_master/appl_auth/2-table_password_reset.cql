/*
03-15-2018		corren.mccoy		Create new table which collects data for "forget passcode" functions
04-03-2018		sara.bergman		change table name from password_reset to passcode_reset
									change data type of token_expiry from date to timestamp
									add column reset_complete type boolean to ensure that a passcode can't be reset more than once per reset request 
*/
CREATE TABLE IF NOT EXISTS appl_auth.passcode_reset (
    organization_name 		text,		// must match the org name in appl_auth
	email_token				uuid,
	token_expiry			timestamp,		// within 24 hours of request		
	email_address			text,
	reset_attempts			int,
	ip_address				text,		// IP address where request for reset originated
	reset_complete			boolean,    //a passcode can't be reset more than once per reset request
    PRIMARY KEY (organization_name, email_token)	
)
with comment = 'Used within the forgot passcode process to manage and validate users who require a reset';
