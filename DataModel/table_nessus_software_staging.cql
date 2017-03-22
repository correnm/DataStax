CREATE TABLE g2.nessus_software_staging (
    internal_system_id text,      // UUID computed to ensure uniqueness
    cpe_id text,                  // product
    part text,                    // [A]pplication, [O]perating System, [S]ystem
    vendor text,                  // Search w/ Solr
    product text,                 // Search w/ Solr
    version text,                 // Search w/ Solr
    version_update text,          // Search w/ Solr

    end_of_sale date,
    end_of_life_date date,
    end_of_support_date date,

    record_type text,
    number_of_records bigint,
    total_records bigint,

    line_of_business text,        // MV or Solr?
    buisiness_criticality text,
    data_classification text,
    risk_factor text,
    business_interruption_threshold text,
    inherent_impact_value float,

    asset_visibility text,
    asset_type text,
    application_category text,   // Solr

    date_added date,
    added_by_user text,

    patch_availability text,
    cyvar float,                  // Cyber Value at Risk
    vulnerability_count int,
    risk_likelihood float,        //

    PRIMARY KEY ((internal_system_id), part, vendor, product, version)
);