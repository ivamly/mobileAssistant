package com.zzx.backend.assistant.controller;

import com.zzx.backend.assistant.dto.rs.FullRecordInfoRs;
import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@RequestMapping("/api/v1/records")
public interface RecordController {

    @GetMapping
    Collection<RecordWithSummaryRs> findAllRecordWithSummary();

    @GetMapping("/{id}")
    FullRecordInfoRs getFullRecordInfo(@PathVariable String id);
}
