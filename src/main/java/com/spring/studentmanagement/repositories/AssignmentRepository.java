package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.Assignment;
import com.spring.studentmanagement.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Long> {

    boolean existsByTitleAndCourse(String title, Course course);

    List<Assignment> findByCourse(Course course);
    List<Assignment> findByCourseOrderByDeadlineAsc(Course course);

    long countByCourse(Course course);
}
