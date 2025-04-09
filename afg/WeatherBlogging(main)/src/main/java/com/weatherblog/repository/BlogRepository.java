package com.weatherblog.repository;

import com.weatherblog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
    List<Blog> findByOrderByTimestampDesc(); // Get latest blogs

	List<Blog> findAllByOrderByTimestampDesc();
	List<Blog> findByAreaContainingIgnoreCase(String area);
	@Query("SELECT DISTINCT b FROM Blog b LEFT JOIN FETCH b.tags ORDER BY b.timestamp DESC")
	List<Blog> findAllWithTags();


} 