USE dod;

CREATE TYPE IF NOT EXISTS hardware_relationships(
    site_or_ou_name 					text,
    ip_subnet_or_building 				text,  	
    internal_system_id 					uuid 
);