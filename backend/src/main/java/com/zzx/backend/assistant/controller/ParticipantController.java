package com.zzx.backend.assistant.controller;

import com.zzx.backend.assistant.dto.rq.ParticipantNameUpdateRq;
import com.zzx.backend.common.dto.ParticipantData;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/participants")
public interface ParticipantController {

    @PatchMapping("/updateName")
    ParticipantData updateParticipantName(@RequestBody ParticipantNameUpdateRq rq);
}
