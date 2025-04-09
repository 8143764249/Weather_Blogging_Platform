package com.weatherblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.weatherblog.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findByName(String name);
}
