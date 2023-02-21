package com.study.board.repository;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;

import java.util.List;

//DTO 적용
public interface PostRepositoryV2 {

    public Long save(PostDto dto);

    public Post findById(Long id);

    public List<Post> findAll();

    public Post update(Post post, PostDto updateParam);

    public void delete(Long id);

    public void clear();
}
