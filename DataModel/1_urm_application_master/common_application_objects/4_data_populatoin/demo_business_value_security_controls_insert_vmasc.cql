/**
demo insert of vmasc.business_value_security_controls. 
scores for security controls for each business process aggregate to resistance strengths in the vmasc.busicess_value_attribution_table 
*/

--Admissions--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','PNT',  7.95, 'high', 'internal and external testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','DOS' , 7.95, 'high', 'single point protection', false, true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','CLD',  7.95, 'high', '<10%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'SEG' , 7.95, 'high', 'micro-segmentation', false, true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','ECR', 7.95, 'high', 'internal and external use of NIST compliant crytopgraphic algorithms', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','IRT',  7.95, 'high', '3rd Party Contractor Retainer', true, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','MFA',  7.95, 'high', 'multiple apps and desktop/workstation', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'IAM' ,  7.95, 'high', 'IAM solution', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','MOB',  7.95, 'high', 'fully managed corp only (permitted)', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'CPM',  7.95, 'high', 'CCB', false, true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry',  'CLS', 7.95, 'high', 'Document Management System; formal definitions for data classes', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','BCM',  7.95, 'high', 'Plan in place, tested only once', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'EXS' , 7.95, 'high', 'CEO', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'CSO' , 7.95, 'high', 'FTE', true, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry', 'TIP' ,  7.95, 'high', 'automated tools and fee-based subscriptions', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5327fa49-13fa-468d-8562-fd66ba255750 ,'industry','STF', 7.95, 'high', '1 FTE dedicated', false, true,'InfoSec Staff' );

--Burns--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8,'industry', 'FW1', 5.45, 'medium', 'NGFW on perimeter only', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8,'industry', 'IDS', 5.45, 'medium', 'External only automatic prevention enabled', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','EMS', 5.45, 'medium', 'internal relay and external (ingress), no quarrantine', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','DLP', 5.45, 'medium', 'gateway with automated response, or gateway and internal monitor only', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'WLS' ,  5.45, 'medium', 'WPA2 ENT', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','VLN',  5.45, 'medium', 'bi-annually', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'VPN',  5.45, 'medium', 'S2S, Client, NIST approved algorithms', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','PNT',  5.45, 'medium', 'full external only', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','DOS' ,  5.45, 'medium', 'only at provider level', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','CLD',   5.45, 'medium', '<25%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'SEG' ,  2, 'low', 'internal DMZ only', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'WCF', 2, 'low', 'URL only', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','ECR', 2, 'low', 'https/ssl/tls only', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','SIM', 2, 'low', 'not monitored', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','IRT',  2, 'low', 'In-house & standard OS tools', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','MFA',  2, 'low', 'single app', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'IAM' ,  2, 'low', 'formal policy/procedure', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','MOB',  2, 'low', 'HDE, strong pin', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'EMP',  5.45, 'medium', 'Client and Network AV with sandbox submission', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, ba1ba4c9-5719-4ad6-9673-10fce1978ec8 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );

--Dental--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933,'industry', 'FW1', 5.45, 'medium', 'NGFW on perimeter only', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933,'industry', 'IDS', 5.45, 'medium', 'External only automatic prevention enabled', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','EMS', 5.45, 'medium', 'internal relay and external (ingress), no quarrantine', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','DLP', 5.45, 'medium', 'gateway with automated response, or gateway and internal monitor only', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'WLS' ,  5.45, 'medium', 'WPA2 ENT', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','VLN',  5.45, 'medium', 'bi-annually', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'VPN',  5.45, 'medium', 'S2S, Client, NIST approved algorithms', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','PNT',  5.45, 'medium', 'full external only', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','DOS' ,  5.45, 'medium', 'only at provider level', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','CLD',   5.45, 'medium', '<25%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'SEG' ,  2, 'low', 'internal DMZ only', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'WCF', 2, 'low', 'URL only', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','ECR', 2, 'low', 'https/ssl/tls only', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','SIM', 2, 'low', 'not monitored', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','IRT',  2, 'low', 'In-house & standard OS tools', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','MFA',  2, 'low', 'single app', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'IAM' ,  2, 'low', 'formal policy/procedure', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','MOB',  2, 'low', 'HDE, strong pin', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'EMP',  5.45, 'medium', 'Client and Network AV with sandbox submission', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  3268037c-383e-48b1-93b0-09ece7294933 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );








