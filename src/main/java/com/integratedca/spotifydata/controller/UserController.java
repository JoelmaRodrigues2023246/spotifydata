package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.User;
import com.integratedca.spotifydata.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    // Display registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle user registration
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
        if (user.getRoles().isEmpty()) {
            user.getRoles().add("ROLE_USER");
        }
        userService.save(user);
        model.addAttribute("success", "User registered successfully");
        return "redirect:/login?success";
    }

    // Display user account page
    @GetMapping("/user_account")
    public String showUserAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByUsername(currentUsername);
        model.addAttribute("user", user);
        return "user_account";
    }

    // Handle password update
    @PostMapping("/user_account/update_password")
    public String updateUserPassword(@ModelAttribute("user") @jakarta.validation.Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user_account";
        }
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("error", "Passwords do not match");
            return "user_account";
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userService.findByUsername(currentUsername);

        // Check if the old password is correct
        if (!userService.checkPassword(currentUser, user.getOldPassword())) {
            model.addAttribute("error", "Old password is incorrect");
            return "user_account";
        }

        // Update password
        userService.updatePassword(currentUsername, user.getPassword());
        model.addAttribute("success", "Password updated successfully");
        return "user_account";
    }

    // Handle account deletion
    @PostMapping("/user_account/delete")
    public String deleteUserAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        userService.deleteByUsername(currentUsername);
        SecurityContextHolder.clearContext();
        return "redirect:/login?accountDeleted";
    }
}