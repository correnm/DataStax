CREATE TABLE g2.refs (
  org_id uuid,
  reference_key text,
  reference_value text,  // can be any string, such as a JSON or a text list
  PRIMARY KEY ((org_id), reference_key)
)
