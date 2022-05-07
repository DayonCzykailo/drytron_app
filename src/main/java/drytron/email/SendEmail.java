package drytron.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    public static void main(String[] args) {

        String para = "dayon.czykailo@gmail.com";
        String de = "drytroncorp@gmail.com";
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(de, "drytron123");

            }

        });
        session.setDebug(true);//-------Setar falso, depois

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(de));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(para));

            message.setSubject("Drytron Corp - Sistema de gerenciamento.");

            message.setText("RECUPERAÇÃO de SENHA");
            System.out.println("MANDANDO...");
            
            
            Transport.send(message);
            System.out.println("CÓDIGO DE RECUPERAÇÃO ENVIADO ...");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

}