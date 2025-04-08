package com.weatherblog.controller;

import com.weatherblog.model.Blog;
import com.weatherblog.model.User;
import com.weatherblog.repository.BlogRepository;
//import com.weatherblog.repository.LikeRepository;
import com.weatherblog.repository.UserRepository;
import com.weatherblog.service.BlogService;
import com.weatherblog.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private BlogService blogService;

    @Autowired
    private UserService userService;

    @ModelAttribute("latestBlogs")
    public List<Blog> getLatestBlogs() {
        return blogRepository.findByOrderByTimestampDesc();
    }

    @GetMapping("/blog/create")
    public String showCreatePostPage(HttpSession session) {
        if (session.getAttribute("loggedUser") == null) {
            return "redirect:/user/login";
        }
        return "createPost";
    }


    @GetMapping("/blog/all")
    public String showAllBlogs(HttpSession session, Model model) {
        String loggedUser = (String) session.getAttribute("loggedUser");
        if (loggedUser == null) {
            return "redirect:/";
        }

        List<Blog> allBlogs = blogService.getAllBlogs(); // or getLatestBlogs()
        model.addAttribute("latestBlogs", allBlogs); // <-- This is required
        return "latestPosts";
    }


//
//    @GetMapping("/blog/delete/{id}")
//    public String deletePost(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
//        Blog blog = blogRepository.findById(id).orElse(null);
//        if (blog == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Blog post not found.");
//            return "redirect:/blog/all";
//        }
//
//        String loggedUsername = (String) session.getAttribute("loggedUser");
//        if (loggedUsername == null) {
//            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to delete a post.");
//            return "redirect:/user/login";
//        }
//
//        if (!blog.getAuthor().getUsername().equals(loggedUsername)) {
//            redirectAttributes.addFlashAttribute("errorMessage", "Only the author can delete this post.");
//            return "redirect:/blog/all";
//        }
//
//        // Proceed with deletion
//        String imageName = blog.getImageName();
//        if (imageName != null) {
//            Path imagePath = Paths.get("uploads", imageName);
//            try {
//                Files.deleteIfExists(imagePath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        blogRepository.delete(blog);
//        redirectAttributes.addFlashAttribute("successMessage", "Post deleted successfully.");
//        return "redirect:/blog/all";
//    
//    }

    @GetMapping("/blog/update/{id}")
    public String showUpdateForm(@PathVariable Long id, HttpSession session, Model model) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) return "redirect:/blog/all";

        String loggedUsername = (String) session.getAttribute("loggedUser");
        if (loggedUsername == null || !blog.getAuthor().getUsername().equals(loggedUsername)) {
            return "redirect:/user/login";
        }

        model.addAttribute("blog", blog);
        return "updatePost";
    }

    @PostMapping("/blog/publish")
    public String publishPost(@RequestParam String title,
                               @RequestParam String area,
                               @RequestParam("imageFile") MultipartFile file,
                               @RequestParam String description,
                               HttpSession session) {

        String loggedUsername = (String) session.getAttribute("loggedUser");
        if (loggedUsername == null) return "redirect:/user/login";

        User author = userRepository.findByUsername(loggedUsername);
        if (author == null) return "redirect:/user/login";

        String fileName = null;

        if (!file.isEmpty()) {
            try {
                fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get("uploads");
                if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setArea(area);
        blog.setImageName(fileName);
        blog.setDescription(description);
        blog.setAuthor(author);
        blog.setTimestamp(LocalDateTime.now());

        blogRepository.save(blog);
        return "redirect:/home";
    }

    @GetMapping("/blog/search")
    public String searchBlogs(@RequestParam("area") String area, Model model) {
        List<Blog> blogs = blogService.findByAreaContainingIgnoreCase(area);
        model.addAttribute("blogs", blogs);
        model.addAttribute("searchQuery", area);
        return "searchResult";
    }

    @GetMapping("/blog/latest")
    public String showLatestPosts(Model model) {
        List<Blog> latestBlogs = blogRepository.findByOrderByTimestampDesc();
        model.addAttribute("latestBlogs", latestBlogs);
        return "latestPosts";
    }

    @PostMapping("/blog/update/{id}")
    public String updatePost(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String area,
                             @RequestParam String description,
                             @RequestParam("imageFile") MultipartFile file,
                             HttpSession session) {

        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) return "redirect:/blog/all";

        String loggedUsername = (String) session.getAttribute("loggedUser");
        if (loggedUsername == null || !blog.getAuthor().getUsername().equals(loggedUsername)) {
            return "redirect:/user/login";
        }

        blog.setTitle(title);
        blog.setArea(area);
        blog.setDescription(description);

        if (!file.isEmpty()) {
            try {
                if (blog.getImageName() != null) {
                    Path oldImagePath = Paths.get("uploads").resolve(blog.getImageName());
                    Files.deleteIfExists(oldImagePath);
                }
                String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                Path uploadPath = Paths.get("uploads");
                if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                blog.setImageName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        blogRepository.save(blog);
        return "redirect:/blog/all";
    }

//    @PostMapping("/blog/like/{id}")
//    public String toggleLike(@PathVariable Long id, HttpSession session, HttpServletRequest request) {
//        String username = (String) session.getAttribute("loggedUser");
//        if (username == null) return "redirect:/user/login";
//
//        Blog blog = blogRepository.findById(id).orElse(null);
//        User user = userRepository.findByUsername(username);
//
//        if (blog == null || user == null) return "redirect:/home";
//
//        Optional<Like> existingLike = likeRepository.findByUserAndBlog(user, blog);
//        if (existingLike.isPresent()) {
//            likeRepository.delete(existingLike.get()); // Unlike
//        } else {
//            Like like = new Like();
//            like.setUser(user);
//            like.setBlog(blog);
//            likeRepository.save(like); // Like
//        }
//
//        // ✅ Redirect back to the same page (referer)
//        String referer = request.getHeader("Referer");
//        return "redirect:" + referer;
//    }

    @GetMapping("/blog/delete/{id}")
    public String deletePost(@PathVariable Long id, HttpSession session, RedirectAttributes redirectAttributes) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Blog post not found.");
            return "redirect:/blog/all";
        }

        String loggedUsername = (String) session.getAttribute("loggedUser");
        if (loggedUsername == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to delete a post.");
            return "redirect:/user/login";
        }

        if (!blog.getAuthor().getUsername().equals(loggedUsername)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Only the author can delete this post.");
            return "redirect:/blog/all";
        }

        // Proceed with deletion
        String imageName = blog.getImageName();
        if (imageName != null) {
            Path imagePath = Paths.get("uploads", imageName);
            try {
                Files.deleteIfExists(imagePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        blogRepository.delete(blog);
        redirectAttributes.addFlashAttribute("successMessage", "Post deleted successfully.");
        return "redirect:/blog/all";
    }
    
 
}