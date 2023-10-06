package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleServiceImp;
import ru.kata.spring.boot_security.demo.services.UserServiceImp;

import java.security.Principal;

@Controller
@RequestMapping("/adminBootstrap")
public class AdminController {

    private final UserServiceImp userServiceImp;

    private final RoleServiceImp roleServiceImp;

    @Autowired
    public AdminController(UserServiceImp userServiceImp, RoleServiceImp roleServiceImp) {
        this.userServiceImp = userServiceImp;
        this.roleServiceImp = roleServiceImp;
    }

    @GetMapping
    public String pageForAdmin(Model model, Principal principal) {
        model.addAttribute("admin", userServiceImp.findByUsername(principal.getName()));
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
    public String update(@ModelAttribute("user") User user) {
        userServiceImp.updateUser(user);
        return "redirect:/adminBootstrap";
    }

    @DeleteMapping
    public String delete(@ModelAttribute("user") User user) {
        userServiceImp.deleteUser(user.getId());
        return "redirect:/adminBootstrap";
    }

}
