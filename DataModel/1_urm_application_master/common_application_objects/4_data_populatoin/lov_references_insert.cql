/*
Mod Date		author				Description
02-28-2018		corren.mccoy		changed the option_label and database_column for country. Changed to country_name to match tables
02-28-2018		corren.mccoy		added new lookup for breach types to IBM Ponemon study
*/


// Referenced by Table: users
insert into lov_references (database_column, optgroup_label, option_values)
values
('application_role_name', 
'User Roles', 
		{
		'USR':'user',
		'ADM':'admin'
		}
);

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

// Referenced by Table: business_practice
insert into lov_references (database_column, optgroup_label, option_values)
values
('category', 
'Metric Categories', 
		{
		'BIT':'Business Interruption Threshold',
		'BC':'Business Criticality',
		'AT':'Asset Type',
		'AT':'Asset Visibility',
		'IC':'Information Classification Value'
		}
);

	
// Referenced by Table: hardware AND business_practice
insert into lov_references (database_column, optgroup_label, option_values)
values
('asset_type', 
'Asset Type', 
		{
		'DST':'Development/Staging/Testing',
		'IT':'IT Tools',
		'IN':'Infrastructure',
		'PR':'Production'
		}
);

insert into lov_references (database_column, optgroup_label, option_values)
values
('asset_visibility', 
'Asset Visibility', 
		{
		'BP':'Business Partners',
		'CF':'Customer Facing',
		'IWO':'Internal Workforce Only'
		}
);

// Referenced by Table: business_value_attribution AND business_practice
insert into lov_references (database_column, optgroup_label, option_values)
values
('information_classification', 
'Information Classification', 
		{
		'CO':'Confidential',
		'PU':'Public',
		'RE':'Restricted',
		'SE':'Sensitive'
		}
);

insert into lov_references (database_column, optgroup_label, option_values)
values
('business_interruption_threshold', 
'Business Interruption Threshold', 
		{
		'LT2':'Less than 2 hours',
		'24':'Between 2 and 4 hours',
		'2448':'Between 24 and 48 hours',
		'424':'Between 4 and 24 hours',
		'4872':'Between 48 and 72 hours'
		}
);

insert into lov_references (database_column, optgroup_label, option_values)
values
('business_criticality', 
'Business Criticality', 
		{
		'CR':'Critical',
		'HI':'High',
		'LO':'Low',
		'MED':'Medium',
		'NON':'None'
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

// Referenced by business_value_attribution, industry_breach_costs
// See IBM Ponemon, Figure 5
insert into lov_references (database_column, optgroup_label, option_values)
values
('breach_type', 
'Breach Type', 
		{
		'MCA':'Malicious and criminal attach',
		'SG':'System glith',
		'HE':'Human error',
		'UNK':'Unspecified'
		}
);