/*

02-28-2018	corren.mccoy	Name of database column changed. Changed data value for industry associated with dod
03-15-2018	corren.mccoy	Password encryption
05-18-2018	corren.mccoy	Add entry for default security control group
**/

// plaintext passwords - DO NOT USE
INSERT INTO appl_auth.organizations (organization_name,webapp_name,country_name, encrypted_password,industry,keyspace_name,username) VALUES (
'dod','URM','United States','7579659330','Public sector','dod','dod');
INSERT INTO appl_auth.organizations (organization_name,webapp_name,country_name, encrypted_password,industry,keyspace_name,username) VALUES (
'vmasc','URM','United States','7575551212','Education','vmasc','vmasc');
INSERT INTO appl_auth.organizations (organization_name,webapp_name,country_name, ecrypted_password,industry,keyspace_name,username) VALUES (
'g2 ops','URM','United States','7579659330','Technology','g2','g2');


// encrypted passwords
INSERT INTO appl_auth.organizations
(organization_name, webapp_name, country_name, encrypted_password, industry, keyspace_name, username, default_security_control_group)
VALUES('dod', 'URM', 'United States', 'dVbj14Q40xgPkNs5f5pyzg==', 'Public sector', 'dod', 'dod', 'industry');
INSERT INTO appl_auth.organizations
(organization_name, webapp_name, country_name, encrypted_password, industry, keyspace_name, username, default_security_control_group)
VALUES('vmasc', 'URM', 'United States', '57c5IfDDnpbQYOzmUULERw==', 'Education', 'vmasc', 'vmasc', 'industry');
INSERT INTO appl_auth.organizations
(organization_name, webapp_name, country_name, encrypted_password, industry, keyspace_name, username, default_security_control_group)
VALUES('g2 ops', 'URM', 'United States', 'dVbj14Q40xgPkNs5f5pyzg==', 'Technology', 'g2', 'g2', 'industry');
