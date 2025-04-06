package com.zzx.backend.common.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ASRAggregateData {
    private Set<ParticipantData> participants;
    private TranscriptionData transcription;
}
