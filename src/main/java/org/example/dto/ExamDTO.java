package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExamDTO(
        Long id,

        @NotBlank(message = "exam title can not be blank")
        String title,

        @NotNull(message = "start date is required")
        Integer startDate,

        @NotNull(message = "end date is required")
        Integer endDate,

        @NotNull(message = "courseID is required")
        Long courseId
) {}