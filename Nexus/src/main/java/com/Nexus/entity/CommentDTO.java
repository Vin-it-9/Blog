package com.Nexus.entity;

import java.time.LocalDate;

public class CommentDTO {

    private Long id;
    private String content;
    private LocalDate createdAt;
    private User user; // Include User object

    public CommentDTO(Long id, String content, LocalDate createdAt, User user) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.user = user; // Store the User object
    }

    // Getters
    public Long getId() { return id; }
    public String getContent() { return content; }
    public LocalDate getCreatedAt() { return createdAt; }
    public User getUser() { return user; } // Getter for User object
}
