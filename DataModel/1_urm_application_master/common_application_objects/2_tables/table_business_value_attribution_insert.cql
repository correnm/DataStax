USE keyspace;

// remove the old data before reloading if its the same site
truncate table business_value_attribution;

// Acute Care Hospital, Virginia Beach, site id =96a3378e-2f67-4b9d-ab67-9ee9d533b88f
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
80864.26, 				2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 				'Between 2 and 4 hours',
6.59,					'Burns', 								 
391.50,					'Confidential',
2500,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 02b388e8-75cf-4431-a182-fd18a8171417
}});
//Invasive cardiology
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 		uuid(),
11967910, 					2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 					'Between 4 and 24 hours', 
3.7,						'Invasive Cardiology', 	
62640, 						'Confidential',
550,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 02b388e8-75cf-4431-a182-fd18a8171417
}});
// Open heart surgery
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 		uuid(), 
0, 						2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 					'Between 4 and 24 hours',
4.8,						'Open Heart Surgery',
0,						'Confidential',
100,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 02b388e8-75cf-4431-a182-fd18a8171417
}});
// Dental
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 		uuid(), 
161728, 					2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 						'Between 48 and 72 hours', 				
6.3,						'Dental', 								 
783, 						'Confidential',
4500,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 03231e21-a454-4f07-8df5-4697c9f52a14
}});
// Dermatology
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 		uuid(), 
161728, 					2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 					'Between 48 and 72 hours', 				
6.3,						'Dermatology', 								 
5481, 						'Confidential',
150,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 03231e21-a454-4f07-8df5-4697c9f52a14
}});
// Endocrinology
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
16011123, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 48 and 72 hours', 				9.3,
'Endocrinology', 								 
84172, 								'Confidential',
400,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 03dec09e-2993-449c-8bf5-17505ab4ef25
}});
// General Medicine
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
28949405, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				4.9,
'General Medicine', 								 
151902, 								'Confidential',
3800,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id:0dc91f7f-56dc-4e2f-8b49-5c23cba02de7
}}
);
// General surgery
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
37440152, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				3.3,
'General Surgery', 					 
196533, 							'Restricted',
502,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id:ee89bfa9-aaf1-4c9d-a434-1ef2661bb98b
}}
);
// Accounts Payable
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 
'Greater than 72 hours', 				2.3,
'Accounts Payable', 					 
5295560, 					'Restricted',
8157, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 55963e24-573f-47f4-b636-9d63ec6cc9b5
}}
);
// Gynecology
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 		uuid(), 
2345063, 					2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 						'Greater than 72 hours',
2.3,						'Gynecology', 					 
5872,						'Confidential',
15, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 6b767db2-79c0-4d5e-83d4-6c2b389abf89
}}
);
//Hematology
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
8490747, 				2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 					'Greater than 72 hours', 				
1.4,					'Hematology',
44631,					'Sensitive',
114, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: b321d442-74fc-4929-afcd-05cfd9363f9a
}}
);
//Infectious Disease
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
53693869, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 
'Greater than 72 hours', 				5.3,
'Infectious Diseases', 	
281880, 							'Sensitive',								 
720, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 9b9d4028-2248-4b5b-acd0-90a76a644677
}}
);
// Neonatology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					runs_on_hosts, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
16496309, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 4 and 24 hours', 				8.7,
'Neonatology', 	
281880, 							'Sensitive',				 
221, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: a3cffcdc-2cd8-4770-ac12-efbcbee407b8
}}
);
// Normal Newborns
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
142563691, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				6.8,
'Normal Newborns', 					 
748159, 							'Sensitive',
221, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: d0f7c9d0-bf46-482d-9f3a-d059876bca1f
}}
);
// Obstetrics/Delivery
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
154693330, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				3.9,
'Obstetrics/Delivery', 					 
801792, 							'Sensitive',
2048, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: b321d442-74fc-4929-afcd-05cfd9363f9a
}}
);
// Oncology
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
9299389, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				9.2,
'Oncology', 
48937, 							'Sensitive',					 
125, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 42ff8cf9-bff6-4d14-a2d3-0c943dbf4b50
}}
);
// Oncology Surgery
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
404297, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				1.1,
'Oncology Surgery', 
1958, 							'Sensitive',					 
5, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 9b9d4028-2248-4b5b-acd0-90a76a644677
}}
);
// Opthamology
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
323437, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Greater than 72 hours', 				1.2,
'Opthamology', 	
1566, 							'Sensitive',				 
4, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 9b9d4028-2248-4b5b-acd0-90a76a644677
}}
);
//Orthopedic Surgery
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
36548453, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Greater than 72 hours', 				6.1,
'Orthopedic Surgery', 
191835, 							'Sensitive',					 
490, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: d1679cac-dbf3-4c05-b138-fcc12fc5e884
}}
);
// Plastic Surgery
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Greater than 72 hours', 				6.5,
'Plastic Surgery', 					 
0, 							'Sensitive',
0,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: d1679cac-dbf3-4c05-b138-fcc12fc5e884
}}
);
//Psychiatry
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
3234376, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 2 and 4 hours', 				5.8,
'Psychiatry', 		
16385, 							'Restricted',			 
43, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 9b9d4028-2248-4b5b-acd0-90a76a644677
}}
);
// Trauma
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
2991798, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				1.9,
'Trauma, Multiple', 
15660, 							'Restricted',					 
40, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: d29d3084-78ad-4ae3-b440-777b3db5a436
}}
);
// Admissions
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				2.7,
'Admissions', 		
32305337, 							'Restricted',			 
71391, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 32f883c4-59b4-4799-b193-c3331614e75c
},
{
site_id '96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 3ac1e97e-7427-4d8a-a8e5-b40920fff8a2
},
{
site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: f7db00ab-4504-4477-96b4-9893997dab00
}
}
);
// Finance
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Between 48 and 72 hours', 				4.8,
'Finance', 	
21402250, 							'Restricted',				 
42000, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 32f883c4-59b4-4799-b193-c3331614e75c
},
{
site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: f7545251-32f3-41b4-b0c2-e7d278e67f54
}
}
);
// Lab1
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
8490238, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				4.7,
'Lab1', 					 
24957000, 					'Restricted',
64000, 
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: 32f883c4-59b4-4799-b193-c3331614e75c
},
{
site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: f7545251-32f3-41b4-b0c2-e7d278e67f54
}
}
);
// Emergency Room
INSERT INTO business_value_attribution
(site_id, 					business_process_id, 
annual_revenue, 				annual_revenue_year, 
audit_upsert, 					
business_criticality, 				business_interruption_threshold,
business_process_impact_value, 			business_process_name,
cyber_value_at_risk,				information_classification,
record_count,					
runs_on_hosts)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
131800839, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				7.3,
'Emergency Room', 		71391, 
'Restricted',				45000,
{{site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: f17129fa-e4ca-4aa4-b5ff-dca9e0f01c93
},
{
site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: f45fe5a0-1939-4e34-9c02-e4aa75e9135b
},
{
site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id: f7545251-32f3-41b4-b0c2-e7d278e67f54
},
{
site_id:96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 
ip_subnet_or_building: '10.106', 
internal_system_id:f9dd2573-8fb5-45b2-bb24-93585dcd04dd
}
}
);
