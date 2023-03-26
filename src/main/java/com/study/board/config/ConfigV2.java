package com.study.board.config;

import com.study.board.dto.MemberDtoV2;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.MemberV2;
import com.study.board.service.MemberServiceV2;
import com.study.board.service.PostServiceV4;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@RequiredArgsConstructor
public class ConfigV2 {

    private final PostServiceV4 postService;
    private final MemberServiceV2 memberService;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        for (int i = 1; i <= 53; i++) {
            MemberDtoV2 member = new MemberDtoV2("loginId" + i, "pw" + i, "nick" + i);
            memberService.save(member);
        }
        for (int i = 1; i <= 53; i++) {
            PostDtoV2 post = new PostDtoV2("author" + i, "title" + i, "content" + i);
            postService.save(post);
        }
    }
}
