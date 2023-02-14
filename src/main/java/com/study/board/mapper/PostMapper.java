package com.study.board.mapper;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import org.springframework.stereotype.Component;

@Component
public class PostMapper {

    public Post postDtoToPost(PostDto.Post postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setAuthor(postDto.getAuthor());
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }

    public Post postPatchDtoToPost(PostDto.Patch postDto) {
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        return post;
    }

    public PostDto.Response postToPostResponseDto(Post post) {
        return new PostDto.Response(
                post.getAuthor(),
                post.getTitle(),
                post.getContent());
    }
}
