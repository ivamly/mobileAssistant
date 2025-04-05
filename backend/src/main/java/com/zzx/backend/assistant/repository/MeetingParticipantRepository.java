package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.MeetingParticipant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, UUID> {
}