--Dermatology--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 7038c9d7-12fb-4bfe-974d-90a9ea879242 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );



--Emergency Room--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','PNT', 9.5, 'very_high', 'red team testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','DOS' , 9.5, 'very_high', 'multipoint internal protection', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','CLD', 9.5, 'very_high', '0', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'SEG' , 9.5, 'very_high', 'SDN', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','ECR', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','IRT', 9.5, 'very_high', 'Demonstrably mature internal team', true, 'primary', true ,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','MFA', 9.5, 'very_high', 'token based (HW/SW), not SMS/email', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'IAM' , 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','MOB', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'CPM', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry',  'CLS', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','BCM', 9.5, 'very_high', 'Plan in place,regularly tested/validated', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'EXS' ,9.5,'very_high','BOD Member',true,'primary',true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'CSO' ,9.5,'very_high','FTE Report to CEO/BOD',true,'primary',true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'TIP' , 9.5, 'very_high', 'Share and Subscribe', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','STF',9.5,'very_high','>1 FTE dedicated',false,true,'InfoSec Staff' );


--Endocrinology--
--truncate table business_value_security_controls;

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','PNT', 9.5, 'very_high', 'red team testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','DOS' , 9.5, 'very_high', 'multipoint internal protection', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','CLD', 9.5, 'very_high', '0', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'SEG' , 9.5, 'very_high', 'SDN', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','ECR', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','IRT', 9.5, 'very_high', 'Demonstrably mature internal team', true, 'primary', true ,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','MFA', 9.5, 'very_high', 'token based (HW/SW), not SMS/email', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'IAM' , 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','MOB', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'CPM', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry',  'CLS', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','BCM', 9.5, 'very_high', 'Plan in place,regularly tested/validated', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'EXS' ,9.5,'very_high','BOD Member',true,'primary',true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'CSO' ,9.5,'very_high','FTE Report to CEO/BOD',true,'primary',true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry', 'TIP' , 9.5, 'very_high', 'Share and Subscribe', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 198b1830-8e80-4017-bc87-10993b044362 ,'industry','STF',9.5,'very_high','>1 FTE dedicated',false,true,'InfoSec Staff' );




--Facility/Maintenance--


Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','PNT', 9.5, 'very_high', 'red team testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','DOS' , 9.5, 'very_high', 'multipoint internal protection', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','CLD', 9.5, 'very_high', '0', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'SEG' , 9.5, 'very_high', 'SDN', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','ECR', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','IRT', 9.5, 'very_high', 'Demonstrably mature internal team', true, 'primary', true ,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','MFA', 9.5, 'very_high', 'token based (HW/SW), not SMS/email', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'IAM' , 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','MOB', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'CPM', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry',  'CLS', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','BCM', 9.5, 'very_high', 'Plan in place,regularly tested/validated', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'EXS' ,9.5,'very_high','BOD Member',true,'primary',true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'CSO' ,9.5,'very_high','FTE Report to CEO/BOD',true,'primary',true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry', 'TIP' , 9.5, 'very_high', 'Share and Subscribe', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, c258f02d-15ca-47e2-8c85-18f63aa13395 ,'industry','STF',9.5,'very_high','>1 FTE dedicated',false,true,'InfoSec Staff' );


