CREATE TABLE IF NOT EXISTS g2.organizations (
    org_id 				uuid,
    organization_name 	text,		// lookup the name using the org_id. org_id is referenced in child tables
    industry_name 		text,		// references.industry_name = "private", "public"
    audit_insert 		frozen<audit_insert>,
	audit_update		frozen<audit_update>,
    PRIMARY KEY (org_id)
)
with comment = 'Parent table used to describe the organizational structure.';

// a few seed records
insert into organizations (org_id, organization_name, industry_name, audit_insert)
VALUES
(uuid(), 'G2 Ops', 'private', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizations (org_id, organization_name, industry_name, audit_insert)
VALUES
(uuid(), 'PWW 000', 'public', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});

CREATE TABLE IF NOT EXISTS g2.organizational_units (
    org_id 				uuid,		// must be an org_id found in the organizations table
    org_unit_id 		uuid,
    org_unit_name 		text,
    unit_type 			text,		// references.unit_type = "permanent", "M&A evaluation"
    audit_insert 		frozen<audit_insert>,
	audit_update		frozen<audit_update>,
    PRIMARY KEY ((org_id),org_unit_id)
)
WITH COMMENT = 'Represents a subdivision of the parent organization';

// a few seed records
insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type, audit_insert)
VALUES
(cb3a193b-8ef8-4172-8cd0-b7ff94f7ad41,  uuid(), 'Security Engineering', 'permanent', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type, audit_insert)
VALUES
(cb3a193b-8ef8-4172-8cd0-b7ff94f7ad41,  uuid(), 'Systems Engineering', 'permanent', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type, audit_insert)
VALUES
(e609163f-acfb-4a2c-9b49-b8fabafd45c1,  uuid(), 'Communications', 'permanent', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});


CREATE TABLE IF NOT EXISTS g2.sites (
    org_id 					uuid,	// must be an org_id found in the organizations table
    org_unit_id 			uuid,	// must be an org_unit_id found in the organizational_units table
    site_id 				uuid,
    site_name 				text,
	latitude				double,
	longitude				double,
    audit_insert 			frozen<audit_insert>,
	audit_update			frozen<audit_update>,
    PRIMARY KEY ((org_id,org_unit_id),site_id)
) with comment = 'Represents a particular site within the organizational unit';

// a few seed records
insert into sites (org_id, org_unit_id, site_id, site_name, audit_insert)
VALUES
(cb3a193b-8ef8-4172-8cd0-b7ff94f7ad41, 29e96dce-8a16-46e1-b5b7-4384b06ce825, uuid(), 'San Diego', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_insert)
VALUES
(cb3a193b-8ef8-4172-8cd0-b7ff94f7ad41,  d39c6992-83a3-4752-9389-01146ae30160, uuid(), 'Virginia Beach', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_insert)
VALUES
(e609163f-acfb-4a2c-9b49-b8fabafd45c1,  e91b2838-ea21-47c6-948f-e066de1a6a23, uuid(), 'Wichita', {dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'});
