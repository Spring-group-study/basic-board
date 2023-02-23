package com.study.board.controller;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.service.PostServiceV3;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/board")
public class PostControllerV3 {     //validation 구현

    private final PostServiceV3 postServiceV3;

    public PostControllerV3(PostServiceV3 postServiceV3) {
        this.postServiceV3 = postServiceV3;
    }


    //게시판 메인
    @GetMapping("/main")
    public String posts(Model model) {
        List<Post> posts = postServiceV3.findAll();
        model.addAttribute("posts", posts);
        return "/board/main";
    }

    //단일 게시글
    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        Post post = postServiceV3.findById(id);
        model.addAttribute("post", post);
        return "/board/post";
    }

    //게시글 등록
    @GetMapping("/addPost")
    public String addPost(Model model) {
        model.addAttribute("post", new PostDto());
        return "/board/addPost";
    }


    @PostMapping("/addPost")
    public String addPost(@Validated @ModelAttribute PostDto postDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        //validation
        if (bindingResult.hasErrors()) {
            System.out.println("errors = "+ bindingResult);
            return "/board/addPost";
        }

        //성공로직
        Long savedPostId = postServiceV3.save(postDto);

        redirectAttributes.addAttribute("id", savedPostId);
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/post/{id}";
    }

    //게시글 수정
    @GetMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id,Model model) {
        Post post = postServiceV3.findById(id);
        model.addAttribute("post", post);
        return "/board/editPost";
    }

    //게시글 수정버튼 누를 시 단일 게시글 페이지로 이동
    @PostMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id, @Validated @ModelAttribute PostDto updateParam, BindingResult bindingResult) {
        Post pastPost = postServiceV3.findById(id);

        if (bindingResult.hasErrors()) {
            return "/board/editPost";
        }

        postServiceV3.update(pastPost,updateParam);
        return "redirect:/board/post/{id}";
    }


    //테스트용(초기 데이터)
    //@PostConstruct가 schema.sql이랑 문제가 있는거같음... 일단 이건 주석처리 하고 schema.sql에 insert문 10개 추가함
    /*@PostConstruct
    public void init() {
        for (int i = 1; i <= 20; i++) {
            String author = "author" + i;
            String title = "title" + i;
            String content = "content" + i;
            postServiceV3.save(new PostDto(author, title, content));
        }
    }*/

}
