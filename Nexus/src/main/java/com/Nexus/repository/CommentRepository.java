package com.Nexus.repository;

import com.Nexus.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {

    List<Comment> findByBlogId(Long blogId);

}
