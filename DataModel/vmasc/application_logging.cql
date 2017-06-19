CREATE TABLE IF NOT EXISTS vmasc.application_logging (
	org_id				uuid,			// must exist in the organizations table
    app_module_id		uuid,			// must exist in the application_modules table
    event_ts_mask 		text,   		// Stored format is: yyyy-mm-dd 04:00 GMT
    event_datetime 		timestamp,
    severity_r 			text,			// references.severity = low, medium, high, critical
    payload 			text,			// message text
    PRIMARY KEY ((org_id, app_module_id, event_ts_mask), event_datetime)
) WITH CLUSTERING ORDER BY (event_datetime  DESC)
AND COMMENT='Used to log the operational status of imports and abnormal events within the application.';