package com.zzx.backend.assistant.service.entity;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.common.dto.RecordData;

import java.util.Collection;

public interface RecordService {

    RecordData getById(String id);

    RecordData save(RecordData recordData);

    Collection<RecordWithSummaryRs> findAllRecordsWithSummery();
}
