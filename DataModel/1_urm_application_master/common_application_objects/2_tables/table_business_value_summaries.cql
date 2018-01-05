USE keyspacename;

CREATE TABLE IF NOT EXISTS business_daily_summary (
    site_id							uuid,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
	week_start_date					date,  // Sunday that starts the week m/d/yyyy
	day_of_week						date,  // actual date m/d/yyyy
	cyber_value_at_risk				decimal,
	annotation_text					text,
     PRIMARY KEY (site_id, ip_subnet_or_building, week_start_date, day_of_week)
) WITH COMMENT = 'Maintains a daily temporal history of cyvar from business_value_attribution.';

CREATE TABLE IF NOT EXISTS business_weekly_summary (
    site_id							uuid,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
	month_end_date					date,  // last day of the month associated with the week
	week_start_date					date,  // Sunday that starts the week m/d/yyyy
	cyber_value_at_risk				decimal,
	annotation_text					text,
    PRIMARY KEY (site_id, ip_subnet_or_building, month_end_date, week_start_date)
) WITH COMMENT = 'weekly-year aggregation of cyvar data from business_value_attribution.';


CREATE TABLE IF NOT EXISTS business_monthly_summary (
    site_id							uuid,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
	month_end_date					date,  // last day of the month associated with the week
	cyber_value_at_risk				decimal,
	annotation_text					text,
     PRIMARY KEY (site_id, ip_subnet_or_building, month_end_date)
) WITH COMMENT = 'month-year aggregation of cyvar data from business_value_attribution.';

