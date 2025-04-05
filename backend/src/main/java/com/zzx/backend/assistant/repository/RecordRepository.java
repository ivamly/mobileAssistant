package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.dto.rs.RecordWithSummaryRs;
import com.zzx.backend.assistant.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {

    @Query(value = """
            SELECT r.title as title, r.meetingTime as date, s.summary as summary 
            FROM Record r 
            JOIN Summary s ON r.id = s.record.id
            """)
    List<RecordWithSummaryRs> findAllRecordsWithSummery();
}
