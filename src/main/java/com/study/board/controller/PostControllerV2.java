package com.study.board.controller;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.repository.PostRepositoryV2;
import com.study.board.service.PostServiceV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/board")
public class PostControllerV2 {

    private final PostServiceV2 postServiceV2;

    public PostControllerV2(PostServiceV2 postServiceV2) {
        this.postServiceV2 = postServiceV2;
    }


    //게시판 메인
    @GetMapping("/main")
    public String posts(Model model) {
        List<Post> posts = postServiceV2.findAll();
        model.addAttribute("posts", posts);
        return "/board/main";
    }

    //단일 게시글
    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        Post post = postServiceV2.findById(id);
        model.addAttribute("post", post);
        return "/board/post";
    }

    //게시글 등록
    @GetMapping("/addPost")
    public String addPost(Model model) {
        model.addAttribute("post", new PostDto());
        return "/board/addPost";
    }

    /**
     * 질문 : Post savedPost = postServiceV2.save(dto); 이후에 savedPost.getPostId()가 null이 나와서 오류페이지가 뜸...
     * -> SimpleJdbcInsert 사용
     */
    //게시글 등록버튼 누를시 단일게시글 페이지로 redirect -> 새로고침시 게시글 중복등록 막기위함
    @PostMapping("/addPost")
    public String addPost(@ModelAttribute PostDto dto, RedirectAttributes redirectAttributes) {
        Long savedPostId = postServiceV2.save(dto);

        redirectAttributes.addAttribute("id", savedPostId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/post/{id}";
    }

    //게시글 수정
    @GetMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id,Model model) {
        Post post = postServiceV2.findById(id);
        model.addAttribute("post", post);
        return "/board/editPost";
    }

    //게시글 수정버튼 누를 시 단일 게시글 페이지로 이동
    @PostMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id, @ModelAttribute PostDto updateParam) {
        Post pastPost = postServiceV2.findById(id);
        postServiceV2.update(pastPost,updateParam);
        return "redirect:/board/post/{id}";
    }


    //테스트용(초기 데이터)
    @PostConstruct
    public void init() {
        postServiceV2.save(new PostDto("김현수", "테스트제목1", "테스트내용1"));
        postServiceV2.save(new PostDto("김현승", "테스트제목2", "테스트내용2"));
    }

}
