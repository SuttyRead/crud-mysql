package com.ua.sutty.mysql.form;

import com.ua.sutty.mysql.model.Role;
import com.ua.sutty.mysql.model.User;
import com.ua.sutty.mysql.validator.MatchPassword;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MatchPassword
public class UserForm {

    private String username;

    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private String password;

    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$")
    private String confirmPassword;

    @Email
    private String email;

    @Pattern(regexp = "[A-Z][a-z]{1,25}")
    private String firstName;

    @Pattern(regexp = "[A-Z][a-z]{1,25}")
    private String lastName;

    @Past
    private LocalDate birthday;

    public User toUser() {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .birthday(birthday)
                .role(Role.USER)
                .build();
    }

}
