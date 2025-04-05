package com.zzx.backend.llm;

import com.zzx.backend.common.dto.TranscriptionData;
import com.zzx.backend.llm.service.LLMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/llm")
@RequiredArgsConstructor
public class LlmController {

    private final LLMService llmService;

    @GetMapping("/test")
    public void test(@RequestBody String text) {
        TranscriptionData transcriptionData = new TranscriptionData();
        transcriptionData.setTranscription(text);
        llmService.getSummary(new TranscriptionData());
    }

    @GetMapping("/test2")
    public void test2(@RequestBody String text) {
        TranscriptionData transcriptionData = new TranscriptionData();
        transcriptionData.setTranscription(text);
        llmService.searchTasks(new TranscriptionData());
    }
}
