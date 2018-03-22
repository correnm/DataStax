CREATE MATERIALIZED VIEW hardware_by_vendor AS 
SELECT vendor, internal_system_id,  site_id,  ip_subnet_or_building 
FROM hardware 
WHERE vendor IS NOT NULL 
AND internal_system_id IS NOT NULL 
AND site_id IS NOT NULL 
AND ip_subnet_or_building IS NOT NULL 
PRIMARY KEY (vendor, internal_system_id, site_id, ip_subnet_or_building)
WITH COMMENT = 'query alternative for the hardware table.';
   
