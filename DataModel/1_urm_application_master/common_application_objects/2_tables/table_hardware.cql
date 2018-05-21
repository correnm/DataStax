
/**
Modifications
date				author				description
2/13/2018			sara.bergman		removed risk_likelihood float, to be placed into business_value_attribution
02-28-2018			corren.mccoy		column changed to business_process_ids to list<uuid>
04-04-2018			sara.bergman		removed patch_availability
										modified installed_software to data type list<text>
										modified/renamed run_date to run_datetime timestamp
										removed cyvar text
04-27-2018			sara.bergman		removed neighbor_coefficient_value float, 
												ip_gateway text, 
												ip_netmask text  
										altered node_impact_value from float to decimal
										altered installed_software from list<text> to list<software>
06-18-2018			corren.mccoy		add deleted so records can be removed later by a batch job. Also will update runs_on_hosts for any associated business processes.
										Deleted records should not be archived or used in summary (day, week, month)
*/

CREATE TABLE IF NOT EXISTS hardware (
    site_id								uuid,
    ip_subnet_or_building 				text,  	// 192.168.0.0, site/building
    internal_system_id 					uuid,  	// UUID computed to ensure uniqueness
    system_type 						text,
    ip_address 							text,  	// Query with MV
    mac_address 						text,	// Search w/ Solr or MV
    cvss_scores							list<frozen<cvss_scores>>, //
    import_date 						date,    // based on import job in format YYYY-MM-DD
    cpe_id 								text,    // product
    crown_jewel 						boolean,
	part 								text,    // should always be [h]ardware
    vendor 								text,
    product 							text,
    version 							text,
    version_update 						text,
    run_datetime						timestamp,   // From scan file in format YYYY-MM-DD, dual purposing field to be updated every time cvss_scores are updated
    end_of_sale 						date,
    end_of_life_date 					date,
    end_of_support_date 				date,
    node_impact_value 					decimal,		// output of VMASC algorithm
    reportable_flag 					boolean,	// if TRUE, include in calculation of NIV
    vendor_stencil_icon					text,   	// refers to file location on web server for icons to use in viz
    vulnerability_count 				int,		// total number of CVE IDs identified for this item
    audit_upsert 						frozen<audit_upsert>,
	connected_elements					list <frozen <connected_elements>>, // traceroute,
	asset_type							text,		// business practice metrics
	asset_visibility					text,		// business practice metrics
	os_general							text,		// windows, linus
	operating_system					text,		// specific operating system
	installed_software					list <software>, 
	host_name						    text,
	business_process_ids				list<uuid>, // valid entries in business_value_attribution table
	deleted								boolean, // marked for deletion. Record will be removed with a batch job.
    PRIMARY KEY (site_id, ip_subnet_or_building, internal_system_id)
) WITH COMMENT = 'Infrastructure maintained for the site obtained via a vulnerability scan or a physical survey' ;

// CREATE MATERIALIZED VIEW hardware_by_internal_system_id
// CREATE MATERIALIZED VIEW hardware_by_ip_address
