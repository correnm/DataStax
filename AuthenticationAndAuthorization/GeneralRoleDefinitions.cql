//
//   General Role Definitions: https://docs.datastax.com/en/cql/3.3/cql/cql_using/useSecurePermission.html
//
//   Note object hierarchies in Cassandra:
//       ALL KEYSPACES > KEYSPACE > TABLE
//       ALL FUNCTIONS > KEYSPACE > FUNCTION

// sys_admin: role management
CREATE ROLE IF NOT EXISTS sys_admin;
GRANT AUTHORIZE ON ALL KEYSPACES TO sys_admin;
GRANT AUTHORIZE ON ALL FUNCTIONS TO sys_admin;
GRANT CREATE    ON ALL ROLES     TO sys_admin;
GRANT ALTER     ON ALL ROLES     TO sys_admin;
GRANT DROP      ON ALL ROLES     TO sys_admin;
GRANT DESCRIBE  ON ALL ROLES     TO sys_admin;

// admin: schema management
CREATE ROLE IF NOT EXISTS admin;
GRANT SELECT    ON ALL KEYSPACES TO admin;
GRANT MODIFY    ON ALL KEYSPACES TO admin;
GRANT CREATE    ON ALL KEYSPACES TO admin;
GRANT ALTER     ON ALL KEYSPACES TO admin;
GRANT DROP      ON ALL KEYSPACES TO admin;
GRANT CREATE    ON ALL FUNCTIONS TO admin;
GRANT ALTER     ON ALL FUNCTIONS TO admin;
GRANT DROP      ON ALL FUNCTIONS TO admin;

// developer: data management
CREATE ROLE IF NOT EXISTS developer;
GRANT SELECT    ON ALL KEYSPACES TO developer;
GRANT MODIFY    ON ALL KEYSPACES TO developer;
GRANT EXECUTE   ON ALL FUNCTIONS TO developer;


// sample keyspace-specific roles for KEYSPACE keyspace1
//    CREATE ROLE IF NOT EXISTS keyspace1_sys_admin;
//    GRANT AUTHORIZE ON KEYSPACE keyspace1 TO keyspace1_sys_admin;
//
//    CREATE ROLE IF NOT EXISTS keyspace1_admin;
//    GRANT CREATE    ON KEYSPACE keyspace1 TO keyspace1_admin;
//    GRANT ALTER     ON KEYSPACE keyspace1 TO keyspace1_admin;
//    GRANT DROP      ON KEYSPACE keyspace1 TO keyspace1_admin;
//
//    CREATE ROLE IF NOT EXISTS keyspace1_developer;
//    GRANT SELECT    ON KEYSPACE keyspace1 TO keyspace1_developer;
//    GRANT MODIFY    ON KEYSPACE keyspace1 TO keyspace1_developer;
//    GRANT EXECUTE   ON KEYSPACE keyspace1 TO keyspace1_developer;



