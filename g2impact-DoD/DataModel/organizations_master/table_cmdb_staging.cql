USE dod;

CREATE TABLE IF NOT EXISTS cmdb_staging (
    application_name 				text,
    risk_factor 					int,
    data_classification 			text,
    line_of_business 				text,
    import_date 					date,
    configuration_items 			text,	// JSON which describes the hardware where application is installed (IP addresses)
    business_interruption_threshold int,
    business_criticality_rating 	float,
    PRIMARY KEY (application_name)
) WITH COMMENT = 'Staging table to hold imports of CMDB data (e.g., ServiceNow)';
