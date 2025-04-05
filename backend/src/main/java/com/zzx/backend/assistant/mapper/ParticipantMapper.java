package com.zzx.backend.assistant.mapper;

import com.zzx.backend.assistant.model.Participant;
import com.zzx.backend.common.dto.ParticipantData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ParticipantMapper {

    Participant toEntity(ParticipantData data);

    @Mapping(target = "recordId", source = "record.id")
    ParticipantData toData(Participant entity);
}
