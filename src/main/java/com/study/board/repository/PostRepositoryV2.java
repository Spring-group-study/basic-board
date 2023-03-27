package com.study.board.repository;

import com.study.board.entity.MyPost;

import java.util.List;

public interface PostRepositoryV2 {
    List<MyPost> findAll();

    List<MyPost> findAllByPage(int pageNumber);

    int findAllPostCount();

    MyPost findById(Long id);
    Long save(MyPost myPost);
    void savePost(MyPost myPost);
    void update(Long id, String author, String content, String title);
    void delete(Long id);
    void deleteAll();

}
