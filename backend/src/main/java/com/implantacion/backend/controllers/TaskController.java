package com.implantacion.backend.controllers;

import com.implantacion.backend.models.dto.request.CreateTask;
import com.implantacion.backend.models.dto.request.UpdateTask;
import com.implantacion.backend.models.dto.response.TaskResponse;
import com.implantacion.backend.services.ITaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Tasks", description = "API for task management")
public class TaskController {
    private final ITaskService taskService;

    @PostMapping
    @Operation(summary = "Create new task", description = "Creates a new task with title and completed status set to false by default")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Task created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<String> create(
            @Parameter(description = "Data to create the task", required = true)
            @Valid @RequestBody CreateTask createTask) {
        taskService.create(createTask);
        return ResponseEntity.status(HttpStatus.CREATED).body("Object Task created");
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get task by ID", description = "Gets a specific task by its identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task found"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<TaskResponse> getById(
            @Parameter(description = "Task ID", required = true, example = "1")
            @PathVariable Long id) {
        TaskResponse task = taskService.getById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping
    @Operation(summary = "List all tasks", description = "Gets a paginated list of all tasks")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task list retrieved successfully")
    })
    public ResponseEntity<Page<TaskResponse>> getAll(
            @Parameter(description = "Pagination parameters")
            @PageableDefault(size = 10) Pageable pageable) {
        Page<TaskResponse> tasks = taskService.getAll(pageable);
        return ResponseEntity.ok(tasks);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update task", description = "Updates the completed status of an existing task")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Task updated successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<String> update(
            @Parameter(description = "Task ID", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Data to update the task", required = true)
            @Valid @RequestBody UpdateTask updateTask) {
        taskService.update(id, updateTask);
        return ResponseEntity.ok("Object Task updated");
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete task", description = "Deletes a task by its identifier")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Task deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Task not found")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "Task ID", required = true, example = "1")
            @PathVariable Long id) {
        taskService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
