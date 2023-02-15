package com.study.board.repository;

import com.study.board.entity.PostV1;
import com.study.board.dto.PostDtoV1;

import java.util.List;

//V2에서 DTO 적용
public interface PostRepositoryV2 {

    public PostV1 save(PostDtoV1 dto);

    public PostV1 findById(Long id);

    public List<PostV1> findAll();

    public PostV1 update(PostV1 post, PostDtoV1 updateParam);

    public void delete(Long id);

    public void clear();
}
