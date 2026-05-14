package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.models.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    Optional<Professor> findByEmail(String email);
    Optional<Professor> findByUsername(String username);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    List<Professor> findByDepartment(Department department);
    List<Professor> findByIsActive(Boolean isActive);
    long countByIsActive(Boolean isActive);


}
