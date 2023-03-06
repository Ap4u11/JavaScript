package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.security.Principal;

@Controller
public class UserConrtoller {
    private final UserService userService;

    public UserConrtoller(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user")
    public String userPage(Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByEmail(principal.getName()));
        return "user";
    }

    @GetMapping(value = "/admin")
    public String printAdminPage(Principal principal, ModelMap model) {
        model.addAttribute("admin", userService.getUserByEmail(principal.getName()));
        model.addAttribute("users", userService.showUsers());
        model.addAttribute("listRoles", userService.listRoles());
        return "admin";
    }

}
