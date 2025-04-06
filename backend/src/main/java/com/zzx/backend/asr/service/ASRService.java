package com.zzx.backend.asr.service;

import com.zzx.backend.common.dto.ASRAggregateData;
import com.zzx.backend.common.dto.RecordData;

public interface ASRService {

    ASRAggregateData getTranscription(RecordData recordData);
}
