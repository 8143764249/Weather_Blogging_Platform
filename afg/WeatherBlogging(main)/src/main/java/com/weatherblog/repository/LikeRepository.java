package com.weatherblog.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherblog.model.Blog;
import com.weatherblog.model.Like;
import com.weatherblog.model.User;

@Repository
	public interface LikeRepository extends JpaRepository<Like, Long> {
	    Optional<Like> findByUserAndBlog(User user, Blog blog);
	    List<Like> findByBlog(Blog blog);
	}

