package com.study.board.repository;

import com.study.board.domain.PostV1;

import java.util.List;

public interface PostRepositoryV1 {

    public PostV1 save(PostV1 post);

    public PostV1 findById(Long id);

    public List<PostV1> findAll();

    public PostV1 update(PostV1 post, PostV1 updateParam);   //dto적용할것

    public void delete(Long id);

    public void clear();
}
