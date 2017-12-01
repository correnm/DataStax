// The following roles are presumed in the system
//    sys_admin: role management
//    admin:     schema management
//    developer: data management

// Each organization (tenant) will have a separate keyspace and superuser account that's used for 
// connection pooling with the application
CREATE ROLE g2 WITH SUPERUSER = false AND LOGIN = true AND password = 'super_secret_password_only_known_to_a_few';
GRANT g2_developer to g2;



