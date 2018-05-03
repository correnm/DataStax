/**
Enables the ability to query by import_date

Modifications
04-27-2018			sara.bergman		created mv
*/

CREATE MATERIALIZED VIEW IF NOT EXISTS common_platforms_by_import_date 
AS SELECT 	import_date,
			part, 
			vendor, 
			product,
			cpe_id_22, 
			cpe_id_23, 
			version, 
			product_update, 
			edition, 
			language, 
			sw_edition, 
			target_sw, 
			target_hw, 
			other, 
			references, 
			title,
			official
FROM common_platforms 
	WHERE 	import_date IS NOT NULL 
		AND cpe_id_22 IS NOT NULL
		AND part IS NOT NULL
		AND vendor IS NOT NULL
		AND product IS NOT NULL
	PRIMARY KEY (import_date,part,vendor,product, cpe_id_22)
	WITH comment = 'Satisfies query: Select * from common_platforms where import_date = ?;.'