package com.study.board.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class LoginDto {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String loginPw;

    public LoginDto(String loginId, String loginPw) {
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

    public LoginDto() {
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPw() {
        return loginPw;
    }

    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }
}
