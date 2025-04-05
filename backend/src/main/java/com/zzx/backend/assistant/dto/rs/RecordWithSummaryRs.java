package com.zzx.backend.assistant.dto.rs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordWithSummaryRs {

    private String title;
    private OffsetDateTime date;
    private String summary;
}
