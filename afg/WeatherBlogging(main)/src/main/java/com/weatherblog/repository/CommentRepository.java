package com.weatherblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherblog.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByBlogId(Long blogId);
}
