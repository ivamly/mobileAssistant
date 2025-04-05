package com.zzx.mobileassistant.llm.dto;

import lombok.Data;

@Data
public class MeetingResult {
    private CompleteMeeting completeMeeting;
    private MeetingSummary meetingSummary;
    private MeetingTasks meetingTasks;
}
