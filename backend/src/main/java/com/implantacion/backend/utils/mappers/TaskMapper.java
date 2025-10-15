package com.implantacion.backend.utils.mappers;

import com.implantacion.backend.models.dto.request.CreateTask;
import com.implantacion.backend.models.dto.request.UpdateTask;
import com.implantacion.backend.models.dto.response.TaskResponse;
import com.implantacion.backend.models.entities.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskEntity toEntity(CreateTask createTask);
    TaskResponse toResponse(TaskEntity taskEntity);
    void updateEntity(@MappingTarget TaskEntity taskEntity, UpdateTask updateTask);
}
