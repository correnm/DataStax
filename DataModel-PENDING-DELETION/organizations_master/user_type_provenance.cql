USE keyspacename;

CREATE TYPE audit_upsert (
    datechanged		timestamp,
    changedbyusername 	text
);