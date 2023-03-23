package com.study.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class PostV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long postId;
    private String author;
    private String title;
    private String content;

    public PostV2() {
    }

    public PostV2(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
