USE keyspace;

CREATE TABLE IF NOT EXISTS organizational_units (
    org_unit_id 		uuid,
    org_unit_name 		text,
    unit_type_r			text,		// references.unit_type = "permanent", "M&A evaluation"
    audit_upsert 		frozen<audit_upsert>,
    PRIMARY KEY (org_unit_id)
)
WITH COMMENT = 'Represents a subdivision of the parent organization';