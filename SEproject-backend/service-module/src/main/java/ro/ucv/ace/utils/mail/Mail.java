package ro.ucv.ace.utils.mail;

import lombok.Getter;
import lombok.Setter;

/**
 * This class represents a mail object.
 *
 * @author Georgian Vladutu
 */
@Getter
@Setter
public class Mail {

    private String from;

    private String to;

    private String subject;

    private String body;

    public Mail(String from, String to, String subject, String body) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }

    public Mail() {
    }
}
