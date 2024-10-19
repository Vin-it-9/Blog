package com.Nexus.service;

import com.Nexus.entity.Comment;
import com.Nexus.entity.CommentDTO;

import java.util.List;

public interface CommentService {


    Comment addCommentToBlog(Long blogId, String email, String content);

    List<CommentDTO> getCommentsByBlogId(Long blogId);
}
