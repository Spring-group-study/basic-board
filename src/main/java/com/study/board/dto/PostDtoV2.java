package com.study.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class PostDtoV2 {
    @NotBlank
    private String author;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public PostDtoV2() {
    }

    public PostDtoV2(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
