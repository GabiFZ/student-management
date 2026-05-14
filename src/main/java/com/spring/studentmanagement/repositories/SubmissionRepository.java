package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.enums.SubmissionStatus;
import com.spring.studentmanagement.models.Assignment;
import com.spring.studentmanagement.models.Student;
import com.spring.studentmanagement.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {

    List<Submission> findByAssignment(Assignment assignment);
    List<Submission> findByStudent(Student student);
    List<Submission> findByStatus(SubmissionStatus status);
    boolean existsByStudentAndAssignment(Student student, Assignment assignment);
    long countByAssignment(Assignment assignment);
    long countByStatus(SubmissionStatus status);

}
