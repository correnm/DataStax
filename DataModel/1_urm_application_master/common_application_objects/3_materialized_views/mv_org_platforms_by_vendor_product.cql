/**
Table with internal_system_id partitioning the data

Modifications
06-21-2018			sara.bergman		create table

*/

CREATE MATERIALIZED VIEW org_platforms_by_part_vendor_product
AS SELECT 	part,
			vendor,
			product,
			version,
			cpe_id, 
			internal_system_id,
			title
FROM org_platforms
WHERE 	internal_system_id IS NOT NULL  
	AND	part IS NOT NULL
	AND cpe_id IS NOT NULL
	AND	vendor IS NOT NULL
	AND	product IS NOT NULL
	AND	version IS NOT NULL
PRIMARY KEY (part, vendor, product, version, cpe_id, internal_system_id) 
WITH comment = 'Query table by part, vendor, product, version to be able to search table for specific software to add to a node.'