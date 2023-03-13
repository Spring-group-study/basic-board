package com.study.board.dto;

import javax.validation.constraints.NotBlank;

public class MemberDto {

    @NotBlank
    private String memberId;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    public MemberDto(String memberId, String password, String nickname) {
        this.memberId = memberId;
        this.password = password;
        this.nickname = nickname;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
