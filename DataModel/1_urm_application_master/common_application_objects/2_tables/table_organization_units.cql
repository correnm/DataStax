USE keyspace;

CREATE TABLE IF NOT EXISTS organizational_units (
    org_unit_id 		uuid,
    org_unit_name 		text,
    unit_type_r			text,		// references.unit_type = "permanent", "M&A evaluation"
    audit_upsert 		frozen<audit_upsert>,
    PRIMARY KEY (org_unit_id)
)
WITH COMMENT = 'Represents a subdivision of the parent organization';

INSERT INTO g2.organizational_units
(org_unit_id, audit_upsert, org_unit_name, unit_type_r)
VALUES(703161c5-eca3-48a0-8ad9-99f2a6b8d5e7, {datechanged:1497540217931,changedbyusername:'corren.mccoy@g2-ops.com'}, 'Long Term Acute Care', 'permanent');
INSERT INTO g2.organizational_units
(org_unit_id, audit_upsert, org_unit_name, unit_type_r)
VALUES( ed820389-e7d2-4d5d-88f4-681e39099aa6, {datechanged:1497540217973,changedbyusername:'corren.mccoy@g2-ops.com'}, 'Acute Care Hospital', 'permanent');
