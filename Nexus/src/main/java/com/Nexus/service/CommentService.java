package com.Nexus.service;

import com.Nexus.entity.Comment;

import java.util.List;

public interface CommentService {


    Comment addCommentToBlog(Long blogId, String email, String content);
}
