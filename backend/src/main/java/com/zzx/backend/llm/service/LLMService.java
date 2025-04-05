package com.zzx.backend.llm.service;

import com.zzx.backend.common.dto.SummaryData;
import com.zzx.backend.common.dto.TaskData;
import com.zzx.backend.common.dto.TranscriptionData;

import java.util.List;

public interface LLMService {

    SummaryData getMeetingSummary(TranscriptionData transcriptionData);

    List<TaskData> searchMeetingTasks(SummaryData meetingSummary);
}
