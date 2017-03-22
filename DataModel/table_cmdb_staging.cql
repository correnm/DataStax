CREATE TABLE g2.cmdb_staging (
    organization_name text,
    application_name text,
    risk_factor text,
    data_classification text,
    line_of_business text,
    import_date date,
    configuration_items text,
    business_interruption_threshold text,
    business_criticality_rating float,
    PRIMARY KEY ((organization_name), application_name)
);