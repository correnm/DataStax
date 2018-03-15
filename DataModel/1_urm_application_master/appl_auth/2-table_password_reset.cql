/*
03-15-2018		corren.mccoy		Create new table which collects data for "forget passcode" functions
*/
CREATE TABLE IF NOT EXISTS appl_auth.password_reset (
    organization_name 		text,		// must match the org name in appl_auth
	email_token				uuid,
	token_expiry			date,		// within 24 hours of request		
	email_address			text,
	reset_attempts			int,
	ip_address				text,		// IP address where request for reset originated
    PRIMARY KEY (organization_name, email_token)	
)
with comment = 'Used within the forgot passcode process to manage and validate users who require a reset';
