package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.StudentNotFoundException;
import com.spring.studentmanagement.models.Student;

import java.util.List;

public interface StudentService {

    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student updateStudent(Student student, Long id);

    Student getStudentById(Long id) throws StudentNotFoundException;

    void deleteStudentById(Long id);

}
