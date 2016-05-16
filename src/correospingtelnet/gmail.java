/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package correospingtelnet;
//2607:f8b0:4003:c14::6c

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class gmail {
        public String username; 
        public String password; 
        public String to; 
        public String filename; 
        public String subject; 
        public String body; 
        public String from; 
	public static void main(String[] args) {

		final String username = "0161478@up.edu.mx";
		final String password = "AnilloFlor4";

                
		Properties props = new Properties();
                
                //Configuration with TLS 
                /*
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");*/
                
                
		//Configuration with SSL
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("from-email@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse("0161478@gmail.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
				+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
        
        public void sendMail(){
            // Recipient's email ID needs to be mentioned.
            String to = "0161478@up.edu.mx";

            // Sender's email ID needs to be mentioned
            String from = "0161478@up.edu.mx";

            final String username = "0161478@up.edu.mx";//change accordingly
            final String password = "AnilloFlor4";//change accordingly

            // Assuming you are sending email through relay.jangosmtp.net
            String host = "smtp.gmail.com";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");

            // Get the Session object.
            Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(username, password);
                  }
               });

            try {
               // Create a default MimeMessage object.
               Message message = new MimeMessage(session);

               // Set From: header field of the header.
               message.setFrom(new InternetAddress(from));

               // Set To: header field of the header.
               message.setRecipients(Message.RecipientType.TO,
                  InternetAddress.parse(to));

               // Set Subject: header field
               message.setSubject("Testing Subject");

               // Create the message part
               BodyPart messageBodyPart = new MimeBodyPart();

               // Now set the actual message
               messageBodyPart.setText("This is message body");

               // Create a multipar message
               Multipart multipart = new MimeMultipart();

               // Set text message part
               multipart.addBodyPart(messageBodyPart);

               // Part two is attachment
               messageBodyPart = new MimeBodyPart();
               String filename = "archivo.txt";
               DataSource source = new FileDataSource(filename);
               messageBodyPart.setDataHandler(new DataHandler(source));
               messageBodyPart.setFileName(filename);
               multipart.addBodyPart(messageBodyPart);

               // Send the complete message parts
               message.setContent(multipart);

               // Send message
               Transport.send(message);

               System.out.println("Sent message successfully....");

            } catch (MessagingException e) {
               throw new RuntimeException(e);
            }
        }
        public void sendMailFromObject(){
            // Recipient's email ID needs to be mentioned.
            from = username; 

            // Sender's email ID needs to be mentioned
            

            

            // Assuming you are sending email through relay.jangosmtp.net
            String host = "smtp.gmail.com";

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "25");

            // Get the Session object.
            Session session = Session.getInstance(props,
               new javax.mail.Authenticator() {
                  protected PasswordAuthentication getPasswordAuthentication() {
                     return new PasswordAuthentication(username, password);
                  }
               });

            try {
               // Create a default MimeMessage object.
               Message message = new MimeMessage(session);

               // Set From: header field of the header.
               message.setFrom(new InternetAddress(from));

               // Set To: header field of the header.
               message.setRecipients(Message.RecipientType.TO,
                  InternetAddress.parse(to));

               // Set Subject: header field
               message.setSubject(subject);

               // Create the message part
               BodyPart messageBodyPart = new MimeBodyPart();

               // Now set the actual message
               messageBodyPart.setText(body);

               // Create a multipar message
               Multipart multipart = new MimeMultipart();

               // Set text message part
               multipart.addBodyPart(messageBodyPart);

               // Part two is attachment
               messageBodyPart = new MimeBodyPart();
               
               DataSource source = new FileDataSource(filename);
               messageBodyPart.setDataHandler(new DataHandler(source));
               messageBodyPart.setFileName(filename);
               multipart.addBodyPart(messageBodyPart);

               // Send the complete message parts
               message.setContent(multipart);

               // Send message
               Transport.send(message);

               System.out.println("Sent message successfully....");

            } catch (MessagingException e) {
               throw new RuntimeException(e);
            }
        }
}
