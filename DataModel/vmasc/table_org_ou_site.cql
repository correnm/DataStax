CREATE TABLE IF NOT EXISTS vmasc.organizations (
    org_id 				uuid,
    organization_name 	text,		// lookup the name using the org_id. org_id is referenced in child tables
    industry_name_r 	text,		// references.industry_name = "private", "public"
    audit_upsert 		frozen<audit_upsert>,
    PRIMARY KEY (org_id)
)
with comment = 'Parent table used to describe the organizational structure.';

// a few seed records
insert into vmasc.organizations (org_id, organization_name, industry_name_r, audit_upsert)
VALUES
(uuid(), 'VMASC', 'private', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});


CREATE TABLE IF NOT EXISTS vmasc.organizational_units (
    org_id 				uuid,		// must be an org_id found in the organizations table
    org_unit_id 		uuid,
    org_unit_name 		text,
    unit_type_r			text,		// references.unit_type = "permanent", "M&A evaluation"
    audit_upsert 		frozen<audit_upsert>,
    PRIMARY KEY ((org_id),org_unit_id)
)
WITH COMMENT = 'Represents a subdivision of the parent organization';

// a few seed records
insert into vmasc.organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  uuid(), 'Main Campus', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into vmasc.organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  uuid(), 'Modeling & Simulation Center', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});


CREATE TABLE IF NOT EXISTS vmasc.sites (
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
insert into vmasc.sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640, 553f1fac-2eda-4db9-8d11-14a986eb3262, uuid(), 'EC&S Building', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into vmasc.sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  553f1fac-2eda-4db9-8d11-14a986eb3262, uuid(), 'Robbins Hall', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into vmasc.sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  848cf510-981d-4b37-aa9e-38e6dc02dc71, uuid(), 'Suffolk', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
