USE keyspaceName;

CREATE MATERIALIZED VIEW hardware_by_internal_system_id
AS SELECT internal_system_id, site_or_ou_name, ip_subnet_or_building, ip_address
FROM hardware
WHERE internal_system_id IS NOT NULL AND site_or_ou_name IS NOT NULL AND ip_subnet_or_building IS NOT NULL
PRIMARY KEY ((internal_system_id), site_or_ou_name, ip_subnet_or_building)
WITH COMMENT = 'query alternative for the hardware table.';