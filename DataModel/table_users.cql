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
session_id						uuid,
application_role_name			text,
cell_phone_number				text,
cell_carrier					text,
auth_code						int,
send_notification_email			boolean,
audit_insert 					frozen<audit_insert>,
audit_update					frozen<audit_update>,
security						list<frozen <qa_info>>, // security questions and answers
PRIMARY KEY ((user_email))
)
WITH COMMENT = 'Application users for each organization';

insert into users
(user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
('corren.mccoy@g2-ops.com', TRUE,'admin', 
'Corren','McCoy',TRUE, TRUE,'corren.mccoy',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy'},
[{question: 'who', answer: 'me'}, {question: 'what', answer: 'rabbit'}, {question: 'when', answer: 'friday'} ]
);

insert into users
(user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
('john.reddy@g2-ops.com', TRUE,'admin', 
'John','Reddy',TRUE, TRUE,'john.reddy',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy'},
[{question: 'who', answer: 'me'}, {question: 'what', answer: 'rabbit'}, {question: 'when', answer: 'friday'} ]
);

insert into users
(user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
('dang.le@g2-ops.com', TRUE,'user', 
'Danny','Le',TRUE, FALSE,'dang.le',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy'},
[{question: 'who', answer: 'me'}, {question: 'what', answer: 'rabbit'}, {question: 'when', answer: 'friday'} ]
);

insert into users
(user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
('keith.alston@g2-ops.com', TRUE,'admin', 
'Keith','Alston',TRUE, TRUE,'keith.alston',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy'},
[{question: 'who', answer: 'is it'}, {question: 'what', answer: 'are we doing'}, {question: 'when', answer: 'will we be done'} ]
);

insert into users
(user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
('tracy.gregorio@g2-ops.com', TRUE,'user', 
'Tracy','Gregorio',TRUE, FALSE,'tracy.gregorio',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy'},
[{question: 'who', answer: 'is it'}, {question: 'what', answer: 'are we doing'}, {question: 'when', answer: 'will we be done'} ]
);

insert into users
(user_email, active_user_ind, application_role_name, 
first_name, last_name, send_notification_email,system_administrator_ind, user_name,
audit_insert, security)
values
('sara.prokop@g2-ops.com', TRUE,'user', 
'Sara','Prokop',TRUE, FALSE,'sara.prokop',
{dateadded: toUnixTimestamp(now()), addedbyusername: 'corren.mccoy'},
[{question: 'who', answer: 'is it'}, {question: 'what', answer: 'are we doing'}, {question: 'when', answer: 'will we be done'} ]
);