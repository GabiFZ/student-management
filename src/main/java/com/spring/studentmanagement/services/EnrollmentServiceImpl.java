package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.EnrollmentStatus;
import com.spring.studentmanagement.exceptions.CourseNotFoundException;
import com.spring.studentmanagement.exceptions.EnrollmentAlreadyExistsException;
import com.spring.studentmanagement.exceptions.EnrollmentNotFoundException;
import com.spring.studentmanagement.exceptions.StudentNotFoundException;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Enrollment;
import com.spring.studentmanagement.models.Student;
import com.spring.studentmanagement.repositories.CourseRepository;
import com.spring.studentmanagement.repositories.EnrollmentRepository;
import com.spring.studentmanagement.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public Enrollment addEnrollment(Enrollment enrollment) {
        Student student = studentRepository.findById(enrollment.getStudent().getId()).orElseThrow(() -> new StudentNotFoundException("Student with id: " + enrollment.getStudent().getId() + " not found!"));
        Course course = courseRepository.findById(enrollment.getCourse().getId()).orElseThrow(() -> new CourseNotFoundException("Course with id: " + enrollment.getCourse().getId() + "not found!"));

        if (enrollmentRepository.existsByStudentAndCourse(student, course)) {
            throw new EnrollmentAlreadyExistsException("Student " +  student.getFirstName() + " " + student.getLastName() + " is already enrolled to the course: " +  course.getTitle() + "!");
        }
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment getEnrollmentById(Long id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new EnrollmentNotFoundException("Enrollment with id: " + id + " not found!"));
    }

    @Override
    public Enrollment updateEnrollmentById(Enrollment enrollment, Long id) {
        return enrollmentRepository.findById(id).map(existingEnrollment -> {
            existingEnrollment.setStatus(enrollment.getStatus());
            existingEnrollment.setProgressPercentage(enrollment.getProgressPercentage());
            existingEnrollment.setCompletedAt(enrollment.getCompletedAt());
            return enrollmentRepository.save(existingEnrollment);
        }).orElseThrow(() -> new EnrollmentNotFoundException("Enrollment with id: " + id + " not found!"));
    }

    @Override
    public void deleteEnrollmentById(Long id) {
        if (!enrollmentRepository.existsById(id)) {
            throw new EnrollmentNotFoundException("Enrollment with id: " + id + " not found!");
        }
        enrollmentRepository.deleteById(id);

    }

    @Override
    public List<Enrollment> findByStudent(Long studentId) {
        Student student  = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with id: " + studentId + " not found!"));
        return  enrollmentRepository.findByStudent(student);
    }

    @Override
    public List<Enrollment> findByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found!"));
        return  enrollmentRepository.findByCourse(course);
    }

    @Override
    public List<Enrollment> findByStatus(EnrollmentStatus status) {
        return enrollmentRepository.findByStatus(status);
    }

    @Override
    public Enrollment updateProgress(Long enrollmentId, Integer progressPercentage) {
        return enrollmentRepository.findById(enrollmentId).map(existingEnrollment -> {
            existingEnrollment.setProgressPercentage(progressPercentage);
            if(progressPercentage == 100) {
                existingEnrollment.setStatus(EnrollmentStatus.COMPLETED);
                existingEnrollment.setCompletedAt(LocalDateTime.now());

            }
            return enrollmentRepository.save(existingEnrollment);
        }).orElseThrow(() -> new EnrollmentNotFoundException("Enrollment with id: " + enrollmentId + " not found!"));
    }

    @Override
    public long countByCourse(Long courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException("Course with id: " + courseId + " not found!"));
        return enrollmentRepository.countByCourse(course);
    }

    @Override
    public long countByStatus(EnrollmentStatus status) {
        return enrollmentRepository.countByStatus(status);
    }
}
