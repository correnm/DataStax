

/**
Modifications
date				author			description
2/13/2018			sara.bergman	added risk_likelihood
									removed connected_to_hardware

*

USE keyspacename;

CREATE TABLE IF NOT EXISTS business_value_attribution (
site_id								uuid,		// must exist in the SITES table
business_process_id					uuid,
business_process_name				text,
business_interruption_threshold		text,		// must exist in BUSINESS_PRACTICE table
business_criticality				text,		// must exist in BUSINESS_PRACTICE table
information_classification			text,		// must exist in BUSINESS_PRACTICE table
audit_upsert 						frozen<audit_upsert>,
runs_on_hosts						set <frozen<hardware_relationships>>,
stored_records						set <frozen<record_types>>,
cyber_value_at_risk					decimal,
annual_revenue						decimal,
annual_revenue_year					int,			// YYYY
business_process_impact_value		decimal,		// BPIV as aggregated from NIV for runs_on_hardware,
expected_loss_value_min				decimal,
expected_loss_value_max				decimal,
expected_loss_value_distribution	list<decimal>,
risk_appetite						decimal,
risk_likelihood                     decimal,
max_breach_loss						decimal
PRIMARY KEY (site_id, business_process_id)
)
WITH COMMENT = 'Defines the business processes at this organization and assigns the metrics needed to calculate the BPIV';

REM table modification 10-5-2017
alter table business_value_attribution
add expected_loss_value_min decimal;

alter table business_value_attribution
add expected_loss_value_max decimal;

alter table business_value_attribution
add  expected_loss_value_distribution list<decimal>;

alter table business_value_attribution
add risk_appetite decimal;

alter table business_value_attribution
add max_breach_loss decimal;

