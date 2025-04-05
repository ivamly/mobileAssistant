package com.zzx.backend.asr.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class Request {
    private UUID uuid;
    private final Audio audio = new Audio();
}
