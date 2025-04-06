package com.zzx.backend.assistant.service.upload;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    Boolean uploadFile(MultipartFile file);
}
