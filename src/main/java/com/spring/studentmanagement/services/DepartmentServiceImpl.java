package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.DepartmentAlreadyExistsException;
import com.spring.studentmanagement.exceptions.DepartmentNotFoundException;
import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.repositories.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {


    private final DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) {
        if (departmentRepository.existsByName(department.getName())) {
            throw new DepartmentAlreadyExistsException("Department with name " + department.getName() + " already exists");

        }
        return departmentRepository.save(department);
    }

    @Override
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new DepartmentNotFoundException("Department with id " + id + " not found"));
    }

    @Override
    public Department updateDepartment(Department department, Long id) {
        return departmentRepository.findById(id).map(existingDepartment->{
            if(!existingDepartment.getName().equals(department.getName()) && departmentRepository.existsByName(department.getName())) {
                throw new DepartmentAlreadyExistsException("Department with name " + department.getName() + " already exists");
            }
            existingDepartment.setName(department.getName());
            existingDepartment.setDescription(department.getDescription());
            return departmentRepository.save(existingDepartment);
        })
                .orElseThrow(() -> new DepartmentNotFoundException("Department with id " + id + " not found"));
    }

    @Override
    public List<Department> getAllDepartments() {
        return  departmentRepository.findAll();
    }

    @Override
    public void deleteDepartmentById(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new DepartmentNotFoundException("Department with id " + id + " not found");

        }
        departmentRepository.deleteById(id);

    }
}
