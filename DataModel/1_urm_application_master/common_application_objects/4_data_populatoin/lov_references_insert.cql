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
		'PUB':'Public Sector',
		'ED':'Education',
		'TEC':'Technology'
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