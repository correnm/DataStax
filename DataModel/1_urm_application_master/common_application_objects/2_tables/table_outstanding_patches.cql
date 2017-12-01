USE keyspacename;

CREATE TABLE IF NOT EXISTS outstanding_patches (
    site_or_ou_name 				text,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
	run_date					    date,  // run_date from the associated hardware records
    critical_count					int,
	high_count						int,
	medium_count					int,
	low_count						int,
    vulnerability_count 			int,
     PRIMARY KEY ((site_or_ou_name, ip_subnet_or_building), run_date)
) WITH COMMENT = 'Maintains a count of unapplied patches which are older than the organizations threshold.';

