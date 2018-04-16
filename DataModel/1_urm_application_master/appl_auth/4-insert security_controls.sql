UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'na'},
{score:2, category:'low', description:'Everyone enabled',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'RBAC not federated',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'RBAC federated',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'federated strong governance with matching implementation',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='ACX';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none
',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'Work in Progress',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'Plan in place, not tested',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'Plan in place, tested only once',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Plan in place,regularly tested/validated',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='BCM';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'>50%',apply_impact_cost:true, cost_type:'primary'},
{score:2, category:'low', description:'<50%',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'<25%',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'<10%',apply_impact_cost:false, cost_type:'primary'},
{score:9.5, category:'very_high', description:'0',apply_impact_cost:false, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='CLD';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'internal/external tags only',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'formal policy',apply_impact_cost:true, cost_type:'primary'},
{score:7.95, category:'high', description:'Document Management System; formal definitions for data classes',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='CLS';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'secondary'},
{score:2, category:'low', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:5.45, category:'medium', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:7.95, category:'high', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:9.5, category:'very_high', description:'>=1',apply_impact_cost:true, cost_type:'secondary'}]
where security_control_group='industry' and security_control_id='CMP';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'secondary'},
{score:2, category:'low', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:5.45, category:'medium', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:7.95, category:'high', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:9.5, category:'very_high', description:'>=1',apply_impact_cost:true, cost_type:'secondary'}]
where security_control_group='industry' and security_control_id='CON';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'repeatable',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'documented (excel)',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'Change Mgmt Process',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'CCB',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='CPM';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'Advisory Services Contractor (PT)',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'Advisory Services Contractor (FT)',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'FTE',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'FTE Report to CEO/BOD',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='CPO';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'Advisory Services Contractor (PT)',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'Advisory Services Contractor (FT)',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'FTE',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'FTE Report to CEO/BOD',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='CSO';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'gateway, monitor mode',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'gateway with automated response, or gateway and internal monitor only', apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'gateway, internal, and/or host-based with automated response',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation', apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='DLP';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'XXXX',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'only at provider level',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'single point protection',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'multipoint internal protection',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='DOS';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'https/ssl/tls only',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'internal and external use of cryptographic mechanisms', apply_impact_cost:true, cost_type:'primary'},
{score:7.95, category:'high', description:'internal and external use of NIST compliant crytopgraphic algorithms', apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation', apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='ECR';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'client AV signature based only',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'client AV/AM with sandbox submission',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'Client and Network AV with sandbox submission', apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'Client AV/AM with sandbox; different AV/AM vendor protection suite with sandbox submission', apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation', apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='EMP';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'gateway only',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'internal relay and external (ingress), no quarrantine',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'internal relay and external (ingress) with quarrantine enabled',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='EMS';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'not C Suite',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'C Suite not CEO',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'CEO',apply_impact_cost:false, cost_type:'primary'},
{score:9.5, category:'very_high', description:'BOD Member',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='EXS';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'Not NGFW', apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'NGFW on perimeter only', apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'NGFW w/multiple interfaces', apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation', apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='FW1';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'formal policy/procedure',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'in-house management',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'IAM solution',apply_impact_cost:false, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='IAM';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'Internal/External, detection only',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'External only automatic prevention enabled',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'Internal/External automatic prevention enabled',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='IDPS';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'not suited for client',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'partially meets expectations',apply_impact_cost:true, cost_type:'primary'},
{score:7.95, category:'high', description:'meets expectations',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'n/a',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='INS';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'In-house & standard OS tools',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'in-house resources with COTS tools',apply_impact_cost:true, cost_type:'primary'},
{score:7.95, category:'high', description:'3rd Party Contractor Retainer',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Demonstrably mature internal team',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='IRT';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'n/a',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'n/a',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'n/a',apply_impact_cost:false, cost_type:'primary'},
{score:9.5, category:'very_high', description:'yes',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='LSD';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'single app',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'multiple apps',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'multiple apps and desktop/workstation',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'token based (HW/SW), not SMS/email', apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='MFA';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'secondary'},
{score:2, category:'low', description:'HDE, strong pin',apply_impact_cost:false, cost_type:'secondary'},
{score:5.45, category:'medium', description:'fully managed corp and personal',apply_impact_cost:false, cost_type:'secondary'},
{score:7.95, category:'high', description:'fully managed corp only (permitted)',apply_impact_cost:false, cost_type:'secondary'},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:true, cost_type:'secondary'}]
where security_control_group='industry' and security_control_id='MOB';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'specific external apps/hosts only',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'full external only',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'internal and external testing',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'red team testing',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='PNT';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'secondary'},
{score:2, category:'low', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:5.45, category:'medium', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:7.95, category:'high', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:9.5, category:'very_high', description:'yes',apply_impact_cost:true, cost_type:'secondary'}]
where security_control_group='industry' and security_control_id='RTN';


UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'onboarding onlyl',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'annually',apply_impact_cost:true, cost_type:'primary'},
{score:7.95, category:'high', description:'annually with regular information sharing and testing', apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation', apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='SAT';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'internal DMZ only',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'internal VLAN',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'micro-segmentation',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'SDN',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='SEG';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'not monitored',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'8 by 5 monitoring',apply_impact_cost:false, cost_type:'primary'},
{score:7.95, category:'high', description:'24/7 monitoring',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='SIM';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'primary'},
{score:2, category:'low', description:'automated tools only',apply_impact_cost:false, cost_type:'primary'},
{score:5.45, category:'medium', description:'automated tools and free subscriptions',apply_impact_cost:true, cost_type:'primary'},
{score:7.95, category:'high', description:'automated tools and fee-based subscriptions',apply_impact_cost:true, cost_type:'primary'},
{score:9.5, category:'very_high', description:'Share and Subscribe',apply_impact_cost:true, cost_type:'primary'}]
where security_control_group='industry' and security_control_id='TIP';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:'secondary'},
{score:2, category:'low', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:5.45, category:'medium', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:7.95, category:'high', description:'n/a',apply_impact_cost:false, cost_type:'secondary'},
{score:9.5, category:'very_high', description:'>=1',apply_impact_cost:true, cost_type:'secondary'}]
where security_control_group='industry' and security_control_id='TPI';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'annually',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'bi-annually',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'quarterly',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'monthly',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='VLN';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'S2S, client',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'S2S, Client, NIST approved algorithms',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'FIPS Validated with token MFA',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='VPN';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'URL only',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'URL/context',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'URL/context/content',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='WCF';

UPDATE appl_auth.security_controls
SET rating=[{score:0.0,category:'very_low',description:'none/WEP',apply_impact_cost:false, cost_type:''},
{score:2, category:'low', description:'WPA2 PSK',apply_impact_cost:false, cost_type:''},
{score:5.45, category:'medium', description:'WPA2 ENT',apply_impact_cost:false, cost_type:''},
{score:7.95, category:'high', description:'WPA2 Ent with contextual elements (client auth, location, etc.)',apply_impact_cost:false, cost_type:''},
{score:9.5, category:'very_high', description:'Strong governance with matching implementation',apply_impact_cost:false, cost_type:''}]
where security_control_group='industry' and security_control_id='WLS';
