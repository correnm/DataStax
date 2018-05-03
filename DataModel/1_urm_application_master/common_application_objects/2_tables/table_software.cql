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
*/

USE keyspacename;

CREATE TABLE  IF NOT EXISTS software (
part 						text,      // [a]pplication, [o]perating System, [h]ardware
vendor 						text,      // Search w/ Solr
product 					text,      // Search w/ Solr
version 					text,      // Search w/ Solr
cpe_update 					text,      // Search w/ Solr
edition						text,
language					text,
sw_edition					text,
target_hw					text,
target_sw					text,
other						text,
cpe_id 						text,      // product
end_of_sale 				date,
end_of_life_date 			date,
end_of_support_date 		date,
title						text,
installed_on_hardware		list<frozen<hardware_relationships>>, 
PRIMARY KEY ((part, vendor), product, version, cpe_update, edition, language, sw_edition, target_hw, target_sw, other)
) WITH COMMENT = 'Software library within organization that will populate installed_software in hardware table and include hardware impacted.';