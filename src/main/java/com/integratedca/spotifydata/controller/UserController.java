package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.User;
import com.integratedca.spotifydata.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @jakarta.validation.Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "register";
        }
        if (userService.usernameExists(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        userService.save(user);
        model.addAttribute("success", "User registered successfully");
        return "redirect:/login?success";
    }
}
