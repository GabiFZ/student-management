package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.Department;

import java.util.List;

public interface DepartmentService {

        Department addDepartment(Department department);
        Department getDepartmentById(Long id);
        Department updateDepartment(Department department, Long id);
        List<Department> getAllDepartments();
        void deleteDepartmentById(Long id);
}
