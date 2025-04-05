package com.zzx.backend.llm.dto;

import lombok.Data;

import java.util.List;

@Data
public class TaskChat {
    private final List<TaskMessage> messages;

    public TaskChat(String content) {
        this.messages = List.of(new TaskMessage(content));
    }
}
