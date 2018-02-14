//Modified: 2/12/18

USE KEYSPACE appl_auth;

//Vulnerability data taken from NVD 
CREATE TABLE common_vulnerabilities (
	cve_id text, //CVE ID in format CVE-YYYY-XXXX
	cve_name text,//Public name associated with a CVE ID, normally after exposure (Ex. WannaCry)
	date_published text, //Original release date 
	date_modified text, //Last revised date
	cvss_base_score float, //CVSS base score v2.0; temporal scores and environmental scores not currently provided by NVD
	cve_description text, //Describes vulnerability
	cpe_effected list<text>, //List of v2.2 CPE IDs
	patch_references list<text>, //Reference links associated with each CVE that could apply to any CPE within list
	patch_count int, //Number of reference links with vendor_advisory or patch tag within NVD CVE xml files 
	import_date timestamp, //Date imported into cassandra
	PRIMARY KEY (cve_id) //cve_id will be partition key
) WITH comment = 'List of Common Vulnerabilities and Exposures from NVD data feeds to identify security vulnerabilities and show all CPE IDs effected.'
