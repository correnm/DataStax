/** 
Table with update_datetime partitioning the data

Modifications
04/04/2018 				sara.bergman		created table
											

*/

CREATE MATERIALIZED VIEW common_vuln_by_cpe_update
AS SELECT 	cpe_affected,
			update_datetime,
			cve_id,
			cve_name,
			published_date,
			modified_date,
			cvss_base_score,
			cvss_temporal_score,
			cve_description,
			patch_references,
			patch_count,		
			cvss_vector,
			cvss_temporal_vector
FROM common_vulnerabilities
WHERE 	cpe_affected IS NOT NULL
	AND	cve_id IS NOT NULL
	AND	update_datetime IS NOT NULL
PRIMARY KEY (cpe_affected, update_datetime, cve_id) //update_datetime is the new partition key
WITH comment = 'For this cpe, which cves have been modified since the last cve update.'