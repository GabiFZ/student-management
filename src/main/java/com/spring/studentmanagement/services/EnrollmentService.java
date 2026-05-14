package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.EnrollmentStatus;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Enrollment;
import com.spring.studentmanagement.models.Student;

import java.util.List;

public interface EnrollmentService {

    Enrollment addEnrollment(Enrollment enrollment);
    List<Enrollment> findAll();
    Enrollment getEnrollmentById(Long id);
    Enrollment updateEnrollmentById(Enrollment enrollment,  Long id);
    void deleteEnrollmentById(Long id);

    List<Enrollment> findByStudent(Long studentId);
    List<Enrollment> findByCourse(Long courseId);
    List<Enrollment> findByStatus(EnrollmentStatus status);
    Enrollment updateProgress(Long enrollmentId, Integer progressPercentage);

    long countByCourse(Long courseId);
    long countByStatus(EnrollmentStatus status);




}
