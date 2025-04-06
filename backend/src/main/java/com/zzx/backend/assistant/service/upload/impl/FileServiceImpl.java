package com.zzx.backend.assistant.service.upload.impl;

import com.zzx.backend.assistant.service.upload.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    @Override
    public Boolean uploadFile(MultipartFile file) {
        // todo upload file
        return null;
    }
}
