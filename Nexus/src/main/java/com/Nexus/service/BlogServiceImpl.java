package com.Nexus.service;

import com.Nexus.entity.Blog;
import com.Nexus.entity.User;
import com.Nexus.repository.BlogRepository;
import com.Nexus.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;



import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Blog> getAllBlogs() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Blog> getBlogsByEmail(String email) {
        return blogRepository.findByAuthorEmail(email);
    }

    @Override
    @Transactional
    public Blog createBlogWithImage(String title, String content, byte[] imageBytes, String authorEmail) throws SQLException {

        User author = userRepo.findByEmail(authorEmail);

        if (author == null) {
            throw new RuntimeException("Author not found");
        }

        Blog blog = new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        blog.setAuthor(author);
        blog.setCreatedAt(LocalDate.now());

        if (imageBytes != null) {
            Blob blobImage = new SerialBlob(imageBytes);
            blog.setImage(blobImage);
        }

        return blogRepository.save(blog);

    }

    @Override
    public Blog getBlogById(Long id) {
        return blogRepository.findById(id).orElseThrow(() -> new RuntimeException("Blog not found"));
    }

    @Override
    @Transactional
    public Blog updateBlogWithImage(Long id, byte[] imageBytes) throws SQLException {

        Blog blog = getBlogById(id);
        if (blog == null) {
            throw new RuntimeException("Blog not found");
        }

        if (imageBytes != null) {
            Blob blobImage = new SerialBlob(imageBytes);
            blog.setImage(blobImage);
        }

        return blogRepository.save(blog);
    }

    public boolean deleteBlog(Long blogId, String userEmail) {
        Blog blog = blogRepository.findById(blogId).orElse(null);

        if (blog != null && blog.getAuthor().getEmail().equals(userEmail)) {

            blogRepository.delete(blog);
            return true;
        }
        return false;
    }

    @Override
    public long countBlogsByAuthorEmail(String authorEmail) {
        return blogRepository.countByAuthorEmail(authorEmail);  // Use the correct field 'authorEmail'
    }


}
