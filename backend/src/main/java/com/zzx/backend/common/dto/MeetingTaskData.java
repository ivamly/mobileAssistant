package com.zzx.backend.common.dto;

import java.time.OffsetDateTime;

public class MeetingTaskData {
    private String id;
    private String title;
    private String description;
    private OffsetDateTime deadline;
    private MeetingRecordData meetingRecord;
    private MeetingParticipantData meetingParticipant;
}
