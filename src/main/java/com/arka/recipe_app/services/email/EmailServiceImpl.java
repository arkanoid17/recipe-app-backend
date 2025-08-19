package com.arka.recipe_app.services.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender mailSender;

    @Autowired
    ResourceLoader resourceLoader;

    @Value("${spring.mail.username}")
    private String from;


    @Override
    public void sendOtpEMail(String email, String otp) throws MessagingException, IOException {


        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        String htmlTemplate = loadTemplate("otp_template.html");
        Map<String, String> vars = Map.of(
                "{{OTP}}", otp,
                "{{USER_NAME}}", email.split("@")[0],
                "{{YEAR}}", "2025"
        );

        for (var entry : vars.entrySet()) {
            htmlTemplate = htmlTemplate.replace(entry.getKey(), entry.getValue());
        }

        helper.setFrom(from);
        helper.setTo(email);
        helper.setSubject("Recipe app - Verify your email");
        helper.setText(htmlTemplate, true); // true = send as HTML

        mailSender.send(message);

    }


    private String loadTemplate(String templatePath) throws IOException {
            Resource resource = resourceLoader.getResource("classpath:templates/"+templatePath);
        // Process the template if needed
            return new String(resource.getInputStream().readAllBytes());
    }
}
