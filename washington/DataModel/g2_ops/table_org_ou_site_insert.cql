USE keyspacename;

// a few seed records for the ORG
insert into organizations (org_id, organization_name, industry_name_r, audit_upsert)
VALUES
(uuid(), 'G2 Ops', 'private', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});



// a few seed records for the ORG UNIT
insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  uuid(), 'Security Engineering', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  uuid(), 'Systems Engineering', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});


// a few seed records for the SITE
insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b, ed820389-e7d2-4d5d-88f4-681e39099aa6, uuid(), 'San Diego', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, uuid(), 'Virginia Beach', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, uuid(), 'Washington, DC', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
