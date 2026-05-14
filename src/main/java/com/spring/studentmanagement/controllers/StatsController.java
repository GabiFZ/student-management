package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.enums.CourseStatus;
import com.spring.studentmanagement.enums.EnrollmentStatus;
import com.spring.studentmanagement.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final CategoryRepository categoryRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final QuizRepository quizRepository;
    private final LessonRepository lessonRepository;
    private final SubmissionRepository submissionRepository;
    private final QuestionRepository questionRepository;


    @GetMapping()
    public ResponseEntity<Map<String, Long>> getStats() {
        Map<String, Long> stats = new HashMap<>();

        stats.put("totalStudents", studentRepository.count());
        stats.put("totalProfessors", professorRepository.count());
        stats.put("totalCourses", courseRepository.count());
        stats.put("totalDepartments", departmentRepository.count());
        stats.put("totalCategories", categoryRepository.count());
        stats.put("totalEnrollments", enrollmentRepository.count());
        stats.put("activeEnrollments",
                enrollmentRepository.countByStatus(EnrollmentStatus.ACTIVE));
        stats.put("completedEnrollments",
                enrollmentRepository.countByStatus(EnrollmentStatus.COMPLETED));
        stats.put("totalAssignments", assignmentRepository.count());
        stats.put("totalSubmissions", submissionRepository.count());
        stats.put("totalQuestions", questionRepository.count());
        stats.put("totalQuizzes", quizRepository.count());
        stats.put("totalLessons", lessonRepository.count());
        stats.put("publishedCourses",
                (long) courseRepository.findByStatus(CourseStatus.PUBLISHED).size());
        stats.put("draftCourses",
                (long) courseRepository.findByStatus(CourseStatus.DRAFT).size());

        return ResponseEntity.ok(stats);
    }

}
