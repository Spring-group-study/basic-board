package com.study.board.repository;

import com.study.board.entity.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostRepositoryV2 implements PostRepositoryV1{
    //jdbc template 주입
    private final JdbcTemplate jdbcTemplate;

    public PostRepositoryV2(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> findAll() {
        return null;
    }

    @Override
    public Post findById(Long id) {
        return null;
    }

    @Override
    public void save(Post post) {

    }

    @Override
    public void update(Long id, String author, String content, String title) {

    }

    @Override
    public void delete(Long id) {

    }
}
