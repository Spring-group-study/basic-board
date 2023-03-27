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

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberV2 member;

    private String title;
    private String content;



    public PostV2() {
    }

    public PostV2(MemberV2 member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
}
