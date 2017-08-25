USE keyspaceName;
// Application users have access only to a specific keyspace
// CHANGE keyspacename to the organization's name as recorded in appl_auth
CREATE ROLE IF NOT EXISTS keyspacename_sys_admin;
GRANT AUTHORIZE ON KEYSPACE keyspacename TO keyspacename_sys_admin;

CREATE ROLE IF NOT EXISTS keyspacename_admin;
GRANT CREATE    ON KEYSPACE keyspacename TO keyspacename_admin;
GRANT ALTER     ON KEYSPACE keyspacename TO keyspacename_admin;
GRANT DROP      ON KEYSPACE keyspacename TO keyspacename_admin;

CREATE ROLE IF NOT EXISTS keyspacename_developer;
GRANT SELECT    ON KEYSPACE keyspacename TO keyspacename_developer;
GRANT MODIFY    ON KEYSPACE keyspacename TO keyspacename_developer;