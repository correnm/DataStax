select 
table_name,
column_name,
kind,
type,
clustering_order
from system_schema.columns
where keyspace_name='g2'
order by table_name, column_name;