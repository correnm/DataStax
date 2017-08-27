CREATE KEYSPACE dod WITH replication = {'class': 'NetworkTopologyStrategy', 'dc0': '1'}  AND durable_writes = true;

// while in development with just the seed node available
ALTER KEYSPACE dod WITH REPLICATION= {'class': 'NetworkTopologyStrategy', 'dc0': '1'}
// On each affected node, run the nodetool repair command.
// Wait until repair completes on a node, then move to the next node.

