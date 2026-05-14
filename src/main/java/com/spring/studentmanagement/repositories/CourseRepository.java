package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.enums.CourseLevel;
import com.spring.studentmanagement.enums.CourseStatus;
import com.spring.studentmanagement.models.Category;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
   // void getCourseByDepartment(Long departmentId);


   boolean existsByTitle(String title);


   Optional<Course> findByTitle(String title);


   List<Course> findByDepartment(Department department);
   List<Course> findByCategory(Category category);
   List<Course> findByStatus(CourseStatus status);
   List<Course> findByLevel(CourseLevel level);
   List<Course> findByActive(Boolean active);


   List<Course> findByDepartmentAndStatus(Department department, CourseStatus status);
   List<Course> findByCategoryAndStatus(Category category, CourseStatus status);
}
