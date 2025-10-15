package com.implantacion.backend.services;

import com.implantacion.backend.models.dto.request.CreateTask;
import com.implantacion.backend.models.dto.request.UpdateTask;
import com.implantacion.backend.models.dto.response.TaskResponse;
import com.implantacion.backend.models.entities.TaskEntity;
import com.implantacion.backend.repositories.TaskRepository;
import com.implantacion.backend.utils.errors.TaskNotFoundException;
import com.implantacion.backend.utils.mappers.TaskMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public void create(CreateTask task) {
        TaskEntity taskEntity = taskMapper.toEntity(task);
        taskRepository.save(taskEntity);
    }

    @Override
    public TaskResponse getById(Long id) {
        TaskEntity taskEntity = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
        return taskMapper.toResponse(taskEntity);
    }

    @Override
    public void deleteById(Long id) {
        this.getById(id);
        taskRepository.deleteById(id);
    }

    @Override
    public void update(Long id, UpdateTask task) {
        TaskEntity taskEntity = taskRepository.findById(id)
            .orElseThrow(() -> new TaskNotFoundException(id));
        taskMapper.updateEntity(taskEntity, task);
        taskRepository.save(taskEntity);
    }

    @Override
    public Page<TaskResponse> getAll(Pageable pageable) {
        Page<TaskEntity> taskEntities = taskRepository.findAll(pageable);
        return taskEntities.map(taskMapper::toResponse);
    }
}
