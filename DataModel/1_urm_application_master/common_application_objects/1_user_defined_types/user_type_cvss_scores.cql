/**
Change dod only until May--

Modifications
date				author			description
2/26/2018			sara.bergman	added cvss_vector and cvss_temporal_vector
									alter type cvss_scores add cvss_vector text, cvss_temporal_vector text;
4/4/2018			sara.bergman	removed patch_publication_date 
									removed solution which is replaced by patch_references
									added patch_references list<text> which are taken from the NVD
									added patch_count int which is the number of references
									added patched date to indicate whether a vulnerability has been patched or not
									altered all double types to decimal types
*/

USE keyspacename;

CREATE TYPE cvss_scores (
cve_id 							text,   // vulnerability list <cve>CVE-2016-7212</cve>
cvss_base_score 				decimal,	// <cvss_base_score>9.3</cvss_base_score>
cvss_temporal_score 			decimal,	// <cvss_temporal_score>6.9</cvss_temporal_score>
cvss_temporal_vector			text,	// <cvss_temporal_vector>CVSS2#E:ND/RL:OF/RC:C</cvss_temporal_vector>
cvss_vector						text, 	// <cvss_vector>CVSS2#AV:N/AC:H/Au:N/C:P/I:P/A:N</cvss_vector>
iiv_score						decimal,	// output of VMASC algorithm
cve_description                 text,
vuln_modification_date			date,
patch_references	            list<text>, 
patch_count						int,
patched_date					date
);