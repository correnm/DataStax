USE dod;

// a few seed records
insert into organizational_units (org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, 'Pilot System Architecture', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into organizational_units (org_unit_id, org_unit_name, unit_type_r, audit_upsert)
VALUES
(ed820389-e7d2-4d5d-88f4-681e39099aa6, 'External System Architecture', 'permanent', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

