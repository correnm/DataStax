CREATE TABLE IF NOT EXISTS g2.business_practice (
category						text,				// e.g. asset_type
business_value					text,				// e.g., infrastructure
collateral_damage_current		decimal,			// allows customization for evaluation
collateral_damage_default		decimal,			// default values as determined by VMASC analysis
target_distribution_current		decimal,
target_distribution_default		decimal,
confidentiality_req_current		decimal,
confidentiality_req_default		decimal,
integrity_req_current			decimal,
integrity_req_default			decimal,
availability_req_current		decimal,
availability_req_default		decimal,
audit_upsert 					frozen<audit_upsert>,
PRIMARY KEY (category, business_value)
)
WITH COMMENT = 'Defines the business practice attributes needed to calculate the node impact value';