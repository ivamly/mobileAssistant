package com.zzx.backend.asr.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class AsrRequest {
    private UUID uuid;
    private final Audio audio = new Audio();

    @Data
    public static class Audio {
        private final String model = "whisper-fast";
        private final String task = "diar";
        private final int sigm_tr = 25; // todo
        private final String promt = null;
        private final boolean vad_filter = true;
        private final boolean rnoise = true;
    }
}
