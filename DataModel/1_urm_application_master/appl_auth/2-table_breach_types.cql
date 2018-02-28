CREATE TABLE IF NOT EXISTS appl_auth.breach_types (
	publication_year	double,		// YYYY that IBM study was published
    country_name 		text,		// populate using values in lov_references
	breach_type			text,		// root cause of data breach as described in IBM Ponemon study
	distribution_pct	double,		// see figure 5 for percentage. Total for the country should = 100%
	per_capita_cost		double,		// see figure 6 for dollar amount
    PRIMARY KEY (publication_year, country_name, breach_type)	
)
with comment = 'Used to collect metrics in Ponemon Institute Cost of Breach Study';

// Data values for United States as published in 2017 study (page 11)
insert into breach_types (publication_year, country_name, breach_type, distribution_pct, per_capita_cost)
values(2017, 'United States', 'Malicious or criminal attack', 0.52, 244);

insert into breach_types (publication_year, country_name, breach_type, distribution_pct, per_capita_cost)
values(2017, 'United States', 'System glitch', 0.24, 209);

insert into breach_types (publication_year, country_name, breach_type, distribution_pct, per_capita_cost)
values(2017, 'United States', 'Human error', 0.24, 200);
