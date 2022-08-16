package com.example.yaja.entity;

import com.example.yaja.dto.PostRequestDto;

import javax.persistence.*;
import java.util.List;

@Entity
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "post")
    private List<Comment> commentList;

    public Post(String title, String author, String content, String password){
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getContent(){
        return content;
    }

    public String getPassword(){
        return password;
    }

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void update(PostRequestDto postRequestDto){
        this.title = postRequestDto.getTitle();
        this.author = postRequestDto.getAuthor();
        this.content = postRequestDto.getContent();
        this.password = postRequestDto.getPassword();
    }
}
