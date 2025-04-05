package com.zzx.backend.common.dto;

import java.time.OffsetDateTime;

public class TaskData {

    private String id;
    private String title;
    private String description;
    private OffsetDateTime deadline;
    private RecordData record;
    private ParticipantData participant;
}
