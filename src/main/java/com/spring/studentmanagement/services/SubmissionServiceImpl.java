package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.SubmissionStatus;
import com.spring.studentmanagement.exceptions.AssignmentNotFoundException;
import com.spring.studentmanagement.exceptions.StudentNotFoundException;
import com.spring.studentmanagement.exceptions.SubmissionNotFoundException;
import com.spring.studentmanagement.models.Assignment;
import com.spring.studentmanagement.models.Student;
import com.spring.studentmanagement.models.Submission;
import com.spring.studentmanagement.repositories.AssignmentRepository;
import com.spring.studentmanagement.repositories.StudentRepository;
import com.spring.studentmanagement.repositories.SubmissionRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SubmissionServiceImpl implements SubmissionService {

    private final SubmissionRepository submissionRepository;
    private final StudentRepository studentRepository;
    private final AssignmentRepository assignmentRepository;


    @Override
    public Submission addSubmission(Submission submission) {
        Assignment assignment = assignmentRepository.findById(submission.getAssignment().getId()).orElseThrow(()-> new AssignmentNotFoundException("Assignment with id: " + submission.getAssignment().getId() + " not found"));

        Student student = studentRepository.findById(submission.getStudent().getId()).orElseThrow(() -> new StudentNotFoundException("Student with id: " + submission.getStudent().getId() + " not found"));

        submission.setAssignment(assignment);
        submission.setStudent(student);
        return submissionRepository.save(submission);
    }

    @Override
    public Submission getSubmissionById(Long id) {
        return submissionRepository.findById(id).orElseThrow(() -> new SubmissionNotFoundException("Submission with id: " + id + " not found") );
    }

    @Override
    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    @Override
    public Submission updateSubmission(Submission submission, Long id) {
        return submissionRepository.findById(id).map(existingSubmission -> {
            existingSubmission.setScore(submission.getScore());
            existingSubmission.setFeedback(submission.getFeedback());
            existingSubmission.setStatus(submission.getStatus());
            return submissionRepository.save(existingSubmission);
        }).orElseThrow(() -> new SubmissionNotFoundException("Submission with id: " + id + " not found"));

    }

    @Override
    public void deleteSubmissionById(Long id) {
        if (!submissionRepository.existsById(id)) {
            throw new SubmissionNotFoundException("Submission with id: " + id + " not found");
        }
        submissionRepository.deleteById(id);
    }

    @Override
    public List<Submission> getSubmissionsByStatus(SubmissionStatus status) {
        return submissionRepository.findByStatus(status);
    }

    @Override
    public List<Submission> getAllSubmissionsByStudent(Long studentId) {
       Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id: " + studentId + " not found"));
       return submissionRepository.findByStudent(student);
    }

    @Override
    public List<Submission> getSubmissionByAssignment(Long assignmentId) {
       Assignment assignment =   assignmentRepository.findById(assignmentId).orElseThrow(() -> new AssignmentNotFoundException("Assignment with id: " + assignmentId + " not found!"));
       return submissionRepository.findByAssignment(assignment);
    }

    @Override
    public long countByAssignment(Long assignmentId) {
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> new AssignmentNotFoundException("Assignment with id: " + assignmentId + " not found!"));
        return submissionRepository.countByAssignment(assignment);
    }

    @Override
    public Submission gradeSubmission(Long id, Integer score, String feedback) {
        return submissionRepository.findById(id).map(submission -> {
            submission.setScore(score);
            submission.setFeedback(feedback);
            submission.setStatus(SubmissionStatus.GRADED);
            return submissionRepository.save(submission);
        }).orElseThrow(() -> new SubmissionNotFoundException(
                "Submission with id: " + id + " not found"));
    }
}
