package com.zzx.backend.asr.dto;

import lombok.Data;

@Data
public class Audio {
    private final String model = "whisper-fast";
    private final String task = "diar";
    private final int sigm_tr = 50; // todo
    private final String promt = null;
    private final boolean vad_filter = true;
    private final boolean rnoise = true;
}
