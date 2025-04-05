package com.zzx.mobileassistant.llm;

import com.zzx.mobileassistant.asr.dto.Meeting;
import com.zzx.mobileassistant.llm.dto.MeetingResult;

public interface LLMService {
    MeetingResult getMeeting(Meeting meeting);
}
