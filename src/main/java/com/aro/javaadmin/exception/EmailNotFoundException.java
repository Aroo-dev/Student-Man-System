package com.aro.javaadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailNotFoundException extends RuntimeException{

    private String resourceName;
    private String fieldName;


    public EmailNotFoundException(String resourceName, String fieldName){
        super(String.format("%s %s does not exist ", resourceName,fieldName));
        this.fieldName = fieldName;
        this.resourceName = resourceName;
    }
}
