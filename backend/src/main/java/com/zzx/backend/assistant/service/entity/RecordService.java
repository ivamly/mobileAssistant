package com.zzx.backend.assistant.service.entity;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;

import java.util.Collection;

public interface RecordService {

    Collection<RecordWithSummaryRs> findAllRecordsWithSummery();
}
