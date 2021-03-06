USE dod;

CREATE TABLE IF NOT EXISTS hardware (
    site_or_ou_name 					text,
    ip_subnet_or_building 				text,  	// 192.168.0.0, site/building
    internal_system_id 					uuid,  	// UUID computed to ensure uniqueness
    system_type 						text,
    ip_address 							text,  	// Query with MV
    ip_netmask 							text,
    ip_gateway 							text,
    mac_address 						text,	// Search w/ Solr or MV
    cvss_scores							list<frozen<cvss_scores>>, //
    patch_availability 					text,
    import_date 						date,    // based on import job
    cpe_id 								text,    // product
    part 								text,    // should always be [h]ardware
    vendor 								text,
    product 							text,
    version 							text,
    version_update 						text,
    run_date 							date,   // From scan file
    end_of_sale 						date,
    end_of_life_date 					date,
    end_of_support_date 				date,
    node_impact_value 					float,		// output of VMASC algorithm
    neighbor_coefficient_value 			float,		// output of VMASC algorithm
    crown_jewel 						boolean,	// MBSE model element
    reportable_flag 					boolean,	// if TRUE, include in calculation of NIV
    vendor_stencil_icon					text,   	// refers to file location on web server for icons to use in viz
    cyvar 								float,		// output of Cyber Value at Risk (TBD)
    vulnerability_count 				int,		// total number of CVE IDs identified for this item
    risk_likelihood 					float,  	//
    audit_upsert 						frozen<audit_upsert>,
	connected_elements					list <frozen <connected_elements>>, // traceroute,
	asset_type							text,		// business practice metrics
	asset_visibility					text,		// business practice metrics
	business_interruption_threshold		text,		// business practice metrics
	business_criticality				text,		// business practice metrics
	information_classification_value	text,		// business practice metrics
	os_general							text,		// windows, linus
	operating_system					text,		// specific operating system
	installed_software					list <frozen <installed_software>>, 
	host_name						    text,
    PRIMARY KEY ((site_or_ou_name), ip_subnet_or_building, internal_system_id)
) WITH COMMENT = 'Infrastructure maintained for the site obtained via a vulnerability scan or a physical survey' ;

// CREATE MATERIALIZED VIEW hardware_by_internal_system_id
// CREATE MATERIALIZED VIEW hardware_by_ip_address
