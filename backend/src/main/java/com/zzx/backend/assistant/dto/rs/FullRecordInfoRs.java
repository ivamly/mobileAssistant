package com.zzx.backend.assistant.dto.rs;

import com.zzx.backend.common.dto.ParticipantData;
import com.zzx.backend.common.dto.TaskData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullRecordInfoRs {

    String recordTitle;
    String recordDate;
    List<ParticipantData> participants;
    String summary;
    List<TaskData> tasks;
    // todo Transcription
}
