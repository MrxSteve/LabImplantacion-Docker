package com.implantacion.backend.utils.errors;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(String message) {
        super(message);
    }

    public TaskNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TaskNotFoundException(Long id) {
        super("Task not found with id: " + id);
    }
}
