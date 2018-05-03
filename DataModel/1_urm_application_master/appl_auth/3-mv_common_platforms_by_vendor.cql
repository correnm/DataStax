/**
Table with part, vendor, product partitioning the data

Modifications
04-04-2018			sara.bergman		remove cve_list
*/

CREATE MATERIALIZED VIEW IF NOT EXISTS common_platforms_by_vendor 
AS SELECT 	vendor,
			cpe_id_22, 
			cpe_id_23, 
			part,  
			product, 
			version, 
			product_update, 
			edition, 
			language, 
			sw_edition, 
			target_sw, 
			target_hw, 
			other, 
			references,
			import_date, 
			title,
			official 
FROM common_platforms 
	WHERE 	vendor IS NOT NULL 
		AND part IS NOT NULL
		AND product IS NOT NULL
		AND cpe_id_22 IS NOT NULL
	PRIMARY KEY (vendor,part,product,cpe_id_22)
	WITH comment = 'query alternative for the common_platforms table. Searches by vendor.'