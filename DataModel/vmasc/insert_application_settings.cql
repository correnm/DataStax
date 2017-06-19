// Seed data to use when the application is created

// ******security policy
INSERT INTO vmasc.application_settings
(setting_group, setting_code, description, constrained, data_type, min_value, max_value, default_value, current_value, allowed_settings) 
VALUES
(
'account_lockout_policy', 'LOCKDUR','lockout duration time after too many failed attempts', FALSE, 'int', 0, 999, 30, 2, {}
);

INSERT INTO vmasc.application_settings
(setting_group, setting_code, description, constrained, data_type, min_value, max_value, default_value, current_value, allowed_settings) 
VALUES
(
'account_lockout_policy', 'LOCKTHRESH','number of consecutive failed attempts', FALSE, 'int', 0, 100, 5, 5, {}
);

INSERT INTO vmasc.application_settings
(setting_group, setting_code, description, constrained, data_type, min_value, max_value, default_value, current_value, allowed_settings) 
VALUES
(
'account_lockout_policy', 'RESETCNT','number of minutes until the account lockout counter is reset', FALSE, 'int', 1, 1440, 1440, 1440, {}
);

// ******password policy
INSERT INTO vmasc.application_settings
(setting_group, setting_code, description, constrained, data_type, min_value, max_value, default_value, current_value, allowed_settings) 
VALUES
(
'password_policy', 'PASSLGNTH','minimum password length', FALSE, 'int', 10, 128, 10, 910,{}
);

INSERT INTO vmasc.application_settings
(setting_group, setting_code, description, constrained, data_type, min_value, max_value, default_value, current_value, allowed_settings) 
VALUES
(
'password_policy', 'MINPASSAGE','the minimum password age (in days) determines how frequently a user can change her password', FALSE, 'int', 30, 120, 30, 30, {}
);

INSERT INTO vmasc.application_settings
(setting_group, setting_code, description, constrained, data_type, min_value, max_value, default_value, current_value, allowed_settings) 
VALUES
(
'password_policy', 'MAXPASSAGE','the maximum password age (in days) determines how long a user can retain the same password', FALSE, 'int', 30, 120, 30, 30, {}
);