package com.zzx.backend.assistant.controller.impl;

import com.zzx.backend.assistant.controller.FileController;
import com.zzx.backend.assistant.service.upload.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class FileControllerImpl implements FileController {

    private final FileService fileService;

    @Override
    public Boolean uploadFile(MultipartFile file) {
        return null;
    }
}
