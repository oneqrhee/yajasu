package com.example.yaja.repository;

import com.example.yaja.entity.Comment;
import com.example.yaja.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPost(Post post);
}
