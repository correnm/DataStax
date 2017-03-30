select 
table_name,
comment
from system_schema.tables
where keyspace_name='g2'
order by table_name;