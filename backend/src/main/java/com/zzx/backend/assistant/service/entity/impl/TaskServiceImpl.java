package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.mapper.TaskMapper;
import com.zzx.backend.assistant.repository.TaskRepository;
import com.zzx.backend.assistant.service.entity.TaskService;
import com.zzx.backend.common.dto.TaskData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository repository;
    private final TaskMapper mapper;

    @Override
    public TaskData save(TaskData taskData) {
        return mapper.toData(
                repository.save(
                        mapper.toEntity(taskData)
                )
        );
    }

    @Override
    public void saveAll(List<TaskData> tasks) {

    }
}
