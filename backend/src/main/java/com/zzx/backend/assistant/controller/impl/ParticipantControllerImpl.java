package com.zzx.backend.assistant.controller.impl;

import com.zzx.backend.assistant.controller.ParticipantController;
import com.zzx.backend.assistant.dto.rq.ParticipantNameUpdateRq;
import com.zzx.backend.assistant.service.entity.ParticipantService;
import com.zzx.backend.common.dto.ParticipantData;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ParticipantControllerImpl implements ParticipantController {

    private final ParticipantService participantService;

    @Override
    public ParticipantData updateParticipantName(ParticipantNameUpdateRq rq) {
        return participantService.updateName(rq);
    }
}
