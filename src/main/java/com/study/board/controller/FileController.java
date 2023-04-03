package com.study.board.controller;

import com.study.board.config.FileStore;
import com.study.board.dto.FileDto;
import com.study.board.entity.File;
import com.study.board.entity.UploadFile;
import com.study.board.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileController {
    private final FileRepository fileRepository;
    private final FileStore fileStore;

    @GetMapping("/files/new")
    public String newFile(@ModelAttribute FileDto dto) {
        return "file-form";
    }

    @PostMapping("/files/new")
    public String saveFile(@ModelAttribute FileDto dto, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile uploadFile = fileStore.storeFile(dto.getAttachFile());
        List<UploadFile> storeImageFiles = fileStore.storeFiles(dto.getImageFiles());

        File file =new File();
        file.toFile(dto.getFileName(), uploadFile, storeImageFiles);

        fileRepository.save(file);

        redirectAttributes.addAttribute("fileId", file.getId());

        return "redirect:/files/{fileId}";
    }

    @GetMapping("/files/{id}")
    public String items(@PathVariable Long id, Model model) {
        File file = fileRepository.findById(id).get();
        model.addAttribute("file", file);
        return "file-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFileDir(filename));
    }

    @GetMapping("/attach/{fileId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long fileId) throws MalformedURLException {
        File file = fileRepository.findById(fileId).get();
        String storeFileName = file.getAttachFile().getStoreFileName();
        String uploadFileName = file.getAttachFile().getUploadFileName();

        UrlResource urlResource = new UrlResource("file:" + fileStore.getFileDir(storeFileName));

        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" +encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(urlResource);
    }
}
