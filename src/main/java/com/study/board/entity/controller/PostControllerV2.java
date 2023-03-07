package com.study.board.entity.controller;

import com.study.board.dto.PostDto;
import com.study.board.entity.Post;
import com.study.board.mapper.PostMapper;
import com.study.board.service.PostServiceV2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/v2")
public class PostControllerV2 {
    private final PostServiceV2 postServiceV2;
    private final PostMapper postMapper;

    //생성자 주입 방식
    //injection 방식은 총 3가지가 있다
    //필드주입, 세터주입, 생성자주입
    //그 중 생성자주입을 선호하는 이유는 순환참조를 방지하기 위함이다.
    //순환참조는 컴포넌트 간 경계가 사라지고 명확한 구분점이 없어져 개발 및 유지보수에 어려움을 준다.
    public PostControllerV2(PostServiceV2 postServiceV2, PostMapper postMapper) {
        this.postMapper = postMapper;
        this.postServiceV2 = postServiceV2;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> allPost = postServiceV2.getAllPost();
        List<PostDto> collect = allPost.stream().map(a -> new PostDto(a.getId(), a.getAuthor(), a.getTitle(), a.getContent())).collect(Collectors.toList());
        model.addAttribute("form", collect);
        return "/postListV2";
    }

    @GetMapping("/posts/page")
    public String postByPage(Model model) {
        List<Post> allPostPage = postServiceV2.getAllPostByPage(0);
        List<PostDto> collect = allPostPage.stream().map(a -> new PostDto(a.getId(), a.getAuthor(), a.getTitle(), a.getContent())).collect(Collectors.toList());
        int postCount = postServiceV2.getAllPostCount();
        model.addAttribute("pageData", postCount);

        model.addAttribute("form", collect);
        return "/postListPageV2";
    }

    @GetMapping("/posts/page/{pageNumber}")
    public String postByPage(Model model, @PathVariable(value = "pageNumber") int pageNumber) {
        List<Post> allPostByPage = postServiceV2.getAllPostByPage(pageNumber);
        model.addAttribute("form", allPostByPage);
        return "/postListPageV2";
    }

    @GetMapping("/posts/new")
    public String createPost(Model model) {
        model.addAttribute("form", new Post());
        return "/newPostV2";
    }

    @PostMapping("/posts/new")
    public String createPost(@ModelAttribute("form") @Valid PostDto dto, BindingResult result) {
        if (result.hasErrors()) {
            return "/posts/new";
        }else {
        Post post = new Post(dto.getId(), dto.getAuthor(), dto.getTitle(), dto.getContent());
        postServiceV2.savePost(post);
        return "redirect:/home";}
    }

    @GetMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable(value = "postId") Long id) {
        postServiceV2.deletePost(id);
        return "redirect:/home";
    }

    @GetMapping("/post/{postId}")
    public String post(Model model, @PathVariable(value = "postId") Long id) {
        Post onePost = postServiceV2.getOnePost(id);
        model.addAttribute("post", onePost);
        return "/postDetailV2";
    }

    @PostMapping("/post/update")
    public String updatePost(@ModelAttribute Post post) {
        postServiceV2.updatePost(post.getId(), post.getAuthor(), post.getContent(), post.getTitle());
        return "redirect:/home";
    }
}
