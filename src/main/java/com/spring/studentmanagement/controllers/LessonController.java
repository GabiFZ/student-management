package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.models.Lesson;
import com.spring.studentmanagement.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping()
    public ResponseEntity<List<Lesson>> getAllLessons() {
        return  ResponseEntity.ok(lessonService.getAllLessons());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lesson> getLessonById(@PathVariable long id) {
        return ResponseEntity.ok(lessonService.getLessonById(id));
    }

    @PostMapping()
    public ResponseEntity<Lesson> addLesson(@RequestBody Lesson lesson) {
        return ResponseEntity.status(HttpStatus.CREATED).body(lessonService.addLesson(lesson));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable long id, @RequestBody Lesson lesson) {
        return ResponseEntity.ok(lessonService.updateLesson(lesson, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLessonById(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Lesson>> getLessonsByCourse(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.getLessonsByCourse(courseId));
    }

    @GetMapping("/course/{courseId}/ordered")
    public ResponseEntity<List<Lesson>> getLessonsByCourseOrdered(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(
                lessonService.getLessonsByCourseOrdered(courseId));
    }

    @GetMapping("/course/{courseId}/count")
    public ResponseEntity<Long> countLessonsByCourse(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(
                lessonService.countLessonsByCourse(courseId));
    }

}
