CREATE TABLE IF NOT EXISTS g2.application_logging (
    source_module text,
    event_ts_mask text,   		// yyyy-mm-dd 04:00 GMT
    event_datetime timestamp,
    severity text,				// references.severity = low, medium, high, critical
    payload text,				// message text
    PRIMARY KEY ((source_module, event_ts_mask), event_datetime)
)
WITH COMMENT='Used to record the operational status of imports and abnormal events within the application';