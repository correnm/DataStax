/**
Table with cpe_id partitioning the data

Modifications
04-04-2018			sara.bergman		create table
*/

CREATE MATERIALIZED VIEW software_by_cpe 
AS SELECT 	cpe_id,
			part,   
			vendor,
			product,
			version,
			cpe_update,
			edition,
			sw_edition,
			target_hw,
			target_sw,
			language,
			installed_on_hardware,
			end_of_sale,
			end_of_life_date,
			end_of_support_date,
FROM software
WHERE 	cpe_id IS NOT NULL
		part IS NOT NULL   
		vendor IS NOT NULL
		product IS NOT NULL
		version IS NOT NULL
		cpe_update IS NOT NULL
		edition IS NOT NULL
		sw_edition IS NOT NULL
		target_hw IS NOT NULL
		target_sw IS NOT NULL
		language IS NOT NULL
PRIMARY KEY (cpe_id, part,vendor,product,version,cpe_update,edition,language,sw_edition, target_hw, target_sw) //cve_name is the new partition key 
WITH comment = 'Does this particular cpe_id exist in the software library? Is this hardware included in the installed_on_hardware list?'