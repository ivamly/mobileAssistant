package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.Transcription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TranscriptionRepository extends JpaRepository<Transcription, UUID> {
}
