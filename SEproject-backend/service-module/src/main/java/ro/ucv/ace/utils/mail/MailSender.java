package ro.ucv.ace.utils.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Created by Geo on 22.05.2016.
 */
@Component
public class MailSender {

    @Autowired
    private Environment environment;


    public MailSender() {
    }

    public void sendPasswordMail(String to, String password, String username) {
        String mailUsername = environment.getRequiredProperty("mail.smtp.username");
        String mailPassword = environment.getRequiredProperty("mail.smtp.password");

        Properties props = new Properties();
        props.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", environment.getRequiredProperty("mail.smtp.host"));
        props.put("mail.smtp.port", environment.getRequiredProperty("mail.smtp.port"));

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });


        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("seproject@ace.ucv.ro", "SE Project"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Account password");
            message.setText("Your account has been activated!\nUsername:" + username + "\nPassword:" + password);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
