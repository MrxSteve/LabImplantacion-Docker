package com.implantacion.backend.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class CreateTask {
    @NotBlank(message = "Title is required")
    private String title;
}
