package com.zzx.backend.assistant.repository;

import com.zzx.backend.assistant.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {

}
