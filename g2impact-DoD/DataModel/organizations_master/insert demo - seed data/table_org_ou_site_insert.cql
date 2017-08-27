USE dod;

// a few seed records
insert into organizations (org_id, organization_name, industry_name_r, audit_upsert)
VALUES
(uuid(), 'DOD', 'private', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});


// a few seed records
insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  uuid(), 'Main Campus', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizational_units (org_id, org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  uuid(), 'Modeling & Simulation Center', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

// a few seed records
insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640, 553f1fac-2eda-4db9-8d11-14a986eb3262, uuid(), 'EC&S Building', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  553f1fac-2eda-4db9-8d11-14a986eb3262, uuid(), 'Robbins Hall', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  848cf510-981d-4b37-aa9e-38e6dc02dc71, uuid(), 'Suffolk', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
