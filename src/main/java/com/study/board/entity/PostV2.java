package com.study.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class PostV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTO로 하면 init() 실행시 pk가 member의 pk에 이어서 만들어짐
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
