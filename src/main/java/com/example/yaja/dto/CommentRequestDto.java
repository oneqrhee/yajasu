package com.example.yaja.dto;

public class CommentRequestDto {
    private final String author;
    private final String content;

    public CommentRequestDto(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }
}
