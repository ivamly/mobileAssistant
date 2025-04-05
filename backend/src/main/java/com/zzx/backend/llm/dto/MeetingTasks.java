package com.zzx.backend.llm.dto;

import lombok.Data;

import java.util.List;

@Data
public class MeetingTasks {
    private List<Task> tasks;
}
