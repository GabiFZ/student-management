package com.spring.studentmanagement.services;


import com.spring.studentmanagement.exceptions.DepartmentAlreadyExistsException;
import com.spring.studentmanagement.exceptions.DepartmentNotFoundException;
import com.spring.studentmanagement.models.Department;
import com.spring.studentmanagement.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;

    Department department;
    Department department2;

    @BeforeEach
    void setup() {
        department = new Department();
        department.setId(1L);
        department.setName("Informatica");

        department2 =  new Department();
        department2.setId(2L);
        department2.setName("Matematica");



    }

    @Test
    void addDepartment_Success() {
        when(departmentRepository.existsByName("Informatica")).thenReturn(false);

        when(departmentRepository.save(department))
                .thenReturn(department);

        Department result = departmentServiceImpl.addDepartment(department);

        assertNotNull(result);
        assertEquals("Informatica", result.getName());
        verify(departmentRepository,times(1)).save(department);

    }

    @Test
    void addDepartment_AlreadyExists() {
        when(departmentRepository.existsByName("Informatica")).thenReturn(true);



        assertThrows(DepartmentAlreadyExistsException.class, () -> departmentServiceImpl.addDepartment(department));

        verify(departmentRepository,never()).save(department);
    }

    @Test
    void getAllDepartments_Empty() {
        when(departmentRepository.findAll()).thenReturn(List.of());
        List<Department> result = departmentServiceImpl.getAllDepartments();
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(departmentRepository,times(1)).findAll();
    }
    @Test
    void getAllDepartments_Success() {
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(department, department2));
        List<Department> result = departmentServiceImpl.getAllDepartments();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Informatica", result.get(0).getName());
        assertEquals("Matematica", result.get(1).getName());
        verify(departmentRepository,times(1)).findAll();
    }

    @Test
    void getDepartmentById_Success() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Department result = departmentServiceImpl.getDepartmentById(1L);
        assertNotNull(result);
        assertEquals("Informatica", result.getName());
        assertEquals(1L, result.getId());
        verify(departmentRepository,times(1)).findById(1L);
    }

    @Test
    void getDepartmentById_NotFound() {
        when(departmentRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(DepartmentNotFoundException.class, () -> departmentServiceImpl.getDepartmentById(999L));
        verify(departmentRepository,times(1)).findById(999L);
    }

    @Test
    void deleteDepartmentById_Success() {
        when(departmentRepository.existsById(1L)).thenReturn(true);
        // doNothing().when(departmentRepository).deleteById(1L);


        departmentServiceImpl.deleteDepartmentById(1L);
        verify(departmentRepository,times(1)).deleteById(1L);
    }
    @Test
    void deleteDepartmentById_NotFound() {
        when(departmentRepository.existsById(999L)).thenReturn(false);

        assertThrows(DepartmentNotFoundException.class, () -> departmentServiceImpl.deleteDepartmentById(999L));
        verify(departmentRepository,times(1)).existsById(999L);
        verify(departmentRepository, never()).deleteById(any());
    }
    @Test
    void updateDepartment_Success() {
        // ARRANGE
        Department updated = new Department();
        updated.setName("Informatica si IT");
        updated.setDescription("Descriere noua!");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));
        when(departmentRepository.existsByName("Informatica si IT"))
                .thenReturn(false);
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        // ACT
        Department result = departmentServiceImpl
                .updateDepartment(updated, 1L);

        // ASSERT
        assertNotNull(result);
        verify(departmentRepository, times(1)).findById(1L);
        verify(departmentRepository, times(1))
                .save(any(Department.class));
    }




    @Test
    void updateDepartment_NotFound() {
        // ARRANGE
        Department updated = new Department();
        updated.setName("Informatica si IT");

        when(departmentRepository.findById(999L))
                .thenReturn(Optional.empty());

        // ACT + ASSERT
        assertThrows(DepartmentNotFoundException.class,
                () -> departmentServiceImpl.updateDepartment(updated, 999L));

        // VERIFY
        verify(departmentRepository, times(1)).findById(999L);
        verify(departmentRepository, never())
                .save(any(Department.class));
    }

    @Test
    void updateDepartment_AlreadyExists() {
        // ARRANGE
        Department updated = new Department();
        updated.setName("Matematica");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));
        when(departmentRepository.existsByName("Matematica"))
                .thenReturn(true);

        // ACT + ASSERT
        assertThrows(DepartmentAlreadyExistsException.class,
                () -> departmentServiceImpl.updateDepartment(updated, 1L));

        // VERIFY
        verify(departmentRepository, never())
                .save(any(Department.class));
    }

    @Test
    void updateDepartment_SameName_Success() {
        // ARRANGE
        Department updated = new Department();
        updated.setName("Informatica");
        updated.setDescription("Descriere noua!");

        when(departmentRepository.findById(1L))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class)))
                .thenReturn(department);

        // ACT
        Department result = departmentServiceImpl
                .updateDepartment(updated, 1L);

        // ASSERT
        assertNotNull(result);
        verify(departmentRepository, times(1)).findById(1L);

        verify(departmentRepository, never())
                .existsByName(any());
        verify(departmentRepository, times(1))
                .save(any(Department.class));
    }

}
