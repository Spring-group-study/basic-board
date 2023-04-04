package com.study.board.dto;

import javax.validation.constraints.NotBlank;

public class MemberDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    @NotBlank
    private String nickname;

    public MemberDto() {
    }

    public MemberDto(String loginId, String password, String nickname) {
        this.loginId = loginId;
        this.password = password;
        this.nickname = nickname;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
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
