package com.zzx.mobileassistant.asr.dto;

import lombok.Data;

import java.util.List;

@Data
public class Meeting {
    private List<Line> lines;
}
