USE keyspacename;

CREATE TYPE IF NOT EXISTS record_types(
	record_type_name					text,
    record_short_name					text, 	// PII, PCI, PHI
	record_count						int,
	audit_date							date,	// yyyy-mm-dd
    breach_cost_per_record				decimal,
	dark_web_cost_per_record			decimal,
	breach_cost_total					decimal,
	dark_web_cost_total					decimal
);