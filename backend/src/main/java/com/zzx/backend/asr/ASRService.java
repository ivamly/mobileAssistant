package com.zzx.mobileassistant.asr;

import com.zzx.mobileassistant.asr.dto.Meeting;
import com.zzx.mobileassistant.assistant.dto.MeetingRecord;

public interface ASRService {
    Meeting getMeeting(MeetingRecord record);
}
