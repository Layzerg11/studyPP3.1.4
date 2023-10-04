package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;

import java.util.List;

public interface UserService {
    User findUserById(Long userId);
    List<User> getUserList();
    boolean createUser(User user);
    boolean deleteUser(Long userId);
    void updateUser(long id, User updateUser);

}
