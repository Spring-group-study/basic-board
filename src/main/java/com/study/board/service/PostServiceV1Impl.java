package com.study.board.service;

import com.study.board.entity.Post;
import com.study.board.repository.PostRepositoryV1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV1Impl implements PostServiceV1 {

    private final PostRepositoryV1 repository;

    public PostServiceV1Impl(PostRepositoryV1 repository) {
        this.repository = repository;
    }


    @Override
    public List<Post> getAllPost() {
        List<Post> posts = repository.findAll();
        return posts;
    }

    @Override
    public Post getOnePost(Long id) {
        return repository.findById(id);
    }

    @Override
    public void savePost(Post post) {
        repository.save(post);
    }

    @Override
    public void updatePost(Long id, String author, String content, String title) {
        repository.update(id,author,content,title);
    }

    @Override
    public void deletePost(Long id) {
        repository.delete(id);
    }
}
