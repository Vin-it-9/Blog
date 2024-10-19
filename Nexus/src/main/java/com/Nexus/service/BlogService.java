package com.Nexus.service;

import com.Nexus.entity.Blog;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public interface BlogService {


    List<Blog> getAllBlogs();

    Blog getBlogById(Long id);

    Blog createBlogWithImage(String title, String content, byte[] imageBytes, String authorEmail) throws SQLException;

    Blog updateBlogWithImage(Long id, byte[] imageBytes) throws SQLException ;

    long countBlogsByAuthorEmail(String authorEmail);


}

