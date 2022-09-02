package com.example.restfirst.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String msg) {
        super(msg + " not found");
    }
}