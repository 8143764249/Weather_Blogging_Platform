package com.weatherblog.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weatherblog.model.Blog;
import com.weatherblog.model.User;
import com.weatherblog.repository.BlogRepository;
import com.weatherblog.repository.UserRepository;

@Controller
public class HomeController {
     @Autowired
     private UserRepository userRepository;
     @Autowired
     private BlogRepository blogRepository;
    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }

    @GetMapping("/home")
    public String showHomePage(HttpSession session, HttpServletResponse response, Model model) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/user/login";
        }

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Expires", "0");
        model.addAttribute("latestBlogs", blogRepository.findAll());

        return "home";
    }

    @GetMapping("/aboutus")
    public String showAboutUsPage() {
        return "aboutus";
    }
    @GetMapping("/user/profile")
    public String showProfile(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedUser");
        if (username == null) {
            return "redirect:/";
        }

        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
    @GetMapping("/user/updateForm")
    public String showUpdateForm(HttpSession session, Model model) {
        String username = (String) session.getAttribute("loggedUser");
        if (username == null) {
            return "redirect:/";
        }

        User user = userRepository.findByUsername(username);
        model.addAttribute("user", user);
        return "updateProfile";
    }
   

}