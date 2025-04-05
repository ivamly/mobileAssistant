package com.zzx.backend.assistant.facade;

import com.zzx.backend.common.dto.MeetingRecordData;

public interface MeetingProcessFacade {
    void process(MeetingRecordData meeting);
}
