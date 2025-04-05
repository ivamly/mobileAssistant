package com.zzx.backend.assistant.mapper;

import com.zzx.backend.assistant.model.Transcription;
import com.zzx.backend.common.dto.TranscriptionData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TranscriptionMapper {

    Transcription toEntity(TranscriptionData data);

    @Mapping(target = "recordId", source = "record.id")
    TranscriptionData toData(Transcription entity);
}
