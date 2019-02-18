package com.ua.sutty.mysql.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MatchPasswordValidator.class)
@Target( { ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface MatchPassword {
    String message() default "password don't match";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}