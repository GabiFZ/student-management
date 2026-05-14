package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Lesson;
import com.spring.studentmanagement.models.Student;

import java.util.List;

public interface LessonService {

    Lesson addLesson(Lesson lesson);
    List<Lesson> getAllLessons();
    Lesson getLessonById(Long  id);
    Lesson updateLesson(Lesson lesson, Long id);
    void deleteLesson(Long id);


    List<Lesson> getLessonsByCourse(Long courseId);
   // List<Lesson> getLessonsByStudent(Long studentId);
    List<Lesson> getLessonsByCourseOrdered(Long courseId);

    long countLessonsByCourse(Long courseId);

}
