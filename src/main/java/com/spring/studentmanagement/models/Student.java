package com.spring.studentmanagement.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Student")
@Table(name = "students", schema = "public")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = false, length = 65, nullable = false , name = "firstname")
    private String firstName;

    @Column(unique = false, length = 65, nullable = false, name = "lastname")
    private String lastName;

    @Column(name = "username", length = 45, unique = true, nullable = false)
    private String username;

    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @Column(unique = true, length = 65, nullable = false, name = "email")
    private String email;

    @Column(unique = false, nullable = false, name = "age")
    private Integer age;

    @Column(unique = true, nullable = false, name = "student_number")
    private String studentNumber;

    @Column(nullable = false)
    private Boolean isActive = true;

    @Column(nullable = false)
    private LocalDateTime dateAdded = LocalDateTime.now();

//    @Column(name = "department" , nullable = false)
//    private String department;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();


    // Un student are multe Submissions
    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<Submission> submissions = new ArrayList<>();
}
