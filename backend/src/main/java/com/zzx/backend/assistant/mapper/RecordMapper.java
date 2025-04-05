package com.zzx.backend.assistant.mapper;

import com.zzx.backend.assistant.model.Record;
import com.zzx.backend.common.dto.RecordData;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface RecordMapper {

    Record toEntity(RecordData data);

    RecordData toData(Record entity);
}
