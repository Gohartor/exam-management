package org.example.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.example.context.ApplicationContext;

import java.util.Set;
import java.util.stream.Collectors;

public class ValidationUtil {
    private static final Validator validator = ApplicationContext.getInstance().getValidator();

    public static <T> void validate(T t) {
        Set<ConstraintViolation<T>> violations = validator.validate(t);

        if (!violations.isEmpty()) {
            String errorMessage = violations.stream()
                    .map(v -> "- " + v.getPropertyPath() + ": " + v.getMessage())
                    .collect(Collectors.joining("\n", "Validation errors:\n", ""));
            throw new ConstraintViolationException(errorMessage, violations);
        }
    }
}
