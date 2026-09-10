package com.hzair.springboot.thymeleaddemo.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy=CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD}) // * Where can we apply this annotation ? on Method and Field
@Retention(RetentionPolicy.RUNTIME)  // * Pas compris
public @interface CourseCode {

    // define default course code // * 1st annotation paramter
    public String value() default "LUV"; // * If not value passed for 1st parameter, default value = "LUV"

    // define default error message // * 2nd annotation paramter
    public String message() default "must start with LUV"; // ! Obligé par JPA

    // define default groups
    public Class<?>[] groups() default {}; // ! Obligé par JPA

    // define default payloads
    public Class<? extends Payload>[] payload() default {};  // ! Obligé par JPA
}
