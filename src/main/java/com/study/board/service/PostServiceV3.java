package com.study.board.service;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.paging.Pagination;
import com.study.board.repository.PostRepositoryV2;
import com.study.board.repository.PostRepositoryV3;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV3 {

    private final PostRepositoryV3 postRepository;

    public PostServiceV3(PostRepositoryV3 postRepository) {
        this.postRepository = postRepository;
    }

    public Long save(PostDto dto) {
        return postRepository.save(dto);
    }

    public Post findById(Long id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public int postCnt() {
        return postRepository.postCnt();
    }

    public Post update(Post post, PostDto updateParam) {
        return postRepository.update(post, updateParam);
    }

    public List<Post> pagedFindAll(Pagination pagination) {

        return postRepository.pagedFindAll(pagination);

    }

    public void delete(Long id) {
        postRepository.delete(id);
    }
}
