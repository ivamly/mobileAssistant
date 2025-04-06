package com.zzx.backend.assistant.service.upload.impl;

import com.zzx.backend.assistant.exception.FileParseException;
import com.zzx.backend.assistant.facade.RecordProcessorFacade;
import com.zzx.backend.assistant.service.entity.RecordService;
import com.zzx.backend.assistant.service.upload.FileService;
import com.zzx.backend.assistant.service.validator.ValidateService;
import com.zzx.backend.common.dto.RecordData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.time.OffsetDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final RecordProcessorFacade recordProcessorFacade;
    private final ValidateService validateService;
    private final RecordService recordService;

    @Override
    public Boolean uploadFile(MultipartFile file) {

        File content = null;

        validateService.validateFile(file);

        try {
            content = File.createTempFile("tmp", "m4a");

            FileOutputStream outputStream = new FileOutputStream(content);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = file.getInputStream().read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            content.deleteOnExit();
            outputStream.close();
        } catch (Exception e) {
            log.error("Ошибка парсинга файла - " + e.getMessage());
            throw new FileParseException(e.getMessage());
        }

        RecordData recordData = createRecordData(content);
        recordProcessorFacade.process(recordData);

        recordService.save(recordData);

        return Boolean.TRUE;
    }

    private RecordData createRecordData(File content) {
        return RecordData.builder()
                .id(UUID.randomUUID().toString())
                .meetingTime(OffsetDateTime.now())
                .content(content)
                .build();
    }
}
