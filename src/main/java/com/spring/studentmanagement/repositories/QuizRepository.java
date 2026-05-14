package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.enums.QuizStatus;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

    boolean existsByTitleAndCourse(String title, Course course);
    List<Quiz>  findByCourse(Course course);
    List<Quiz>  findByStatus(QuizStatus status);
    long countByCourse(Course course);


}
