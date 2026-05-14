package com.spring.studentmanagement.controllers;


import com.spring.studentmanagement.enums.EnrollmentStatus;
import com.spring.studentmanagement.models.Enrollment;

import com.spring.studentmanagement.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @GetMapping()
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return  ResponseEntity.ok(enrollmentService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long id) {
        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
    }

    @PostMapping()
    public ResponseEntity<Enrollment> addEnrollment(@RequestBody Enrollment enrollment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(enrollmentService.addEnrollment(enrollment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enrollment> updateEnrollmentById(@PathVariable Long id, @RequestBody Enrollment enrollment) {
        return ResponseEntity.ok(enrollmentService.updateEnrollmentById(enrollment, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnrollmentById(@PathVariable Long id) {
        enrollmentService.deleteEnrollmentById(id);
        return ResponseEntity.noContent().build();



    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentId(@PathVariable Long studentId) {
        return ResponseEntity.ok(enrollmentService.findByStudent(studentId));
    }
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(enrollmentService.findByCourse(courseId));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStatus(@PathVariable EnrollmentStatus status) {
        return ResponseEntity.ok(enrollmentService.findByStatus(status));
    }

    @PutMapping("/{id}/progress/{percentage}")
    public ResponseEntity<Enrollment> updateProgress(
            @PathVariable Long id,
            @PathVariable Integer percentage) {
        return ResponseEntity.ok(
                enrollmentService.updateProgress(id, percentage));
    }

    @GetMapping("/count/course/{courseId}")
    public ResponseEntity<Long> countByCourse(
            @PathVariable Long courseId) {
        return ResponseEntity.ok(
                enrollmentService.countByCourse(courseId));
    }

    @GetMapping("/count/status/{status}")
    public ResponseEntity<Long> countByStatus(
            @PathVariable EnrollmentStatus status) {
        return ResponseEntity.ok(
                enrollmentService.countByStatus(status));
    }



}
