package com.study.board.repository;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.PostV2;

import java.util.List;

//V2에서 DTO 적용
public interface PostRepositoryV2 {

    public PostV2 save(PostDtoV2 dto);

    public PostV2 findById(Long id);

    public List<PostV2> findAll();

    public PostV2 update(PostV2 post, PostDtoV2 updateParam);

    public void delete(Long id);

    public void clear();
}
