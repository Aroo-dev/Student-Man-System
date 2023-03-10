package com.aro.javaadmin.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class EmailSenderServiceImpl {

    private static final String HOST_EMAIL = System.getenv("MAIL_USERNAME");
    private static final String WELCOME_SUBJECT = "Welcome";
    private final JavaMailSender emailSender;

    public void sendEmail( String email, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(HOST_EMAIL);
        message.setTo(email);
        message.setSubject(WELCOME_SUBJECT);
        message.setText(text);
        emailSender.send(message);
    }
}
