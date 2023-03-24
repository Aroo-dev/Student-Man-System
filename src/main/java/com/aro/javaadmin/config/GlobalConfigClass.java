package com.aro.javaadmin.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;


@EnableCaching
@Configuration
public class GlobalConfigClass {

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public OpenAPI api(){
        return new OpenAPI()
                .components(new Components()
                .addSecuritySchemes("JWT Token",new SecurityScheme().type(SecurityScheme.Type.HTTP)
                        .scheme("Bearer")
                        .bearerFormat("JWT").in(SecurityScheme.In.HEADER).name("Authorization")))
                .info(new Info().title("Student Management System").version("I"))
                .addSecurityItem(new SecurityRequirement().addList("JWT Token", Arrays.asList("read","write")));
    }



}
