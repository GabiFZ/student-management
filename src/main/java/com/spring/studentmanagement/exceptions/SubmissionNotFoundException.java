package com.spring.studentmanagement.exceptions;

public class SubmissionNotFoundException extends RuntimeException {
    public SubmissionNotFoundException(String message) {
        super(message);
    }
}
