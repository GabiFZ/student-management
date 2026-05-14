package com.spring.studentmanagement.controllers;


import com.spring.studentmanagement.models.Question;
import com.spring.studentmanagement.repositories.QuestionRepository;
import com.spring.studentmanagement.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/questions")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping()
    public ResponseEntity<List<Question>> getAllQuestions() {
        return ResponseEntity.ok(questionService.getAllQuestions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }


    @PostMapping()
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestion(question));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Question> updateQuestionById(@PathVariable Long id, @RequestBody Question question) {
        return ResponseEntity.ok(questionService.updateQuestionById(question, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestionById(@PathVariable Long id) {
        questionService.deleteQuestionById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/quiz/{quizId}")
    public ResponseEntity<List<Question>> getAllQuestionsByQuizId(@PathVariable Long quizId) {
        return ResponseEntity.ok(questionService.getQuestionsByQuiz(quizId));
    }
    @GetMapping("/quiz/{id}/count")
    public ResponseEntity<Long> getCountOfQuestionsByQuizId(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.countQuestionsByQuiz(id));
    }


}
