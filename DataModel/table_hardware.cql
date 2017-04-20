CREATE TABLE IF NOT EXISTS g2.hardware (
    org_id 							uuid,
    site_or_ou_name 				text,
    ip_subnet_or_building 			text,  	// 192.168.0.0, site/building
    internal_system_id 				uuid,  	// UUID computed to ensure uniqueness
    system_type 					text,
    ip_address 						text,  	// Query with MV
    ip_netmask 						text,
    ip_gateway 						text,
    mac_address 					text,	// Search w/ Solr or MV
    cvss_scores						list<frozen<cvss_scores>>, //
    patch_availability 				text,
    traceroute 						text,    // Test out: as list or text?
    import_date 					date,    // based on import job
    cpe_id 							text,    // product
    part 							text,    // should always be [h]ardware
    vendor 							text,
    product 						text,
    version 						text,
    version_update 					text,
    run_date 						date,   // From scan file
    end_of_sale 					date,
    end_of_life_date 				date,
    end_of_support_date 			date,
    node_impact_value 				float,		// output of VMASC algorithm
    neighbor_coefficient_value 		float,		// output of VMASC algorithm
    crown_jewel 					boolean,	// MBSE model element
    reportable_flag 				boolean,	// if TRUE, include in calculation of NIV
    vendor_stencil_icon				text,   	// refers to file location on web server for icons to use in viz
    cyvar 							float,		// output of Cyber Value at Risk (TBD)
    vulnerability_count 			int,		// total number of CVE IDs identified for this item
    risk_likelihood 				float,  	//
    audit_insert 					frozen<audit_insert>,
	audit_update					frozen<audit_update>,
	connected_elements				frozen<connected_elements>,
    PRIMARY KEY ((org_id, site_or_ou_name), ip_subnet_or_building, internal_system_id)
) WITH COMMENT = 'Infrastructure maintained for the site obtained via a vulnerability scan or a physical survey' ;

// CREATE MATERIALIZED VIEW hardware_by_internal_system_id
// CREATE MATERIALIZED VIEW hardware_by_ip_address
