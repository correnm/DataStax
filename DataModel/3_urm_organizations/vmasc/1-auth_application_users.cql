// Application users have access only to a specific keyspace

// Organization = VMASC created on 6/15/2017
// These roles will be created in the system_auth keyspace
CREATE ROLE IF NOT EXISTS vmasc_sys_admin;
GRANT AUTHORIZE ON KEYSPACE vmasc TO vmasc_sys_admin;

CREATE ROLE IF NOT EXISTS vmasc_admin;
GRANT CREATE    ON KEYSPACE vmasc TO vmasc_admin;
GRANT ALTER     ON KEYSPACE vmasc TO vmasc_admin;
GRANT DROP      ON KEYSPACE vmasc TO vmasc_admin;
GRANT SELECT    ON KEYSPACE appl_auth to vmasc_admin;
GRANT MODIFY	ON KEYSPACE appl_auth to vmasc_admin;

CREATE ROLE IF NOT EXISTS vmasc_developer;
GRANT SELECT    ON KEYSPACE vmasc TO vmasc_developer;
GRANT MODIFY    ON KEYSPACE vmasc TO vmasc_developer;
GRANT ALTER 	ON KEYSPACE vmasc to vmasc_developer;
GRANT SELECT    ON KEYSPACE appl_auth to vmasc_developer;
GRANT MODIFY	ON KEYSPACE appl_auth to vmasc_developer;