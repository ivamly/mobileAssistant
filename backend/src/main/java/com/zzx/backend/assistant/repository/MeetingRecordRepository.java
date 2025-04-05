package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.MeetingRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingRecordRepository extends JpaRepository<MeetingRecord, UUID> {
}
