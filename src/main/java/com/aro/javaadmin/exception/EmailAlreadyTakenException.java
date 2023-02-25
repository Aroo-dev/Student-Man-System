package com.aro.javaadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyTakenException extends RuntimeException {


    private String resourceName;
    private String fieldValue;

    public EmailAlreadyTakenException(String resourceName, String fieldName) {
        super(String.format("%s %s is already registered",resourceName,fieldName));
        this.resourceName = resourceName;
        this.fieldValue = fieldName;
    }
}
