package com.Nexus.repository;

import com.Nexus.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.awt.print.Pageable;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long> {

        List<Blog> findAll();

        Blog findByTitle(String title);

        @Query("SELECT b FROM Blog b ORDER BY b.createdAt ASC")
        List<Blog> findAllByOrderByCreatedAtDesc();

        List<Blog> findByAuthorEmail(String email);

        long countByAuthorEmail(String authorEmail);


}
