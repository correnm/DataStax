CREATE TABLE IF NOT EXISTS appl_auth.organizations (
    organization_name 	text,		// store in lower case
	keyspace_name		text,
	username			text,
	encrypted_password  text,		// using aes256 encryption
	industry			text,		// references the industry
	webapp_name			text,		// name must match Tomcat entry. An organization can have more than one IMPACT application
	country_name		text,		// country in which the headquarters for this organization is located
    PRIMARY KEY (organization_name, webapp_name )	
)
with comment = 'Used within the login process to determine the connection info and the applicable application code tree';
