package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/adminBootstrap")
public class AdminController {

    private final UserService userService;

    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String pageForAdmin(Model model, Principal principal) {
        model.addAttribute("admin", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.getUserList());
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.findAllRoles());
        return "adminBootstrap";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/adminBootstrap";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/adminBootstrap";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("user") User user) {
        userService.deleteUser(user.getId());
        return "redirect:/adminBootstrap";
    }

}
