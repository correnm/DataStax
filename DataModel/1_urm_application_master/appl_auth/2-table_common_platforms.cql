//Use keyspace;

/*******************
Common Platform Enumeration (CPE) is a standardized method of describing and identifying classes of 
applications, operating systems, and hardware devices present in an enterprise’s computing assets. CPE 
can be used as a source of information for enforcing and verifying IT management policies relating to 
these assets, such as vulnerability, configuration, and remediation policies. IT management tools can 
collect information about installed products, identify products using their CPE names, and use this 
standardized information to help make fully or partially automated decisions regarding the assets. 

Modifications
4/4/2018				sara.bergman				removed cve_list common_vulnerabilities. table will have a materialized view to search vulnerabilities by cpe_id_22
*****************/

CREATE TABLE IF NOT EXISTS common_platforms (
	cpe_id_22 			text, //This is the cpe id version 2.2
    cpe_id_23 			text, //This is the cpe id version 2.3
    part 				text,
    vendor 				text,
	product 			text,
	version 			text,
	product_update		text,
	edition 			text,
	language 			text,
	sw_edition 			text,
	target_sw 			text,
	target_hw 			text,
	other 				text,
	references 			MAP<text,text>,		// <reference href="https://play.google.com/store/apps/details?id=com.kindle.books.for99">Product information</reference>
	import_date 		timestamp,
	official			boolean,			// CPE IDs created by an organization are not official -> FALSE
    PRIMARY KEY ((cpe_id_22), part, vendor, product))
	WITH comment = 'CPE Dictionary with vulnerabilities maintained via NIST data feeds, obtained when a vulnerability scan is not available';

