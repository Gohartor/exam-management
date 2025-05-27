package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record QuestionDTO(
        Long id,

        @NotBlank(message = "not blank")
        String text,

        @NotNull(message = "question type should be selected")
        String type,

        @NotNull(message = "input the question bankID")
        Long questionBankId
) {}