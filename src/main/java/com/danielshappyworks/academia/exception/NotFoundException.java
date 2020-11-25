package com.danielshappyworks.academia.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String obj, Long id) {
        super("Could not find "+ obj + " " + id);
    }
}