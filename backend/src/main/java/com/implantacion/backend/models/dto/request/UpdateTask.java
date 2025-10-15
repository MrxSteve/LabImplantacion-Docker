package com.implantacion.backend.models.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class UpdateTask {
    @NotNull(message = "Completed status is required")
    private Boolean completed;
}
