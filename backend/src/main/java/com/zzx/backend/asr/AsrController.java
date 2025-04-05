package com.zzx.backend.asr;

import com.zzx.backend.asr.service.ASRService;
import com.zzx.backend.common.dto.RecordData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("/asr")
@RequiredArgsConstructor
public class AsrController {

    private final ASRService asrService;

    @PostMapping("/test")
    public void post(@RequestParam MultipartFile file) throws IOException {
        RecordData recordData = new RecordData();
        recordData.setContent(getFile(file.getInputStream()));
        asrService.getTranscription(recordData);
    }

    private File getFile(InputStream inputStream) {
        File tempFile;
        try {
            tempFile = File.createTempFile("temp-audio-file", ".m4a");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            return tempFile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
