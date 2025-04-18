package com.zzx.backend.assistant.mapper;

import com.zzx.backend.assistant.model.Summary;
import com.zzx.backend.common.dto.SummaryData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SummaryMapper {

    Summary toEntity(SummaryData data);

    @Mapping(target = "recordId", source = "record.id")
    SummaryData toData(Summary entity);
}
