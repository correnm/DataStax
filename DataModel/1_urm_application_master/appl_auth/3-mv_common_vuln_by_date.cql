//Table with date_modified partitioning the data
CREATE MATERIALIZED VIEW common_vuln_by_date AS SELECT cve_id, cve_name, date_published, date_modified, cvss_base_score,
cve_description, cpe_effected, patch_references, patch_count, import_date FROM common_vulnerabilites
WHERE date_modified IS NOT NULL AND cve_id IS NOT NULL
PRIMARY KEY (date_modified, cve_id) //date_modified is the new partition key
) WITH comment = 'Will allow the querying by date modified.'