CREATE TABLE IF NOT EXISTS sites (
    org_unit_id 			uuid,	// must be an org_unit_id found in the organizational_units table
    site_id 				uuid,
    site_name 				text,
	latitude				double,
	longitude				double,
    audit_upsert 			frozen<audit_upsert>,
    PRIMARY KEY ((org_unit_id), site_id)
) with comment = 'Represents a particular site within the organizational unit';
