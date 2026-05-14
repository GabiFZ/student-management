package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.SubmissionStatus;
import com.spring.studentmanagement.models.Submission;

import java.util.List;

public interface SubmissionService {

    Submission addSubmission(Submission submission);
    Submission getSubmissionById(Long id);
    List<Submission> getAllSubmissions();
    Submission updateSubmission(Submission submission, Long id);
    void deleteSubmissionById(Long id);

    List<Submission> getSubmissionsByStatus(SubmissionStatus status);
    List<Submission> getAllSubmissionsByStudent(Long studentId);
    List<Submission> getSubmissionByAssignment(Long assignmentId);
    long countByAssignment(Long assignmentId);
    Submission gradeSubmission(Long id, Integer score, String feedback);

}
