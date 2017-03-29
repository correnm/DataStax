CREATE TABLE IF NOT EXISTS g2.users (
org_id 		uuid,
user_email text,
user_name text,
user_role text,
hashed_password text,
PRIMARY KEY ((org_id), user_email)
);

