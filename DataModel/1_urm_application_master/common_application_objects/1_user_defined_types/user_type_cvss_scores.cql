/**
Modifications
date				author			description
2/26/2018			sara.bergman	added cvss_vector and cvss_temporal_vector
									alter type cvss_scores add cvss_vector text, cvss_temporal_vector text;
*/

USE keyspacename;

CREATE TYPE cvss_scores (
cve_id 							text,   // vulnerability list <cve>CVE-2016-7212</cve>
cvss_base_score 				double,	// <cvss_base_score>9.3</cvss_base_score>
cvss_temporal_score 			double,	// <cvss_temporal_score>6.9</cvss_temporal_score>
cvss_temporal_vector			text,	// <cvss_temporal_vector>CVSS2#E:ND/RL:OF/RC:C</cvss_temporal_vector>
cvss_vector						text, 	// <cvss_vector>CVSS2#AV:N/AC:H/Au:N/C:P/I:P/A:N</cvss_vector>
iiv_score						double,	// output of VMASC algorithm
cve_description                 text,
vuln_publication_date			date,
patch_publication_date			date,
solution                        text
);