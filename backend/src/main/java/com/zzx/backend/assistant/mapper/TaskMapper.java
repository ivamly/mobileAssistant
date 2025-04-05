package com.zzx.backend.assistant.mapper;

import com.zzx.backend.assistant.model.Task;
import com.zzx.backend.common.dto.TaskData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {

    Task toEntity(TaskData data);

    @Mapping(target = "recordId", source = "record.id")
    @Mapping(target = "participantId", source = "participant.id")
    TaskData toData(Task entity);
}
