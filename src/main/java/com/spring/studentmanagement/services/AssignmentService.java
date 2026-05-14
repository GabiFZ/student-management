package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.Assignment;

import java.util.List;

public interface AssignmentService {

    Assignment addAssignment(Assignment assignment);
    List<Assignment> getAllAssignments();
    Assignment getAssignmentById(Long id);
    Assignment updateAssignmentById(Assignment assignment, Long id);
    void deleteAssignmentById(Long id);

    List<Assignment> getAssignmentsByCourse(Long courseId);
    List<Assignment> getAssignmentsByCourseOrdered(Long courseId);
    long countAssignmentsByCourse(Long courseId);

}
