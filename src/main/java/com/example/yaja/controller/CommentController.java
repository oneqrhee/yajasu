package com.example.yaja.controller;

import com.example.yaja.dto.CommentRequestDto;
import com.example.yaja.service.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{postId}/comment")
    public void createComment(@PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto){
        commentService.createComment(postId, commentRequestDto);
    }

    @PutMapping("/comment/{commentId}")
    public void updateComment(@PathVariable Long commentId, @RequestBody CommentRequestDto commentRequestDto){
        commentService.updateComment(commentId, commentRequestDto);
    }

    @DeleteMapping("/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId){
        commentService.deleteComment(commentId);
    }
}
