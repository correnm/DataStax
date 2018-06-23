/**
SMS Gateways needed to send password reset emails to users. Select the
providers to query based on the country of the organization. Sample
records for insert retrieved from http://smsemailgateway.com/

Modifications
06-22-2018		corren.mccoy	
*/

CREATE TABLE IF NOT EXISTS sms_gateways (
	country_name	text, // use lov_references to validate country name
	provider		text, // cell carrier e.g., Verizon
	email_domain	text, // vtext.com
	notes			text,
    PRIMARY KEY ((country_name), provider)
) WITH COMMENT = 'Master list of SMS Gateways needed to send password reset emails to users' ;


//
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Bell','txt.bell.ca','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Bell Mobility','txt.bellmobility.ca','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Koodo Mobile','msg.koodomobile.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Fido (Microcell)','fido.ca','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Manitoba Telecom Systems','text.mtsmobility.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','NBTel','wirefree.informe.ca','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','PageNet','pagegate.pagenet.ca','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Rogers','pcs.rogers.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Sasktel','sms.sasktel.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Telus','msg.telus.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'Canada','Virgin Mobile','vmobile.ca','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Metro PCS','mymetropcs.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','T-Mobile','tmomail.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Verizon Wireless','vtext.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','AT&T','txt.att.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Sprint PCS','messaging.sprintpcs.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Nextel','messaging.nextel.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cricket','sms.mycricket.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','US Cellular','email.uscc.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cingular (GSM)','cingularme.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cingular (TDMA)','mmode.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','3 River Wireless','sms.3rivers.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Advantage Communications','advantagepaging.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','AirVoice','mmode.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','AllTel','message.alltel.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Bell South','sms.bellsouth.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Bell South (Blackberry)','bellsouthtips.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Bell South Mobility','blsdcs.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Bluegrass Cellular','sms.bluecell.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Boost Mobile','myboostmobile.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','CallPlus','mmode.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Carolina Mobile Communications','cmcpaging.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cellular One','mobile.celloneusa.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cellular One East Coast','phone.cellone.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cellular One PCS','paging.cellone-sf.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cellular One South West','swmsg.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cellular One West','mycellone.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cellular South','csouth1.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Central Vermont Communications','cvcpaging.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','CenturyTel','messaging.centurytel.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cingular Wireless','mobile.mycingular.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Cook Paging','cookmail.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Corr Wireless Communications','corrwireless.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Dobson Communications Corporation','mobile.dobson.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Dobson-Alex Wireless / Dobson-Cellular One','mobile.cellularone.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Edge Wireless','sms.edgewireless.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Galaxy Corporation','sendabeep.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','GrayLink / Porta-Phone','epage.porta-phone.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','GTE','gte.pagegate.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Houston Cellular','text.houstoncellular.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Inland Cellular Telephone','inlandlink.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','JSM Tele-Page','jsmtel.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Lauttamus Communication','e-page.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','MCI','pagemci.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','MCI Phone','mci.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Metrocall','page.metrocall.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Metrocall 2-way','my2way.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Midwest Wireless','clearlydigital.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Mobilecom PA','page.mobilcom.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Mobilfone','page.mobilfone.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Morris Wireless','beepone.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Nextel','page.nextel.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','NPI Wireless','npiwireless.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Ntelos','pcs.ntelos.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Omnipoint','omnipoint.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','OnlineBeep','onlinebeep.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Pacific Bell','pacbellpcs.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','PageMart','pagemart.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','PageOne NorthWest','page1nw.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','PCS One','pcsone.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Pioneer / Enid Cellular','msg.pioneerenidcellular.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Price Communications','mobilecell1se.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','ProPage','page.propage.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Public Service Cellular','sms.pscel.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Qwest','qwestmp.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','RAM Page','ram-page.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Safaricom','safaricomsms.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Satelindo GSM','satelindogsm.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Simple Freedom','text.simplefreedom.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Smart Telecom','mysmart.mymobile.ph','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Southern LINC','page.southernlinc.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Southwestern Bell','email.swbw.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Sprint Paging','sprintpaging.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','SunCom','tms.suncom.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Surewest Communications','mobile.surewest.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Teletouch','pageme.teletouch.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Telus','msg.telus.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','TIM','timnet.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Triton','tms.suncom.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','TSR Wireless','beep.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Unicel','utext.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','USA Mobility','mobilecomm.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Verizon PCS','myvzw.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Virgin Mobile','vmobl.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','West Central Wireless','sms.wcc.net','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Western Wireless','cellularonewest.com','');
INSERT INTO SMS_Gateways (country_name,provider,email_domain,notes) VALUES (
'United States','Wyndtell','wyndtell.com','');
