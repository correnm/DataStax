USE dod;

CREATE TYPE IF NOT EXISTS audit_upsert (
    datechanged			timestamp,
    changedbyusername 	text
);