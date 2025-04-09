package com.weatherblog.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blog_likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Like() {}

    public Like(Blog blog, User user) {
        this.blog = blog;
        this.user = user;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Blog getBlog() { return blog; }
    public void setBlog(Blog blog) { this.blog = blog; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
