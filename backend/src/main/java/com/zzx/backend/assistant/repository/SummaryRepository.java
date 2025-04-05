package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.Summary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SummaryRepository extends JpaRepository<Summary, UUID> {
}
