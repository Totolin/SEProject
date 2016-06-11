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
 * This class is used to send an email using smtp.
 *
 * @author Georgian Vladutu
 */
@Component
public class MailSender {

    @Autowired
    private Environment environment;


    public MailSender() {
    }

    /**
     * Sends an email to inform the recipient about his/her account being created.
     *
     * @param to       the recipient
     * @param password password of the recipient
     * @param username username of the recipient
     */
    public void sendCreateAccountMail(String to, String password, String username) {
        Session session = createSession();

        sendMail(session, "seproject@ace.ucv.ro", "SE Project", to, "Account password",
                "Your account has been activated!\nUsername:" + username + "\nPassword:" + password);
    }

    /**
     * Sends an email to inform the recipient about his/her account being updated.
     *
     * @param to       the recipient
     * @param password new password of the recipient
     * @param username new username of the recipient
     */
    public void sendUpdateAccountMail(String to, String password, String username) {
        Session session = createSession();

        sendMail(session, "seproject@ace.ucv.ro", "SE Project", to, "Account password",
                "Your account has been updated!\nUsername:" + username + "\nPassword:" + password);
    }

    private Session createSession() {
        String mailUsername = environment.getRequiredProperty("mail.smtp.username");
        String mailPassword = environment.getRequiredProperty("mail.smtp.password");

        Properties props = new Properties();
        props.put("mail.smtp.auth", environment.getRequiredProperty("mail.smtp.auth"));
        props.put("mail.smtp.starttls.enable", environment.getRequiredProperty("mail.smtp.starttls.enable"));
        props.put("mail.smtp.host", environment.getRequiredProperty("mail.smtp.host"));
        props.put("mail.smtp.port", environment.getRequiredProperty("mail.smtp.port"));

        return Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailUsername, mailPassword);
            }
        });
    }

    private void sendMail(Session session, String address, String personal, String to, String subject, String text) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(address, personal));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(text);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
