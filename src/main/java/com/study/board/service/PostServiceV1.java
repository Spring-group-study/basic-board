package com.study.board.service;

import com.study.board.entity.PostV1;
import com.study.board.dto.PostDtoV1;
import com.study.board.repository.PostRepositoryV2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV1 {

    private final PostRepositoryV2 postRepository;

    public PostServiceV1(PostRepositoryV2 postRepository) {
        this.postRepository = postRepository;
    }

    public PostV1 save(PostDtoV1 dto) {
        return postRepository.save(dto);
    }

    public PostV1 findById(Long id) {
        return postRepository.findById(id);
    }

    public List<PostV1> findAll() {
        return postRepository.findAll();
    }

    public PostV1 update(PostV1 post, PostDtoV1 updateParam) {
        return postRepository.update(post, updateParam);
    }
}
