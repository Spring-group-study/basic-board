package com.study.board.controller;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.paging.Pagination;
import com.study.board.paging.PagingConst;
import com.study.board.service.PostServiceV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostControllerV4 {     //validation 구현

    private final PostServiceV3 postServiceV3;
    private final PagingConst pConst;

    private final int POST_CNT_PER_PAGE=10;     //한 페이지당 게시글 갯수
    private final int PAGE_CNT_PER_BLOCK = 5;   //한 블럭당 페이지 갯수



    //게시판 메인
    @GetMapping("/main/{page}")
    public String posts(@PathVariable int page, Model model) {
        //페이징 구현
        Pagination pagination = new Pagination(postServiceV3.postCnt(), page, POST_CNT_PER_PAGE, PAGE_CNT_PER_BLOCK);
        model.addAttribute("pagination", pagination);
        model.addAttribute("posts", postServiceV3.pagedFindAll(pagination));
        model.addAttribute("pagesInCurrentBlock",pagination.pagesInCurrentBlock());
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
    public String addPost(@Validated @ModelAttribute("post") PostDto postDto,
                          BindingResult bindingResult,          //BindingResult는 항상 ModelAttribute 바로뒤에 써야함
                          RedirectAttributes redirectAttributes) {

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
    public String editPost(@PathVariable Long id, @Validated @ModelAttribute("post") PostDto updateParam, BindingResult bindingResult) {
        Post pastPost = postServiceV3.findById(id);

        if (bindingResult.hasErrors()) {
            System.out.println("errors= "+bindingResult);
            return "/board/editPost";
        }

        postServiceV3.update(pastPost,updateParam);
        return "redirect:/board/post/{id}";
    }




}
