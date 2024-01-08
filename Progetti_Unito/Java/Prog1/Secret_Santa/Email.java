// File Name SendEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Email {

    public static void main(String[] args) {       

    }
	public static void SendEmail(List<String> emails_From,List<Integer> emails_To, String Username, String Password, int Revisione ){
		
		Properties properties = System.getProperties();
		String host = "smtp.gmail.com";
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
		
		/*String host = "tebisitalia-it.mail.protection.outlook.com";
        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "25");
        properties.put("mail.smtp.ssl.enable", "false");
        properties.put("mail.smtp.auth", "false");*/

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(Username, Password);

            }

        });

        // Used to debug SMTP issues
        //session.setDebug(true);
		for(int i = 0; i < emails_From.size(); i++){
			try {
				// Create a default MimeMessage object.
				MimeMessage message = new MimeMessage(session);

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(Username));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(emails_From.get(i)));

				// Set Subject: header field
				message.setSubject("Tebis - Babbo Natale segreto Rev. " + Revisione);

				// Now set the actual message
			message.setText("Ciao,\nquesto Natale sarai il Babbo Natale segreto di " + emails_From.get(emails_To.get(i)) + ".\nMi raccomando, fai un regalo simpatico!\nGrazie per la partecipazione.\n\nBabbo Natale segreto\n*<]:{)");

				System.out.println("Invio...");
				// Send message
				Transport.send(message);
				System.out.println("Messaggio inviato con successo....");
			
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
		}

	}

}