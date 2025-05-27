package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminDTO(
        Long id,

        @NotBlank(message = "user name can not be bank")
        @Size(min = 6, message = "student name can not less than 6")
        String username,

        @NotBlank(message = "password can not blank")
        @Size(min = 6, max = 100, message = "student name can not less than 8")
        String password
) {}