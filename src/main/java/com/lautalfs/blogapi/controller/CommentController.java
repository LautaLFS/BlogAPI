package com.lautalfs.blogapi.controller;

import com.lautalfs.blogapi.dto.ApiResponse;
import com.lautalfs.blogapi.dto.CommentDTO;
import com.lautalfs.blogapi.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/comment")
    public ResponseEntity<CommentDTO> createComment(@RequestBody CommentDTO comment,
                                                    @PathVariable String postId){
        return ResponseEntity.status(HttpStatus.OK).body(
                commentService.createComment(comment,postId));
    }
    @GetMapping("/{commentId}/comment")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable String commentId){
        this.commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Comment successfully deleted", true));
    }
}
