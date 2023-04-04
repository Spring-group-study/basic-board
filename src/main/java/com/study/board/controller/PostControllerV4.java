package com.study.board.controller;

import com.study.board.dto.PostDtoV2;
import com.study.board.entity.MemberV2;
import com.study.board.entity.PostV2;
import com.study.board.file.FileStore;
import com.study.board.jpapaging.JpaPagination;
import com.study.board.jpapaging.JpaPagingConst;
import com.study.board.mapper.MapperV5;
import com.study.board.repository.PostJpaRepository;
import com.study.board.service.PostServiceV4;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostControllerV4 {     //validation 구현

    private final PostServiceV4 postService;
    private final MapperV5 mapper;
    private final FileStore fileStore;

    //게시판 메인
    @GetMapping("/main/{page}")
    public String posts(@PathVariable int page, Model model) {
        Pageable pageable = PageRequest.of(page-1, JpaPagingConst.POST_CNT_PER_PAGE,Sort.by("postId").descending());

        model.addAttribute("pagination", new JpaPagination(postService.postCnt(), page));
        model.addAttribute("posts", postService.findAllByPage(pageable));
        model.addAttribute("pagesInCurrentBlock", new JpaPagination(postService.postCnt(), page).pagesInCurrentBlock());
        model.addAttribute("keyword", new String());

        return "/board/main";
    }

    @PostMapping("/main/{page}")
    public String posts(@ModelAttribute("keyword") String keyword, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("search", keyword);
        return "redirect:/board/searchMain/1";
    }

    @GetMapping("/searchMain/{page}")
    public String search(@PathVariable int page,@RequestParam("search") String keyword, Model model) {
        List<PostV2> posts = postService.findByKeyword(keyword);

        /*Pageable pageable = PageRequest.of(page - 1, JpaPagingConst.POST_CNT_PER_PAGE, Sort.by("postId").descending());
        JpaPagination pagination = new JpaPagination(posts.size(), page);*/

        model.addAttribute("posts", posts);
        /*model.addAttribute("pagination", pagination);
        model.addAttribute("pagesInCurrentBlock", pagination.pagesInCurrentBlock());*/
        model.addAttribute("keyword",new String());
        return "board/searchMain";
    }

    @PostMapping("/searchMain/{page}")
    public String search(@ModelAttribute("keyword") String keyword, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("search", keyword);
        return "redirect:/board/searchMain/1";
    }

    //단일 게시글
    @GetMapping("/post/{id}")
    public String post(@PathVariable Long id, HttpServletRequest request, Model model) {
        PostV2 post = postService.findById(id);
//        Boolean isAuthor = postService.isAuthor(id, request);

        model.addAttribute("isAuthor", mapper.getMemberFromSession(request).getMemberId());   //true일 경우 html에 수정 버튼 나타나도록
        model.addAttribute("post", post);
        return "/board/post";
    }

    //게시글 등록
    @GetMapping("/addPost")
    public String addPost(HttpServletRequest request, Model model) {
        MemberV2 loginMember = mapper.getMemberFromSession(request);

        PostDtoV2 post = new PostDtoV2(loginMember.getNickname());
        model.addAttribute("post", post);
        return "/board/addPost";
    }


    @PostMapping("/addPost")
    public String addPost(@Validated @ModelAttribute("post") PostDtoV2 postDto,
                          BindingResult bindingResult,          //BindingResult는 항상 ModelAttribute 바로뒤에 써야함
                          HttpServletRequest request,
                          RedirectAttributes redirectAttributes) throws ServletException, IOException {
        if (bindingResult.hasErrors()) {
            return "board/addPost";
        }
        Long savedPostId = postService.save(postDto, request);
        PostV2 post = postService.findById(savedPostId);
        postService.saveAttachFile(post,fileStore.storeFile(postDto.getAttachFile()));
        postService.saveImageFiles(post,fileStore.storeFiles(postDto.getImageFiles()));
        redirectAttributes.addAttribute("id", savedPostId);
        return "redirect:/board/post/{id}";
    }

    //이미지파일 업로드
    @ResponseBody
    @GetMapping("/post/images/{filename}")
    public Resource downloadImageFiles(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    //첨부파일 다운로드 + uploadFileName 한글이면 오류터짐
    @GetMapping("/post/{id}/attach")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long id) throws MalformedURLException {
        PostV2 post = postService.findById(id);
        String storeFileName = post.getAttachFile().getStoreFileName();
        String uploadFileName = post.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));
        String contentDisposition="attachment; filename=\""+uploadFileName+"\"";

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition).body(urlResource);
    }

    //게시글 수정 -> 접근 validation 필요
    @GetMapping("/post/{id}/editPost")
    public String editPost(@PathVariable Long id, Model model) {
        PostV2 post = postService.findById(id);
        PostDtoV2 dto = mapper.postEntityToDto(post);
        model.addAttribute("post", dto);
        model.addAttribute("postId", post.getPostId());
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

    @GetMapping("/post/{id}/delete")
    public String delete(@PathVariable Long id,HttpServletRequest request) {
        postService.delete(id);
        return "board/delete";
    }
}
