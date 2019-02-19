package com.ua.sutty.mysql.validator;

import com.ua.sutty.mysql.form.UserForm;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchPasswordValidator implements ConstraintValidator<
        MatchPassword, UserForm> {
    public void initialize(final MatchPassword constraint) {
    }

    public boolean isValid(final UserForm userForm,
                           final ConstraintValidatorContext context) {
        return userForm.getPassword().equals(userForm.getConfirmPassword());
    }
}
