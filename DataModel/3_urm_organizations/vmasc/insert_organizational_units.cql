
// a few seed records
insert into organizational_units (org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(553f1fac-2eda-4db9-8d11-14a986eb3262, 'Main Campus', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizational_units (org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(848cf510-981d-4b37-aa9e-38e6dc02dc71, 'Modeling & Simulation Center', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
