CREATE TABLE IF NOT EXISTS hardware_stat_rollup (
  org_id 							uuid,
  site_or_ou_name 					text,
  ip_subnet_or_building 			text,	// 192.168.0.0, site/building
  internal_system_id 				text,   // UUID computed to ensure uniqueness
  reporting_date 					date,   // Date reported: Q1 would be yyyy/mar/31
  reporting_label 					text,   // YYYY-Q1
  vulnerability_count 				int,
  node_impact_value 				float,
  risk_likelihood 					float,
  cyvar 							float,                  // Cyber Value at Risk
  PRIMARY KEY ((org_id, site_or_ou_name), ip_subnet_or_building, internal_system_id, reporting_date)
) WITH COMMENT = 'Year-Quarterly aggregation of the hardware statistics.';
