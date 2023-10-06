package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {

    List<User> getUserList();
    boolean createUser(User user);
    boolean deleteUser(Long userId);
    User updateUser(User updateUser);
    User findByUsername(String username);

}
