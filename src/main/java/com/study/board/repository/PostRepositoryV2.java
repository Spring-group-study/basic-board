package com.study.board.repository;

import com.study.board.entity.Post;

import java.util.List;

public interface PostRepositoryV2 {
    List<Post> findAll();

    List<Post> findAllByPage(int pageNumber);

    int findAllPostCount();

    Post findById(Long id);
    Long save(Post post);
    void savePost(Post post);
    void update(Long id, String author, String content, String title);
    void delete(Long id);
    void deleteAll();

}
