Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'FW1',5.45,'medium','NGFW on perimeter only',false,true, 'Firewall; Security Architecture');

Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'IDS',5.45,'medium','External only automatic prevention enabled',false,true, 'Intrusion Detection / Prevention');

Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry','EMS',5.45,'medium','internal relay and external (ingress), no quarantine',false,true,'Email/messaging Security');
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values('industry','DLP',2,'low','gateway, monitor mode',false,'primary',true,'Data Loss Prevention',9.3);
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'WLS' ,2,'low','WPA2 PSK',false,true, 'Wireless Security Posture');
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry','VLN',2,'low','annually',false,true, 'Vulnerability Scanning');
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'VPN' ,5.45,'medium','S2S, Client, NIST approved algorithms',false,true,'Virtual Private Networking' );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry','PNT',5.45,'medium','full external only',false,true, 'Penetration Testing');
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry','DOS' ,0.0,'very_low','none',false,true,'DDoS Defense' );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values('industry','CLD',5.45,'medium','<25%',false,'primary',true, '% of Restricted Data in Cloud',-13.40 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'SEG' ,5.45,'medium','internal VLAN',false,true, 'Segmentation / Security Zones');
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'WCF',7.95,'high','URL/context/content',false,true, 'Web Content Filtering');
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values('industry','ECR',5.45,'medium','internal and external use of cryptographic mechanisms',true,'primary',true, 'Encryption/privacy/data protection',22.50);
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values('industry','SIM',7.95,'high','24/7 monitoring',true,'primary',true ,'Security Information & Event Management (SIEM)', 7.7 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values('industry','IRT',5.45,'medium','in-house resources with COTS tools',true,'primary',true ,'Incident Response Team', 25.90);
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry','ACX' ,7.95,'high','RBAC federated',false,true,'Access Control / Authorization' );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry','MFA',5.45,'medium','multiple apps',false,true,'Multifactor Authentication' );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry', 'IAM' ,5.45,'medium','in-house management',false,'primary',true,'Identity & Access Management (IAM)', 5.00 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry','MOB',5.45,'medium','fully managed corp and personal',false,'secondary',true, 'Mobile Security', -6.50);
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'EMP', 7.95,'high','Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission',false,true ,'Endpoint Malware Protection' );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry', 'CPM',5.45,'medium','Change Mgmt Process',false,true ,'Configuration Management (Patching)' );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry',  'CLS',7.95,'high','Document Management System; formal definitions for data classes',true,'primary',true,'Data Classification',6.10 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry', 'SAT' ,7.95,'high','annually with regular information sharing and testing',true,'primary',true , 'Security and Awareness Training',16.80);
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry','BCM',5.45,'medium','Plan in place, not tested',false,'primary',true,'Business Continuity Management', 15.40 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control, impact_cost)
	Values('industry', 'EXS' ,9.5,'very_high','BOD Member',true,'primary',true,'Executive Sponsorship', 8.20 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry', 'CSO' ,9.5,'very_high','FTE Report to CEO/BOD',true,'primary',true ,'Chief Information Security Officer / Chief Security Officer',7.90);

Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry','INS',5.45,'medium','partially meets expectations',true,'primary',true ,'Cyber Insurance Policy', 9.90 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,cost_type,impacts_resistance_strength, security_control,impact_cost)
	Values('industry', 'TIP' ,5.45,'medium','automated tools and free subscriptions',true,'primary',true,'Threat Intelligence Consumption / Sharing', 8.20 );
	
Insert into org_security_controls (security_control_group, security_control_id, score, category, description, apply_impact_cost,impacts_resistance_strength, security_control)
	Values('industry','STF',9.5,'very_high','>1 FTE dedicated',false,true,'InfoSec Staff' );

