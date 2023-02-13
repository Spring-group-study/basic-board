package com.study.board.service;

import com.study.board.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPost();
    Post getOnePost(Long id);
    void savePost(Post post);

    void updatePost(Long id, String author, String content, String title);

    void deletePost(Long id);
}