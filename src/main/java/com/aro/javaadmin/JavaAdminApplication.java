package com.aro.javaadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true)
@SpringBootApplication
public class JavaAdminApplication  {


    public static void main(String[] args) {
        System.out.println("chuj w dupe arowi");
        SpringApplication.run(JavaAdminApplication.class, args);
    }

    //todo pozmieniac nazwy pol w encjach
}
