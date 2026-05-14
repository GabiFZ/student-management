package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.enums.EnrollmentStatus;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Enrollment;
import com.spring.studentmanagement.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long > {

    boolean existsByStudentAndCourse(Student student, Course course);

    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);

    List<Enrollment> findByStudent(Student student);
    List<Enrollment> findByCourse(Course course);
    List<Enrollment> findByStatus(EnrollmentStatus status);
    List<Enrollment> findByStudentAndStatus(Student student, EnrollmentStatus status);

    long countByCourse(Course course);
    long countByStatus(EnrollmentStatus status);
    long countByStudent(Student student);
}
