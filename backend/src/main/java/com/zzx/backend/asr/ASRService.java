package com.zzx.backend.asr;


import com.zzx.backend.asr.dto.Meeting;
import com.zzx.backend.assistant.dto.MeetingRecord;

public interface ASRService {
    Meeting getMeeting(MeetingRecord record);
}
