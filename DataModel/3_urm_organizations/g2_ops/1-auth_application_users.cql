// Application users have access only to a specific keyspace
// Organization = G2 ops
// These roles will be created in the system_auth keyspace
CREATE ROLE IF NOT EXISTS g2_sys_admin;
GRANT AUTHORIZE ON KEYSPACE g2 TO g2_sys_admin;

CREATE ROLE IF NOT EXISTS g2_admin;
GRANT CREATE    ON KEYSPACE g2 TO g2_admin;
GRANT ALTER     ON KEYSPACE g2 TO g2_admin;
GRANT DROP      ON KEYSPACE g2 TO g2_admin;
GRANT SELECT    ON KEYSPACE appl_auth to g2_admin;
GRANT MODIFY    ON KEYSPACE appl_auth to g2_admin;


CREATE ROLE IF NOT EXISTS g2_developer;
GRANT SELECT    ON KEYSPACE g2 TO g2_developer;
GRANT MODIFY    ON KEYSPACE g2 TO g2_developer;
GRANT SELECT    ON KEYSPACE appl_auth to g2_developer;
GRANT MODIFY    ON KEYSPACE appl_auth to g2_developer;

CREATE ROLE IF NOT EXISTS g2_limited_developer;
GRANT SELECT    ON KEYSPACE g2 TO g2_limited_developer;
GRANT MODIFY    ON KEYSPACE g2 TO g2_limited_developer;
GRANT ALTER 	ON KEYSPACE g2 to g2_limited_developer;
GRANT SELECT    ON KEYSPACE appl_auth to dod_limited_developer;