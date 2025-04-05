package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.mapper.RecordMapper;
import com.zzx.backend.assistant.repository.RecordRepository;
import com.zzx.backend.assistant.service.entity.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

    private final RecordRepository repository;
    private final RecordMapper recordMapper;

    @Override
    public Collection<RecordWithSummaryRs> findAllRecordsWithSummery() {
        return repository.findAllRecordsWithSummery();
    }
}
