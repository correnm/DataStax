USE keyspacename;

CREATE TABLE IF NOT EXISTS hardware_daily_summary (
    site_or_ou_name 				text,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
	week_start_date					date,  // Sunday that starts the week m/d/yyyy
	day_of_week						date,  // actual date m/d/yyyy
    critical_count					int,
	high_count						int,
	medium_count					int,
	low_count						int,
    vulnerability_count 			int,
	patches_critical_count			int,	// exceeding threshold
	patches_high_count				int,
	patches_medium_count			int,
	patches_low_count				int,
    patches_count 					int,
     PRIMARY KEY (site_or_ou_name, ip_subnet_or_building, week_start_date, day_of_week)
) WITH COMMENT = 'Maintains a daily temporal history of vulnerability data from hardware.';

CREATE TABLE IF NOT EXISTS hardware_weekly_summary (
    site_or_ou_name 				text,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
	month_end_date					date,  // last day of the month associated with the week
	week_start_date					date,  // Sunday that starts the week m/d/yyyy
	critical_count					int,
	high_count						int,
	medium_count					int,
	low_count						int,
    vulnerability_count 			int,
	patches_critical_count			int,	// exceeding threshold
	patches_high_count				int,
	patches_medium_count			int,
	patches_low_count				int,
    patches_count 					int,
     PRIMARY KEY (site_or_ou_name, ip_subnet_or_building, month_end_date, week_start_date)
) WITH COMMENT = 'weekly-year aggregation of vulnerability data from hardware.';


CREATE TABLE IF NOT EXISTS hardware_monthly_summary (
    site_or_ou_name 				text,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
	month_end_date					date,  // last day of the month associated with the week
	critical_count					int,
	high_count						int,
	medium_count					int,
	low_count						int,
    vulnerability_count 			int,
	patches_critical_count			int,	// exceeding threshold
	patches_high_count				int,
	patches_medium_count			int,
	patches_low_count				int,
    patches_count 					int,
     PRIMARY KEY (site_or_ou_name, ip_subnet_or_building, month_end_date)
) WITH COMMENT = 'month-year aggregation of vulnerability data from hardware.';

