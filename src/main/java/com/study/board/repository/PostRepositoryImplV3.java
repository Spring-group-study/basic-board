package com.study.board.repository;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.mapper.MapperV2;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * PostDto 적용
 * JDBC Template + H2 DB 사용
 */

@Repository
public class PostRepositoryImplV3 implements PostRepositoryV3 {

    private final JdbcTemplate template;

    public PostRepositoryImplV3(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    private MapperV2 mapper = new MapperV2();

    @Override
    public Long save(PostDto dto) {
        Post post = mapper.SaveDtoToEntity(dto);
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(template);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("post_id");

        Map<String, String> data = new HashMap<>();
        data.put("author", post.getAuthor());
        data.put("title", post.getTitle());
        data.put("content", post.getContent());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(data));
        post.setPostId(key.longValue());

        return post.getPostId();
    }

    @Override
    public Post findById(Long postId) {
        String sql = "select * from post where post_id=?";
        return template.queryForObject(sql, postRowMapper(), postId);
    }

    private RowMapper<Post> postRowMapper() {
        return (rs, rowNum)->{  //while + rs.Next() 있어서 row1개 나올수도 있고 여러개 나올수도 있음
            Post post = new Post(rs.getString("author"),
                    rs.getString("title"),
                    rs.getString("content"));
            post.setPostId(rs.getLong("post_id"));
            return post;
        };
    }


    //findAll (1) : 메서드 따로 빼서 구현 -> 제일 깔끔
    @Override
    public List<Post> findAll() {
        String sql = "select * from post";
        return template.query(sql, postRowMapper());    //위 메서드 이용
    }


    //findAll (2) : 익명클래스 이용
    /**
    @Override
    public List<PostV2> findAll() {
        String sql = "select * from post";
        return template.query(sql, new RowMapper<PostV2>() {
            @Override
            public PostV2 mapRow(ResultSet rs, int rowNum) throws SQLException {
                PostV2 post = new PostV2(rs.getString("author"),
                        rs.getString("title"),
                        rs.getString("content"));
                post.setPostId(rs.getLong("post_id"));
                return post;
            }
        });
    }
    */

    @Override
    public Post update(Post post, PostDto updateParam) {
        Post updatedPost = mapper.updateDtoToEntity(post, updateParam);
        String sql = "update post set (author,title,content)=(?,?,?) where post_id=?";
        template.update(sql, updatedPost.getAuthor(), updatedPost.getTitle(), updatedPost.getContent(), updatedPost.getPostId());
        return updatedPost;
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from post where post_id=?";
        template.update(sql, id);
    }

    //테스터용
    @Override
    public void clear() {
        String sql = "delete from post";
    }
}
