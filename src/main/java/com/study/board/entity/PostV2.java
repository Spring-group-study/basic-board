package com.study.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PostV2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberV2 member;

    private String title;
    private String content;
    private LocalDateTime createTime=LocalDateTime.now();




    public PostV2() {
    }

    public PostV2(MemberV2 member, String title, String content) {
        this.member = member;
        this.title = title;
        this.content = content;
    }
}
