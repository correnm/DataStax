CREATE TABLE g2.mbse_staging (
    organization_name text,
    site_or_ou_name text,
    building text,

    magic_draw_id text,

    ip_address text,

    name text,
    part text,
    vendor text,
    product text,
    version text,
    version_update text,

    os_vendor text,
    os_product text,
    os_version text,
    os_version_update text,

    firmware_vendor text,
    firmware_product text,
    firmware_version text,
    firmware_version_update text,

    product_subsystem text,          // Line of business

    connector_list text,

    PRIMARY KEY ((organization_name,site_or_ou_name,building),magic_draw_id)
);