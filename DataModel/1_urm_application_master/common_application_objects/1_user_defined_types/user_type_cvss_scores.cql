USE keyspacename;

CREATE TYPE cvss_scores (
cve_id 							text,   // vulnerability list <cve>CVE-2016-7212</cve>
cvss_base_score 				decimal,	// <cvss_base_score>9.3</cvss_base_score>
cvss_temporal_score 			decimal,	// <cvss_temporal_score>6.9</cvss_temporal_score>
iiv_score						decimal,	// output of VMASC algorithm
cve_description                 text,
vuln_publication_date			date,
patch_publication_date			date,
solution                        text
);