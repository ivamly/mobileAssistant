package com.zzx.backend.assistant.service.validator;

import org.springframework.web.multipart.MultipartFile;

public interface ValidateService {

    void validateFile(MultipartFile file);
}
