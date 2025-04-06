package com.zzx.backend.common.dto;

import lombok.Builder;
import lombok.Data;

import java.io.File;
import java.time.OffsetDateTime;

@Data
@Builder
public class RecordData {

    private String id;
    private String title;
    private File content; // audio file
    private OffsetDateTime meetingTime;
}
