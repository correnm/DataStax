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
			title
FROM software
WHERE 	cpe_id IS NOT NULL
	AND	part IS NOT NULL   
	AND	vendor IS NOT NULL
	AND	product IS NOT NULL
	AND	version IS NOT NULL
	AND	cpe_update IS NOT NULL
	AND	edition IS NOT NULL
	AND	sw_edition IS NOT NULL
	AND	target_hw IS NOT NULL
	AND	target_sw IS NOT NULL
	AND	language IS NOT null
	AND	other IS NOT NULL
PRIMARY KEY (cpe_id, part,vendor,product,version,cpe_update,edition,language,sw_edition, target_hw, target_sw, other) //cve_name is the new partition key 
WITH comment = 'Does this particular cpe_id exist in the software library? Is this hardware included in the installed_on_hardware list?'