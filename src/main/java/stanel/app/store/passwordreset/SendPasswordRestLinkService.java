package stanel.app.store.passwordreset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendPasswordRestLinkService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    public SendPasswordRestLinkService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendPasswordRestLink(String email, String resetLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromMail);
        message.setTo(email);   
        message.setSubject("Reset your app.store password");
        message.setText("""
                We received a request to reset your app.store password.

                        Open the link below to choose a new password.
                        This link expires in 15 minutes and can only be used once.

                        %s

                        If you did not request this reset, you can ignore this email.""".formatted(resetLink));
        mailSender.send(message);
    }

}
