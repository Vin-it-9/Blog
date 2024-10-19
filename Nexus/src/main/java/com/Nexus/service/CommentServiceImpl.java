package com.Nexus.service;

import com.Nexus.entity.Blog;
import com.Nexus.entity.Comment;
import com.Nexus.entity.CommentDTO;
import com.Nexus.entity.User;
import com.Nexus.repository.BlogRepository;
import com.Nexus.repository.CommentRepository;
import com.Nexus.repository.UserRepo;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private BlogRepository blogRepository;


    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ListableBeanFactory listableBeanFactory;

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

    @Override
    public List<CommentDTO> getCommentsByBlogId(Long blogId) {
        List<Comment> comments = commentRepository.findByBlogId(blogId);
        List<CommentDTO> commentDTOs = new ArrayList<>();

        for (Comment comment : comments) {
            User user = userRepo.findByEmail(comment.getUser().getEmail()); // Fetch user by email
            commentDTOs.add(new CommentDTO(
                    comment.getId(),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    user // Pass the User object
            ));
        }

        return commentDTOs;
    }



}

