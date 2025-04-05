package com.zzx.backend.llm.service.impl;

import com.zzx.backend.asr.dto.Meeting;
import com.zzx.backend.llm.dto.MeetingResult;
import com.zzx.backend.llm.service.LLMService;
import org.springframework.stereotype.Service;

@Service
public class LLMServiceImpl implements LLMService {
    @Override
    public MeetingResult getMeeting(Meeting meeting) {
        return null;
    }
}
