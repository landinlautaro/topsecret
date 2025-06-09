package com.example.topsecret.exception;

public class IncompleteDataException extends RuntimeException {
    public IncompleteDataException(String message) {
        super(message);
    }
}