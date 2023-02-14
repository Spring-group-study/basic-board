package com.study.board.entity;


public class Post {
    Long id;
    String author;
    String title;
    String content;

    public Post() {

    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post(Long id, String author, String title, String content) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
