package com.lautalfs.blogapi.service.impl;

import com.lautalfs.blogapi.dto.CommentDTO;
import com.lautalfs.blogapi.entity.CommentEntity;
import com.lautalfs.blogapi.exception.ResourceNotFoundException;
import com.lautalfs.blogapi.mapper.CommentMapper;
import com.lautalfs.blogapi.repository.CommentRepository;
import com.lautalfs.blogapi.repository.PostRepository;
import com.lautalfs.blogapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    @Override
    public CommentDTO createComment(CommentDTO comment, String postId) {
        var post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("post", "id", postId)
        );
        var commentEntity = commentMapper.map(comment, CommentEntity.class);
        commentEntity.setPostEntity(post);
        var commentResponse = commentRepository.save(commentEntity);
        return commentMapper.map(commentResponse, CommentDTO.class);
    }

    @Override
    public void deleteComment(String id) {
        var comment = commentRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("post", "id", id));
        this.commentRepository.delete(comment);
    }
}
