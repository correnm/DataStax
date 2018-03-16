// Application users have access only to a specific keyspace

// Organization = DOD created on 6/15/2017
// These roles will be created in the system_auth keyspace
CREATE ROLE IF NOT EXISTS dod_sys_admin;
GRANT AUTHORIZE ON KEYSPACE dod TO dod_sys_admin;

CREATE ROLE IF NOT EXISTS dod_admin;
GRANT CREATE    ON KEYSPACE dod TO dod_admin;
GRANT ALTER     ON KEYSPACE dod TO dod_admin;
GRANT DROP      ON KEYSPACE dod TO dod_admin;
GRANT SELECT    ON KEYSPACE appl_auth to dod_admin;
GRANT MODIFY    ON KEYSPACE appl_auth to dod_admin;

CREATE ROLE IF NOT EXISTS dod_developer;
GRANT SELECT    ON KEYSPACE dod TO dod_developer;
GRANT MODIFY    ON KEYSPACE dod TO dod_developer;
GRANT SELECT    ON KEYSPACE appl_auth to dod_developer;
GRANT MODIFY    ON KEYSPACE appl_auth to dod_developer;

CREATE ROLE IF NOT EXISTS dod_limited_developer;
GRANT SELECT    ON KEYSPACE dod TO dod_limited_developer;
GRANT MODIFY    ON KEYSPACE dod TO dod_limited_developer;
GRANT ALTER 	ON KEYSPACE dod to dod_limited_developer;
GRANT SELECT    ON KEYSPACE appl_auth to dod_limited_developer;
