USE dod;

CREATE TABLE IF NOT EXISTS hardware_statistics (
    site_or_ou_name 				text,
    ip_subnet_or_building 			text,  // 192.168.0.0, site/building
    internal_system_id 				text,  // UUID computed to ensure uniqueness
    observation_date 				date,  // Date analytics run on hardware reporting NIV, etc.
    vulnerability_count 			int,
    node_impact_value 				float,
    risk_likelihood 				float,
    cyvar 							float,  // Cyber Value at Risk
    PRIMARY KEY ((site_or_ou_name), ip_subnet_or_building, internal_system_id, observation_date)
) WITH COMMENT = 'Maintains a daily temporal history of analytics information for each component.';

CREATE TABLE IF NOT EXISTS hardware_stat_rollup (
  site_or_ou_name 					text,
  ip_subnet_or_building 			text,	// 192.168.0.0, site/building
  internal_system_id 				text,   // UUID computed to ensure uniqueness
  reporting_date 					date,   // Date reported: Q1 would be yyyy/mar/31
  reporting_label 					text,   // YYYY-Q1
  vulnerability_count 				int,
  node_impact_value 				float,
  risk_likelihood 					float,
  cyvar 							float,   // Cyber Value at Risk
  PRIMARY KEY ((site_or_ou_name), ip_subnet_or_building, internal_system_id, reporting_date)
) WITH COMMENT = 'Year-Quarterly aggregation of the hardware statistics.';
