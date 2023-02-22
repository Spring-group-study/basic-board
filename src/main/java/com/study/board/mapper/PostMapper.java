package com.study.board.mapper;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostMapper {

    @Bean
    public PostDto mapPostToDto(Post post) {
        PostDto postDto = new PostDto(post.getId(), post.getAuthor(), post.getTitle(),post.getContent());
        return postDto;
    }
}
