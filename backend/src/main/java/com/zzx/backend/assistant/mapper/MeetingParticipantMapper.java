package com.zzx.backend.assistant.mapper;

import com.zzx.backend.assistant.model.MeetingParticipant;
import com.zzx.backend.common.dto.MeetingParticipantData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MeetingParticipantMapper {

    MeetingParticipant toEntity(MeetingParticipantData data);

    MeetingParticipantData toData(MeetingParticipant entity);
}
