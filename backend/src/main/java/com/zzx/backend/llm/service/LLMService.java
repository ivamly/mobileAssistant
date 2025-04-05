package com.zzx.backend.llm.service;

import com.zzx.backend.asr.dto.Meeting;
import com.zzx.backend.llm.dto.MeetingResult;

public interface LLMService {
    MeetingResult getMeeting(Meeting meeting);
}
