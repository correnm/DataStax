/** 
Table with date_modified partitioning the data

Modifications
04/04/2018 				sara.bergman		added all columns of common_vulnerabilities table to materialized view
											changed name to common_vuln_by_date_modified

*/

CREATE MATERIALIZED VIEW common_vuln_by_modified_date 
AS SELECT 	modified_date,
			cpe_affected,
			cve_id,
			cve_name,
			published_date,
			cvss_base_score,
			cvss_temporal_score,
			cve_description,
			patch_references,
			patch_count,		
			update_datetime,
			cvss_vector,
			cvss_temporal_vector
FROM common_vulnerabilities
WHERE 	modified_date IS NOT NULL 
	AND cpe_affected IS NOT NULL
	AND	cve_id IS NOT NULL
PRIMARY KEY (modified_date, cpe_affected, cve_id) 
WITH comment = 'What new modified cves occured on selected dates?'