package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.mapper.SummaryMapper;
import com.zzx.backend.assistant.repository.SummaryRepository;
import com.zzx.backend.assistant.service.entity.SummaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SummaryServiceImpl implements SummaryService {

    private final SummaryRepository repository;
    private final SummaryMapper mapper;

    @Override
    public SummaryData save(SummaryData summaryData) {
        return mapper.toData(
                repository.save(
                        mapper.toEntity(summaryData)
                )
        );
    }
}
