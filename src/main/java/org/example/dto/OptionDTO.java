package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record OptionDTO(
        Long id,

        @NotBlank(message = "text option cannot blank")
        String text,

        @NotNull(message = "correct option is required ")
        Boolean isCorrect,

        @NotNull(message = "questionID is required")
        Long questionId
) {}