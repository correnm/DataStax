CREATE TABLE g2.application_logging (
    source_module text,
    event_ts_mask datetime,   // yyyy-mm-dd 04:00 GMT
    event_datetime timestamp,
    severity text,
    payload text,
    PRIMARY KEY ((source_module, event_ts_mask) event_datetime)
);



