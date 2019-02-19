package com.ua.sutty.mysql.controller;

import com.ua.sutty.mysql.form.UserForm;
import com.ua.sutty.mysql.model.User;
import com.ua.sutty.mysql.repository.UserRepository;
import com.ua.sutty.mysql.validator.EmailValidator;
import com.ua.sutty.mysql.validator.UsernameValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class UserController {

    private final UserRepository userRepository;

    private final EmailValidator emailValidator;

    private final UsernameValidator usernameValidator;

    public UserController(final UserRepository userRepository,
                          final EmailValidator emailValidator,
                          final UsernameValidator usernameValidator) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.usernameValidator = usernameValidator;
    }

    @InitBinder("userForm")
    public void addBinder(final WebDataBinder binder) {
        binder.addValidators(emailValidator);
        binder.addValidators(usernameValidator);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {

        log.trace("Call getAllUsers method");

        List<User> users = this.userRepository.findAll();

        if (users.isEmpty()) {
            log.trace("User is empty");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") final Long id) {

        log.trace("Call getUser method");

        User user = userRepository.findUserById(id);

        if (user == null) {
            log.trace("User is null");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody @Valid final UserForm userForm) {

        log.trace("Call saveUser method");

        if (userForm == null) {
            log.trace("UserForm is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User user = userForm.toUser();
        this.userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid final User user,
                                           @PathVariable final Long id) {
        log.trace("Call updateUser method");

        if (user == null) {
            log.trace("User is null");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        if (!userRepository.existsById(id)) {
            log.trace("User doesn't exists");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        User userById = userRepository.findUserById(id);

        if (!userById.getUsername().equals(user.getUsername())
                || !userById.getEmail().equals(user.getEmail())) {
            log.trace("You can't change username or email");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        user.setId(id);
        this.userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") final Long id) {

        log.trace("Call deleteUser method");

        User user = this.userRepository.findUserById(id);

        if (user == null) {
            log.trace("User is null");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userRepository.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
