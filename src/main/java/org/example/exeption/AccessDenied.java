package org.example.exeption;

public class AccessDenied extends RuntimeException {
    public AccessDenied(String message) {
        super(message);
    }
}