--Finance--

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','PNT',  7.95, 'high', 'internal and external testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','DOS' , 7.95, 'high', 'single point protection', false, true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','CLD',  7.95, 'high', '<10%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'SEG' , 7.95, 'high', 'micro-segmentation', false, true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','ECR', 7.95, 'high', 'internal and external use of NIST compliant crytopgraphic algorithms', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','IRT',  7.95, 'high', '3rd Party Contractor Retainer', true, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','MFA',  7.95, 'high', 'multiple apps and desktop/workstation', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'IAM' ,  7.95, 'high', 'IAM solution', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','MOB',  7.95, 'high', 'fully managed corp only (permitted)', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'CPM',  7.95, 'high', 'CCB', false, true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry',  'CLS', 7.95, 'high', 'Document Management System; formal definitions for data classes', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','BCM',  7.95, 'high', 'Plan in place, tested only once', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'EXS' , 7.95, 'high', 'CEO', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'CSO' , 7.95, 'high', 'FTE', true, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry', 'TIP' ,  7.95, 'high', 'automated tools and fee-based subscriptions', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 77e9a541-50df-4356-b14a-544a9f57d871 ,'industry','STF', 7.95, 'high', '1 FTE dedicated', false, true,'InfoSec Staff' );





--General Medicine--

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 64bf9a55-a128-4228-bf08-f8725b5ba3a8 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );



--General Surgery--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c,'industry', 'FW1', 5.45, 'medium', 'NGFW on perimeter only', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c,'industry', 'IDS', 5.45, 'medium', 'External only automatic prevention enabled', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','EMS', 5.45, 'medium', 'internal relay and external (ingress), no quarrantine', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','DLP', 5.45, 'medium', 'gateway with automated response, or gateway and internal monitor only', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'WLS' ,  5.45, 'medium', 'WPA2 ENT', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','VLN',  5.45, 'medium', 'bi-annually', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'VPN',  5.45, 'medium', 'S2S, Client, NIST approved algorithms', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','PNT',  5.45, 'medium', 'full external only', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','DOS' ,  5.45, 'medium', 'only at provider level', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','CLD',   5.45, 'medium', '<25%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'SEG' ,  2, 'low', 'internal DMZ only', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'WCF', 2, 'low', 'URL only', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','ECR', 2, 'low', 'https/ssl/tls only', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','SIM', 2, 'low', 'not monitored', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','IRT',  2, 'low', 'In-house & standard OS tools', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','MFA',  2, 'low', 'single app', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'IAM' ,  2, 'low', 'formal policy/procedure', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','MOB',  2, 'low', 'HDE, strong pin', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'EMP',  5.45, 'medium', 'Client and Network AV with sandbox submission', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 43dc3191-5d7b-4c78-ae37-a4daf72c3e6c ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );









--Gynecology--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 22131b2f-1a55-4b8f-a497-1aa69fc6ad6c ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );









--Infectious Diseases--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 6a3d92e7-8ecd-4306-bc84-874d3e0555a8 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );










--Lab1--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','PNT',  7.95, 'high', 'internal and external testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','DOS' , 7.95, 'high', 'single point protection', false, true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','CLD',  7.95, 'high', '<10%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'SEG' , 7.95, 'high', 'micro-segmentation', false, true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','ECR', 7.95, 'high', 'internal and external use of NIST compliant crytopgraphic algorithms', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','IRT',  7.95, 'high', '3rd Party Contractor Retainer', true, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','MFA',  7.95, 'high', 'multiple apps and desktop/workstation', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'IAM' ,  7.95, 'high', 'IAM solution', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','MOB',  7.95, 'high', 'fully managed corp only (permitted)', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'CPM',  7.95, 'high', 'CCB', false, true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry',  'CLS', 7.95, 'high', 'Document Management System; formal definitions for data classes', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','BCM',  7.95, 'high', 'Plan in place, tested only once', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'EXS' , 7.95, 'high', 'CEO', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'CSO' , 7.95, 'high', 'FTE', true, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry', 'TIP' ,  7.95, 'high', 'automated tools and fee-based subscriptions', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  27f5c9a3-4070-4b1a-88da-ce1060e1da75 ,'industry','STF', 7.95, 'high', '1 FTE dedicated', false, true,'InfoSec Staff' );



