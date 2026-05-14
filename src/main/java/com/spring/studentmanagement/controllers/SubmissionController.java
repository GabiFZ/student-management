package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.enums.SubmissionStatus;
import com.spring.studentmanagement.models.Submission;
import com.spring.studentmanagement.services.SubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;

    @GetMapping()
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        return ResponseEntity.ok(submissionService.getAllSubmissions());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.getSubmissionById(id));
    }
    @PostMapping()
    public ResponseEntity<Submission> addSubmission(@RequestBody Submission submission) {
        return ResponseEntity.status(HttpStatus.CREATED).body(submissionService.addSubmission(submission));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Submission> updateSubmissionById(@PathVariable Long id, @RequestBody Submission submission) {
        return ResponseEntity.ok(submissionService.updateSubmission(submission, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubmissionById(@PathVariable Long id) {
        submissionService.deleteSubmissionById(id);
        return ResponseEntity.noContent().build();

    }
    @GetMapping("/assignment/{id}")
    public ResponseEntity<List<Submission>> getAllSubmissionsByAssignmentId(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.getSubmissionByAssignment(id));
    }
    @GetMapping("/student/{id}")
    public ResponseEntity<List<Submission>> getAllSubmissionsByStudentId(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.getAllSubmissionsByStudent(id));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Submission>> getAllSubmissionsByStatus(@PathVariable SubmissionStatus status) {
        return ResponseEntity.ok(submissionService.getSubmissionsByStatus(status));
    }
    @PutMapping("/{id}/grade")
    public ResponseEntity<Submission> gradeSubmission(@PathVariable Long id, @RequestParam Integer score, @RequestParam String feedback) {
        return ResponseEntity.ok(submissionService.gradeSubmission(id, score, feedback));
    }
    @GetMapping("/count/assignment/{id}")
    public ResponseEntity<Long> getCountByAssignmentId(@PathVariable Long id) {
        return ResponseEntity.ok(submissionService.countByAssignment(id));
    }

}
