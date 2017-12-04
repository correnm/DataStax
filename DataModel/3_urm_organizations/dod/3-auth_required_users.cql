// The following roles are presumed in the system
//    sys_admin: role management
//    admin:     schema management
//    developer: data management


// The application admin account responsible for managing authentication. 
CREATE ROLE washington WITH SUPERUSER = false AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';
GRANT dod_developer to washington;

// The admin account must have select access on each tenant's keyspace to access the keyspace.users table for login validation
GRANT select on KEYSPACE dod to washington;

// Each organization (tenant) will have a separate keyspace and superuser account that's used for 
// connection pooling with the application
CREATE ROLE dod WITH SUPERUSER = false AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';
GRANT dod_developer to dod;



