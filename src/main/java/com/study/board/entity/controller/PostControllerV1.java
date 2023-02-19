package com.study.board.entity.controller;

import com.study.board.entity.Post;
import com.study.board.service.PostServiceV1;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/v1")
public class PostControllerV1 {
    private final PostServiceV1 postServiceV1;

    //생성자 주입 방식
    //injection 방식은 총 3가지가 있다
    //필드주입, 세터주입, 생성자주입
    //그 중 생성자주입을 선호하는 이유는 순환참조를 방지하기 위함이다.
    //순환참조는 컴포넌트 간 경계가 사라지고 명확한 구분점이 없어져 개발 및 유지보수에 어려움을 준다.
    public PostControllerV1(PostServiceV1 postServiceV1) {
        this.postServiceV1 = postServiceV1;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        List<Post> allPost = postServiceV1.getAllPost();
        model.addAttribute("form", allPost);
        return "/postListV1";
    }

    @GetMapping("/posts/new")
    public String createPost(Model model) {
        model.addAttribute("form",new Post());
        return "/newPostV1";
    }

    @PostMapping("/posts/new")
    public String createPost(@ModelAttribute("form") Post post) {
        postServiceV1.savePost(post);
        return "redirect:/home";
    }

    @GetMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable(value = "postId") Long id) {
        postServiceV1.deletePost(id);
        return "/home";
    }

    @GetMapping("/post/{postId}")
    public String post(Model model, @PathVariable(value = "postId") Long id) {
        Post onePost = postServiceV1.getOnePost(id);
        model.addAttribute("post", onePost);
        return "/postDetailV1";
    }

    @PostMapping("/post/update/")
    public String updatePost(@ModelAttribute Post post) {
        postServiceV1.updatePost(post.getId(), post.getAuthor(),post.getContent(),post.getTitle());
        return "redirect:/home";
    }

    /*@PostConstruct
    */
}
