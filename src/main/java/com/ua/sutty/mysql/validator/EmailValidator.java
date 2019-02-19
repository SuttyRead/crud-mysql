package com.ua.sutty.mysql.validator;

import com.ua.sutty.mysql.form.UserForm;
import com.ua.sutty.mysql.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmailValidator implements Validator {

    private final UserRepository userRepository;

    public EmailValidator(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(final Class<?> aClass) {
        return UserForm.class.equals(aClass);
    }

    @Override
    public void validate(final Object o, final Errors errors) {
        UserForm userForm = (UserForm) o;
        if (userRepository.existsByEmail(userForm.getEmail())) {
            errors.rejectValue("email", "This email already registered");
        }
    }

}
