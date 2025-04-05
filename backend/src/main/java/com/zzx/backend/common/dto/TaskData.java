package com.zzx.backend.common.dto;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class TaskData {

    private String id;
    private String title;
    private String description;
    private OffsetDateTime deadline;
    private String recordId;
    private String participantId;
}
