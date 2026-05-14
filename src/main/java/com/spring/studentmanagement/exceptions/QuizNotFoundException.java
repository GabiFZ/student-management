package com.spring.studentmanagement.exceptions;

public class QuizNotFoundException extends RuntimeException {

    public QuizNotFoundException(String message) {
            super(message);
    }
}
