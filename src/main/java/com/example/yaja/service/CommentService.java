package com.example.yaja.service;

import com.example.yaja.dto.CommentRequestDto;
import com.example.yaja.entity.Comment;
import com.example.yaja.entity.Post;
import com.example.yaja.repository.CommentRepository;
import com.example.yaja.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Transactional
    public void createComment(Long postId, CommentRequestDto commentRequestDto){
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."));
        String author = commentRequestDto.getAuthor();
        String content = commentRequestDto.getContent();

        commentRepository.save(new Comment(author, content, post));
    }

    @Transactional
    public void updateComment(Long commentId, CommentRequestDto commentRequestDto){
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "댓글이 존재하지 않습니다."));
        comment.updateComment(commentRequestDto);
    }

    @Transactional
    public void deleteComment(Long commentId){
        commentRepository.deleteById(commentId);
    }
}
