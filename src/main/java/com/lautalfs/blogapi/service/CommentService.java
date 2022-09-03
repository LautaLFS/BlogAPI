package com.lautalfs.blogapi.service;

import com.lautalfs.blogapi.dto.CommentDTO;

public interface CommentService {

    CommentDTO createComment(CommentDTO comment, String postId);

    void deleteComment(String id);
}
