/**
 * Created by rdw1995 on 9/20/16.
 */
public class Email {
    String destination;
    String subject;
    String body;
    boolean isSpam;

    public Email(String destination, String subject, String body, boolean isSpam) {
        this.destination = destination;
        this.subject = subject;
        this.body = body;
        this.isSpam = isSpam;
    }
}
