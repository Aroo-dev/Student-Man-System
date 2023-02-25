package com.aro.javaadmin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {

    private String resourceName;
    private String fieldValue;

    public StudentNotFoundException(String resourceName, String fieldName) {
        super(String.format("%s with name %s not found",resourceName,fieldName));
        this.resourceName = resourceName;
        this.fieldValue = fieldName;
    }
}
