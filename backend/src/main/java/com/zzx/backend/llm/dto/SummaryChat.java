package com.zzx.backend.llm.dto;

import lombok.Data;

import java.util.List;

@Data
public class SummaryChat {
    private final List<SummaryMessage> messages;

    public SummaryChat(String content) {
        this.messages = List.of(new SummaryMessage(content));
    }
}
