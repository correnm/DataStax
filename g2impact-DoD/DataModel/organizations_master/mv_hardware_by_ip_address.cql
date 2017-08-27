USE dod;

CREATE MATERIALIZED VIEW hardware_by_ip_address
AS SELECT ip_address, internal_system_id, site_or_ou_name, ip_subnet_or_building
FROM hardware
WHERE ip_address IS NOT NULL AND internal_system_id IS NOT NULL AND site_or_ou_name IS NOT NULL AND ip_subnet_or_building IS NOT NULL
PRIMARY KEY ((ip_address), internal_system_id, site_or_ou_name, ip_subnet_or_building)
WITH COMMENT = 'query alternative for the hardware table.';
