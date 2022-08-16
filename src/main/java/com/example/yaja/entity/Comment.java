package com.example.yaja.entity;

import com.example.yaja.dto.CommentRequestDto;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Comment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String author;

    private String content;

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    Post post;

    public Comment() {
    }

    public Comment(String author, String content, Post post) {
        this.author = author;
        this.content = content;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public void updateComment(CommentRequestDto commentRequestDto){
        this.author = commentRequestDto.getAuthor();
        this.content = commentRequestDto.getContent();
    }
}