--Neonatology--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','PNT',  7.95, 'high', 'internal and external testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','DOS' , 7.95, 'high', 'single point protection', false, true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','CLD',  7.95, 'high', '<10%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'SEG' , 7.95, 'high', 'micro-segmentation', false, true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','ECR', 7.95, 'high', 'internal and external use of NIST compliant crytopgraphic algorithms', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','IRT',  7.95, 'high', '3rd Party Contractor Retainer', true, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','MFA',  7.95, 'high', 'multiple apps and desktop/workstation', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'IAM' ,  7.95, 'high', 'IAM solution', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','MOB',  7.95, 'high', 'fully managed corp only (permitted)', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'CPM',  7.95, 'high', 'CCB', false, true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry',  'CLS', 7.95, 'high', 'Document Management System; formal definitions for data classes', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','BCM',  7.95, 'high', 'Plan in place, tested only once', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'EXS' , 7.95, 'high', 'CEO', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'CSO' , 7.95, 'high', 'FTE', true, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry', 'TIP' ,  7.95, 'high', 'automated tools and fee-based subscriptions', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 314d4788-bc1e-4b7e-a49f-2b69eabc3d55 ,'industry','STF', 7.95, 'high', '1 FTE dedicated', false, true,'InfoSec Staff' );




--Normal Newborns--

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4,'industry', 'FW1', 5.45, 'medium', 'NGFW on perimeter only', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4,'industry', 'IDS', 5.45, 'medium', 'External only automatic prevention enabled', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','EMS', 5.45, 'medium', 'internal relay and external (ingress), no quarrantine', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','DLP', 5.45, 'medium', 'gateway with automated response, or gateway and internal monitor only', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'WLS' ,  5.45, 'medium', 'WPA2 ENT', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','VLN',  5.45, 'medium', 'bi-annually', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'VPN',  5.45, 'medium', 'S2S, Client, NIST approved algorithms', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','PNT',  5.45, 'medium', 'full external only', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','DOS' ,  5.45, 'medium', 'only at provider level', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','CLD',   5.45, 'medium', '<25%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'SEG' ,  2, 'low', 'internal DMZ only', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'WCF', 2, 'low', 'URL only', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','ECR', 2, 'low', 'https/ssl/tls only', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','SIM', 2, 'low', 'not monitored', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','IRT',  2, 'low', 'In-house & standard OS tools', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','MFA',  2, 'low', 'single app', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'IAM' ,  2, 'low', 'formal policy/procedure', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','MOB',  2, 'low', 'HDE, strong pin', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'EMP',  5.45, 'medium', 'Client and Network AV with sandbox submission', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 47e02b00-45a8-4a10-b5f5-fdf19e9388f4 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );









--Obstetrics/Delivery--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3d524965-3812-4906-a201-2917b925e43d ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );











--Oncology--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5a9cdd28-2283-42b6-aa0e-af3c18db4ded ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );



--Oncology Surgery--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5,'industry', 'FW1', 5.45, 'medium', 'NGFW on perimeter only', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5,'industry', 'IDS', 5.45, 'medium', 'External only automatic prevention enabled', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','EMS', 5.45, 'medium', 'internal relay and external (ingress), no quarrantine', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','DLP', 5.45, 'medium', 'gateway with automated response, or gateway and internal monitor only', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'WLS' ,  5.45, 'medium', 'WPA2 ENT', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','VLN',  5.45, 'medium', 'bi-annually', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'VPN',  5.45, 'medium', 'S2S, Client, NIST approved algorithms', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','PNT',  5.45, 'medium', 'full external only', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','DOS' ,  5.45, 'medium', 'only at provider level', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','CLD',   5.45, 'medium', '<25%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'SEG' ,  2, 'low', 'internal DMZ only', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'WCF', 2, 'low', 'URL only', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','ECR', 2, 'low', 'https/ssl/tls only', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','SIM', 2, 'low', 'not monitored', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','IRT',  2, 'low', 'In-house & standard OS tools', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','MFA',  2, 'low', 'single app', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'IAM' ,  2, 'low', 'formal policy/procedure', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','MOB',  2, 'low', 'HDE, strong pin', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'EMP',  5.45, 'medium', 'Client and Network AV with sandbox submission', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 3c7bb4ab-287c-47cb-9317-5f4f0225e5f5 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );



