package com.zzx.backend.asr.service;

import com.zzx.backend.common.dto.MeetingRecordData;
import com.zzx.backend.common.dto.MeetingTranscriptionData;

public interface ASRService {
    MeetingTranscriptionData getMeetingTranscription(MeetingRecordData meetingRecordData);
}
