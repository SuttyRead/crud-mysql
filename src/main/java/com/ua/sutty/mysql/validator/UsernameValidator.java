package com.ua.sutty.mysql.validator;

import com.ua.sutty.mysql.form.UserForm;
import com.ua.sutty.mysql.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsernameValidator implements Validator {

    private final UserRepository userRepository;

    public UsernameValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserForm userForm = (UserForm) o;
        if (userRepository.existsByUsername(userForm.getUsername())) {
            errors.rejectValue("username", "This username already registered");
        }
    }

}
