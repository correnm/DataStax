/**
Modified: 2/12/18
03-12-2018		corren.mccoy		added field for cvss_vector
04-04-2018		sara.bergman		modified/renamed date_published and date_modified to published_date and modified_date date
									altered cpe_affected to datatype text
									renamed import_date to update_datetime
									added cpe_affected as part of the primary key
*/

USE KEYSPACE appl_auth;

//Vulnerability data taken from NVD 
CREATE TABLE common_vulnerabilities (
	cve_id 					text, 			//CVE ID in format CVE-YYYY-XXXX
	cpe_affected 			text, 			//List of v2.2 CPE IDs affected will be create an entry per CPE ID
	cve_name 				text,			//Public name associated with a CVE ID, normally after exposure (Ex. WannaCry)
	published_date 			date, 			//Original release date 
	modified_date			date, 			//Last revised date. WIll default to published_date if modified_date is null
	cvss_base_score 		decimal, 		//CVSS base score v2.0; temporal scores and environmental scores not currently provided by NVD
	cvss_temporal_score		decimal,		//can be calculated from the base. May be included in scans
	cve_description 		text, 			//Describes vulnerability
	patch_references 		list<text>,		//Reference links associated with each CVE that could apply to any CPE within list
	patch_count 			int, 			//Number of reference links with vendor_advisory or patch tag within NVD CVE xml files 
	update_datetime			timestamp, 		//Date imported into cassandra //yyyy-mm-dd HH:mm:ss
	cvss_vector				text,			// AV:L/AC:L/Au:N/C:N/I:P/A:N 
	cvss_temporal_vector	text,	
	PRIMARY KEY (cve_id, cpe_affected) 				//cve_id will be partition key
) WITH comment = 'List of Common Vulnerabilities and Exposures from NVD data feeds to identify security vulnerabilities and show all CPE IDs effected.'
