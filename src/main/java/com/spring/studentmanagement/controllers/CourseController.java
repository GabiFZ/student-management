package com.spring.studentmanagement.controllers;

import com.spring.studentmanagement.enums.CourseLevel;
import com.spring.studentmanagement.enums.CourseStatus;
import com.spring.studentmanagement.models.Course;
import com.spring.studentmanagement.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;


    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourses() {
        return  ResponseEntity.ok(courseService.getAllCourses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }

    @PostMapping()
    public ResponseEntity<Course> addCourse(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.addCourse(course));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @RequestBody Course course) {
        return ResponseEntity.ok(courseService.updateCourse(course, id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<Course>> getCourseByDepartmentId(@PathVariable Long departmentId) {
        return ResponseEntity.ok(courseService.getCoursesByDepartment(departmentId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<Course>> getCourseByCategoryId(@PathVariable Long categoryId) {
        return ResponseEntity.ok(courseService.getCoursesByCategory(categoryId));
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Course>> getCourseByStatus(@PathVariable CourseStatus status) {
        return ResponseEntity.ok(courseService.getCoursesByStatus(status));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Course>> getCourseByLevel(@PathVariable CourseLevel level) {
        return ResponseEntity.ok(courseService.getCoursesByLevel(level));
    }
    @GetMapping("/count")
    public ResponseEntity<Long> getCourseCount() {
        return ResponseEntity.ok(courseService.countAllCourses());
    }
    @GetMapping("/count/{status}")
    public ResponseEntity<Long> getCourseCountByStatus(@PathVariable CourseStatus status) {
        return ResponseEntity.ok(courseService.countCoursesByStatus(status));
    }



}
