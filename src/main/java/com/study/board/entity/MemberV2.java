package com.study.board.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class MemberV2 {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;          //DB에서 PK
    private String loginId;            //로그인 ID
    private String password;
    private String nickname;

    @OneToMany(mappedBy = "member")
    private List<PostV2> posts = new ArrayList<>();

    public MemberV2() {
    }

    public MemberV2(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}
