package com.zzx.backend.assistant.service.validator.impl;

import com.zzx.backend.assistant.exception.ValidateException;
import com.zzx.backend.assistant.service.validator.ValidateService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ValidateServiceImpl implements ValidateService {

    private final static long MAX_SIZE = 2147483648L;

    @Override
    public void validateFile(MultipartFile file) {

        if (file.getSize() > MAX_SIZE) {
            throw new ValidateException("Размер файла слишком велик");
        }

        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null || originalFilename.isEmpty()) {
            throw new ValidateException("Файл не найден");
        }

        // Допустимые форматы файлов
        List<String> allowedExtensions = List.of("mp4", "mp3", "wav");

        int indexOfLastDot = originalFilename.lastIndexOf('.') + 1;
        String format = originalFilename.substring(indexOfLastDot).toLowerCase();

        if (!allowedExtensions.contains(format)) {
            throw new ValidateException("Неверный формат файла");
        }
    }
}
