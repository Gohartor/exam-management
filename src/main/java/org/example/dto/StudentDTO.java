package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public record StudentDTO(
        Long id,

        @NotBlank(message = "student name can not blank")
        @Size(min = 2, max = 100, message = "student name should be between 2 to 100 character")
        String name,

        @NotBlank(message = "student number can not blank")
        @Size(min = 10, max = 10, message = "student number should 10 digits")
        String studentNumber
) {}