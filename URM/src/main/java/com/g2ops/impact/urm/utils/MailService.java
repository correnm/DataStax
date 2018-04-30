package com.g2ops.impact.urm.utils;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

import com.g2ops.impact.urm.utils.EncryptionDecryptionService;


public class MailService {

	private static MailService mailService = null;

    private static Session mailSession;

	// get the servlet's context
	private static ServletContext ctx = SessionUtils.getRequest().getServletContext();

	// read the mail parameters from the web.xml file
	private static final String HOST = ctx.getInitParameter("mail_HOST");
    private static final String PORT = ctx.getInitParameter("mail_PORT");
    private static final String USER = ctx.getInitParameter("mail_USER"); // must be valid user
    private static String PASSWORD = ctx.getInitParameter("mail_PASSWORD"); // must be valid password for user above
    //private static final String FROM = "john.reddy@g2-ops.com"; // full info for user, e.g. "Fred Smith <smit0012@d.umn.edu>"


    // constructor
    private MailService() {

    	// decrypt the mail account password
    	try {
			PASSWORD = EncryptionDecryptionService.decrypt(PASSWORD);
			//System.out.println("decrypted mail password: " + PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Properties props = new Properties();

        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.user", USER);
        props.put("mail.password", PASSWORD);

        // create Authenticator object to pass to Session.getDefaultInstance method
        Authenticator auth = new Authenticator() {
        	// override the getPasswordAuthentication method
        	//@Override
        	protected PasswordAuthentication getPasswordAuthentication() {
        		return new PasswordAuthentication(USER, PASSWORD);
        	}
        };
        
        mailSession = Session.getInstance(props, auth);
        //mailSession = Session.getInstance(props, new Authenticator() {
        	// override the getPasswordAuthentication method
        	//protected PasswordAuthentication getPasswordAuthentication() {
        		//return new PasswordAuthentication(USER, PASSWORD);
        	//}
        //});
        //mailSession.setDebug(true);

    }


    // method for sending an email
    public static void sendMessage(String recipient, String subject, String message) throws MessagingException {

        if ( mailService == null ) {
            mailService = new MailService();
        }

        MimeMessage mimeMessage = new MimeMessage(mailSession);

        mimeMessage.setFrom(new InternetAddress(USER));
        mimeMessage.setSender(new InternetAddress(USER));
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setSubject(subject);
        mimeMessage.setSentDate(new Date());
        mimeMessage.setContent(message, "text/html");

		System.out.println("*** before transport.send ***");
        Transport.send(mimeMessage);
		System.out.println("*** after transport.send ***");

		//transport.close();
		//System.out.println("*** after transport.close ***");

    }


}
