package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.CourseLevel;
import com.spring.studentmanagement.enums.CourseStatus;
import com.spring.studentmanagement.models.Course;

import java.util.List;

public interface CourseService {

    // CRUD
    Course addCourse(Course course);
    Course getCourseById(Long id);
    List<Course> getAllCourses();
    Course updateCourse(Course course, Long id);
    void deleteCourseById(Long id);

    // FILTERS
    List<Course> getCoursesByDepartment(Long departmentId);
    List<Course> getCoursesByCategory(Long categoryId);
    List<Course> getCoursesByStatus(CourseStatus status);
    List<Course> getCoursesByLevel(CourseLevel level);

    // ADMIN
    long countAllCourses();
    long countCoursesByStatus(CourseStatus status);
}
