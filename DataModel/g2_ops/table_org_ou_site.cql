CREATE TABLE IF NOT EXISTS g2.organizations (
    org_id 				uuid,
    organization_name 	text,		// lookup the name using the org_id. org_id is referenced in child tables
    industry_name_r 	text,		// references.industry_name = "private", "public"
    audit_upsert 		frozen<audit_upsert>,
    PRIMARY KEY (org_id)
)
with comment = 'Parent table used to describe the organizational structure.';

// a few seed records
insert into organizations (org_id, organization_name, industry_name_r, audit_upsert)
VALUES
(uuid(), 'G2 Ops', 'private', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});


CREATE TABLE IF NOT EXISTS g2.organizational_units (
    org_id 				uuid,		// must be an org_id found in the organizations table
    org_unit_id 		uuid,
    org_unit_name 		text,
    unit_type_r			text,		// references.unit_type = "permanent", "M&A evaluation"
    audit_upsert 		frozen<audit_upsert>,
    PRIMARY KEY ((org_id),org_unit_id)
)
WITH COMMENT = 'Represents a subdivision of the parent organization';

// a few seed records
insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  uuid(), 'Security Engineering', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  uuid(), 'Systems Engineering', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});


CREATE TABLE IF NOT EXISTS g2.sites (
    org_id 					uuid,	// must be an org_id found in the organizations table
    org_unit_id 			uuid,	// must be an org_unit_id found in the organizational_units table
    site_id 				uuid,
    site_name 				text,
	latitude				double,
	longitude				double,
    audit_upsert 			frozen<audit_upsert>,
    PRIMARY KEY ((org_id,org_unit_id), site_id)
) with comment = 'Represents a particular site within the organizational unit';

// a few seed records
insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b, ed820389-e7d2-4d5d-88f4-681e39099aa6, uuid(), 'San Diego', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, uuid(), 'Virginia Beach', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(df72e1dd-a385-4ab9-ba30-70b9df8d539b,  703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, uuid(), 'Washington, DC', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
