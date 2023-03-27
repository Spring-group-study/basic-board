package com.study.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginDtoV2 {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    public LoginDtoV2() {
    }

    public LoginDtoV2(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
