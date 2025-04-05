package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.MeetingTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingTaskRepository extends JpaRepository<MeetingTask, UUID> {
}
