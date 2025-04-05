package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.MeetingTranscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingTranscriptionRepository extends JpaRepository<MeetingTranscription, UUID> {
}
