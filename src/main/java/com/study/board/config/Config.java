package com.study.board.config;

import com.study.board.dto.PostDto;
import com.study.board.repository.PostRepositoryImplV3;
import com.study.board.service.PostServiceV3;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.PostConstruct;

@Configuration
public class Config {
    private final PostServiceV3 postServiceV3;
    private final PostRepositoryImplV3 postRepositoryImplV3;

    JdbcTemplate template = new JdbcTemplate();

    public Config(PostServiceV3 postServiceV3,PostRepositoryImplV3 postRepositoryImplV3) {
        this.postServiceV3 = postServiceV3;
        this.postRepositoryImplV3 = postRepositoryImplV3;
    }
/*
//    db초기화 복붙

    drop table if exists post;

    create table post (
            post_id bigint generated by default as identity,
            author varchar(20) not null,
    title varchar(255) not null,
    content varchar(255) not null,
    primary key (post_id));

*/

    //초기 데이터
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        postRepositoryImplV3.clear();
        for (int i = 1; i <= 112; i++) {
            String author = "author" + i;
            String title = "title" + i;
            String content = "content" + i;
            postServiceV3.save(new PostDto(author, title, content));
        }
    }

}
