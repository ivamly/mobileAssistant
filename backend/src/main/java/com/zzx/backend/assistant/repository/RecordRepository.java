package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {

    @Query(value = """
            SELECT new com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs(r.title, r.meetingTime, s.summary) 
            FROM Summary s 
            JOIN s.record r
            """)
    List<RecordWithSummaryRs> findAllRecordsWithSummery();
}
