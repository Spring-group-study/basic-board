package com.study.board.repository;

import com.study.board.entity.MyPost;

import java.util.List;
public interface PostRepositoryV1 {
    List<MyPost> findAll();
    MyPost findById(Long id);
    void save(MyPost myPost);

    void update(Long id, String author, String content, String title);

    void delete(Long id);


}
