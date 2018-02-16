//Table with cve_name partitioning the data
CREATE MATERIALIZED VIEW common_vuln_by_name AS SELECT cve_id, cve_name, date_published, date_modified, cvss_base_score,
cve_description, cpe_effected, patch_references, patch_count, import_date FROM common_vulnerabilities
WHERE cve_name IS NOT NULL AND cve_id IS NOT NULL
PRIMARY KEY (cve_name, cve_id) //cve_name is the new partition key 
 WITH comment = 'Adds capability to query based on cve_name'