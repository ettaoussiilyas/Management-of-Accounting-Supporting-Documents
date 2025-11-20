package com.cabinetcomptable.security.exception;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Email or Password is invalide");
    }
}
