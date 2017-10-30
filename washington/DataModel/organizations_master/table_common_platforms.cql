Use Keyspace vmasc;

CREATE TABLE common_platforms (
	cpe_id_22 text, //This is the cpe id version 2.2
    cpe_id_23 text, //This is the cpe id version 2.3
    part text,
    vendor text,
	product text,
	version text,
	update text,
	edition text,
	language text,
	sw_edition text,
	target_sw text,
	target_hw text,
	other text,
	references list<text,text>,
	cve_list list<vmasc.cvss_scores>,
	import_date timestamp,
    PRIMARY KEY (cpe_id_22)
) WITH comment = 'CPE Dictionary with vulnerabilities maintained via NIST data feeds, obtained when a vulnerability scan is not available'

CREATE MATERIALIZED VIEW common_platforms_by_part_vendor_product AS SELECT cpe_id_22, cpe_id_23 part, vendor, product, version, update, edition, language, sw_edition, target_sw, target_hw, other, 
references, cve_list, import_date FROM common_platforms 
	WHERE part IS NOT NULL AND vendor IS NOT NULL AND product IS NOT NULL AND version IS NOT NULL AND update IS NOT NULL AND edition IS NOT NULL AND language IS NOT NULL AND sw_edition IS NOT NULL 
	AND target_hw IS NOT NULL AND other IS NOT NULL 
	PRIMARY KEY (part, vendor, product, cpe_id_22) 
	WITH comment = 'query alternative for the common_platforms table. Searches by part, vendor, and product.'
	
CREATE MATERIALIZED VIEW common_platforms_by_vendor AS SELECT cpe_id_22, cpe_id_23 part, vendor, product, version, update, edition, language, sw_edition, target_sw, target_hw, other, 
references, cve_list, import_date FROM common_platforms 
	WHERE part IS NOT NULL AND vendor IS NOT NULL AND product IS NOT NULL AND version IS NOT NULL AND update IS NOT NULL AND edition IS NOT NULL AND language IS NOT NULL AND sw_edition IS NOT NULL 
	AND target_sw IS NOT NULL AND target_hw IS NOT NULL AND other IS NOT NULL
	PRIMARY KEY (vendor, cpe_id_22)
	WITH comment = 'query alternative for the common_platforms table. Searches by vendor.'	
	
CREATE MATERIALIZED VIEW common_platforms_by_product AS SELECT cpe_id_22, cpe_id_23 part, vendor, product, version, update, edition, language, sw_edition, target_sw, target_hw, other, 
references, cve_list, import_date FROM common_platforms 
	WHERE part IS NOT NULL AND vendor IS NOT NULL AND product IS NOT NULL AND version IS NOT NULL AND update IS NOT NULL AND edition IS NOT NULL AND language IS NOT NULL AND sw_edition IS NOT NULL 
	AND target_sw IS NOT NULL AND target_hw IS NOT NULL AND other IS NOT NULL
	PRIMARY KEY (product, cpe_id_22)
	WITH comment = 'query alternative for the common_platforms table. Searches by product.'
	
CREATE MATERIALIZED VIEW common_platforms_by_cpe_id_23 AS SELECT cpe_id_23, cpe_id_22, cve_list FROM common_platforms 
	WHERE cpe_id_23 IS NOT NULL AND cpe_id_22 IS NOT NULL AND cve_list IS NOT NULL
	PRIMARY KEY (cpe_id_23, cpe_id_22) 
	WITH comment = 'query alternative for the common_platforms table. Searches by cpe_id_23 to get cpe_id_22, cve_list.'	

  


