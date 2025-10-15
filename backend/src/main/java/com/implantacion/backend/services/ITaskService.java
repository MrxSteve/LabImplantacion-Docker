package com.implantacion.backend.services;

import com.implantacion.backend.models.dto.request.CreateTask;
import com.implantacion.backend.models.dto.request.UpdateTask;
import com.implantacion.backend.models.dto.response.TaskResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ITaskService {
    void create(CreateTask task);
    TaskResponse getById(Long id);
    void deleteById(Long id);
    void update(Long id, UpdateTask task);
    Page<TaskResponse> getAll(Pageable pageable);
}
