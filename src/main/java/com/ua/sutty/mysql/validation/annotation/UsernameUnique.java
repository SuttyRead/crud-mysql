package com.ua.sutty.mysql.validation.annotation;

import com.ua.sutty.mysql.validation.validator.UsernameUniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UsernameUniqueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UsernameUnique {
    String message() default "username must be unique";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}