--Open Heart Surgery--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92,'industry', 'FW1', 5.45, 'medium', 'NGFW on perimeter only', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92,'industry', 'IDS', 5.45, 'medium', 'External only automatic prevention enabled', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','EMS', 5.45, 'medium', 'internal relay and external (ingress), no quarrantine', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','DLP', 5.45, 'medium', 'gateway with automated response, or gateway and internal monitor only', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'WLS' ,  5.45, 'medium', 'WPA2 ENT', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','VLN',  5.45, 'medium', 'bi-annually', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'VPN',  5.45, 'medium', 'S2S, Client, NIST approved algorithms', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','PNT',  5.45, 'medium', 'full external only', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','DOS' ,  5.45, 'medium', 'only at provider level', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','CLD',   5.45, 'medium', '<25%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'SEG' ,  2, 'low', 'internal DMZ only', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'WCF', 2, 'low', 'URL only', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','ECR', 2, 'low', 'https/ssl/tls only', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','SIM', 2, 'low', 'not monitored', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','IRT',  2, 'low', 'In-house & standard OS tools', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','MFA',  2, 'low', 'single app', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'IAM' ,  2, 'low', 'formal policy/procedure', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','MOB',  2, 'low', 'HDE, strong pin', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'EMP',  5.45, 'medium', 'Client and Network AV with sandbox submission', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, d9290d27-2de4-4719-b460-1ffebeb01c92 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );



--Opthamology--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 5887ed15-8dd7-4efe-8510-b2a7d0d7f8d0 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );


--Trauma, Multiple--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 72d41067-3852-4686-a831-58253ec40511 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );





--Orthopedic Surgery--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912,'industry', 'FW1', 5.45, 'medium', 'NGFW on perimeter only', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912,'industry', 'IDS', 5.45, 'medium', 'External only automatic prevention enabled', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','EMS', 5.45, 'medium', 'internal relay and external (ingress), no quarrantine', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','DLP', 5.45, 'medium', 'gateway with automated response, or gateway and internal monitor only', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'WLS' ,  5.45, 'medium', 'WPA2 ENT', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','VLN',  5.45, 'medium', 'bi-annually', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'VPN',  5.45, 'medium', 'S2S, Client, NIST approved algorithms', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','PNT',  5.45, 'medium', 'full external only', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','DOS' ,  5.45, 'medium', 'only at provider level', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','CLD',   5.45, 'medium', '<25%', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'SEG' ,  2, 'low', 'internal DMZ only', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'WCF', 2, 'low', 'URL only', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','ECR', 2, 'low', 'https/ssl/tls only', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','SIM', 2, 'low', 'not monitored', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','IRT',  2, 'low', 'In-house & standard OS tools', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','ACX' , 0.0, 'very_low', 'none', false, true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','MFA',  2, 'low', 'single app', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'IAM' ,  2, 'low', 'formal policy/procedure', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','MOB',  2, 'low', 'HDE, strong pin', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'EMP',  5.45, 'medium', 'Client and Network AV with sandbox submission', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2786c486-deb0-4b74-9e68-3ac407299912 ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );



