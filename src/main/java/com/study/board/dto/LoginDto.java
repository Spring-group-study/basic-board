package com.study.board.dto;

import javax.validation.constraints.NotBlank;

public class LoginDto {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    public LoginDto() {
    }

    public LoginDto(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
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
}
