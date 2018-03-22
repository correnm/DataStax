CREATE MATERIALIZED VIEW IF NOT EXISTS common_platforms_by_vendor 
AS SELECT cpe_id_22, cpe_id_23, part, vendor, product, version, product_update, edition, language, sw_edition, target_sw, target_hw, other, 
references, cve_list, import_date, official 
FROM common_platforms 
	WHERE part IS NOT NULL AND vendor IS NOT NULL AND product IS NOT NULL AND version IS NOT NULL AND Product_update IS NOT NULL AND edition IS NOT NULL AND language IS NOT NULL AND sw_edition IS NOT NULL 
	AND target_sw IS NOT NULL AND target_hw IS NOT NULL AND other IS NOT NULL
	PRIMARY KEY ((vendor), part, product, cpe_id_22)
	WITH comment = 'query alternative for the common_platforms table. Searches by vendor.'