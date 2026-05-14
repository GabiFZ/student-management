package com.spring.studentmanagement.services;

import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.models.Professor;

import java.util.List;

public interface ProfessorService {

    Professor addProfessor(Professor professor);
    List<Professor> getAllProfessors();
    Professor updateProfessor(Professor professor, Long id);
    void deleteProfessorById(Long id);
    Professor getProfessorById(Long id);

    List<Professor> findByDepartment(Long departmentId);
    List<Professor> findByIsActive(Boolean isActive);
    long countByIsActive(Boolean isActive);


}
