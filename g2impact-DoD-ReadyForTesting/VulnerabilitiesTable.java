package com.g2ops.washington.beans;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.g2ops.washington.types.Vulnerability;
import com.g2ops.washington.utils.Vulnerabilities;

/**
 * @author 		John Reddy, G2 Ops, Virginia Beach, VA
 * @version 	1.00, July 2017
 * @see			
 * @exception
 * 
 * <p>Known Bugs: (a list of bugs and other problems)
 *
 *
 * <p>Revision History:
 * Date				Author				Revision Description
 * 19-Jul-2017		corren.mccoy		Added additional placeholder data for demo purposes
 * 11-Aug-2017		corren.mccoy		Change in values for critical assets to match PCI, PHI, PII icon selection
 * 										dashboard-slide-1a.xhtml in vulnerabilitiesTableID section.
 * 28-Aug-2017		corren.mccoy		Change business process to mission values
 */
@ManagedBean
@SessionScoped
public class VulnerabilitiesTable extends Vulnerabilities implements Serializable {

	private static final long serialVersionUID = 1L;

	public VulnerabilitiesTable () {
		// Data is: ID, CVEID, description, CVSSbase, CVSStemporal, IIV, BusinessProcess, PatchAvailability, CriticalAssets
		// The ID should match a node in the TopologyNodeChart so it's clickable and controls the detailed view
		super("Vulnerabilities Detail",
			new Vulnerability("01", "CVE-2015-8866", "ext/libxml/libxml.c in PHP before 5.5.22 and 5.6.x before 5.6.6, when PHP-FPM is used", 9.6, 6.0, 9.7, "Logistics Resupply Request", "Confirm: https://bugs.php.net/bug.php?id=64938", "PII"),
			new Vulnerability("02", "CVE-2016-2326", "Integer overflow in the asf_write_packet function in libavformat/asfenc", 8.8, 5.9, 4.7, "Chemical Downwind", "GLSA-201705-08", "PHI"),
			new Vulnerability("02", "CVE-2015-1791", "Race condition in the ssl3_get_new_session_ticket function", 6.8, 6.4, 6.7, "Meteorological-Fallout", "http://link.com/topatch", "PCI"),
			new Vulnerability("03", "CVE-2015-1791", "Race condition in the ssl3_get_new_session_ticket function", 6.8, 6.4, 5.7, "Operations Summary", "http://link.com/topatch", "PHI"),
			new Vulnerability("04", "CVE-2016-2326", "Integer overflow in the asf_write_packet function in libavformat/asfenc", 8.8, 5.9, 5.3, "Rehabilitation", "GLSA-201705-08", "PHI"),
			new Vulnerability("09", "CVE-2009-2526", "Microsoft Windows Vista Gold, SP1, and SP2 and Server 2008 Gold and SP2 do not properly validate fields in SMBv2 packets", 7.8, null, 4.7, "Radar Status Report", "Vendor Advisory: http://www.microsoft.com/technet/security/Bulletin/MS09-050.mspx", "PHI"),
			new Vulnerability("09", "CVE-2009-2532", "Microsoft Windows Vista Gold, SP1, and SP2, Windows Server 2008 Gold and SP2, and Windows 7 RC do not properly process the command value in an SMB Multi-Protocol", 10.0, null, 10.0, "Fire Support", "Vendor Advisory: http://www.microsoft.com/technet/security/Bulletin/MS09-050.mspx", "PHI"),
			new Vulnerability("10", "CVE-2016-7854", "Adobe Reader and Acrobat before 11.0.18, Acrobat and Acrobat Reader DC Classic before 15.006.30243", 9.8, 5.9, 8.1, "Explosive Ordinance", "Confirm: https://helpx.adobe.com/security/products/acrobat/apsb16-33.html", "PII"),
			new Vulnerability("10", "CVE-2009-3551", "Off-by-one error in the dissect_negprot_response function in packet-smb.c in Wireshark 1.2.0", 5.0, null, 2.3, "Human Remains Searach and Recovery", "Patch: http://www.wireshark.org/docs/relnotes/wireshark-1.2.3.html", "PHI"),
			new Vulnerability("11", "CVE-2016-7854", "Adobe Reader and Acrobat before 11.0.18, Acrobat and Acrobat Reader DC Classic before 15.006.30243", 9.8, 5.9, 8.1, "Explosive Ordinance", "Confirm: https://helpx.adobe.com/security/products/acrobat/apsb16-33.html", "PII"),
			new Vulnerability("11", "CVE-2009-3551", "Off-by-one error in the dissect_negprot_response function in packet-smb.c in Wireshark 1.2.0", 5.0, null, 2.3, "Friendly Nuclear Strike", "Patch: http://www.wireshark.org/docs/relnotes/wireshark-1.2.3.html", "PHI"),
			new Vulnerability("12", "CVE-2016-7854", "Adobe Reader and Acrobat before 11.0.18, Acrobat and Acrobat Reader DC Classic before 15.006.30243", 9.8, 5.9, 8.1, "Decontamination Request", "Confirm: https://helpx.adobe.com/security/products/acrobat/apsb16-33.html", "PII"),
			new Vulnerability("12", "CVE-2009-3551", "Off-by-one error in the dissect_negprot_response function in packet-smb.c in Wireshark 1.2.0", 5.0, null, 2.3, "Personnel Status Report", "Patch: http://www.wireshark.org/docs/relnotes/wireshark-1.2.3.html", "PHI"),
			new Vulnerability("13", "CVE-2016-7854", "Adobe Reader and Acrobat before 11.0.18, Acrobat and Acrobat Reader DC Classic before 15.006.30243", 9.8, 5.9, 8.1, "Message Correction", "Confirm: https://helpx.adobe.com/security/products/acrobat/apsb16-33.html", "PII"),
			new Vulnerability("13", "CVE-2009-3551", "Off-by-one error in the dissect_negprot_response function in packet-smb.c in Wireshark 1.2.0", 5.0, null, 2.3, "Message Correction", "Patch: http://www.wireshark.org/docs/relnotes/wireshark-1.2.3.html", "PHI"),
			new Vulnerability("14", "CVE-2015-8866", "ext/libxml/libxml.c in PHP before 5.5.22 and 5.6.x before 5.6.6, when PHP-FPM is used", 9.6, 6.0, 9.7, "Mail Distribution Scheme Change", "Confirm: https://bugs.php.net/bug.php?id=64938", "PCI"),
			new Vulnerability("14", "CVE-2016-2326", "Integer overflow in the asf_write_packet function in libavformat/asfenc", 8.8, 5.9, 4.7, "Flight Control Information", "GLSA-201705-08", "PHI"),
			new Vulnerability("15", "CVE-2015-1791", "Race condition in the ssl3_get_new_session_ticket function", 6.8, 6.4, 6.7, "Patrol Report", "http://link.com/topatch", "PCI"),
			new Vulnerability("16", "CVE-2015-1791", "Race condition in the ssl3_get_new_session_ticket function", 6.8, 6.4, 5.7, "Rear Area Security", "http://link.com/topatch", "PHI"),
			new Vulnerability("17", "CVE-2016-2326", "Integer overflow in the asf_write_packet function in libavformat/asfenc", 8.8, 5.9, 5.3, "Media Contact Report", "GLSA-201705-08", "PHI"),
			new Vulnerability("18", "CVE-2009-2526", "Microsoft Windows Vista Gold, SP1, and SP2 and Server 2008 Gold and SP2 do not properly validate fields in SMBv2 packets", 7.8, null, 4.7, "Psychological Operations", "Vendor Advisory: http://www.microsoft.com/technet/security/Bulletin/MS09-050.mspx", "PHI"),
			new Vulnerability("19", "CVE-2009-2532", "Microsoft Windows Vista Gold, SP1, and SP2, Windows Server 2008 Gold and SP2, and Windows 7 RC do not properly process the command value in an SMB Multi-Protocol", 10.0, null, 10.0, "Medical Evacuation", "Vendor Advisory: http://www.microsoft.com/technet/security/Bulletin/MS09-050.mspx", "PII"),
			new Vulnerability("20", "CVE-2009-2532", "Microsoft Windows Vista Gold, SP1, and SP2, Windows Server 2008 Gold and SP2, and Windows 7 RC do not properly process the command value in an SMB Multi-Protocol", 10.0, null, 10.0, "General Administrative", "Vendor Advisory: http://www.microsoft.com/technet/security/Bulletin/MS09-050.mspx", "PHI"),
			new Vulnerability("05", "CVE-2009-2532", "Microsoft Windows Vista Gold, SP1, and SP2, Windows Server 2008 Gold and SP2, and Windows 7 RC do not properly process the command value in an SMB Multi-Protocol", 10.0, null, 10.0, "Reconnaissance Exploitation", "Vendor Advisory: http://www.microsoft.com/technet/security/Bulletin/MS09-050.mspx", "PHI")
				);
	}
}
