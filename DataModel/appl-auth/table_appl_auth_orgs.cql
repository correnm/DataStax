CREATE TABLE IF NOT EXISTS appl_auth.organizations (
    organization_name 	text,		// store in lower case
	keyspace_name		text,
	username			text,
	hashed_password		text,
    PRIMARY KEY (organization_name)
	
)
with comment = 'Used within the login process to determine the connection info.';

insert into appl_auth.organizations
(organization_name, keyspace_name, username, hashed_password)
values
('g2 ops','g2','g2','7579659330');

insert into appl_auth.organizations
(organization_name, keyspace_name, username, hashed_password)
values
('vmasc','vmasc','vmasc','7575551212');