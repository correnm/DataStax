USE dod;

// remove the old data before reloading if its the same site
truncate table dod.business_value_attribution;

// headquarters, Virginia Beach, site id =96a3378e-2f67-4b9d-ab67-9ee9d533b88f
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
80864.26, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 2 and 4 hours', 				6.59,
'Serious Incident Report', 								{}, 
391.50, 								'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 1, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 388, 
dark_web_cost_total: 3.50}},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 02b388e8-75cf-4431-a182-fd18a8171417
}}
);
//Invasive cardiology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
11967910, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 4 and 24 hours', 				3.7,
'Airlift Request', 								{}, 
62640, 								'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 160, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 62080, 
dark_web_cost_total: 560},
{record_type_name: 'PII', 
record_short_name: 'PII',
record_count: 160, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 0.75, 
breach_cost_total: 62080, 
dark_web_cost_total: 291}},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 02b388e8-75cf-4431-a182-fd18a8171417
}}
);
// Open heart surgery
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 										2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 4 and 24 hours', 				4.8,
'Flight Control Information', 								{}, 
0, 								'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 0, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 0, 
dark_web_cost_total: 560},
{record_type_name: 'PII', 
record_short_name: 'PII',
record_count: 160, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 0.75, 
breach_cost_total: 0, 
dark_web_cost_total: 0}},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 02b388e8-75cf-4431-a182-fd18a8171417
}}
);
// Dental
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
161728, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 
'Between 48 and 72 hours', 				6.3,
'Human Remains Search and Recovery', 								{}, 
783, 								'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 2, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 776, 
dark_web_cost_total: 7}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 03231e21-a454-4f07-8df5-4697c9f52a14
}}
);
// Dermatology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
161728, 										2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 48 and 72 hours', 				6.3,
'Intelligence Report', 								{}, 
5481, 								'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 14, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 5432, 
dark_web_cost_total: 49}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 03231e21-a454-4f07-8df5-4697c9f52a14
}}
);
// Endocrinology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
16011123, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 48 and 72 hours', 				9.3,
'Logistics Resupply Request', 								{}, 
84172, 								'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 215, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 83420, 
dark_web_cost_total: 752}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 03dec09e-2993-449c-8bf5-17505ab4ef25
}}
);
// General Medicine
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
28949405, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				4.9,
'Mail Distribution Scheme Change', 								{}, 
151902, 								'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 388, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 150544, 
dark_web_cost_total: 1358}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id:0dc91f7f-56dc-4e2f-8b49-5c23cba02de7
}}
);
// General surgery
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
37440152, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				3.3,
'Media Contact Report', 					{}, 
196533, 							'Restricted',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 502, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 194776, 
dark_web_cost_total: 1757}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id:ee89bfa9-aaf1-4c9d-a434-1ef2661bb98b
}}
);
// Accounts Payable
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 
'Greater than 72 hours', 				2.3,
'Message Correction', 					{}, 
5295560, 							'Restricted',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 8157, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 2895735, 
dark_web_cost_total: 28549}
,
{record_type_name: 'PII', 
record_short_name: 'PII',
record_count: 6088, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 1.50, 
breach_cost_total: 2362144, 
dark_web_cost_total: 9132}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 55963e24-573f-47f4-b636-9d63ec6cc9b5
}}
);
// Gynecology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
2345063, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 
'Greater than 72 hours', 				2.3,
'Meteorological-Fallout', 					{}, 
5872, 							'Confidential',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 15, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 5820, 
dark_web_cost_total: 52.50}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 6b767db2-79c0-4d5e-83d4-6c2b389abf89
}}
);
//Hematology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
8490747, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 
'Greater than 72 hours', 				1.4,
'Military Postal Facility', 					{}, 
44631, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 114, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 44232, 
dark_web_cost_total: 399}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: b321d442-74fc-4929-afcd-05cfd9363f9a
}}
);
//Infectious Disease
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
53693869, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Low', 
'Greater than 72 hours', 				5.3,
'Operations Summary', 					{}, 
281880, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 720, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 279360, 
dark_web_cost_total: 2520}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
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
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
16496309, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 4 and 24 hours', 				8.7,
'Personnel Status Report', 					{}, 
281880, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 221, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 85749, 
dark_web_cost_total: 774}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: a3cffcdc-2cd8-4770-ac12-efbcbee407b8
}}
);
// Normal Newborns
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
142563691, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				6.8,
'Radar Status Report', 					{}, 
748159, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 221, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 85749, 
dark_web_cost_total: 774}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: d0f7c9d0-bf46-482d-9f3a-d059876bca1f
}}
);
// Obstetrics/Delivery
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
154693330, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				3.9,
'Rear Area Security', 					{}, 
801792, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 2048, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 794624, 
dark_web_cost_total: 7168}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: b321d442-74fc-4929-afcd-05cfd9363f9a
}}
);
// Oncology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
9299389, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				9.2,
'Chemical Downwind', 					{}, 
48937, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 125, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 48500, 
dark_web_cost_total: 438}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 42ff8cf9-bff6-4d14-a2d3-0c943dbf4b50
}}
);
// Oncology Surgery
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
404297, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'High', 
'Between 2 and 4 hours', 				1.1,
'Decontamination Request', 					{}, 
1958, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 5, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 1940, 
dark_web_cost_total: 18}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 9b9d4028-2248-4b5b-acd0-90a76a644677
}}
);
// Opthamology
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
323437, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Greater than 72 hours', 				1.2,
'Electronic Warfare', 					{}, 
1566, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 4, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 1552, 
dark_web_cost_total: 14}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 9b9d4028-2248-4b5b-acd0-90a76a644677
}}
);
//Orthopedic Surgery
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
36548453, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Greater than 72 hours', 				6.1,
'Explosive Ordinance', 					{}, 
191835, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 490, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 190120, 
dark_web_cost_total: 1715}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: d1679cac-dbf3-4c05-b138-fcc12fc5e884
}}
);
// Plastic Surgery
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Greater than 72 hours', 				6.5,
'Fire Support', 					{}, 
0, 							'Sensitive',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 0, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 0, 
dark_web_cost_total: 0}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: d1679cac-dbf3-4c05-b138-fcc12fc5e884
}}
);
//Psychiatry
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
3234376, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Medium', 
'Between 2 and 4 hours', 				5.8,
'Friendly Nuclear Strike', 					{}, 
16385, 							'Restricted',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 43, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 16684, 
dark_web_cost_total: 151}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 9b9d4028-2248-4b5b-acd0-90a76a644677
}}
);
// Trauma
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
2991798, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				1.9,
'General Administrative', 					{}, 
15660, 							'Restricted',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 40, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 1552, 
dark_web_cost_total: 140}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: d29d3084-78ad-4ae3-b440-777b3db5a436
}}
);
// Admissions
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				2.7,
'Medical Evacuation', 					{}, 
32305337, 							'Restricted',
{{record_type_name: 'PII', 
record_short_name: 'PII',
record_count: 71391, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 0.75, 
breach_cost_total: 27699708, 
dark_web_cost_total: 53543},
{record_type_name: 'PCI', 
record_short_name: 'PCI',
record_count: 11687, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 1.50, 
breach_cost_total: 4534556, 
dark_web_cost_total: 17531}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 32f883c4-59b4-4799-b193-c3331614e75c
},
{
site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 3ac1e97e-7427-4d8a-a8e5-b40920fff8a2
},
{
site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: f7db00ab-4504-4477-96b4-9893997dab00
}
}
);
// Finance
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
0, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Between 48 and 72 hours', 				4.8,
'Message Correction', 					{}, 
21402250, 							'Restricted',
{{record_type_name: 'PII', 
record_short_name: 'PII',
record_count: 15000, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 0.75, 
breach_cost_total: 5820000, 
dark_web_cost_total: 1250},
{record_type_name: 'PCI', 
record_short_name: 'PCI',
record_count: 27000, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 1.50, 
breach_cost_total: 10476000, 
dark_web_cost_total: 40500}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 32f883c4-59b4-4799-b193-c3331614e75c
},
{
site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: f7545251-32f3-41b4-b0c2-e7d278e67f54
}
}
);
// Lab1
INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
8490238, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				4.7,
'Patrol Report', 					{}, 
24957000, 							'Restricted',
{{record_type_name: 'PII', 
record_short_name: 'PII',
record_count: 36000, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 0.75, 
breach_cost_total: 13968000, 
dark_web_cost_total: 27000},
{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 28000, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 10864000, 
dark_web_cost_total: 98000}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: 32f883c4-59b4-4799-b193-c3331614e75c
},
{
site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: f7545251-32f3-41b4-b0c2-e7d278e67f54
}
}
);
// Emergency Room
 INSERT INTO business_value_attribution
