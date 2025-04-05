package com.zzx.backend.llm.service;

import com.zzx.backend.common.dto.MeetingSummaryData;
import com.zzx.backend.common.dto.MeetingTaskData;
import com.zzx.backend.common.dto.MeetingTranscriptionData;

import java.util.List;

public interface LLMService {
    MeetingSummaryData getMeetingSummary(MeetingTranscriptionData meetingTranscriptionData);
    List<MeetingTaskData> searchMeetingTasks(MeetingSummaryData meetingSummary);
}
