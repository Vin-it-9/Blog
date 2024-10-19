package com.Nexus.entity;

import java.time.LocalDate;
import java.util.List;

public class BlogDTO {

    private Long id;
    private String title;
    private String content;
    private User author; // Include the Author object directly
    private LocalDate createdAt;
    private List<CommentDTO> comments; // List of comments associated with the blog

    public BlogDTO(Blog blog, List<CommentDTO> comments) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.content = blog.getContent();
        this.author = blog.getAuthor(); // Store the author directly
        this.createdAt = blog.getCreatedAt();
        this.comments = comments; // Store comments in the DTO
    }

    // Getters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getContent() { return content; }
    public User getAuthor() { return author; } // Getter for author
    public LocalDate getCreatedAt() { return createdAt; }
    public List<CommentDTO> getComments() { return comments; }
}
