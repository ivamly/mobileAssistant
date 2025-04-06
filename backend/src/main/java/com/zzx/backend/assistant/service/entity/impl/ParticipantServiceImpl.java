package com.zzx.backend.assistant.service.entity.impl;

import com.zzx.backend.assistant.dto.rq.ParticipantNameUpdateRq;
import com.zzx.backend.assistant.error.ParticipantNotFoundException;
import com.zzx.backend.assistant.mapper.ParticipantMapper;
import com.zzx.backend.assistant.repository.ParticipantRepository;
import com.zzx.backend.assistant.service.entity.ParticipantService;
import com.zzx.backend.common.dto.ParticipantData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ParticipantServiceImpl implements ParticipantService {

    private final ParticipantRepository repository;
    private final ParticipantMapper mapper;

    @Override
    public ParticipantData getById(String id) throws ParticipantNotFoundException {
        return mapper.toData(
                repository.findById(UUID.fromString(id))
                        .orElseThrow(() -> new ParticipantNotFoundException(id))
        );
    }

    @Override
    public ParticipantData updateName(ParticipantNameUpdateRq rq) {

        ParticipantData participant = getById(rq.id());

        ParticipantData updatedParticipant = participant.toBuilder()
                .name(rq.name())
                .build();

        return mapper.toData(
                repository.save(
                        mapper.toEntity(updatedParticipant)
                )
        );
    }
}
