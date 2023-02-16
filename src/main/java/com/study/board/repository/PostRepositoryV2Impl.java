package com.study.board.repository;

import com.study.board.entity.Post;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryV2Impl implements PostRepositoryV2 {
    //jdbc template 주입
    private final JdbcTemplate jdbcTemplate;

    static Long postId = 0l;

    public PostRepositoryV2Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Post> findAll() {
        String sql = "select * from post";
        try {
            List<Post> postList = jdbcTemplate.query(sql, new PostMapper());
            return postList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Post findById(Long id) {
        String sql = "select * from post where id=?";
        List<Post> query = jdbcTemplate.query(sql, new PostMapper(), id);
        return query.isEmpty() ? null : query.get(0);
    }

    @Override
    public void save(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        try {
            Map<String, String> data = new HashMap<>();
            data.put("content", post.getContent());
            data.put("author", post.getAuthor());
            data.put("title", post.getTitle());

            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(data));
            post.setId(key.longValue());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    //이 방법은 강제로 순차적으로 증가하는 PRIMARY KEY ID값을 넣어줘야 한다.
    public void savePost(Post post) {
        String sql = "insert into post values(?,?,?,?)";
        try {
            jdbcTemplate.update(sql, postId, post.getContent(), post.getAuthor(), post.getTitle());
            postId++;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void update(Long id, String author, String content, String title) {
        String sql = "update post set author=?, content=?, title=? where id=?";
        try {
            jdbcTemplate.update(sql, content, title, author, id);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from post where id=?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    static class PostMapper implements RowMapper<Post> {
        @Override
        public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
            Post post = new Post(
                    rs.getLong("id"), rs.getString("content"),
                    rs.getString("title"), rs.getString("author"));
            return post;
        }
    }
}