USE dod;

// a few seed records
insert into sites (org_unit_id, site_id, site_name, audit_upsert)
VALUES
(ed820389-e7d2-4d5d-88f4-681e39099aa6, 1b4d46f1-2adc-46b8-aa23-7e1ce03fc04c, 'Battlefield Reporting', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_unit_id, site_id, site_name, audit_upsert)
VALUES
(703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, 96a3378e-2f67-4b9d-ab67-9ee9d533b88f, 'Component Analysis', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});

insert into sites (org_unit_id, site_id, site_name, audit_upsert)
VALUES
(703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, c0811226-9512-4e96-8e60-8f9b208eac99, 'Devices', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