(site_id, 								business_process_id, 
annual_revenue, 						annual_revenue_year, 
audit_upsert, 							
business_criticality, 
business_interruption_threshold, 		business_process_impact_value, 
business_process_name, 					connected_to_hardware, 
cyber_value_at_risk, 					information_classification,
stored_records,
runs_on_hardware)
VALUES
(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 	uuid(), 
131800839, 								2015, 
{datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy'},
'Critical', 
'Less than 2 hours', 				7.3,
'Rear Area Security', 					{}, 
27949576, 							'Restricted',
{{record_type_name: 'PHI', 
record_short_name: 'PHI',
record_count: 71391, 
audit_date:  toDate(dateof(now())), 
breach_cost_per_record: 388, 
dark_web_cost_per_record: 3.50, 
breach_cost_total: 27699708, 
dark_web_cost_total: 249869}
},
{{site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: f17129fa-e4ca-4aa4-b5ff-dca9e0f01c93
},
{
site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: f45fe5a0-1939-4e34-9c02-e4aa75e9135b
},
{
site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id: f7545251-32f3-41b4-b0c2-e7d278e67f54
},
{
site_or_ou_name: '96a3378e-2f67-4b9d-ab67-9ee9d533b88f', 
ip_subnet_or_building: '10.106', 
internal_system_id:f9dd2573-8fb5-45b2-bb24-93585dcd04dd
}
}
);
