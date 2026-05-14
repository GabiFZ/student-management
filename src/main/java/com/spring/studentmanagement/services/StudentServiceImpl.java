package com.spring.studentmanagement.services;

import com.spring.studentmanagement.exceptions.StudentAlreadyExistsException;
import com.spring.studentmanagement.exceptions.StudentNotFoundException;
import com.spring.studentmanagement.models.Student;
import com.spring.studentmanagement.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;


    @Override
    public Student addStudent(Student student) {
       // if (studentRepository.existsByEmail(student.getEmail())) {
           // throw  new StudentAlreadyExistsException(student.getEmail() + " already exists");
        if(studentAlreadyExists(student.getEmail())) {
            throw new StudentAlreadyExistsException(student.getEmail() +  " already exists!");
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Student student, Long id) {

        return studentRepository.findById(id).map(st -> {

            if(!st.getEmail().equals(student.getEmail()) && studentAlreadyExists(student.getEmail())) {
                throw new StudentAlreadyExistsException(student.getEmail() +  " already exists!");
            }
            st.setFirstName(student.getFirstName());
            st.setLastName(student.getLastName());
            st.setUsername(student.getUsername());
            st.setEmail(student.getEmail());
            st.setDepartment(student.getDepartment());
            st.setAge(student.getAge());
            st.setIsActive(student.getIsActive());
            return studentRepository.save(st);
        }).orElseThrow(() -> new StudentNotFoundException("Sorry, this student could not be found!"));
    }

    @Override
    public Student getStudentById(Long id) throws StudentNotFoundException {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Sorry, no student found with the Id :" +  id));


    }


    @Override
    public void deleteStudentById(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException("Sorry, student not found!");


        }
        studentRepository.deleteById(id);

    }

    private boolean studentAlreadyExists(String email) {
        return studentRepository.findByEmail(email).isPresent();
    }
}
