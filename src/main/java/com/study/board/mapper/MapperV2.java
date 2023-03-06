package com.study.board.mapper;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;

public class MapperV2 {
    public Post SaveDtoToEntity(PostDto dto) {
        //롬복 추가 후 builder패턴 이용할것
        Post post = new Post(dto.getAuthor(), dto.getTitle(), dto.getContent());
        return post;
    }

    public Post updateDtoToEntity(Post post, PostDto dto) {
        post.setAuthor(dto.getAuthor());
        post.setTitle(dto.getTitle());
        post.setContent(dto.getContent());

        return post;
    }
}
