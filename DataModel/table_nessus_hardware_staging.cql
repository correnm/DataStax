CREATE TABLE g2.nessus_hardware_staging (
    org_id uuid,
    site_or_ou_name text,
    ip_subnet_or_building text,   // 192.168.0.0, site/building
    internal_system_id text,      // UUID computed to ensure uniqueness
    system_type text,
    ip_address text,              // Query with MV
    ip_netmask text,
    ip_gateway text,
    mac_address text,             // Search w/ Solr or MV
    cvss_base_score text,
    cvss_temporal_score text,
    traceroute text,              // Test out: as list or text?
    import_date date,             // based on import job

    cpe_id text,                  // product
    part text,                    // should always be [S]ystem
    vendor text,
    product text,
    version text,
    version_update text,

    run_date date,                // From scan file
    cve_id text,                  // vulnerability list

    end_of_sale date,
    end_of_life_date date,
    end_of_support_date date,

    node_impact_value float,
    date_added date,
    added_by_user text,
    neighbor_coefficient_value float,
    crown_jewel boolean,
    reportable_flag boolean,

    vendor_stencil text,         // ref to file location
    patch_availability text,
    cyvar float,                 // Cyber Value at Risk

    vulnerability_count int,
    risk_likelihood float,

    PRIMARY KEY ((org_id, site_or_ou_name), ip_subnet_or_building, internal_system_id)
);


