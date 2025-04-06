package com.zzx.backend.llm.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class TaskRequest {
    private UUID uuid;
    private final TaskChat taskChat;
    private final String model = "GigaChat";
    private final Object temperature = null;
    private final Object top_p = null;
    private final Object n = null;
    private final Object max_tokens = null;
    private final Object repetition_penalty = null;

    public TaskRequest(String content) {
        this.taskChat = new TaskChat(content);
    }
}
