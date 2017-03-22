CREATE TABLE g2.organizations (
    organization_name text,
    inudstry_name text,
    organization_id text,

    provenance_map text,

    PRIMARY KEY (organization_name)
);

CREATE TABLE g2.organizational_units (
    organization_name text,
    org_unit_id text,
    org_unit_name text,
    unit_type text,

    provenance_map text,

    PRIMARY KEY ((organization_name),org_unit_id)
);

CREATE TABLE g2.sites (
    organization_name text,
    org_unit_id text,
    site_id text,
    site_name text,

    provenance_map text,

    PRIMARY KEY ((organization_name,org_unit_id),site_id)
);