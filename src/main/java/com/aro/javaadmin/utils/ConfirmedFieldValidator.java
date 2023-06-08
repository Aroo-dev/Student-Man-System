package com.aro.javaadmin.utils;

import org.springframework.beans.BeanWrapperImpl;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class ConfirmedFieldValidator implements ConstraintValidator<ConfirmedField, Object> {


    private String originalField;
    private String confirmationField;
    private String message;


    public void initialize(final ConfirmedField constraintAnnotation) {

        this.originalField = constraintAnnotation.originalField();
        this.confirmationField = constraintAnnotation.confirmationField();
        this.message = constraintAnnotation.message();
    }


    public boolean isValid(final Object value, final ConstraintValidatorContext context) {

        Object fieldValue = new BeanWrapperImpl(value).getPropertyValue(originalField);
        Object fieldMatchValue = new BeanWrapperImpl(value).getPropertyValue(confirmationField);

        boolean isValid = fieldValue != null && fieldValue.equals(fieldMatchValue);

        if (!isValid) {

            context.disableDefaultConstraintViolation();
            context
                    .buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(confirmationField)
                    .addConstraintViolation();
        }


        return isValid;

    }

}
