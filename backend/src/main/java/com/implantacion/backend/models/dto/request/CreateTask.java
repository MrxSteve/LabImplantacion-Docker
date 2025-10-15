package com.implantacion.backend.models.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class CreateTask {
    @NotBlank(message = "Titlte is required")
    private String title;

    @NotNull(message = "Completed is required")
    private Boolean completed;
}
