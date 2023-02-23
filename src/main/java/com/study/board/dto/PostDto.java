package com.study.board.dto;


import javax.validation.constraints.NotBlank;

public class PostDto {

    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public PostDto() {
    }

    public PostDto(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
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
}
