package drytron.email;

import drytron.util.Mensagens;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public abstract class Email {

    public static boolean mandar(String para, String uuid) {
        final String de = "drytronCorp@outlook.com";
        final String senha = "drytron123";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(de, senha);
            }
        });
        session.setDebug(true);//<--setar falso para a apresentação

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(de));
            message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(para));
            message.setSubject("RECUPERAÇÃO de SENHA");
            message.setText("Seu código :" + uuid);

            Transport.send(message);

        } catch (MessagingException e) {
            Mensagens.mensagemErro("ERRO EM MANDAR O CÓDIGO", "NÃO FOI POSSÍVEL MANDAR O CÓDIGO DE RECUPERAÇÃO, TENTE OUTRA HORA: ERRO:" + e.getMessage());
            throw new RuntimeException(e);
        }
        Mensagens.mensagemInfo("CÓDIGO DE VERIFICAÇÃO FOI ENVIADO", "VERIFIQUE SUA CAIXA DE E-MAIL E SPAM.");

        return true;
    }

}
