CREATE TABLE  IF NOT EXISTS g2.software (
internal_system_id 			uuid,      // FK to hardware table, UUID computed to ensure uniqueness
cpe_id 						text,      // product
part 						text,      // [A]pplication, [O]perating System,
vendor 						text,      // Search w/ Solr
product 					text,      // Search w/ Solr
version 					text,      // Search w/ Solr
version_update 				text,      // Search w/ Solr
end_of_sale 				date,
end_of_life_date 			date,
end_of_support_date 		date,
record_type_r 				text,		// references.record_type = PHI, PCI, PI
number_of_records 			bigint,		// for this record_type
total_records 				bigint,		// across all record types
line_of_business 			text,        // MV or Solr?
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
PRIMARY KEY ((internal_system_id), part, vendor, product, version)
) WITH COMMENT = 'Contains software as imported from a vulnerability scan.';