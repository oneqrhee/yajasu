package com.example.yaja.dto;

public class PostRequestDto {
    private final String title;
    private final String author;
    private final String content;
    private final String password;

    public PostRequestDto(String title, String author, String content, String password) {
        this.title = title;
        this.author = author;
        this.content = content;
        this.password = password;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getPassword() {
        return password;
    }
}
