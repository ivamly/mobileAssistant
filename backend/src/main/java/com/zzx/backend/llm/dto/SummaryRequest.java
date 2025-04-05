package com.zzx.backend.llm.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class SummaryRequest {
    private UUID uuid;
    private final SummaryChat summaryChat;
    private final String model = "GigaChat";
    private final int temperature = 1;
    private final int top_p = 1;
    private final int n = 1;
    private final int max_tokens = 1;
    private final int repetition_penalty = 1;

    public SummaryRequest(String content) {
        this.summaryChat = new SummaryChat(content);
    }
}
