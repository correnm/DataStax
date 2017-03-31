CREATE TYPE g2.audit_insert (
    dateadded 			timestamp,
    addedbyusername 	text
);

CREATE TYPE g2.audit_update (
    dateupdated 		timestamp,
    updatedbyusername 	text
);