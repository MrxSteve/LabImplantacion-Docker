package com.implantacion.backend.models.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class TaskResponse {
    private UUID id;
    private String title;
    private Boolean completed;
    private LocalDateTime createdAt;
}
