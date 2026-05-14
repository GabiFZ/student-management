package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.Question;
import com.spring.studentmanagement.models.Quiz;

import java.util.List;

public interface QuestionService {

    Question addQuestion(Question question);
    Question updateQuestionById(Question question, Long id);
    void deleteQuestionById(Long id);
    List<Question> getAllQuestions();
    Question getQuestionById(Long id);

    List<Question> getQuestionsByQuiz(Long  quizId);
    long countQuestionsByQuiz(Long  quizId);
}
