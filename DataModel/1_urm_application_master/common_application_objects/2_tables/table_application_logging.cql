
date				author				description
11-May-2018			sara.bergman		Removed app_module_id uuid
												event_ts_mask text
										Added process_name text
										Altered Primary Key from ((app_module_id, event_ts_mask), event_datetime) to (module_name, event_datetime)

USE keyspaceName;
CREATE TABLE IF NOT EXISTS application_logging (
    process_name		text,			// name of the calling process
    event_datetime 		timestamp,		//Stored format is: yyyy-mm-dd 04:00 GMT
    severity_r 			text,			// references.severity = slf4j severity levels. errors only. no warnings or info. 
    message_text		text,			// message text printStackTrace for severe errors
    PRIMARY KEY (module_name, event_datetime)
) WITH CLUSTERING ORDER BY (event_datetime  DESC)
AND COMMENT='Used to log the operational status of imports and abnormal events within the application.';