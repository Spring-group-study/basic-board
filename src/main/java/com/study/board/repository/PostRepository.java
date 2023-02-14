package com.study.board.repository;

import com.study.board.entity.Post;

import java.util.List;

public interface PostRepository {
    Post save(Post post);
    Post findOne(long postId);
    List<Post> findAll();
    void delete(long postId);
}
