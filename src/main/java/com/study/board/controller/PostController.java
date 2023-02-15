package com.study.board.controller;

import com.study.board.domain.Post;
import com.study.board.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/board/posts")
public class PostController {

    private final PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping
    public String showAllPost(Model model) {
        List<Post> posts = postRepository.findAllPost();
//        List<PostDto> postDtoList = posts.stream().reduce(post -> new PostDto(post));
        model.addAttribute("posts", posts);
        return "board/posts";
    }

    @GetMapping("/{postId}")
    public String postDetail(@PathVariable Long postId, Model model) {
        Post post = postRepository.findPost(postId);
        model.addAttribute("post", post);
        return "board/post";
    }

    @GetMapping("add")
    public String addForm() {
        return "board/addForm";
    }

    @PostMapping("/add")
    public String addPost(Post post, RedirectAttributes redirectAttributes) {
        Post savedPost = postRepository.save(post);
        redirectAttributes.addAttribute("postId", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/posts/{postId}";
    }

    @GetMapping("/{postId}/edit")
    public String editForm(@PathVariable Long postId, Model model) {
        Post post = postRepository.findPost(postId);
        model.addAttribute("post", post);
        return "board/editForm";
    }

    @PostMapping("/{postId}/edit")
    public String editPost(@PathVariable Long postId, @ModelAttribute Post post) {
        postRepository.updatePost(postId, post);
        return "redirect:/board/posts/{postId}";
    }

    @GetMapping("/{postId}/delete")
    public String deletePost(@PathVariable Long postId) {
        postRepository.deletePost(postId);
        return "board/posts";
    }

    @PostConstruct
    private void init() {
        postRepository.save(new Post("AAA", "java", "This is java"));
        postRepository.save(new Post("BBB", "python", "This is python"));
        postRepository.save(new Post("CCC", "javascript", "This is javascript"));
        postRepository.save(new Post("DDD", "docker", "This is docker"));
    }

}
