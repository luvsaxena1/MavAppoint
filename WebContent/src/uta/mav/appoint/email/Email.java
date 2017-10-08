\package uta.mav.appoint.email;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Email {
	private String subject;
	private String text;
	private String toEmail;
	
	public Email(String sub, String txt, String destEmail)
	{
		subject = sub;
		text = txt;
		toEmail = destEmail;
	}
	public void sendMail()
	{
		final String user = "XXXXXX";
        final String pass = "XXXXXX";
 
        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.host", "smtp.gmail.com");    
        props.put("mail.smtp.socketFactory.port", "465");    
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");    
        props.put("mail.smtp.auth", "true");    
        props.put("mail.smtp.port", "587");    
 
        Session session = Session.getDefaultInstance(props,
          new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
          });
 
        try {
 
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mavappoint2017@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(text);
 
            Transport.send(message);
 
            //System.out.println("Done");
 
        } 
		catch (MessagingException e) {
			System.err.println(e);
            throw new RuntimeException(e);
        }
	}
}
