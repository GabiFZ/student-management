package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.QuizStatus;
import com.spring.studentmanagement.exceptions.CourseNotFoundException;
import com.spring.studentmanagement.exceptions.QuizAlreadyExistsException;
import com.spring.studentmanagement.exceptions.QuizNotFoundException;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Quiz;
import com.spring.studentmanagement.repositories.CourseRepository;
import com.spring.studentmanagement.repositories.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@RequiredArgsConstructor
@Service
public class QuizServiceImpl implements QuizService {

    private final  QuizRepository quizRepository;
    private final CourseRepository courseRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        Course course = courseRepository.findById(quiz.getCourse().getId()).orElseThrow(() -> new CourseNotFoundException("Course with id: " + quiz.getCourse().getId() +  "not found!"));

        if(quizRepository.existsByTitleAndCourse(quiz.getTitle(), course)) {
            throw new QuizAlreadyExistsException("Quiz with title: " + quiz.getTitle() + " already exists in this course!");
        }
        quiz.setCourse(course);
        return  quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuizById(Quiz quiz, Long id) {
        return quizRepository.findById(id).map(existingQuiz -> {

            if(!existingQuiz.getTitle().equals(quiz.getTitle()) && quizRepository.existsByTitleAndCourse(quiz.getTitle(), existingQuiz.getCourse())) {
                throw new QuizAlreadyExistsException("Quiz with title: " + quiz.getTitle() + " already exists in this course!");
            }

            existingQuiz.setTitle(quiz.getTitle());
            existingQuiz.setDescription(quiz.getDescription());
            existingQuiz.setTimeLimit(quiz.getTimeLimit());
            existingQuiz.setStatus(quiz.getStatus());
            existingQuiz.setCourse(quiz.getCourse());
            return quizRepository.save(existingQuiz);
        }).orElseThrow(() -> new QuizNotFoundException("Quiz with id: " + id + " not found!"));
    }

    @Override
    public void deleteQuizById(Long id) {
        if (!quizRepository.existsById(id)) {
            throw new QuizNotFoundException("Quiz with id: " + id + " not found!");
        }
        quizRepository.deleteById(id);
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException("Quiz with id: " + id + " not found!"));
    }

    @Override
    public List<Quiz> getQuizzesByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found!"));
        return quizRepository.findByCourse(course);
    }

    @Override
    public List<Quiz> getQuizzesByStatus(QuizStatus status) {
        return quizRepository.findByStatus(status);
    }

    @Override
    public long countQuizzesByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found!"));
        return quizRepository.countByCourse(course);
    }
}
