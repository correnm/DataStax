CREATE TABLE g2.cmdb_staging (
    org_id uuid,
    application_name text,
    risk_factor int,
    data_classification text,
    line_of_business text,
    import_date date,
    configuration_items text,
    business_interruption_threshold text,
    business_criticality_rating float,
    PRIMARY KEY ((org_id), application_name)
);