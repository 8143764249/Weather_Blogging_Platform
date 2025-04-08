package com.weatherblog.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Auto-generated ID

    private String title;
    private String description;
    private String area;

    @Column(name = "image_name")
    private String imageName; // Will store uploaded image filename
//    @Column(nullable = false)
//    private int likes = 0;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "user_id") // Links to the user who wrote the blog
    private User author;
    @OneToMany(mappedBy = "blog")
    

    @PrePersist
    protected void onCreate() {
        timestamp = LocalDateTime.now();  // Automatically set timestamp when created
    }
//    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Like> likess = new ArrayList<>();
//
//    public int getLikesCount() {
//        return likess.size();
//    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getImageName() { return imageName; }
    public void setImageName(String imageName) { this.imageName = imageName; }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
//    public int getLikes() { return likes; }
//    public void setLikes(int likes) { this.likes = likes; }
    public User getAuthor() { return author; }
    public void setAuthor(User author) { this.author = author; }
}
