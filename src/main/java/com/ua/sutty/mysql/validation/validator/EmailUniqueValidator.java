package com.ua.sutty.mysql.validation.validator;

import com.ua.sutty.mysql.repository.UserRepository;
import com.ua.sutty.mysql.validation.annotation.EmailUnique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueValidator implements ConstraintValidator<
        EmailUnique, String> {

    private UserRepository userRepository;

    public EmailUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initialize(final EmailUnique constraint) {
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        return !userRepository.existsByEmail(email);
    }

}
