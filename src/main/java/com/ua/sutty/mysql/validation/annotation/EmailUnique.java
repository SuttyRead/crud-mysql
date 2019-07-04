package com.ua.sutty.mysql.validation.annotation;

import com.ua.sutty.mysql.validation.validator.EmailUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = EmailUniqueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnique {
    String message() default "email must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}