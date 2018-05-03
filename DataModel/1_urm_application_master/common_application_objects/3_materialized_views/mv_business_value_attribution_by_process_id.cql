/**
Enables the ability to query the business value attribution table by business_process_id

Modifications
04-27-2018			sara.bergman		created mv
*/

CREATE MATERIALIZED VIEW IF NOT EXISTS business_value_attribution_by_process_id 
AS SELECT 	business_process_id,
			site_id,								
			business_process_name,				
			business_interruption_threshold	,	
			business_criticality,				
			information_classification,			
			audit_upsert, 						
			runs_on_hosts,											
			cyber_value_at_risk	,				
			annual_revenue,						
			annual_revenue_year	,				
			business_process_impact_value,		
			expected_loss_value_min,				
			expected_loss_value_max,				
			expected_loss_value_distribution,	
			risk_appetite,						
			risk_likelihood,                     
			default_breach_type,						
			resistance_strength,					
			record_count			
FROM business_value_attribution
	WHERE 	business_process_id IS NOT NULL 
		AND site_id IS NOT NULL
	PRIMARY KEY (business_process_id,site_id)
	WITH comment = 'Satisfies query: Select * from business_value_attribution_by_process_id where business_process_id = ?;.'