package com.spring.studentmanagement.controllers;


import com.spring.studentmanagement.models.Assignment;
import com.spring.studentmanagement.services.AssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/assignments")
public class AssignmentController {

    private final AssignmentService assignmentService;

    @GetMapping()
    public ResponseEntity<List<Assignment>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
        return ResponseEntity.ok(assignmentService.getAssignmentById(id));
    }
    @PostMapping()
    public ResponseEntity<Assignment> addAssignment(@RequestBody Assignment assignment) {
        return ResponseEntity.status(HttpStatus.CREATED).body(assignmentService.addAssignment(assignment));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Assignment> updateAssignmentById(@PathVariable Long id, @RequestBody Assignment assignment) {
        return ResponseEntity.ok(assignmentService.updateAssignmentById(assignment, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssignmentById(@PathVariable Long id) {
        assignmentService.deleteAssignmentById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourse(@PathVariable Long courseId) {
        return ResponseEntity.ok(assignmentService.getAssignmentsByCourse(courseId));
    }
    @GetMapping("/course/{id}/ordered")
    public ResponseEntity<List<Assignment>> getAssignmentsByCourseOrdered(@PathVariable Long id){
        return ResponseEntity.ok(assignmentService.getAssignmentsByCourseOrdered(id));
    }
    @GetMapping("/course/{id}/count")
    public ResponseEntity<Long> getAssignmentCount(@PathVariable Long id){
        return ResponseEntity.ok(assignmentService.countAssignmentsByCourse(id));
    }
}
