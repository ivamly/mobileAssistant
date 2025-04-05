package com.zzx.backend.assistant.service.view.impl;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.error.UiError;
import com.zzx.backend.assistant.service.entity.RecordService;
import com.zzx.backend.assistant.service.view.UiViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UiViewServiceImpl implements UiViewService {

    private final RecordService recordService;

    @Override
    public Collection<RecordWithSummaryRs> getRecordWithSummary() {
        try {
            return recordService.findAllRecordsWithSummery();
        } catch (Exception ex) {
            throw new UiError(ex.getMessage());
        }
    }
}
