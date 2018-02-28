CREATE TABLE IF NOT EXISTS appl_auth.industry_breach_costs (
	publication_year	double,		// YYYY that IBM study was published
    country_name 		text,		// populate using values in lov_references
	industry_name		text,		// see figure 4 for industry name e.g., Health, Financial
	per_capita_cost		double,		// see figure 4 for dollar amount for the industry e.g., $380
	direct_cost_pct		double,		// see figure 15 for allocation e.g., direct/(direct + indirect)
    PRIMARY KEY (publication_year, country_name, industry_name)	
)
with comment = 'Used to collect metrics published in Ponemon Institute Cost of Breach Study';
