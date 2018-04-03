/*
Mod Date		author				Description
04-03-2018		sara.bergman		added country and industry information

*/


// Referenced by Table: appl_auth
insert into lov_references (database_column, optgroup_label, option_values)
values
('industry', 
'Industry', 
		{
		'PUB': 'Public Sector',
		'ED':  'Education',
		'TEC': 'Technology',
		'HLT': 'Health',
		'FIN': 'Financial',
		'SRV': 'Services',
		'LSC': 'Life Science',
		'RET': 'Retail',
		'COM': 'Communications',
		'IND': 'Industrial',
		'NRG': 'Energy',
		'CON': 'Consumer',
		'ENT': 'Entertainment',
		'HSP': 'Hospitality',
		'TRN': 'Transportation',
		'MDA': 'Media',
		'RES': 'Research'
		}
);

// Countries represented in the IBM Cost of Breach Study
insert into lov_references (database_column, optgroup_label, option_values)
values
('country_name', 
'Country', 
		{
		'US':'United States',
		'UK':'United Kingdom',
		'DE':'Germany',
		'AU':'Australia',
		'FR':'France',
		'BR':'Brazil',
		'JP':'Japan',
		'IT':'Italy',
		'IN':'India',
		'CA':'Canada',
		'ZA':'South Africa',
		'ME':'Middle East (incl United Arab Emirates and Saudi Arabia',
		'AS':'ASEAN Region (incl Singaport, Indonesia, the Philippines and Malaysis'
		}
);
