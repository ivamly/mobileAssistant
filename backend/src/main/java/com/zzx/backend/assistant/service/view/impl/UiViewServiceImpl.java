package com.zzx.backend.assistant.service.view.impl;

import com.zzx.backend.assistant.dto.rs.FullRecordInfoRs;
import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.error.UiViewException;
import com.zzx.backend.assistant.service.entity.ParticipantService;
import com.zzx.backend.assistant.service.entity.RecordService;
import com.zzx.backend.assistant.service.entity.SummaryService;
import com.zzx.backend.assistant.service.entity.TaskService;
import com.zzx.backend.assistant.service.view.UiViewService;
import com.zzx.backend.common.dto.RecordData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class UiViewServiceImpl implements UiViewService {

    private final RecordService recordService;
    private final SummaryService summaryService;
    private final ParticipantService participantService;
    private final TaskService taskService;

    @Override
    public Collection<RecordWithSummaryRs> findAllRecordWithSummary() {
        try {
            return recordService.findAllRecordsWithSummery();
        } catch (Exception ex) {
            log.error("error from findRecordWithSummary - " + ex.getMessage());
            throw new UiViewException(ex.getMessage());
        }
    }

    @Override
    public FullRecordInfoRs getFullRecordInfo(String id) {

        RecordData recordData = recordService.getById(id);

        // todo
        return null;
    }
}
