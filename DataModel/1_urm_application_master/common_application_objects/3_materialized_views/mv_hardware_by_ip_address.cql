USE keyspacename;

CREATE MATERIALIZED VIEW hardware_by_ip_address
AS SELECT 	ip_address, 
			internal_system_id, 
			site_id, 
			ip_subnet_or_building,
			system_type ,													
			mac_address ,						
			cvss_scores	,				
			import_date ,						
			cpe_id 	,
			crown_jewel	,		
			part 	,							
			vendor 	,							
			product ,							
			version ,						
			version_update ,						
			run_datetime,						
			end_of_sale ,						
			end_of_life_date ,					
			end_of_support_date ,			
			node_impact_value 	,									
			reportable_flag 	,			
			vendor_stencil_icon	,				
			vulnerability_count ,				
			audit_upsert ,						
			connected_elements,				
			asset_type	,						
			asset_visibility,			
			os_general	,						
			operating_system,				
			installed_software,				
			host_name			,
			business_process_ids				
FROM hardware
WHERE 	ip_address IS NOT NULL 
	AND internal_system_id IS NOT NULL 
	AND site_id IS NOT NULL 
	AND ip_subnet_or_building IS NOT NULL
PRIMARY KEY ((ip_address), internal_system_id, site_id, ip_subnet_or_building)
WITH COMMENT = 'query alternative for the hardware table.';
