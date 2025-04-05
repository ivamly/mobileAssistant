package com.zzx.backend.asr.dto;

import lombok.Data;

import java.util.List;

@Data
public class Meeting {
    private List<Line> lines;
}
