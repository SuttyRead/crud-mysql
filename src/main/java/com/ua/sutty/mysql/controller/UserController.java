package com.ua.sutty.mysql.controller;

import com.ua.sutty.mysql.form.UserForm;
import com.ua.sutty.mysql.model.User;
import com.ua.sutty.mysql.repository.UserRepository;
import com.ua.sutty.mysql.validator.EmailValidator;
import com.ua.sutty.mysql.validator.UsernameValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserRepository userRepository;

    private final EmailValidator emailValidator;

    private final UsernameValidator usernameValidator;

    public UserController(UserRepository userRepository, EmailValidator emailValidator,
                          UsernameValidator usernameValidator) {
        this.userRepository = userRepository;
        this.emailValidator = emailValidator;
        this.usernameValidator = usernameValidator;
    }

    @InitBinder("userForm")
    public void addBinder(WebDataBinder binder) {
        binder.addValidators(emailValidator);
        binder.addValidators(usernameValidator);
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = this.userRepository.findAll();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        User user = userRepository.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody @Valid UserForm userForm) {
        if (userForm == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = userForm.toUser();
        this.userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user, @PathVariable Long id) {
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (!userRepository.existsById(id)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User userById = userRepository.findUserById(id);
        if (!userById.getUsername().equals(user.getUsername()) || !userById.getEmail().equals(user.getEmail())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        user.setId(id);
        this.userRepository.save(user);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<User> deleteUser(@PathVariable("id") Long id) {
        User user = this.userRepository.findUserById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.userRepository.deleteUserById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
