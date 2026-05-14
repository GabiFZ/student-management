package com.spring.studentmanagement.controllers;


import com.spring.studentmanagement.models.Professor;
import com.spring.studentmanagement.services.ProfessorService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin("http://localhost:5173")
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    private final ProfessorService professorService;

    @GetMapping()
    public ResponseEntity<List<Professor>> findAll() {
        return ResponseEntity.ok(professorService.getAllProfessors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.getProfessorById(id));
    }

    @PostMapping()
    public ResponseEntity<Professor> createProfessor(@RequestBody Professor professor) {
      return ResponseEntity.status(HttpStatus.CREATED).body(professorService.addProfessor(professor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Professor>updateProfessor(@PathVariable Long id, @RequestBody Professor professor) {
        return ResponseEntity.status(HttpStatus.OK).body(professorService.updateProfessor(professor, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfessor(@PathVariable Long id) {
        professorService.deleteProfessorById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<List<Professor>> findByDepartment(@PathVariable Long id) {
        return ResponseEntity.ok(professorService.findByDepartment(id));
    }

    @GetMapping("/active/{isActive}")
    public ResponseEntity<List<Professor>> findByIsActive(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(professorService.findByIsActive(isActive));
    }

    @GetMapping("/count/{isActive}")
    public ResponseEntity<Long>countByIsActive(@PathVariable Boolean isActive) {
        return ResponseEntity.ok(professorService.countByIsActive(isActive));
    }

}
