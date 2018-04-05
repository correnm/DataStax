/** 
Table with date_modified partitioning the data

Modifications
04/04/2018 				sara.bergman		added all columns of common_vulnerabilities table to materialized view
											changed name to common_vuln_by_date_modified

*/

CREATE MATERIALIZED VIEW common_vuln_by_date_modified 
AS SELECT 	date_modified,
			cpe_affected,
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
			cvss_temporal_vector
FROM common_vulnerabilities
WHERE 	date_modified IS NOT NULL 
	AND cpe_affected IS NOT NULL
	AND	cve_id IS NOT NULL
PRIMARY KEY (date_modified, cpe_affected, cve_id) //date_modified is the new partition key
) WITH comment = 'What new modified cve's occured on selected dates.'