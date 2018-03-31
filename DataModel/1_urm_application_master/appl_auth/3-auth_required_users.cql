// The following roles are presumed in the system
//    sys_admin: role management
//    admin:     schema management
//    developer: data management

CREATE ROLE ops_center WITH SUPERUSER = true AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';

// The application admin account responsible for managing authentication and connection pooling for the organization
CREATE ROLE washington WITH SUPERUSER = false AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';

// Must have write privileges on administrative table for data management
CREATE ROLE IF NOT EXISTS appl_auth_developer;
GRANT SELECT ON KEYSPACE appl_auth TO appl_auth_developer;
GRANT MODIFY ON KEYSPACE appl_auth TO appl_auth_developer;
GRANT ALTER on KEYSPACE appl_auth TO appl_auth_developer;

// manage user passwords
GRANT select on KEYSPACE system_auth to appl_auth_developer;
GRANT select on KEYSPACE system_schema to appl_auth_developer;
GRANT describe on all roles to appl_auth_developer;
GRANT alter on all roles to appl_auth_developer;

// The admin account must have select access on each tenant's keyspace to access the keyspace.users table for login validation
GRANT appl_auth_developer to washington;
GRANT select on KEYSPACE g2 to washington;
GRANT select on KEYSPACE vmasc to washington;
GRANT g2_developer to washington;

// Each organization (tenant) will have a separate keyspace and superuser account that's used for 
// connection pooling with the application
CREATE ROLE g2 WITH SUPERUSER = false AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';
GRANT g2_developer to g2;

CREATE ROLE vmasc WITH SUPERUSER = false AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';
GRANT vmasc_developer to vmasc;





