package com.study.board.mapper;

import com.study.board.dto.PostDtoV1;
import com.study.board.entity.PostV1;

public class Mapper {
    public PostV1 SaveDtoToEntity(PostDtoV1 dto) {
        //롬복 추가 후 builder패턴 이용할것
        PostV1 post = new PostV1(dto.getAuthor(), dto.getTitle(), dto.getContent());
        return post;
    }

    public PostV1 updateDtoToEntity(PostV1 post, PostDtoV1 dto) {
        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }
}
