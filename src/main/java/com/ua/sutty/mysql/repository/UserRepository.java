package com.ua.sutty.mysql.repository;

import com.ua.sutty.mysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserById(Long id);
    void deleteUserById(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsById(Long id);
}
