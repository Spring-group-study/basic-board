package com.study.board.repository;

import com.study.board.entity.Post;

import java.util.List;

public interface PostRepositoryV1 {

    public Post save(Post post);

    public Post findById(Long id);

    public List<Post> findAll();

    public Post update(Post post, Post updateParam);   //dto적용할것

    public void delete(Long id);

    public void clear();
}
