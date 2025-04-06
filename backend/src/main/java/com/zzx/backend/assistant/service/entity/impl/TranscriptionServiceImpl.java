package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.service.entity.TranscriptionService;
import com.zzx.backend.common.dto.TranscriptionData;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TranscriptionServiceImpl implements TranscriptionService {

    private final TranscriptionRepository repository;
    private final TranscriptionMapper mapper;

    @Override
    public TranscriptionData getById(String id) {
        return new TranscriptionData();
    }

    public TranscriptionData save(TranscriptionData transcriptionData) {
        return mapper.toData(
                repository.save(
                        mapper.toEntity(transcriptionData)
                )
        );
    }
}
