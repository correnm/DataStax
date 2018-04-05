/** 
Table with cpe_affected partitioning the data

Modifications
04/04/2018 				sara.bergman		created table
											

*/

CREATE MATERIALIZED VIEW common_vuln_by_cpe_date_modified 
AS SELECT 	cpe_affected,
			date_modified,
			cve_id,
			cve_name,
			date_published,
			date_modified,
			cvss_base_score,
			cvss_temporal_score,
			cve_description,
			patch_references,
			patch_count,
			update_datetime,			
			cvss_vector,
			cvss_temporal_vector,			
FROM common_vulnerabilities
WHERE 	cpe_affected IS NOT NULL
	AND	cve_id IS NOT NULL
PRIMARY KEY (cpe_affected, date_modified,cve_id)
) WITH comment = 'For this cpe, which cve's have been modified since date_modified.'