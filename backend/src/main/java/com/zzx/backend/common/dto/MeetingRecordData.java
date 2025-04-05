package com.zzx.backend.common.dto;

import com.zzx.backend.assistant.model.MeetingSummary;

import java.io.File;
import java.time.OffsetDateTime;
import java.util.Set;

public class MeetingRecordData {
    private String id;
    private File content; // audio file
    private OffsetDateTime meetingTime;
    private MeetingTranscriptionData meetingTranscription;
    private MeetingSummary meetingSummary;
    private Set<MeetingParticipantData> meetingParticipants;
}
