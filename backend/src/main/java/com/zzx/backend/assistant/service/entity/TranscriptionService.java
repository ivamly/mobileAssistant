package com.zzx.backend.assistant.service.entity;

import com.zzx.backend.common.dto.TranscriptionData;

public interface TranscriptionService {

    TranscriptionData getById(String id);

    TranscriptionData save(TranscriptionData transcriptionData);
}
