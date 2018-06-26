/**
Table with internal_system_id partitioning the data

Modifications
06-21-2018			sara.bergman		create table

*/

CREATE MATERIALIZED VIEW org_platforms_by_internal_system_id 
AS SELECT 	internal_system_id,
			cpe_id, 
			vendor,
			product,
			version,
			title,
			business_process_ids
FROM org_platforms
WHERE 	internal_system_id IS NOT NULL  
	AND cpe_id IS NOT NULL
	AND	vendor IS NOT NULL
	AND	product IS NOT NULL
	AND	version IS NOT NULL
PRIMARY KEY (internal_system_id, cpe_id, vendor, product, version) 
WITH comment = 'Query table by internal_system_id to be able to update business_process_ids in table.'