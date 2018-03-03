CREATE TABLE IF NOT EXISTS appl_auth.industry_breach_costs (
	publication_year			double,		// YYYY that IBM study was published
    country_name 				text,		// populate using values in lov_references
	industry_name				text,		// see figure 4 for industry name e.g., Health, Financial
	per_capita_cost				double,		// see figure 4 for dollar amount for the industry e.g., $380
	direct_cost_pct				double,		// see figure 15 for allocation e.g., direct/(avg per capita)
	indirect_cost_pct			double,		// see figure 15 for allocation e.g., direct/(avg per capita)
	direct_per_capita_cost		double,		// see figure 15 for direct portion of per capita cost
	indirect_per_capita_cost	double,		// see figure 15 for indirect portion of per capita cost
	verizon_dbir_industry_name	text,		// mapping of IBM Ponemon to breach/incident references in Verizon DBIR 
    PRIMARY KEY (publication_year, country_name, industry_name)	
)
with comment = 'Used to collect metrics published in Ponemon Institute Cost of Breach Study';
