/*
03-15-2018		corren.mccoy		removed webapp_name from partition key
05-18-2018		corren.mccoy		added default security control group which determines the worksheet
*/
CREATE TABLE IF NOT EXISTS appl_auth.organizations (
    organization_name 				text,		// store in lower case
	keyspace_name					text,
	username						text,
	encrypted_password  			text,		// using aes256 encryption
	industry						text,		// references the industry
	webapp_name						text,		// name must match Tomcat entry. An organization can have more than one IMPACT application
	country_name					text,		// country in which the headquarters for this organization is located
	resistance_strength 			decimal,
	default_security_control_group	text,		// must match entry in appl_auth.security_controls
    PRIMARY KEY (organization_name)	
)
with comment = 'Used within the login process to determine the connection info and the applicable application code tree';




