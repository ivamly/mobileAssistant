package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecordRepository extends JpaRepository<Record, UUID> {

}
