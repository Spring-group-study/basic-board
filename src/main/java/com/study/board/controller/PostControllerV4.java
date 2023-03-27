package com.study.board.controller;

import com.study.board.dto.PostDto;
import com.study.board.dto.PostDtoV2;
import com.study.board.entity.Post;
import com.study.board.entity.PostV2;
import com.study.board.jpapaging.JpaPagination;
import com.study.board.mapper.MapperV5;
import com.study.board.paging.Pagination;
import com.study.board.paging.PagingConst;
import com.study.board.service.PostServiceV3;
import com.study.board.service.PostServiceV4;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostControllerV4 {     //validation 구현

    private final PostServiceV4 postService;
    private final MapperV5 mapper;

    //게시판 메인
    @GetMapping("/main/{page}")
    public String posts(@PathVariable int page, Model model) {
        List<PostV2> postList = postService.findAllByPage(page);
        JpaPagination pagination = new JpaPagination(postService.postCnt(), page);
        List<Integer> pagesInCurrentBlock = pagination.pagesInCurrentBlock();


        model.addAttribute("pagination", pagination);
        model.addAttribute("posts", postList);
        model.addAttribute("pagesInCurrentBlock", pagesInCurrentBlock);

        return "/board/main";
    }

    //단일 게시글
    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        PostV2 post = postService.findById(id);
        model.addAttribute("post", post);
        return "/board/post";
    }

    //게시글 등록
    @GetMapping("/addPost")
    public String addPost(Model model) {
        PostDtoV2 post = new PostDtoV2();
        model.addAttribute("post", post);
        return "/board/addPost";
    }


    @PostMapping("/addPost")
    public String addPost(@Validated @ModelAttribute("post") PostDtoV2 postDto,
                          BindingResult bindingResult,          //BindingResult는 항상 ModelAttribute 바로뒤에 써야함
                          RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "board/addPost";
        }
        Long savedPostId = postService.save(postDto);
        redirectAttributes.addAttribute("id", savedPostId);
        return "redirect:/board/post/{id}";
    }

    //게시글 수정
    @GetMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id, Model model) {
        PostV2 post = postService.findById(id);
        PostDtoV2 dto = mapper.postEntityToDto(post);
        model.addAttribute("post", dto);
        return "/board/editPost";
    }

    //게시글 수정버튼 누를 시 단일 게시글 페이지로 이동
    @PostMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id, @Validated @ModelAttribute("post") PostDtoV2 updateParam,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "board/editPost";
        }
        PostV2 post = postService.findById(id);
        postService.update(post, updateParam);
        return "redirect:/board/post/{id}";
    }


}
