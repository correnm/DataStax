CREATE TABLE IF NOT EXISTS organizations (
    org_id 				uuid,
    organization_name 	text,		// lookup the name using the org_id. org_id is referenced in child tables
    industry_name_r 	text,		// references.industry_name
    audit_upsert 		frozen<audit_upsert>,
    PRIMARY KEY (org_id)
)
with comment = 'Parent table used to describe the organizational structure.';

// a few seed records
insert into organizations (org_id, organization_name, industry_name_r, audit_upsert)
VALUES
(uuid(), 'G2 Ops', 'technology', {datechanged: toUnixTimestamp(now()), changedbyusername: 'corren.mccoy@g2-ops.com'});
