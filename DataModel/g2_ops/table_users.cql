CREATE TABLE IF NOT EXISTS g2.users (
user_email 						text,
user_name 						text,
hashed_password 				text,
default_lens_view_r				text,	// references.lens
first_name						text,
last_name						text,
login_timeout_start_time		timestamp,
num_consecutive_failed_attempts	int,
last_successful_login_date_time	timestamp,
login_start_time				timestamp,
system_administrator_ind		boolean,
active_user_ind					boolean,
password_last_reset				date,
application_role_name			text,
cell_phone_number				text,
cell_carrier					text,
auth_code						int,
send_notification_email			boolean,
audit_upsert 					frozen<audit_upsert>,
security						list<frozen <qa_info>>, // security questions and answers
ip_address						text, 					// record the login host
PRIMARY KEY ((user_email))
)
WITH COMMENT = 'Application users for each organization';