package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuestionsBankDTO(
        Long id,

        @NotBlank(message = "question bank title can not blank")
        String title,

        @NotNull(message = "teacherID is required")
        Long teacherId
) {}