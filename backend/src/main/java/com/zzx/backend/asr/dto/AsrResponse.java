package com.zzx.backend.asr.dto;

import lombok.Data;

@Data
public class AsrResponse {
    private String uuid;
    private Message message;

    @Data
    public static class Message {
        private int id;
        private String role;
        private String content;
        private String type;
        private long created;
        private String serviceType;
    }
}