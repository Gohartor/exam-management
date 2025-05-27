package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CourseDTO(
        Long id,

        @NotBlank(message = "course title can not blank")
        String title
) {}