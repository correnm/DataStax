USE keyspacename;

CREATE TYPE IF NOT EXISTS hardware_relationships(
    site_id 							uuid,
    ip_subnet_or_building 				text,  	
    internal_system_id 					uuid 
);