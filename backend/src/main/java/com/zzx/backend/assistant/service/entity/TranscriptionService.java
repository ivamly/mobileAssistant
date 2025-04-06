package com.zzx.backend.assistant.service.entity;

import com.zzx.backend.common.dto.TranscriptionData;

public interface TranscriptionService {

    TranscriptionData getById(String id);

    void save(TranscriptionData transcriptionData);

}
