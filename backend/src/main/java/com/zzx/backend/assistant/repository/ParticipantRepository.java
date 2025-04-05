package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
}
