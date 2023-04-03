package com.study.board.controller;

import com.study.board.dto.PostDto;
import com.study.board.entity.MyPost;
import com.study.board.entity.Post;
import com.study.board.mapper.PostMapper;
import com.study.board.service.PostServiceV2;
import com.study.board.service.PostServiceV3;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/v3")
@RequiredArgsConstructor
public class PostControllerV3 {

    private final PostServiceV3 postServiceV3;

    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> allMyPost = postServiceV3.getAllPost();
        List<PostDto> collect = allMyPost.stream().map(a -> new PostDto(a.getId(), a.getAuthor(), a.getTitle(), a.getContent())).collect(Collectors.toList());
        model.addAttribute("form", collect);
        return "/postListV3";
    }

    @GetMapping("/posts/page")
    public String postByPage(Model model, HttpServletRequest request) {
        Page<Post> allPostPage = postServiceV3.getAllPostByPage(5);
        List<PostDto> collect = allPostPage.stream().map(a -> new PostDto(a.getId(), a.getAuthor(), a.getTitle(), a.getContent())).collect(Collectors.toList());
        int postCount = allPostPage.getTotalPages();

        model.addAttribute("pageData", postCount);
        model.addAttribute("form", collect);
        return "/postListPageV3";
    }

    @GetMapping("/posts/page/{pageNumber}")
    public String postByPage(Model model, @PathVariable(value = "pageNumber") int pageNumber) {
        Pageable page = Pageable.ofSize(5);
        Pageable pageable = page.withPage(pageNumber);
        int totalPages = postServiceV3.getAllPostByPage(5).getTotalPages();
        Page<Post> postByPage = postServiceV3.getPostByPage(pageable);
        model.addAttribute("form", postByPage);
        model.addAttribute("pageData",totalPages);
        return "/postListPageV3";
    }

    @GetMapping("/posts/new")
    public String createPost(Model model) {
        model.addAttribute("form", new Post());
        return "/newPostV3";
    }

    @PostMapping("/posts/new")
    public String createPost(@ModelAttribute("form") @Valid PostDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "/posts/new";
        }else {
        Post myPost = new Post();
        myPost.toEntity(dto.getAuthor(),dto.getTitle(),dto.getContent());
        postServiceV3.savePost(myPost);
        return "redirect:/home";}
    }

    @GetMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable(value = "postId") Long id) {
        postServiceV3.deletePost(id);
        return "redirect:/home";
    }

    @GetMapping("/post/{postId}")
    public String post(Model model, @PathVariable(value = "postId") Long id) {
        Post oneMyPost = postServiceV3.getOnePost(id);
        model.addAttribute("post", oneMyPost);
        return "/postDetailV3";
    }

    @PostMapping("/post/update")
    public String updatePost(@ModelAttribute(value = "post") PostDto dto) {
        postServiceV3.updatePost(dto.getId(), dto.getAuthor(), dto.getContent(), dto.getTitle());
        return "redirect:/home";
    }
}
