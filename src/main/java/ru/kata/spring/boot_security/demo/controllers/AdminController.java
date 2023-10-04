package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.services.RoleServiceImp;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping("/adminBootstrap")
public class AdminController {

    private final UserRepository userRepository;

    private final UserServiceImp userServiceImp;

    private final RoleServiceImp roleServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp, UserRepository userRepository) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String pageForAdmin(Model model, Principal principal) {
        model.addAttribute("admin", userRepository.findByUsername(principal.getName()));
        model.addAttribute("users", userServiceImp.getUserList());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleServiceImp.findAllRoles());
        return "adminBootstrap";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userServiceImp.createUser(user);
        return "redirect:/adminBootstrap";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userServiceImp.updateUser(id, user);
        return "redirect:/adminBootstrap";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("user") User user) {
        userServiceImp.deleteUser(user.getId());
        return "redirect:/adminBootstrap";
    }

}
