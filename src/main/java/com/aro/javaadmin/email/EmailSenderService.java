package com.aro.javaadmin.email;

import org.springframework.context.annotation.Profile;

@Profile(value = "prod")
public interface EmailSenderService {
    //TODO

    void sendEmail( String email, String text);
}
