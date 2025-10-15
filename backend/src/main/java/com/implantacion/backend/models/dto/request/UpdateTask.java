package com.implantacion.backend.models.dto.request;

import lombok.*;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @Builder
public class UpdateTask {
    private Boolean completed;
}
