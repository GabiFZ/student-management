package com.spring.studentmanagement.services;

import com.spring.studentmanagement.enums.CourseLevel;
import com.spring.studentmanagement.enums.CourseStatus;
import com.spring.studentmanagement.exceptions.CategoryNotFoundException;
import com.spring.studentmanagement.exceptions.CourseAlreadyExistsException;
import com.spring.studentmanagement.exceptions.CourseNotFoundException;
import com.spring.studentmanagement.exceptions.DepartmentNotFoundException;
import com.spring.studentmanagement.models.Category;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.repositories.CategoryRepository;
import com.spring.studentmanagement.repositories.CourseRepository;
import com.spring.studentmanagement.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CategoryRepository categoryRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Course addCourse(Course course) {
       if (courseRepository.existsByTitle(course.getTitle())) {
           throw new CourseAlreadyExistsException("Course with title: " + course.getTitle() + " already exists");
       }
       return  courseRepository.save(course);
    }

    @Override
    public Course getCourseById(Long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException("Course with id: " + id + " not found"));
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(Course course, Long id) {
        return courseRepository.findById(id).map(existingCourse -> {

            if(!existingCourse.getTitle().equals(course.getTitle()) && courseRepository.existsByTitle(course.getTitle())) {
                throw new CourseAlreadyExistsException("Course with title: " + course.getTitle() + " already exists");
            }
            existingCourse.setTitle(course.getTitle());
            existingCourse.setDescription(course.getDescription());
            existingCourse.setThumbnailUrl(course.getThumbnailUrl());
            existingCourse.setPrice(course.getPrice());
            existingCourse.setDuration(course.getDuration());
            existingCourse.setLevel(course.getLevel());
            existingCourse.setStatus(course.getStatus());
            existingCourse.setActive(course.getActive());
            existingCourse.setDepartment(course.getDepartment());
            existingCourse.setCategory(course.getCategory());
            existingCourse.setProfessor(course.getProfessor());

            return  courseRepository.save(existingCourse);
        }).orElseThrow(() -> new CourseNotFoundException("Course with id: " + id + " not found"));

    }

    @Override
    public void deleteCourseById(Long id) {
        if(!courseRepository.existsById(id)) {
            throw new CourseNotFoundException("Course with id: " + id + " not found");
        }
        courseRepository.deleteById(id);
    }

    @Override
    public List<Course> getCoursesByDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new DepartmentNotFoundException("Department with id: " + departmentId + " not found"));
        return courseRepository.findByDepartment(department);
    }

    @Override
    public List<Course> getCoursesByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category with id: " + categoryId + " not found"));
        return courseRepository.findByCategory(category);
    }

    @Override
    public List<Course> getCoursesByStatus(CourseStatus status) {
        return courseRepository.findByStatus(status);
    }

    @Override
    public List<Course> getCoursesByLevel(CourseLevel level) {
        return courseRepository.findByLevel(level);
    }

    @Override
    public long countAllCourses() {
        return  courseRepository.count();
    }

    @Override
    public long countCoursesByStatus(CourseStatus status) {
        return courseRepository.findByStatus(status).size();
    }
}
