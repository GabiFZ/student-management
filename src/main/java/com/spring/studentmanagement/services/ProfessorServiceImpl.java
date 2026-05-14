package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.DepartmentNotFoundException;
import com.spring.studentmanagement.exceptions.ProfessorAlreadyExistsException;
import com.spring.studentmanagement.exceptions.ProfessorNotFoundException;
import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.models.Professor;
import com.spring.studentmanagement.repositories.DepartmentRepository;
import com.spring.studentmanagement.repositories.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public Professor addProfessor(Professor professor) {
        if (professorRepository.existsByEmail(professor.getEmail())) {
            throw new ProfessorAlreadyExistsException("Professor with email: " +  professor.getEmail() + " already exists!");
        }
        if (professorRepository.existsByUsername(professor.getUsername())) {
            throw new ProfessorAlreadyExistsException(
                    "Professor with username: " + professor.getUsername() + " already exists!");
        }
        return professorRepository.save(professor);
    }

    @Override
    public List<Professor> getAllProfessors() {
        return professorRepository.findAll();
    }

    @Override
    public Professor updateProfessor(Professor professor, Long id) {
        return professorRepository.findById(id).map(existingProfessor -> {
            if (!existingProfessor.getEmail().equals(professor.getEmail())
                    && professorRepository.existsByEmail(professor.getEmail())) {
                throw new ProfessorAlreadyExistsException(
                        "Professor with email: " + professor.getEmail() + " already exists!");
            }
            if (!existingProfessor.getUsername().equals(professor.getUsername())
                    && professorRepository.existsByUsername(professor.getUsername())) {
                throw new ProfessorAlreadyExistsException(
                        "Professor with username: " + professor.getUsername() + " already exists!");
            }
            existingProfessor.setFirstName(professor.getFirstName());
            existingProfessor.setLastName(professor.getLastName());
            existingProfessor.setEmail(professor.getEmail());
            existingProfessor.setUsername(professor.getUsername());
            existingProfessor.setPhone(professor.getPhone());
            existingProfessor.setBio(professor.getBio());
            existingProfessor.setAvatarUrl(professor.getAvatarUrl());
            existingProfessor.setIsActive(professor.getIsActive());
            existingProfessor.setDepartment(professor.getDepartment());
            return professorRepository.save(existingProfessor);
        }).orElseThrow(() -> new ProfessorNotFoundException(
                "Professor with id: " + id + " not found!"));
    }

    @Override
    public void deleteProfessorById(Long id) {
            if (!professorRepository.existsById(id)) {
                throw new ProfessorNotFoundException("Professor with id: " + id + " not found!");
            }
            professorRepository.deleteById(id);
    }

    @Override
    public Professor getProfessorById(Long id) {
        return professorRepository.findById(id).orElseThrow(() -> new ProfessorNotFoundException("Professor with id: " + id + " not found!"));
    }

    @Override
    public List<Professor> findByDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new DepartmentNotFoundException("Department with id: " + departmentId + " not found!"));
        return professorRepository.findByDepartment(department);
    }

    @Override
    public List<Professor> findByIsActive(Boolean isActive) {
        return professorRepository.findByIsActive(isActive);
    }

    @Override
    public long countByIsActive(Boolean isActive) {
        return professorRepository.countByIsActive(isActive);
    }
}
