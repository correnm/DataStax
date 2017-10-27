CREATE TABLE IF NOT EXISTS appl_auth.organizations_ (
    organization_name 	text,		// store in lower case
	keyspace_name		text,
	username			text,
	hashed_password		text,
	industry			text,		// references.industry_name = "private", "public"
	webapp_name			text,		// name must match Tomcat entry,
	country_name		text,		// country in which the headquarters for this organization is located
    PRIMARY KEY (organization_name, webapp_name )
	
)
with comment = 'Used within the login process to determine the connection info and the applicable application code tree';

insert into appl_auth.organizations
(organization_name, keyspace_name, username, hashed_password, industry, webapp_name, country_name)
values
('g2 ops','g2','g2','7579659330','private', 'washington','United States');

insert into appl_auth.organizations
(organization_name, keyspace_name, username, hashed_password, industry, webapp_name, country_name)
values
('vmasc','vmasc','vmasc','7575551212','private', 'washington', 'United States');

insert into appl_auth.organizations
(organization_name, keyspace_name, username, hashed_password, industry, webapp_name, country_name)
values
('dod','dod','dod','7579659330','public', 'g2Impact-DoD','United States');