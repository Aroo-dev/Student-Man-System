package com.aro.javaadmin.email;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.SimpleMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@RequiredArgsConstructor
@Service
public class EmailSenderService {

    private static final String HOST_EMAIL = "axxrxxo@gmail.com";
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
