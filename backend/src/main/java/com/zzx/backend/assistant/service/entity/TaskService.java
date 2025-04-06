package com.zzx.backend.assistant.service.entity;

import com.zzx.backend.common.dto.TaskData;

import com.zzx.backend.common.dto.TaskData;

import java.util.List;

public interface TaskService {

    TaskData save(TaskData taskData);
    void saveAll(List<TaskData> tasks);
}
