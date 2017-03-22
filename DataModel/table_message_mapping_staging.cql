CREATE TABLE g2.message_mapping_staging (
  magic_draw_id text,
  site text,
  mission_support text,
  connector text,
  destination_id text,
  PRIMARY KEY ((magic_draw_id), destination_id)
);