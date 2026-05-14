//package com.spring.studentmanagement.runners;
//
//
//import com.spring.studentmanagement.models.Student;
//import com.spring.studentmanagement.repositories.StudentRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Configuration
//public class StudentDataLoader {
//
//    @Bean
//    CommandLineRunner loadSampleData(StudentRepository studentRepository) {
//        return args -> {
//            if (!studentRepository.existsByUsername("Admin")) {
//                Student student = new Student();
//                student.setFirstName("Admin");
//                student.setLastName("User");
//                student.setUsername("Admin");
//                student.setPassword("admin@123");
//                student.setEmail("admin@example.com");
//                student.setAge(25);
//                student.setStudentNumber("STU001");
//                student.setDateAdded(LocalDateTime.now());
//                student.setActive(true);
//                studentRepository.save(student);
//                System.out.println("Admin user created!");
//
//
//            } else {
//                Student student = studentRepository.findByUsername("Admin").get();
//                System.out.println("Username " + student.getUsername() + " already exists!");
//            }
//        };
//    }
//}
