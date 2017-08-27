USE dod;

CREATE TABLE IF NOT EXISTS scanner_hardware_staging (
 	site_or_ou_name 				text,
    ip_subnet_or_building 			text,   // 192.168, site/building
    internal_system_id 				uuid,   // UUID computed to ensure uniqueness
    system_type 					text,
    ip_address 						text, 	// Query with MV
    mac_address 					text,   // Search w/ Solr or MV
    cvss_scores						list <FROZEN <cvss_scores>>,
    connected_elements 				list <FROZEN<connected_elements>>,
    import_date 					date,   // based on import job
    cpe_id 							text,   // product
    run_date 						date,  // From scan file
    audit_upsert 					frozen<audit_upsert>,
	vendor							text,
	vulnerability_count				int,
	host_name						text,
    PRIMARY KEY ((site_or_ou_name), ip_subnet_or_building, internal_system_id)
) WITH COMMENT = 'Stores hardware as imported from a vulnerability scan.';


