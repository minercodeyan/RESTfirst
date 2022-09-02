package com.example.restfirst.exceptions;

public class RegistrationException extends RuntimeException{

    public RegistrationException(String message) {
        super(message + "уже есть");
    }
}
