package stanel.app.store.resetPassword;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${app.password-reset-url}")
    private String frontend;

    public SendEmail(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

   
    public void sendPasswordResetEmail(String to, String rawToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        String link = frontend + "?token=" + rawToken;
        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Reset your app.store password ");
        message.setText("""
                    We received a request to reset your app.store password.

                                Open the link below to choose a new password.
                                This link expires in 15 minutes and can only be used once.

                                %s

                                If you did not request this reset, you can ignore this email.
                """.formatted(link));
        mailSender.send(message);
    }

}
