package com.zzx.backend.assistant.service.entity;

import com.zzx.backend.assistant.dto.rq.ParticipantNameUpdateRq;
import com.zzx.backend.assistant.exception.ParticipantNotFoundException;
import com.zzx.backend.common.dto.ParticipantData;

import java.util.Collection;

public interface ParticipantService {

    ParticipantData getById(String id) throws ParticipantNotFoundException;

    ParticipantData updateName(ParticipantNameUpdateRq rq) throws ParticipantNotFoundException;

    void saveAll(Collection<ParticipantData> participantsData);
}
