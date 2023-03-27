package com.study.board.mapper;

import com.study.board.dto.PostDto;
import com.study.board.entity.MyPost;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostMapper {

    @Bean
    public PostDto mapPostToDto(MyPost myPost) {
        PostDto postDto = new PostDto(myPost.getId(), myPost.getAuthor(), myPost.getTitle(), myPost.getContent());
        return postDto;
    }
}
