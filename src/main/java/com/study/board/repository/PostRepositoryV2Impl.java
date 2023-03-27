package com.study.board.repository;

import com.study.board.entity.MyPost;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional(readOnly = true)
public class PostRepositoryV2Impl implements PostRepositoryV2 {
    //jdbc template 주입
    private final JdbcTemplate jdbcTemplate;

    static Long postId = 0l;

    public PostRepositoryV2Impl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<MyPost> findAll() {
        String sql = "select * from post";
        try {
            List<MyPost> myPostList = jdbcTemplate.query(sql, new PostMapper());
            return myPostList;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public List<MyPost> findAllByPage(int pageNumber) {
        String sql = "select * from post order by id limit 5 offset ?";

        switch (pageNumber) {
            case 1:
                return jdbcTemplate.query(sql, new PostMapper(), 5);
            case 2:
                return jdbcTemplate.query(sql, new PostMapper(),10);
            case 3:
                return jdbcTemplate.query(sql, new PostMapper(), 15);
            default:
                return jdbcTemplate.query(sql, new PostMapper(), 0);
        }
    }

    @Override
    public int findAllPostCount() {
        String sql = "select count(*) from post";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    @Override
    public MyPost findById(Long id) {
        String sql = "select * from post where id=?";
        List<MyPost> query = jdbcTemplate.query(sql, new PostMapper(), id);
        return query.isEmpty() ? null : query.get(0);
    }

    // JDBC Core에서 제공하는 SimpleJdbcInsert메서드를 사용해서
    // HashMap에 데이터를 담아 jdbcInsert로 db Insert query를 생성한다.
    @Override
    @Transactional
    public Long save(MyPost myPost) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        try {
            Map<String, String> data = new HashMap<>();
            data.put("content", myPost.getContent());
            data.put("author", myPost.getAuthor());
            data.put("title", myPost.getTitle());

            Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(data));
            myPost.setId(key.longValue());
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return myPost.getId();
    }

    //이 방법은 강제로 순차적으로 증가하는 PRIMARY KEY ID값을 넣어줘야 한다.
    @Override
    @Transactional
    public void savePost(MyPost myPost) {
        String sql = "insert into post values(?,?,?,?)";
        try {
            jdbcTemplate.update(sql, postId, myPost.getContent(), myPost.getAuthor(), myPost.getTitle());
            postId++;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Transactional
    public void update(Long id, String author, String content, String title) {
        String sql = "update post set author=?, content=?, title=? where id=?";
        try {
            jdbcTemplate.update(sql, content, title, author, id);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        String sql = "delete from post where id=?";
        try {
            jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    @Transactional
    public void deleteAll() {
        String sql = "delete from post";
        jdbcTemplate.update(sql);
    }

    // table Row와 데이터를 매핑해준다.
    static class PostMapper implements RowMapper<MyPost> {
        @Override
        public MyPost mapRow(ResultSet rs, int rowNum) throws SQLException {
            MyPost myPost = new MyPost(
                    rs.getLong("id"), rs.getString("content"),
                    rs.getString("title"), rs.getString("author"));
            return myPost;
        }
    }
}
