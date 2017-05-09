// Application users have access only to a specific keyspace

CREATE ROLE IF NOT EXISTS g2_sys_admin;
GRANT AUTHORIZE ON KEYSPACE g2 TO g2_sys_admin;

CREATE ROLE IF NOT EXISTS g2_admin;
GRANT CREATE    ON KEYSPACE g2 TO g2_admin;
GRANT ALTER     ON KEYSPACE g2 TO g2_admin;
GRANT DROP      ON KEYSPACE g2 TO g2_admin;

CREATE ROLE IF NOT EXISTS g2_developer;
GRANT SELECT    ON KEYSPACE g2 TO g2_developer;
GRANT MODIFY    ON KEYSPACE g2 TO g2_developer;

