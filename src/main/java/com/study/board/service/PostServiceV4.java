package com.study.board.service;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.Post;
import com.study.board.entity.PostV2;
import com.study.board.repository.PostRepositoryV4;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceV4 {

    private final PostRepositoryV4 postRepository;

    public Long save(PostDtoV2 dto) {
        return postRepository.save(dto);
    }

    public PostV2 findById(Long id) {
        return postRepository.findById(id);
    }

    public List<PostV2> findAllByPage(int page) {
        return postRepository.findAllPerPage(page);
    }

    public Integer postCnt() {
        return postRepository.postCnt();
    }

    public PostV2 update(PostV2 post, PostDtoV2 updateParam) {
        return postRepository.update(post, updateParam);
    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
