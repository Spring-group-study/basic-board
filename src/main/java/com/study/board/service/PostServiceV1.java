package com.study.board.service;

import com.study.board.entity.MyPost;

import java.util.List;

public interface PostServiceV1 {

    List<MyPost> getAllPost();
    MyPost getOnePost(Long id);
    void savePost(MyPost myPost);

    void updatePost(Long id, String author, String content, String title);

    void deletePost(Long id);
}