package group.msg.gbi.supplychain.ports.mail;

import io.smallrye.mutiny.Uni;

public class EmailServiceImp implements EmailService {

    public Uni<Void> sendEmail(String to, String subject, String body) {
        System.out.println("EmailServiceImp: to: " + to + ", subject: " + subject + ", body: " + body);
        return Uni.createFrom().voidItem();
    }
}
