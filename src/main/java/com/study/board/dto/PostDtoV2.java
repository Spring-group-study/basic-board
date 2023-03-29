package com.study.board.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class PostDtoV2 {
    @NotBlank
    private String nickname;

    @NotBlank
    private String title;

    @NotBlank
    private String content;



    public PostDtoV2() {
    }

    public PostDtoV2(String nickname) {
        this.nickname = nickname;
    }

    public PostDtoV2(String nickname, String title, String content) {
        this.nickname = nickname;
        this.title = title;
        this.content = content;
    }
}
