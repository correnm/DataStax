USE keyspaceName;

CREATE MATERIALIZED VIEW g2.hardware_by_internal_system_id
AS SELECT org_id, internal_system_id, site_or_ou_name, ip_subnet_or_building, ip_address
FROM g2.hardware
WHERE org_id IS NOT NULL AND internal_system_id IS NOT NULL AND site_or_ou_name IS NOT NULL AND ip_subnet_or_building IS NOT NULL
PRIMARY KEY ((org_id, internal_system_id), site_or_ou_name, ip_subnet_or_building)
WITH COMMENT = 'query alternative for the hardware table.';