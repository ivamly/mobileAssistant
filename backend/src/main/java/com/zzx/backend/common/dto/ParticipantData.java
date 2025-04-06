package com.zzx.backend.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class ParticipantData {

    private String id;
    private String name;
    private String jobTitle;
    private String email;
    private String recordId;
}
