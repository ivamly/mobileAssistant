package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.exception.RecordNotFoundException;
import com.zzx.backend.assistant.mapper.RecordMapper;
import com.zzx.backend.assistant.repository.RecordRepository;
import com.zzx.backend.assistant.service.entity.RecordService;
import com.zzx.backend.common.dto.RecordData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository repository;
    private final RecordMapper mapper;

    @Override
    public RecordData getById(String id) {
        return mapper.toData(
                repository.findById(UUID.fromString(id))
                        .orElseThrow(() -> new RecordNotFoundException(id))
        );
    }

    @Override
    public Collection<RecordWithSummaryRs> findAllRecordsWithSummery() {
        return repository.findAllRecordsWithSummery();
    }
}