--Plastic Surgery--
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a,'industry', 'FW1',  0.0, 'very_low', 'none', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a,'industry', 'IDS', 0.0, 'very_low', 'none', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','EMS',  0.0, 'very_low', 'none', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','DLP',  0.0, 'very_low', 'none', false, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'WLS' ,   0.0, 'very_low', 'none/WEP', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','VLN',   0.0, 'very_low', 'none', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'VPN',  0.0, 'very_low', 'none', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','PNT',  0.0, 'very_low', 'none', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','DOS' , 0.0, 'very_low', 'none', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','CLD',  0.0, 'very_low', '>50%', true, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'SEG' , 0.0, 'very_low', 'none', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'WCF',  0.0, 'very_low', 'none', false,  true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','ECR', 0.0, 'very_low', 'none', false, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','SIM', 0.0, 'very_low', 'none', false, 'primary', true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','IRT',  0.0, 'very_low', 'none', false, 'primary', true,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','ACX' , 0.0, 'very_low', 'none', false, 'na', true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','MFA',   0.0, 'very_low', 'none', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'IAM' ,  0.0, 'very_low', 'none', false, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','MOB',  0.0, 'very_low', 'none', false, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'EMP',   0.0, 'very_low', 'client AV signature based only', false,  true,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'CPM',  2, 'low', 'documented (excel)', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry',  'CLS',  2, 'low', 'internal/external tags only', false, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'SAT' , 2, 'low', 'onboarding onlyl', false, 'primary', true, 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','BCM', 2, 'low', 'Work in Progress', false, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'EXS' ,  2, 'low', 'not C Suite', false, 'primary', true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'CSO' ,  2, 'low', 'Advisory Services Contractor (PT)', false, 'primary', true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','INS',  2, 'low', 'not suited for client', false, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry', 'TIP' ,  2, 'low', 'automated tools only', false, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 2569330c-1922-4558-bebb-ac0516d8514a ,'industry','STF', 2, 'low', 'IT with assigned duties', false,  true,'InfoSec Staff' );



--Psychiatry--
--truncate table business_value_security_controls;

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf,'industry', 'FW1', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Firewall; Security Architecture');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf,'industry', 'IDS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Intrusion Detection / Prevention');

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','EMS', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Email/messaging Security');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','DLP', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Loss Prevention',9.3);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'WLS' , 9.5, 'very_high', 'Strong governance with matching implementation', false,  true, 'Wireless Security Posture');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','VLN', 9.5, 'very_high', 'monthly', false,  true, 'Vulnerability Scanning');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'VPN', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Virtual Private Networking' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','PNT', 9.5, 'very_high', 'red team testing', false,  true, 'Penetration Testing');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','DOS' , 9.5, 'very_high', 'multipoint internal protection', false,  true,'DDoS Defense' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','CLD', 9.5, 'very_high', '0', false, 'primary', true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'SEG' , 9.5, 'very_high', 'SDN', false,  true, 'Segmentation / Security Zones');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','ECR', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true, 'Encryption/privacy/data protection',22.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','IRT', 9.5, 'very_high', 'Demonstrably mature internal team', true, 'primary', true ,'Incident Response Team', 25.90);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','MFA', 9.5, 'very_high', 'token based (HW/SW), not SMS/email', false,  true,'Multifactor Authentication' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'IAM' , 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','MOB', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'secondary', true, 'Mobile Security', -6.50);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'CPM', 9.5, 'very_high', 'Strong governance with matching implementation', false,  true,'Configuration Management (Patching)' );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry',  'CLS', 9.5, 'very_high', 'Strong governance with matching implementation', true, 'primary', true,'Data Classification',6.10 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','BCM', 9.5, 'very_high', 'Plan in place,regularly tested/validated', true, 'primary', true,'Business Continuity Management', 15.40 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'EXS' ,9.5,'very_high','BOD Member',true,'primary',true,'Executive Sponsorship', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'CSO' ,9.5,'very_high','FTE Report to CEO/BOD',true,'primary',true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','INS', 9.5, 'very_high', 'n/a', true, 'primary', true,'Cyber Insurance Policy', 9.90 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry', 'TIP' , 9.5, 'very_high', 'Share and Subscribe', true, 'primary', true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into business_value_security_controls(site_id, business_process_id,security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values(96a3378e-2f67-4b9d-ab67-9ee9d533b88f,  9dc94f42-b6ad-40dd-8c81-ea2eac73ebbf ,'industry','STF',9.5,'very_high','>1 FTE dedicated',false,true,'InfoSec Staff' );











