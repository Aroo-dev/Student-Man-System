package com.aro.javaadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class BadAuthenticationException extends RuntimeException{

    private final String fieldName;

    public BadAuthenticationException(String fieldName) {
        super(String.format("You are not authorized to update this %s's data", fieldName));
        this.fieldName = fieldName;
    }
}
