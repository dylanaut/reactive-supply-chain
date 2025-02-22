package group.msg.gbi.supplychain.ports.mail;

import io.smallrye.mutiny.Uni;

public interface EmailService {
    Uni<Void> sendEmail(String to, String subject, String body);
}
