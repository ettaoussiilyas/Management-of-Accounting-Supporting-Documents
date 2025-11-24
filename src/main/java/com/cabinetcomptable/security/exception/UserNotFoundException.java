package com.cabinetcomptable.security.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String email) {
        super("No user found with this email: " + email);
    }
}
