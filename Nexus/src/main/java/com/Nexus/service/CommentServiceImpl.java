package com.Nexus.service;

import com.Nexus.entity.Blog;
import com.Nexus.entity.Comment;
import com.Nexus.entity.User;
import com.Nexus.repository.BlogRepository;
import com.Nexus.repository.CommentRepository;
import com.Nexus.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;


@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserRepo userRepo;

    @Override
    @Transactional
    public Comment addCommentToBlog(Long blogId, String email, String content) {
        Blog blog = blogRepository.findById(blogId)
                .orElseThrow(() -> new RuntimeException("Blog not found"));

        User user = userRepo.findByEmail(email);

        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Comment comment = new Comment();
        comment.setBlog(blog);
        comment.setUser(user);
        comment.setContent(content);
        comment.setCreatedAt(LocalDate.now());

        return commentRepository.save(comment);
    }

}

