package com.study.board.repository;

import com.study.board.entity.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostRepositoryV2Impl implements PostRepositoryV2{
    //jdbc template 주입
    private final JdbcTemplate jdbcTemplate;

    public PostRepositoryV2Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> findAll() {
        String sql = "select * from post";
        List<Post> postList = jdbcTemplate.query(sql, new PostMapper());
        return postList;
    }

    @Override
    public Post findById(Long id) {
        String sql = "select * from post where id=?";
        List<Post> query = jdbcTemplate.query(sql, new PostMapper(), id);
        return query.isEmpty() ? null : query.get(0);
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

    static class PostMapper implements RowMapper<Post> {

        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post(
                    rs.getLong("id"),rs.getString("content"),
                    rs.getString("title"), rs.getString("author"));
            return post;
        }
    }
}
