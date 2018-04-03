/*
03-15-2018		corren.mccoy		Created new table which collects data for "forget passcode" functions
04-03-2018		sara.bergman		changed table name from password_reset to passcode_reset
									changed data type of token_expiry from date to timestamp
									added column reset_complete type boolean to ensure that a passcode can't be reset more than once per reset request 
									added column reset_request_datetime to do date arithmetic within the Java code
*/
CREATE TABLE IF NOT EXISTS appl_auth.passcode_reset (
    organization_name 		text,		// must match the org name in appl_auth
	email_token				uuid,
	token_expiry			timestamp,		// within 24 hours of request		
	email_address			text,
	reset_attempts			int,
	ip_address				text,		// IP address where request for reset originated
	reset_complete			boolean,    //a passcode can't be reset more than once per reset request
    reset_request_datetime  timestamp   //used for java arithmetic to produce token_expiry
	PRIMARY KEY (organization_name, email_token)	
)
with comment = 'Used within the forgot passcode process to manage and validate users who require a reset';
