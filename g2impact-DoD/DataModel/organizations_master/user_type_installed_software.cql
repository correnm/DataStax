USE dod;

CREATE TYPE IF NOT EXISTS installed_software(
    software 		text,		// name of the application or cpe id
    iiv_score		decimal		// calculated a precursor for node impact value
);