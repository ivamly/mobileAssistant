package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.service.entity.TaskService;
import com.zzx.backend.common.dto.TaskData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Override
    public void saveAll(List<TaskData> tasks) {

    }
}
