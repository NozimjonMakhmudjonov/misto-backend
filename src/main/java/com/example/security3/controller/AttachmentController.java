package com.example.security3.controller;

import com.example.security3.constants.ApiConstants;
import com.example.security3.service.AttachmentService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;

@RestController
@RequestMapping(ApiConstants.FILE_API)
@RequiredArgsConstructor
@Slf4j
public class AttachmentController {
    private final AttachmentService attachmentService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Long upload(@RequestParam MultipartFile request){
        return attachmentService.upload(request);
    }
    @GetMapping("/{id}")
    public void download(@PathVariable Long id, HttpServletResponse response){
        attachmentService.download(id, response);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        try {
            attachmentService.delete(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
