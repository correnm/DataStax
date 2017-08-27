// Application users have access only to a specific keyspace

// Organization = DOD created on 8/26/2017
CREATE ROLE IF NOT EXISTS dod_sys_admin;
GRANT AUTHORIZE ON KEYSPACE dod TO dod_sys_admin;

CREATE ROLE IF NOT EXISTS dod_admin;
GRANT CREATE    ON KEYSPACE dod TO dod_admin;
GRANT ALTER     ON KEYSPACE dod TO dod_admin;
GRANT DROP      ON KEYSPACE dod TO dod_admin;

CREATE ROLE IF NOT EXISTS dod_developer;
GRANT SELECT    ON KEYSPACE dod TO dod_developer;
GRANT MODIFY    ON KEYSPACE dod TO dod_developer;