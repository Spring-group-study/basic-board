package com.study.board.service;

import com.study.board.dto.PostDtoV1;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.PostV1;
import com.study.board.entity.PostV2;
import com.study.board.repository.PostRepositoryV2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV2 {

    private final PostRepositoryV2 postRepository;

    public PostServiceV2(PostRepositoryV2 postRepository) {
        this.postRepository = postRepository;
    }

    public PostV2 save(PostDtoV2 dto) {
        return postRepository.save(dto);
    }

    public PostV2 findById(Long id) {
        return postRepository.findById(id);
    }

    public List<PostV2> findAll() {
        return postRepository.findAll();
    }

    public PostV2 update(PostV2 post, PostDtoV2 updateParam) {
        return postRepository.update(post, updateParam);
    }
}
