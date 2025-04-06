package com.zzx.backend.assistant.facade.impl;

import com.zzx.backend.asr.service.ASRService;
import com.zzx.backend.assistant.service.entity.ParticipantService;
import com.zzx.backend.assistant.service.entity.SummaryService;
import com.zzx.backend.assistant.service.entity.TaskService;
import com.zzx.backend.assistant.service.entity.TranscriptionService;
import com.zzx.backend.common.dto.ASRAggregateData;
import com.zzx.backend.common.dto.RecordData;
import com.zzx.backend.common.dto.SummaryData;
import com.zzx.backend.common.dto.TaskData;
import com.zzx.backend.llm.service.LLMService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordProcessorFacade implements com.zzx.backend.assistant.facade.RecordProcessorFacade {

    private final ASRService asrService;
    private final LLMService llmService;
    private final ParticipantService participantService;
    private final TranscriptionService transcriptionService;
    private final SummaryService summaryService;
    private final TaskService taskService;

    @Async
    @Override
    public void process(RecordData meeting) {
        ASRAggregateData asrAggregateData = asrService.getTranscription(meeting);
        participantService.saveAll(asrAggregateData.getParticipants());
        transcriptionService.save(asrAggregateData.getTranscription());
        SummaryData summary = llmService.getSummary(transcriptionService.getById(asrAggregateData.getTranscription().getId()));
        summaryService.save(summary);
        List<TaskData> taskData = llmService.searchTasks(asrAggregateData.getTranscription());
        taskService.saveAll(taskData);
    }
}
