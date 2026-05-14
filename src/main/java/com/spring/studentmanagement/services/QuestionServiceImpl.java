package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.QuestionAlreadyExistsException;
import com.spring.studentmanagement.exceptions.QuestionNotFoundException;
import com.spring.studentmanagement.exceptions.QuizNotFoundException;
import com.spring.studentmanagement.models.Question;
import com.spring.studentmanagement.models.Quiz;
import com.spring.studentmanagement.repositories.QuestionRepository;
import com.spring.studentmanagement.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final  QuestionRepository questionRepository;
    private final QuizRepository quizRepository;

    @Override
    public Question addQuestion(Question question) {

        Quiz quiz = quizRepository.findById(question.getQuiz().getId()).orElseThrow(() -> new QuizNotFoundException("Quiz with id: " + question.getQuiz().getId() + " not found!"));

        if (questionRepository.existsByTextAndQuiz(question.getText(), quiz)) {
            throw new QuestionAlreadyExistsException("Question with text " + question.getText() + " already exists");
        }
        question.setQuiz(quiz);
        return questionRepository.save(question);
    }

    @Override
    public Question updateQuestionById(Question question, Long id) {
        return questionRepository.findById(id).map(existingQuestion -> {
            if(!existingQuestion.getText().equals(question.getText()) && questionRepository.existsByTextAndQuiz(question.getText(), existingQuestion.getQuiz())) {
                throw new QuestionAlreadyExistsException("Question with text " + question.getText() + " already exists in this quiz!");

            }
            existingQuestion.setText(question.getText());
            existingQuestion.setOptionA(question.getOptionA());
            existingQuestion.setOptionB(question.getOptionB());
            existingQuestion.setOptionC(question.getOptionC());
            existingQuestion.setOptionD(question.getOptionD());
            existingQuestion.setCorrectAnswers(question.getCorrectAnswers());
            existingQuestion.setPoints(question.getPoints());
            return questionRepository.save(existingQuestion);
        }).orElseThrow(() -> new QuestionNotFoundException("Question with id: " + id + " not found!"));
    }

    @Override
    public void deleteQuestionById(Long id) {
        if (!questionRepository.existsById(id)) {
            throw new QuestionNotFoundException("Question with id: " + id + " not found!");
        }
        questionRepository.deleteById(id);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public Question getQuestionById(Long id) {
        return questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException("Question with id: " + id + " not found!"));
    }

    @Override
    public List<Question> getQuestionsByQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException(
                        "Quiz with id: " + quizId + " not found!"));
        return questionRepository.findByQuiz(quiz);
    }

    @Override
    public long countQuestionsByQuiz(Long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException(
                        "Quiz with id: " + quizId + " not found!"));
        return questionRepository.countByQuiz(quiz);
    }
}
