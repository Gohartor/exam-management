package org.example.exeption;

public class ExistUser extends RuntimeException {
    public ExistUser(String message) {
        super(message);
    }
}
