CREATE TABLE IF NOT EXISTS g2.scanner_hardware_staging (
 	site_or_ou_name 				text,
    ip_subnet_or_building 			text,   // 192.168.0.0, site/building
    internal_system_id 				text,   // UUID computed to ensure uniqueness
    system_type 					text,
    ip_address 						text, 	// Query with MV
    ip_netmask 						text,
    ip_gateway 						text,
    mac_address 					text,   // Search w/ Solr or MV
    cvss_scores						list <FROZEN <cvss_scores>>,
    cvss_temporal_score 			double,
    traceroute 						text,	// Test out: as list or text?
    import_date 					date,   // based on import job
    cpe_id 							text,   // product
    part 							text,   // should always be [h]ardware
    vendor 							text,
    product 						text,
    version 						text,
    version_update 					text,
    run_date 						date,  // From scan file
    cve_id 							text,  // vulnerability list
    end_of_sale 					date,
    end_of_life_date 				date,
    end_of_support_date 			date,
    node_impact_value 				float,
    neighbor_coefficient_value 		float,
    crown_jewel 					boolean,
    reportable_flag 				boolean,
    vendor_stencil_icon 			text,   // ref to file location
    patch_availability 				text,
    cyvar 							float,	  // Cyber Value at Risk
    vulnerability_count 			int,
    risk_likelihood 				float,
    audit_upsert 					frozen<audit_upsert>,
    PRIMARY KEY ((site_or_ou_name), ip_subnet_or_building, internal_system_id)
) WITH COMMENT = 'Stores hardware as imported from a vulnerability scan.';


