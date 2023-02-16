package com.study.board.mapper;

import com.study.board.dto.PostDtoV1;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.PostV1;
import com.study.board.entity.PostV2;

public class MapperV2 {
    public PostV2 SaveDtoToEntity(PostDtoV2 dto) {
        //롬복 추가 후 builder패턴 이용할것
        PostV2 post = new PostV2(dto.getAuthor(), dto.getTitle(), dto.getContent());
        return post;
    }

    public PostV2 updateDtoToEntity(PostV2 post, PostDtoV2 dto) {
        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }
}
