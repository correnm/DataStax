CREATE TABLE vmasc.application_settings (
    setting_group text,
    setting_code text,
    allowed_settings frozen<set<text>>,
    constrained boolean,
    current_value int,
    data_type text,
    default_value int,
    description text,
    max_value int,
    min_value int,
    PRIMARY KEY (setting_group, setting_code)
) WITH comment = 'Manages application and configuration settings';
