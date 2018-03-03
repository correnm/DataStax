CREATE TABLE IF NOT EXISTS appl_auth.industry_security_incidents (
	publication_year				double,		// YYYY that Verizon DBIR study was published
	verizon_dbir_industry_name		text,		// see Table 1 for industry name e.g., Health, Financial
	sample_size						double,		// number of organizations in the industry who contributed data
	incidents_total					double,
	incidents_small					double,
	incidents_large					double,
	incidents_unk					double,
	breaches_total					double,
	breaches_small					double,
	breaches_large					double,
	breaches_unk					double,	
	probability_of_attack			double,		// breaches_total/incidents_total
    PRIMARY KEY (publication_year, verizon_dbir_industry_name)	
)
with comment = 'Used to collect metrics published in Verizon Data Breach Incident Report';
