CREATE TABLE g2.organizations (
    org_id uuid,
    organization_name text,
    inudstry_name text,
    organization_id text,

    provenance_map text,

    PRIMARY KEY (org_id)
);

CREATE TABLE g2.organizational_units (
    org_id uuid,
    org_unit_id uuid,
    org_unit_name text,
    unit_type text,

    provenance_map text,

    PRIMARY KEY ((org_id),org_unit_id)
);

CREATE TABLE g2.sites (
    org_id uuid,
    org_unit_id uuid,
    site_id uuid,
    site_name text,

    provenance_map text,

    PRIMARY KEY ((org_id,org_unit_id),site_id)
);