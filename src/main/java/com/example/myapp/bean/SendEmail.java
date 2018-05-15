package com.example.myapp.bean;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
	
	
	   public String mail(User usr) {

		      String to = usr.getEmail();
		      String from = "webdevsummer2018@gmail.com";
		      final String username = "webdevsummer2018";
		      final String password = "Northeastern@123";

		      String host = "smtp.gmail.com";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", "587");

		      Session session = Session.getInstance(props,
		    		  new javax.mail.Authenticator() {
		    			protected PasswordAuthentication getPasswordAuthentication() {
		    				return new PasswordAuthentication(username, password);
		    			}
		    		  });

		      try {
			   Message message = new MimeMessage(session);
			   message.setFrom(new InternetAddress(from));
			   message.setRecipients(Message.RecipientType.TO,
		               InternetAddress.parse(to));
			   message.setSubject("Password Reset Link");
			   message.setText("Hi "+usr.getFirst()+" "+usr.getLast()+", \n\n Your Password is: "+usr.getPassword()+"\n\n Best Regards,\n Team SpringBoot.");

			   Transport.send(message);

			   return "SUCCESSFULLY SENT";

		      } catch (MessagingException e) {
		          return "SOMETHING WENT WRONG.";
		      }
		   }

}
