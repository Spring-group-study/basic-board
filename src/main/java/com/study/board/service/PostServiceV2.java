package com.study.board.service;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.repository.PostRepositoryV2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV2 {

    private final PostRepositoryV2 postRepository;

    public PostServiceV2(PostRepositoryV2 postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(PostDto dto) {
        return postRepository.save(dto);
    }

    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post update(Post post, PostDto updateParam) {
        return postRepository.update(post, updateParam);
    }
}
