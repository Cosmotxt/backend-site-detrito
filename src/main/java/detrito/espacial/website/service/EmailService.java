package detrito.espacial.website.service;

import java.io.UnsupportedEncodingException;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import detrito.espacial.website.DTO.UserDTO;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    private final Environment environment;
    private final JavaMailSender mailSender;
    
    public EmailService(Environment environment, JavaMailSender mailSender) {
        this.environment = environment;
        this.mailSender = mailSender;
    }

    public void sendEmail(UserDTO user) throws MessagingException, UnsupportedEncodingException {
        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        email.setFrom(new InternetAddress(mailFrom));
        email.setTo("detritoe@gmail.com");
        email.setSubject(user.getName() + " mandou uma mensagem!");
        String text = "Nome: " + user.getName() + "\n" +
        "E-mail: " + user.getEmail() + "\n\n" +
        user.getMessage();
        email.setText(text);
        
        mailSender.send(mimeMessage);

    }

    
}
