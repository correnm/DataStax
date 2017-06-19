CREATE MATERIALIZED VIEW g2.hardware_by_ip_address
AS SELECT org_id, ip_address, internal_system_id, site_or_ou_name, ip_subnet_or_building
FROM g2.hardware
WHERE org_id IS NOT NULL AND ip_address IS NOT NULL AND ip_address IS NOT NULL AND internal_system_id IS NOT NULL AND site_or_ou_name IS NOT NULL AND ip_subnet_or_building IS NOT NULL
PRIMARY KEY ((org_id, ip_address), internal_system_id, site_or_ou_name, ip_subnet_or_building)
WITH COMMENT = 'query alternative for the hardware table.';
