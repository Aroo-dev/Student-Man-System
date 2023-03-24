package com.aro.javaadmin.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target(ElementType.TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = ConfirmedFieldValidator.class)
@Documented

public @interface ConfirmedField {


    String message() default "Doesn't match the original";


    String originalField();


    String confirmationField();


    Class<?>[] groups() default {};


    Class<? extends Payload>[] payload() default {};


    @Target({ElementType.TYPE})

    @Retention(RetentionPolicy.RUNTIME)

    @interface List {

        ConfirmedField[] value();

    }

}
