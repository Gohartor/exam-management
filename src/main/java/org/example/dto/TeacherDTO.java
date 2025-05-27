package org.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record TeacherDTO(
        Long id,

        @NotBlank(message = "teacher name can not be blank")
        @Size(min = 2, max = 100, message = "student name should be between 2 to 100 character")
        String name,

        @NotBlank(message = "student number can not blank")
        @Size(min = 10, max = 10, message = "student number should 10 digits")
        String teacherCode
) {}