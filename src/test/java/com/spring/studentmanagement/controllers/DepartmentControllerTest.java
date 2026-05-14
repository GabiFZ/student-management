package com.spring.studentmanagement.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        departmentRepository.deleteAll();
    }

    @Test
    void getAllDepartments_Returns200() throws Exception {

        // ARRANGE
        Department dept = new Department();
        dept.setName("Informatica");
        dept.setDescription("Departamentul de informatica");

        departmentRepository.save(dept);

        // ACT + ASSERT
        mockMvc.perform(get("/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Informatica"));
    }
}