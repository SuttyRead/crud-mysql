package com.ua.sutty.mysql.validation.validator;

import com.ua.sutty.mysql.repository.UserRepository;
import com.ua.sutty.mysql.validation.annotation.UsernameUnique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UsernameUniqueValidator implements ConstraintValidator<
        UsernameUnique, String> {

    private UserRepository userRepository;

    public UsernameUniqueValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void initialize(final UsernameUnique constraint) {
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(username);
    }

}
