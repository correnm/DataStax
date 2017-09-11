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
(2cd3bc78-350f-47ce-bd53-7525b93f0640, 553f1fac-2eda-4db9-8d11-14a986eb3262, c0811226-9512-4e96-8e60-8f9b208eac99, 'EC&S Building', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  553f1fac-2eda-4db9-8d11-14a986eb3262, 1b4d46f1-2adc-46b8-aa23-7e1ce03fc04c, 'Robbins Hall', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_id, org_unit_id, site_id, site_name, audit_upsert)
VALUES
(2cd3bc78-350f-47ce-bd53-7525b93f0640,  848cf510-981d-4b37-aa9e-38e6dc02dc71, 96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 'Suffolk', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
