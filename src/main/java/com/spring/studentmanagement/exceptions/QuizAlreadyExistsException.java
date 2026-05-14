package com.spring.studentmanagement.exceptions;

public class QuizAlreadyExistsException extends RuntimeException {
    public QuizAlreadyExistsException(String message) {
        super(message);
    }
}
