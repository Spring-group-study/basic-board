package com.study.board.service;

import com.study.board.entity.Post;
import com.study.board.repository.PostRepositoryV2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceV2Impl implements PostServiceV2 {

    private final PostRepositoryV2 repository;

    public PostServiceV2Impl(PostRepositoryV2 repository) {
        this.repository = repository;
    }


    @Override
    public List<Post> getAllPost() {
        List<Post> posts = repository.findAll();
        return posts;
    }

    @Override
    public List<Post> getAllPostByPage(int pageNumber) {
        List<Post> allByPage = repository.findAllByPage(pageNumber);
        return allByPage;
    }

    //생성해야 할 페이지 수 리턴
    @Override
    public int getAllPostCount() {
        int allPostCount = repository.findAllPostCount();
        return (allPostCount/5)+1;
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
