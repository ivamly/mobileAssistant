package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.MeetingSummary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingSummaryRepository extends JpaRepository<MeetingSummary, UUID> {
}
