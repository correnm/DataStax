USE keyspacename;

CREATE MATERIALIZED VIEW business_value_attribution_by_process_name
AS SELECT business_process_name, site_id, business_process_id
FROM business_value_attribution
WHERE business_process_name IS NOT NULL AND site_id IS NOT NULL AND business_process_id IS NOT NULL
PRIMARY KEY ((business_process_name), site_id, business_process_id)
WITH COMMENT = 'query alternative for the business_value_attribution table.';