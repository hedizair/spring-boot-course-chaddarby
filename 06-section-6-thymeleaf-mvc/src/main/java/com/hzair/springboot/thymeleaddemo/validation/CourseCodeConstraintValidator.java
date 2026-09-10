package com.hzair.springboot.thymeleaddemo.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {
    // * We pass to ConstraintValidator<..> the custome annotation @interface, so CourseCode here

    private String coursePrefix;

    @Override
    public void initialize(CourseCode theCourseCode) {
        coursePrefix = theCourseCode.value(); // * Contain de value "LUV" here
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorOrConstraint) {

        boolean result = false;

        if (theCode != null) {
            result = theCode.startsWith(coursePrefix);

        } else {
            result = true; // ! this part of the code dont have to validate if the value is null or not, so we pass true if the value is null
        }

        return result;
    }

}
