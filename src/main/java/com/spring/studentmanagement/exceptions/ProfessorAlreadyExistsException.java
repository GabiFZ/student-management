package com.spring.studentmanagement.exceptions;

public class ProfessorAlreadyExistsException extends RuntimeException {
    public ProfessorAlreadyExistsException(String message) {
        super(message);
    }
}
