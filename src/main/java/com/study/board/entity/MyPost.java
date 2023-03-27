package com.study.board.entity;

import org.springframework.context.annotation.Configuration;

@Configuration
public class MyPost {
    private Long id;
    private String author;
    private String title;
    private String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MyPost() {

    }

    public MyPost(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
    public void toEntity(String author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
