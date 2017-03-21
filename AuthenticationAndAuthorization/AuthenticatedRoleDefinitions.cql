//
// "User" Definitions: https://docs.datastax.com/en/cql/3.3/cql/cql_using/useSecureRoles.html
//

// The following roles are presumed in the system
//    sys_admin: role management
//    admin:     schema management
//    developer: data management

// These commands can be run in cqlsh or DevCenter

CREATE ROLE g2su WITH SUPERUSER = true AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';

ALTER USER cassandra WITH PASSWORD 'new_cassandra_login_password_goes_here' AND SUPERUSER = false;


// Create a first user and assign global permissions
CREATE ROLE IF NOT EXISTS first_user WITH PASSWORD = 'change_this';
GRANT sys_admin TO first_user;
GRANT admin     TO first_user;
GRANT developer TO first_user;


// Create another user and assign just developer access to one keyspace
CREATE ROLE IF NOT EXISTS second_user WITH PASSWORD = 'change_this';
GRANT keyspace1_developer TO second_user;

