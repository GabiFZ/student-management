package com.spring.studentmanagement.repositories;

import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    Optional<Student> findByUsername(String username);
    Optional<Student> findByEmail(String email);

    List<Student> findByDepartment(Department department);
    List<Student> findByIsActive(Boolean isActive);
    long countByIsActive(Boolean isActive);
    long countByDepartment(Department department);

}
