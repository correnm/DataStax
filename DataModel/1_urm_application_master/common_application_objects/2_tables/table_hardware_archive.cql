

/**
Modifications
date				author			description
2/13/2018			sara.bergman	removed risk_likelihood float, to be placed into business_value_attribution
02-28-2018			corren.mccoy	column changed to business_process_ids to list<uuid>
04-05-2018			sara.bergman	Modified/Renamed installed_software to software_installed list<text>
									Modified/renamed run_date to run_datetime timestamp
									removed cyvar text
04-27-2018			sara.bergman	removed neighbor_coefficient_value float, 
											ip_gateway text, 
											ip_netmask text 
									altered node_impact_value from float to decimal
									altered installed_software from list<text> to list<software>
*/

USE keyspacename;

CREATE TABLE IF NOT EXISTS hardware_archive (
    site_id								uuid,
    ip_subnet_or_building 				text,  	// 192.168.0.0, site/building
	archive_date 						date,   // date the current architecture was archived
    internal_system_id 					uuid,  	// UUID computed to ensure uniqueness
    system_type 						text,
    ip_address 							text,  	// Query with MV
    mac_address 						text,	// Search w/ Solr or MV
    cvss_scores							list<frozen<cvss_scores>>, //
    patch_availability 					text,
    cpe_id 								text,    // product
    part 								text,    // should always be [h]ardware
    vendor 								text,
    product 							text,
    version 							text,
    version_update 						text,
    run_date 							date,   // From scan file in format YYYY-MM-DD (last entry)
    end_of_sale 						date,
    end_of_life_date 					date,
    end_of_support_date 				date,
    node_impact_value 					decimal,		// output of VMASC algorithm
    crown_jewel 						boolean,	// MBSE model element
    reportable_flag 					boolean,	// if TRUE, include in calculation of NIV
    vendor_stencil_icon					text,   	// refers to file location on web server for icons to use in viz
    vulnerability_count 				int,		// total number of CVE IDs identified for this item
    audit_upsert 						frozen<audit_upsert>,
	connected_elements					list <frozen <connected_elements>>, // traceroute,
	asset_type							text,		// business practice metrics
	asset_visibility					text,		// business practice metrics
	os_general							text,		// windows, linux
	operating_system					text,		// specific operating system
	installed_software					list <software>, 
	host_name						    text,
	business_process_ids				list<uuid>, // valid entries in business_value_attribution table
    PRIMARY KEY (site_id, ip_subnet_or_building, archive_date, internal_system_id)
) WITH COMMENT = 'Historical Infrastructure (by archive date) maintained for the site obtained via a vulnerability scan or a physical survey' ;

