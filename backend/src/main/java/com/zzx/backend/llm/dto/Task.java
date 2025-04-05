package com.zzx.backend.llm.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {
    private String person;
    private String task;
    private LocalDateTime deadline;
}
