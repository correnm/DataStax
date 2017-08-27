USE dod;

CREATE TABLE IF NOT EXISTS business_value_attribution (
site_id								uuid,		// must exist in the SITES table
business_process_id					uuid,
business_process_name				text,
business_interruption_threshold		text,		// must exist in BUSINESS_PRACTICE table
business_criticality				text,		// must exist in BUSINESS_PRACTICE table
information_classification			text,		// must exist in BUSINESS_PRACTICE table
audit_upsert 						frozen<audit_upsert>,
connected_to_hardware				set <frozen<hardware_relationships>>,
runs_on_hardware					set <frozen<hardware_relationships>>,
stored_records						set <frozen<record_types>>,
cyber_value_at_risk					decimal,
annual_revenue						decimal,
annual_revenue_year					int,			// YYYY
business_process_impact_value		decimal,		// BPIV as aggregated from NIV for runs_on_hardware
PRIMARY KEY (site_id, business_process_id)
)
WITH COMMENT = 'Defines the business processes at this organization and assigns the metrics needed to calculate the BPIV';