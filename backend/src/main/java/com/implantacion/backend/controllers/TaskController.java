package com.implantacion.backend.controllers;

import com.implantacion.backend.models.dto.request.CreateTask;
import com.implantacion.backend.models.dto.request.UpdateTask;
import com.implantacion.backend.models.dto.response.TaskResponse;
import com.implantacion.backend.services.ITaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final ITaskService taskService;

    @PostMapping
    public ResponseEntity<String> create(@Valid @RequestBody CreateTask createTask) {
        taskService.create(createTask);
        return ResponseEntity.status(HttpStatus.CREATED).body("Object Task created");
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getById(@PathVariable Long id) {
        TaskResponse task = taskService.getById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    public ResponseEntity<Page<TaskResponse>> getAll(@PageableDefault(size = 10) Pageable pageable) {
        Page<TaskResponse> tasks = taskService.getAll(pageable);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody UpdateTask updateTask) {
        taskService.update(id, updateTask);
        return ResponseEntity.ok("Object Task updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
