package com.weatherblog.service;

import com.weatherblog.model.Blog;
import com.weatherblog.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    public void saveBlog(Blog blog) {
        blogRepository.save(blog);
    }

    public List<Blog> getLatestBlogs() {
        return blogRepository.findByOrderByTimestampDesc();
    }

	public List<Blog> getAllBlogs() {
		// TODO Auto-generated method stub
        return blogRepository.findAllByOrderByTimestampDesc();
	}
	public List<Blog> findByAreaContainingIgnoreCase(String area) {
	    return blogRepository.findByAreaContainingIgnoreCase(area);
	}



}