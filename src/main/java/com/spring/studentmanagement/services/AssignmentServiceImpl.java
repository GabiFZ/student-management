package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.AssignmentAlreadyExistsException;
import com.spring.studentmanagement.exceptions.AssignmentNotFoundException;
import com.spring.studentmanagement.exceptions.CourseNotFoundException;
import com.spring.studentmanagement.models.Assignment;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.repositories.AssignmentRepository;
import com.spring.studentmanagement.repositories.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AssignmentServiceImpl implements AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;

    @Override
    public Assignment addAssignment(Assignment assignment) {


        Course course = courseRepository.findById(assignment.getCourse().getId()).orElseThrow(() -> new CourseNotFoundException("Course with id: " + assignment.getCourse().getId() + " not found!"));

        if(assignmentRepository.existsByTitleAndCourse(assignment.getTitle(), assignment.getCourse())){
            throw new AssignmentAlreadyExistsException("Assignment with title " + assignment.getTitle() + " already exists");
        }
        assignment.setCourse(course);
        return assignmentRepository.save(assignment);
    }

    @Override
    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    @Override
    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id).orElseThrow(() -> new AssignmentNotFoundException("Assignment with id " + id + " not found!"));
    }

    @Override
    public Assignment updateAssignmentById(Assignment assignment, Long id) {
        return assignmentRepository.findById(id).map(existingAssignment -> {
            if (!existingAssignment.getTitle().equals(assignment.getTitle())
                    && assignmentRepository.existsByTitleAndCourse(
                    assignment.getTitle(), existingAssignment.getCourse())) {
                throw new AssignmentAlreadyExistsException(
                        "Assignment with title " + assignment.getTitle() + " already exists!");
            }
            existingAssignment.setTitle(assignment.getTitle());
            existingAssignment.setDescription(assignment.getDescription());
            existingAssignment.setDeadline(assignment.getDeadline());
            existingAssignment.setMaxScore(assignment.getMaxScore());
            return assignmentRepository.save(existingAssignment);
        }).orElseThrow(() -> new AssignmentNotFoundException(
                "Assignment with id " + id + " not found!"));
    }

    @Override
    public void deleteAssignmentById(Long id) {
            if (!assignmentRepository.existsById(id)) {
                throw new AssignmentNotFoundException("Assignment with id " + id + " not found!");
            }
            assignmentRepository.deleteById(id);
    }

    @Override
    public List<Assignment> getAssignmentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course with id " + courseId + " not found!"));
        return assignmentRepository.findByCourse(course);
    }

    @Override
    public List<Assignment> getAssignmentsByCourseOrdered(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course with id " + courseId + " not found!"));
        return assignmentRepository.findByCourseOrderByDeadlineAsc(course);
    }

    @Override
    public long countAssignmentsByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(()-> new CourseNotFoundException("Course with id " + courseId + " not found!"));
        return assignmentRepository.countByCourse(course);
    }
}
