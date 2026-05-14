package com.spring.studentmanagement.controllers;


import com.spring.studentmanagement.enums.QuizStatus;
import com.spring.studentmanagement.models.Quiz;
import com.spring.studentmanagement.services.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/quizzes")
public class QuizController {

    private final QuizService quizService;

    @GetMapping()
    public ResponseEntity<List<Quiz>> getAllQuizzes() {
        return ResponseEntity.ok(quizService.getAllQuizzes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.getQuizById(id));
    }
    @PostMapping()
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz) {
        return ResponseEntity.status(HttpStatus.CREATED).body(quizService.addQuiz(quiz));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Quiz> updateQuizById(@PathVariable Long id, @RequestBody Quiz quiz) {
        return ResponseEntity.ok(quizService.updateQuizById(quiz, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizById(@PathVariable Long id) {
        quizService.deleteQuizById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Quiz>> getAllQuizzesByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(quizService.getQuizzesByCourse(courseId));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Quiz>> getAllQuizzesByStatus(@PathVariable QuizStatus status) {
        return ResponseEntity.ok(quizService.getQuizzesByStatus(status));
    }
    @GetMapping("/course/{id}/count")
    public ResponseEntity<Long> getQuizzesCount(@PathVariable Long id) {
        return ResponseEntity.ok(quizService.countQuizzesByCourse(id));
    }
}
