package com.integratedca.spotifydata.controller;

import com.integratedca.spotifydata.model.User;
import com.integratedca.spotifydata.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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

    // Show registration form
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    // Handle user registration
    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") @jakarta.validation.Valid User user, BindingResult result,
            Model model) {
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
        return "redirect:/login?success=true";
    }

    // Show user account page
    @GetMapping("/user_account")
    public String showUserAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userService.findByUsername(currentUsername);
        model.addAttribute("user", user);
        model.addAttribute("username", currentUsername);
        return "user_account";
    }

    //Update password
    @PostMapping("/user_account/update_password")
    public String updateUserPassword(@ModelAttribute("user") @jakarta.validation.Valid User user, BindingResult result,
            Model model) {
        if (result.hasErrors()) {
            if (result.hasFieldErrors("oldPassword")) {
                model.addAttribute("error", "Old password is required");
            } else if (result.hasFieldErrors("password")) {
                model.addAttribute("error", "New password is required and must be at least 5 characters long");
            } else if (result.hasFieldErrors("confirmPassword")) {
                model.addAttribute("error", "Confirm password is required");
            }
            return "user_account";
        }
        
        if (user.getOldPassword() == null || user.getOldPassword().isEmpty()) {
            model.addAttribute("error", "Old password is required");
            return "user_account";
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            model.addAttribute("error", "New password is required and must be at least 5 characters long");
            return "user_account";
        }
        if (user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
            model.addAttribute("error", "Confirm password is required");
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

        // Check if new password is the same as the old password
        if (userService.checkPassword(currentUser, user.getPassword())) {
            model.addAttribute("error", "New password cannot be the same as the old password");
            return "user_account";
        }

        // Update password
        userService.updatePassword(currentUsername, user.getPassword());
        model.addAttribute("success", "Password updated successfully!");
        return "user_account";
    }

    // Handle account deletion
    @PostMapping("/user_account/delete")
    public String deleteUserAccount(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        userService.deleteByUsername(currentUsername);
        SecurityContextHolder.clearContext();
        return "redirect:/login?accountDeleted=true";
    }

    // Handle user logout and redirect to login page
    @GetMapping("/perform_logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        model.addAttribute("logoutMessage", "You have been logged out");
        return "redirect:/login?logout=true";
    }
}
