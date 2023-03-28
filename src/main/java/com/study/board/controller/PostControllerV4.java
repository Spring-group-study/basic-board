package com.study.board.controller;

import com.study.board.dto.PostDto;
import com.study.board.dto.PostLoginDto;
import com.study.board.entity.Member;
import com.study.board.entity.Post;
import com.study.board.service.PostServiceV4;
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
@RequestMapping("/v4")
@RequiredArgsConstructor
public class PostControllerV4 {

    private final PostServiceV4 postServiceV4;

    @GetMapping("/posts/page")
    public String postByPage(Model model, HttpServletRequest request) {
        Member loginMember = (Member) request.getSession().getAttribute(MemberId.MEMBER_ID);
        String memberId = loginMember.getMemberId();

        Page<Post> allPostPage = postServiceV4.getAllPostByMemberIdPage(5, memberId);
        List<PostDto> collect = allPostPage.stream().map(a -> new PostDto(a.getId(), a.getAuthor(), a.getTitle(), a.getContent())).collect(Collectors.toList());
        int postCount = allPostPage.getTotalPages();

        model.addAttribute("pageData", postCount);
        model.addAttribute("form", collect);
        return "/postListPageV4";
    }

    @GetMapping("/posts/page/{pageNumber}")
    public String postByPage(Model model, @PathVariable(value = "pageNumber") int pageNumber, HttpServletRequest request) {
        Pageable page = Pageable.ofSize(5);
        Pageable pageable = page.withPage(pageNumber);

        Member loginMember = (Member) request.getSession().getAttribute(MemberId.MEMBER_ID);
        String memberId = loginMember.getMemberId();


        int totalPages = postServiceV4.getAllPostByMemberIdPage(5, memberId).getTotalPages();
        Page<Post> postByPage = postServiceV4.getPostByPage(pageable);
        model.addAttribute("form", postByPage);
        model.addAttribute("pageData",totalPages);
        return "/postListPageV4";
    }

    @GetMapping("/posts/new")
    public String createPost(Model model, HttpServletRequest request) {
        Member loginMember = (Member) request.getSession().getAttribute(MemberId.MEMBER_ID);
        PostLoginDto post = new PostLoginDto();
        post.setAuthor(loginMember.getMemberId());
        model.addAttribute("form", post);
        return "/newPostV4";
    }

    @PostMapping("/posts/new")
    public String createPost(@ModelAttribute("form") @Valid PostDto dto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "/posts/new";
        }else {
            Member loginMember = (Member) request.getSession().getAttribute(MemberId.MEMBER_ID);
            Long memberId = loginMember.getId();
            postServiceV4.savePostWithMemberId(dto,memberId);
        return "redirect:/home";}
    }

    @GetMapping("/post/delete/{postId}")
    public String deletePost(@PathVariable(value = "postId") Long id) {
        postServiceV4.deletePost(id);
        return "redirect:/home";
    }

    @GetMapping("/post/{postId}")
    public String post(Model model, @PathVariable(value = "postId") Long id) {
        Post oneMyPost = postServiceV4.getOnePost(id);
        model.addAttribute("post", oneMyPost);
        return "/postDetailV4";
    }

    @PostMapping("/post/update")
    public String updatePost(@ModelAttribute(value = "post") PostDto dto) {
        postServiceV4.updatePost(dto.getId(), dto.getAuthor(), dto.getContent(), dto.getTitle());
        return "redirect:/home";
    }
}
