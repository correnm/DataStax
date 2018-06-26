/**
Modifications
04-04-2018			sara.bergman			removed:
												internal_system_id 			uuid,      // FK to hardware table, UUID computed to ensure uniqueness
												record_type_r 				text,		// references.record_type = PHI, PCI, PI
												number_of_records 			bigint,		// for this record_type
												total_records 				bigint,		// across all record types
												line_of_business 			text,       // MV or Solr?
												business_criticality 		text,
												data_classification 		text,
												risk_factor 				text,
												business_interruption_threshold text,
												inherent_impact_value 		float,
												asset_visibility 			text,
												asset_type 					text,
												application_category 		text,   // Solr
												audit_upsert 				frozen<audit_upsert>,
												patch_availability text,
												cyvar 						float,                  // Cyber Value at Risk
												vulnerability_count 		int,
												risk_likelihood 			float,        //
											altered:
												primary key is hiearchy elements, partition on part and vendor
												version_update renamed to cpe_update
											added:
												edition						text,
												language					text,
												sw_edition					text,
												target_hw					text,
												target_sw					text,
												other						text
04-17-2018			sara.bergman			added tile text
06-21-2018			sara.bergman			added business_process_ids 		list<uuid>,
												  internal_system_id		uuid
											removed:
												installed_on_hardware to flatten out to be able to query the business_process_ids per installation on hardware
											altered:
												primary key to cpe_id, internal_system_id, vendor, product, version 
												name from software to org_platforms
*/

USE keyspacename;

CREATE TABLE  IF NOT EXISTS org_platforms (
cpe_id						text,		// could be hardware or software 
internal_system_id			uuid,
vendor						text,
product						text,
version						text,
part 						text,      // [a]pplication, [o]perating System, [h]ardware
cpe_update 					text,      // Search w/ Solr
edition						text,
language					text,
sw_edition					text,
target_hw					text,
target_sw					text,
other						text,
end_of_sale 				date,
end_of_life_date 			date,
end_of_support_date 		date,
title						text,
host_name					text, 		//to display for user
business_process_ids		list<UUID>, 
PRIMARY KEY (cpe_id, internal_system_id, vendor, product, version)
) WITH COMMENT = 'User can query the table for business processes that are affected by a specific cpe, both hardware and software.';

