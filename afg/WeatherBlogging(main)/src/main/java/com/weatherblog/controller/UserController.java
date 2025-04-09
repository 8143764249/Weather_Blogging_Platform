package com.weatherblog.controller;

import com.weatherblog.model.User;
import com.weatherblog.repository.UserRepository;
import com.weatherblog.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String showRegistrationPage() {
        return "register";
    }


    
    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {
            redirectAttributes.addFlashAttribute("errorMessage", "User already exists!");
            return "redirect:/";  // assuming "/" maps to index.jsp
        }

        userRepository.save(user);
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! Please log in.");
        return "redirect:/";
    }


    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {
        User user = userService.authenticateUser(username, password);
        if (user != null) {
//        	session.setAttribute("loggedInUser", user); 
            session.setAttribute("loggedUser", user.getUsername());  // Store only the username
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


} 