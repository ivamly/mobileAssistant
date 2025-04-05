package com.zzx.backend.assistant.mapper;

import com.zzx.backend.assistant.model.Transcription;
import com.zzx.backend.common.dto.TranscriptionData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TranscriptionMapper {

    Transcription toEntity(TranscriptionData data);

    TranscriptionData toData(Transcription entity);
}
