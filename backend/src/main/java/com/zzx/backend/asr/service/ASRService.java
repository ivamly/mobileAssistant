package com.zzx.backend.asr.service;

import com.zzx.backend.common.dto.RecordData;
import com.zzx.backend.common.dto.TranscriptionData;

public interface ASRService {

    TranscriptionData getMeetingTranscription(RecordData recordData);
}
