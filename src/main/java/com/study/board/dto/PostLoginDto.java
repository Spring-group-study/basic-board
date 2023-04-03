package com.study.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostLoginDto {
        private Long id;
        @NotEmpty
        private String author;
        private String title;
        private String content;
}
