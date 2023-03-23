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
public class MemberV2 {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long memberId;          //DB에서 PK
    private String loginId;            //로그인 ID
    private String password;
    private String nickname;

    public MemberV2() {
    }

    public MemberV2(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }
}
