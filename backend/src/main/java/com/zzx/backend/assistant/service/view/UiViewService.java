package com.zzx.backend.assistant.service.view;

import com.zzx.backend.assistant.dto.rs.FullRecordInfoRs;
import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;

import java.util.Collection;

public interface UiViewService {

    Collection<RecordWithSummaryRs> findAllRecordWithSummary();
    FullRecordInfoRs getFullRecordInfo(String id);
}
