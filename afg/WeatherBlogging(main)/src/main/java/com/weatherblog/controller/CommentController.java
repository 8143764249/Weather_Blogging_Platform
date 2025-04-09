package com.weatherblog.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.weatherblog.model.Blog;
import com.weatherblog.model.Comment;
import com.weatherblog.model.User;
import com.weatherblog.repository.BlogRepository;
import com.weatherblog.repository.CommentRepository;
import com.weatherblog.repository.UserRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/add/{blogId}")
    public String addComment(@PathVariable Long blogId,
                             @RequestParam String content,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {

        String username = (String) session.getAttribute("loggedUser");
        if (username == null) {
            redirectAttributes.addFlashAttribute("error", "You must be logged in to comment.");
            return "redirect:/user/login";
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not found.");
            return "redirect:/user/login";
        }

        Blog blog = blogRepository.findById(blogId).orElse(null);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("error", "Blog not found.");
            return "redirect:/home";
        }

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setBlog(blog);

        commentRepository.save(comment);

        return "redirect:/blog/latest";  // Redirect back to the latest blog posts
    }

    @PostMapping("/update/{commentId}")
    public String updateComment(@PathVariable("commentId") Long commentId,
                                @RequestParam String content,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        String username = (String) session.getAttribute("loggedUser");
        if (username == null) {
            redirectAttributes.addFlashAttribute("error", "Please login first.");
            return "redirect:/user/login";
        }

        Comment comment = commentRepository.findById(commentId).orElse(null);
        if (comment == null || !comment.getUser().getUsername().equals(username)) {
            redirectAttributes.addFlashAttribute("error", "Unauthorized update.");
            return "redirect:/blog/latest";
        }

        comment.setContent(content);
        commentRepository.save(comment);
        return "redirect:/blog/latest";
    }
//
//    @GetMapping("/delete/{commentId}")
//    public String deleteComment(@PathVariable("commentId") Long commentId,
//                                HttpSession session,
//                                RedirectAttributes redirectAttributes) {
//
//        String username = (String) session.getAttribute("loggedUser");
//        if (username == null) {
//            redirectAttributes.addFlashAttribute("error", "Please login first.");
//            return "redirect:/user/login";
//        }
//
//        Comment comment = commentRepository.findById(commentId).orElse(null);
//        if (comment == null || !comment.getUser().getUsername().equals(username)) {
//            redirectAttributes.addFlashAttribute("error", "Unauthorized deletion.");
//            System.out.println("comment not deleted");
//
//            return "redirect:/blog/latest";
//        }
//
//        commentRepository.delete(comment);
//        System.out.println("Logged-in user: " + username);
//        System.out.println("Comment's author: " + comment.getUser().getUsername());
//
//        return "redirect:/blog/latest";
//    }
    @GetMapping("/delete/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {

        String username = (String) session.getAttribute("loggedUser");

        if (username == null) {
            redirectAttributes.addFlashAttribute("error", "Please login first.");
            return "redirect:/user/login";
        }

        Comment comment = commentRepository.findById(commentId).orElse(null);

        if (comment == null) {
            redirectAttributes.addFlashAttribute("error", "Comment not found.");
            return "redirect:/blog/latest";
        }

        else if (!comment.getUser().getUsername().equals(username)) {
            redirectAttributes.addFlashAttribute("error", "Unauthorized deletion attempt.");
            return "redirect:/blog/latest";
        }
        else {
        commentRepository.delete(comment);
        redirectAttributes.addFlashAttribute("success", "Comment deleted successfully.");

        return "redirect:/blog/latest";  // ✅ <-- changed
    }
    }



}

