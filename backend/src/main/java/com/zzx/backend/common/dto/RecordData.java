package com.zzx.backend.common.dto;

import com.zzx.backend.assistant.model.Summary;

import java.io.File;
import java.time.OffsetDateTime;
import java.util.Set;

public class RecordData {

    private String id;
    private File content; // audio file
    private OffsetDateTime meetingTime;
    private TranscriptionData transcription;
    private Summary summary;
    private Set<ParticipantData> participants;
}
