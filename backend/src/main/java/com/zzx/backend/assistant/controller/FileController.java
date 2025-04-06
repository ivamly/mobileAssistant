package com.zzx.backend.assistant.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/files")
public interface FileController {

    @PostMapping("/upload")
    Boolean uploadFile(MultipartFile file);
}
