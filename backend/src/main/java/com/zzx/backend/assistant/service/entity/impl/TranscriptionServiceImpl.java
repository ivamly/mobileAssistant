package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.service.entity.TranscriptionService;
import com.zzx.backend.common.dto.TranscriptionData;
import org.springframework.stereotype.Service;

@Service
public class TranscriptionServiceImpl implements TranscriptionService {
    @Override
    public TranscriptionData getById(String id) {
        return new TranscriptionData();
    }

    @Override
    public void save(TranscriptionData transcriptionData) {

    }
}
