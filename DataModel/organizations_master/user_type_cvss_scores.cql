USE keyspacename;

CREATE TYPE cvss_scores (
cve_id 							text,   // vulnerability list <cve>CVE-2016-7212</cve>
cvss_base_score 				double,	// <cvss_base_score>9.3</cvss_base_score>
cvss_temporal_score 			double,	// <cvss_temporal_score>6.9</cvss_temporal_score>
cvss_environmental_score		double	// output of VMASC algorithm
);