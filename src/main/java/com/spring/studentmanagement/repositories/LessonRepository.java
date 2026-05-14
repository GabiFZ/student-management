package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourse(Course course);
    List<Lesson> findByCourseOrderByOrderIndexAsc(Course course);

    //boolean existByTitleAndCourse(String title, Course course);

    boolean existsByTitleAndCourse(String title, Course course);

   // long countByCourse(Course course);
    long countByCourseId(Long courseId);


}
