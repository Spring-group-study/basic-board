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
@RequestMapping("/board")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/main")
    public String posts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "/board/main";
    }

    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, Model model) {
        Post post = postRepository.findById(id);
        model.addAttribute("post", post);
        return "/board/post";
    }

    @GetMapping("/addPost")
    public String addPost(Model model) {
/*        th:object 를 적용하려면 먼저 해당 오브젝트 정보를 넘겨주어야 한다.
        등록 폼이기 때문에 데이터가 비어있는 빈 오브젝트를 만들어서 뷰에 전달하자.
        ->빈 생성자 만들어줘야함
        */
        model.addAttribute("post", new Post());
        return "/board/addPost";
    }

    @PostMapping("/addPost")    //dto적용할것
    public String addPost(@ModelAttribute Post post, RedirectAttributes redirectAttributes) {
        Post savedPost = postRepository.save(post);
        redirectAttributes.addAttribute("id", savedPost.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/board/post/{id}";
    }

    @GetMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id,Model model) {
        Post post = postRepository.findById(id);
        model.addAttribute("post", post);
        return "/board/editPost";
    }

    @PostMapping("/post/{id}/editPost")     //dto적용할것
    public String editPost(@PathVariable Long id, @ModelAttribute Post updateParam) {
        Post pastPost = postRepository.findById(updateParam.getId());
        postRepository.update(pastPost,updateParam);
        return "redirect:/board/post/{id}";
    }


    //테스터용(초기 데이터)
    @PostConstruct
    public void init() {
        postRepository.save(new Post("김현수", "테스트제목1", "테스트내용1"));
        postRepository.save(new Post("김현승", "테스트제목2", "테스트내용2"));
    }

}
