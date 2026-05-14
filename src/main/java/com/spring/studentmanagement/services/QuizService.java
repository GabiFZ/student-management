package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.QuizStatus;
import com.spring.studentmanagement.models.Quiz;

import java.util.List;

public interface QuizService {

    Quiz addQuiz(Quiz quiz);
    Quiz updateQuizById(Quiz quiz, Long id);
    void deleteQuizById(Long id);
    List<Quiz> getAllQuizzes();
    Quiz getQuizById(Long id);

    List<Quiz> getQuizzesByCourse(Long courseId);
    List<Quiz> getQuizzesByStatus(QuizStatus status);
    long countQuizzesByCourse(Long courseId);
}
