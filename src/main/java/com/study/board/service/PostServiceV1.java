package com.study.board.service;

import com.study.board.entity.Post;
import com.study.board.repository.PostRepositoryV1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV1 {

    private final PostRepositoryV1 postRepository;

    public PostServiceV1(PostRepositoryV1 postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post dto) {
        return postRepository.save(dto);
    }

    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post update(Post post, Post updateParam) {
        return postRepository.update(post, updateParam);
    }
}
