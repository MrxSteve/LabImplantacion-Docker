package com.implantacion.backend.models.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class TaskResponse {
    private Long id;
    private String title;
    private Boolean completed;
    private LocalDateTime createdAt;
}
