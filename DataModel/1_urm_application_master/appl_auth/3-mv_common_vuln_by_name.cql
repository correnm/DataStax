/**
Table with cve_name partitioning the data

Modifications
04-04-2018			sara.bergman		added additional columns within the common_vulnerabilities table
*/


CREATE MATERIALIZED VIEW common_vuln_by_name 
AS SELECT 	cve_name,
			cpe_affected,
			cve_id,
			cve_name,
			published_date,
			modified_date,
			cvss_base_score,
			cvss_temporal_score,
			cve_description,
			patch_references,
			patch_count,
			update_datetime,			
			cvss_vector,
			cvss_temporal_vector
FROM common_vulnerabilities
WHERE 	cve_name IS NOT NULL 
	AND cve_id IS NOT NULL
	AND cpe_affected IS NOT NULL
PRIMARY KEY (cve_name, cpe_affected, cve_id) //cve_name is the new partition key 
 WITH comment = 'Does a vulnerability name affect a particular cpe in my environment?'