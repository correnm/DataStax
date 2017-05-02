CREATE TABLE IF NOT EXISTS g2.users (
org_id 							uuid,
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
session_id						uuid,
application_role_name			text,
cell_phone_number				text,
cell_carrier					text,
auth_code						int,
send_notification_email			boolean,
audit_insert 					frozen<audit_insert>,
audit_update					frozen<audit_update>,
security						list<frozen <qa_info>>, // security questions and answers
PRIMARY KEY ((org_id), user_email)
)
WITH COMMENT = 'Application users for each organization';

insert into users
(org_id, user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
(cb3a193b-8ef8-4172-8cd0-b7ff94f7ad41,'corren.mccoy@g2-ops.com', TRUE,'admin', 
'Corren','McCoy',TRUE, TRUE,'corren.mccoy@g2-ops.com',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'},
[{question: 'who', answer: 'me'}, {question: 'what', answer: 'rabbit'}, {question: 'when', answer: 'friday'} ]
);

insert into users
(org_id, user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
(cb3a193b-8ef8-4172-8cd0-b7ff94f7ad41,'john.reddy@g2-ops.com', TRUE,'admin', 
'John','Reddy',TRUE, TRUE,'john.reddy@g2-ops.com',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'},
[{question: 'who', answer: 'me'}, {question: 'what', answer: 'rabbit'}, {question: 'when', answer: 'friday'} ]
);

insert into users
(org_id, user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
(cb3a193b-8ef8-4172-8cd0-b7ff94f7ad41,'dang.le@g2-ops.com', TRUE,'user', 
'Danny','Le',TRUE, FALSE,'dang.le@g2-ops.com',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy@g2-ops.com'},
[{question: 'who', answer: 'me'}, {question: 'what', answer: 'rabbit'}, {question: 'when', answer: 'friday'} ]
);