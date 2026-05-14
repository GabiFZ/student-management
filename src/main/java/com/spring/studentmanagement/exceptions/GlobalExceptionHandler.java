package com.spring.studentmanagement.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// Aceasta clasă va intercepta excepțiile din controller
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // Prinde excepția custom pentru email deja existent
    @ExceptionHandler(StudentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleStudentAlreadyExists(StudentAlreadyExistsException ex) {
        logger.error("StudentAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 e mai corect decât 500
                .body(new ErrorResponse(ex.getMessage()));
    }
    // Not found Exception
    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException ex) {
        logger.error("StudentNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }

    // Prinde excepția custom pentru email deja existent
    @ExceptionHandler(DepartmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentAlreadyExists(DepartmentAlreadyExistsException ex) {
        logger.error("DepartmentAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 e mai corect decât 500
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(DepartmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDepartmentNotFound(DepartmentNotFoundException ex) {
        logger.error("DepartmentNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCategoryAlreadyExists(CategoryAlreadyExistsException ex) {
        logger.error("CategoryAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 e mai corect decât 500
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFound(CategoryNotFoundException ex) {
        logger.error("CategoryNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(ProfessorAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleProfessorAlreadyExists(ProfessorAlreadyExistsException ex) {
        logger.error("ProfessorAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 e mai corect decât 500
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(ProfessorNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfessorNotFound(ProfessorNotFoundException ex) {
        logger.error("ProfessorNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(CourseAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCourseAlreadyExists(CourseAlreadyExistsException ex) {
        logger.error("CourseAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 e mai corect decât 500
                .body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(CourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCourseNotFound(CourseNotFoundException ex) {
        logger.error("CourseNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(LessonNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLessonNotFound(LessonNotFoundException ex) {
        logger.error("LessonNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(AssignmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleAssignmentAlreadyExists(AssignmentAlreadyExistsException ex) {
        logger.error("AssignmentAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 e mai corect decât 500
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(AssignmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleAssignmentNotFound(AssignmentNotFoundException ex) {
        logger.error("AssignmentNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(QuizAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleQuizAlreadyExists(QuizAlreadyExistsException ex) {
        logger.error("QuizAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT) // 409 e mai corect decât 500
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(QuizNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuizNotFound(QuizNotFoundException ex) {
        logger.error("QuizNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(QuestionAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleQuestionAlreadyExists(QuestionAlreadyExistsException ex) {
        logger.error("QuestionAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(QuestionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleQuestionNotFound(QuestionNotFoundException ex) {
        logger.error("QuestionNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(EnrollmentAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEnrollmentAlreadyExists(EnrollmentAlreadyExistsException ex) {
        logger.error("EnrollmentAlreadyExistsException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEnrollmentNotFound(EnrollmentNotFoundException ex) {
        logger.error("EnrollmentNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }
    @ExceptionHandler(SubmissionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSubmissionNotFound(SubmissionNotFoundException ex) {
        logger.error("SubmissionNotFoundException caught: {}", ex.getMessage(), ex);
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(ex.getMessage()));
    }


    // Prinde toate celelalte erori generale
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("A apărut o eroare pe server"));
    }
}

// Clasa simplă pentru mesajul de eroare
class ErrorResponse {
    private String message;

    public ErrorResponse(String message) { this.message = message; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}